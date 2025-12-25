package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.model.entity.User;
import com.university.java_lecture_3.model.projection.UserSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(
            nativeQuery = true,
            value = """
                SELECT *
                FROM users
                WHERE age BETWEEN :minAge AND :maxAge
                """
    )
    List<User> findUsersByAgeRange(Integer minAge, Integer maxAge);


    @Query(
            nativeQuery = true,
            value = "SELECT * FROM users WHERE email LIKE '%' || :emailDomain")
    List<User> findUsersByEmailDomain(String emailDomain);


@Query(nativeQuery = true,value = """
            SELECT u.* FROM users u 
            JOIN groups g ON u.group_id = g.id
            WHERE g.name == :groupName
        """)
List<User> findUsersByGroupName(String groupName);



@Query(
        nativeQuery = true, value = """
            SELECT u.id,
                u."name",
                u.age,
                u.email,
                u."role",
                g."name" AS groupName,
                count(er.id) AS eventsCount
            FROM users u 
            LEFT JOIN "groups" g 
                ON g.id = u.group_id
            LEFT JOIN event_registrations er 
                ON er.user_id = u.id
            GROUP BY u.id,g."name"
""")
List<UserSummaryProjection> findAllUsersSummaries();

}