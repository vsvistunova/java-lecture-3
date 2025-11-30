package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.model.Group;
import com.university.java_lecture_3.projection.GroupProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("""
            SELECT g.id AS id,
                    g.name AS name,
                    g.curatorName AS curatorName,
                    count(u.id) AS usersCount
            FROM Group g
            LEFT JOIN g.users u
            GROUP BY g.id
            """)
    List<GroupProjection> findAllWithUserCount(Pageable pageable);

}
