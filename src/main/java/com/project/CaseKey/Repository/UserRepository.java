package com.project.CaseKey.Repository;

import com.project.CaseKey.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String userId);
}
