package com.d1sxrder3d.java_spring_prfct.repository;

import com.d1sxrder3d.java_spring_prfct.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}