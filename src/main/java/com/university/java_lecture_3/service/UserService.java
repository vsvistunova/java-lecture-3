package com.university.java_lecture_3.service;

import com.university.java_lecture_3.mapper.EventMapper;
import com.university.java_lecture_3.mapper.GroupMapper;
import com.university.java_lecture_3.mapper.UserMapper;
import com.university.java_lecture_3.model.dto.request.CreateUserRequest;
import com.university.java_lecture_3.model.dto.request.UpdateUserRequest;
import com.university.java_lecture_3.model.dto.response.UserDetailedResponse;
import com.university.java_lecture_3.model.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.model.entity.Group;
import com.university.java_lecture_3.model.entity.User;
import com.university.java_lecture_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;  //List<User> users = TestDataUtil.createTestUsers();
    private final GroupMapper groupMapper;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;
    private final GroupService groupService;

    public List<UserSummaryResponse> getAllUsers(int pageSize, int pageNumber) {
        Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize,  Sort.by("id").descending());

        return userRepository.findAllUsersSummaries()
                .stream()
                .map(userMapper::toSummaryResponse)
                .toList();
    }

    public List<UserSummaryResponse> getAllEvents() {
        return userRepository.findAll()
            .stream()
             .map(userMapper::toSummaryResponse)
             .toList();
    }

    public UserDetailedResponse getUserById(Long userId){
        return userRepository.findById(userId)
                .map(userMapper::toDetailedResponse)
                .orElseThrow(
                () -> new RuntimeException("Пользователь с id %s не найден!".formatted(userId)));
    }

    public UserDetailedResponse createUser(CreateUserRequest request) {
        Long groupId = request.getGroupId();
        String role = request.getRole();

        if (role.equals("student") && groupId == null) {
            throw new RuntimeException("Student must have a group");
        } else if (!role.equals("student") && groupId != null) {
            throw new RuntimeException("%s must not have a group".formatted(role));
        }

        Group group = null;
        if (groupId != null) {
            group = groupService.getGroupById(groupId);
        }

        User entityToSave = userMapper.toEntity(request,group);
        User savedEntity = userRepository.save(entityToSave);
        return userMapper.toDetailedResponse(savedEntity);
    }

    public UserDetailedResponse updateUser(Long userId, UpdateUserRequest request) {
        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id %s not found!".formatted(userId)));
        if (request.getName() != null) userToUpdate.setName(request.getName());

        if (request.getAge()!= null) userToUpdate.setAge(request.getAge());

        if (request.getGroupId() != null){
            Group group = groupService.getGroupById(request.getGroupId());
            userToUpdate.setGroup(group);
        }

        User updatedEntity = userRepository.save(userToUpdate);
        return userMapper.toDetailedResponse(updatedEntity);
    }

    public List<UserSummaryResponse> getUsersByAge(Integer minAge, Integer maxAge) {
        return userRepository.findUsersByAgeRange(minAge,maxAge)
                .stream()
                .map(userMapper::toSummaryResponse)
                .toList();
    }
    public List<UserSummaryResponse> getUsersByEmail(String email) {
        return userRepository.findUsersByEmailDomain(email)
                .stream()
                .map(userMapper::toSummaryResponse)
                .toList();
    }

    public List<UserSummaryResponse> getUsersByGroupName(String groupName) {
        return userRepository.findUsersByGroupName(groupName)
                .stream()
                .map(userMapper::toSummaryResponse)
                .toList();
    }







//    public User getUserById(Long userId) {
//        Optional<User> first =  users.stream()
//                .filter(user -> user.getId().equals(userId))
//                .findFirst();
//
//        return  first
//                .orElseThrow(() -> new RuntimeException("Пользователь с id " + userId + " не найден"));
//    }
//
//    public List<User> getUsersByAge( Integer minAge,  Integer maxAge) {
//        List<User> result = users.stream()
//                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
//                .collect(Collectors.toList());
//
//        if (result.isEmpty()) {
//            throw new RuntimeException("пользователи с возрастом от " + minAge + " до " + maxAge + " не найдены");
//        }
//        return result;
//    }
//
//    public User createUser( User newUser) {
//        users.add(newUser);
//        return newUser;
//    }
//
//    public User updateUser( Long userId,  User updatedUser) {
//        User currentUser = users.stream()
//                .filter(u -> u.getId().equals(userId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Пользователь с id " + userId + " не найден"));
//
//        currentUser.setAge(updatedUser.getAge());
//        currentUser.setName(updatedUser.getName());
//        currentUser.setEmail(updatedUser.getEmail());
//        return currentUser;
//    }
//
//    public boolean deleteUser( Long userId) {
//        User userToDelete = users.stream()
//                .filter(u -> u.getId().equals(userId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Пользователь с id " + userId + " не найден"));
//        users.remove(userToDelete);
//        return true;
//    }


}


