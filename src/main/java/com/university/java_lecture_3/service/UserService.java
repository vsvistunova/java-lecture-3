package com.university.java_lecture_3.service;

import com.university.java_lecture_3.dto.request.UserRequest;
import com.university.java_lecture_3.dto.response.UserDetailedResponse;
import com.university.java_lecture_3.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.exception.NotFoundException;
import com.university.java_lecture_3.mapper.UserMapper;
import com.university.java_lecture_3.model.Group;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.projection.UserSummaryProjection;
import com.university.java_lecture_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GroupService groupService;

    public List<UserSummaryResponse> getAllByAgeBetween(int minAge, int maxAge, Pageable pageable) {
        List<UserSummaryProjection> users = userRepository.findAllByAgeBetween(minAge, maxAge, pageable);

        return userMapper.toSummaryResponseListFromProjection(users);
    }

    public User getById(Long id) {
        return userRepository.findWithDetailsById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    public UserDetailedResponse getDetailedById(Long id) {
        return userMapper.toDetailedResponse(getById(id));
    }

    public List<UserSummaryResponse> getAll(Pageable pageable) {
        List<UserSummaryProjection> users = userRepository.findAllUserSummaries(pageable);

        return userMapper.toSummaryResponseListFromProjection(users);
    }

    public UserDetailedResponse save(UserRequest userRequest) {
        Group group = groupService.getById(userRequest.groupId());

        User user = userMapper.toEntity(userRequest);
        user.setGroup(group);

        User savedUser = userRepository.save(user);

        return userMapper.toDetailedResponse(savedUser);
    }

    public UserDetailedResponse update(Long id, UserRequest userDetails) {
        User user = getById(id);
        Group group = groupService.getById(userDetails.groupId());

        user.setName(userDetails.name());
        user.setAge(userDetails.age());
        user.setEmail(userDetails.email());
        user.setRole(userDetails.role());
        user.setGroup(group);

        User updatedUser = userRepository.save(user);

        return userMapper.toDetailedResponse(updatedUser);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));

        userRepository.delete(user);
    }

}
