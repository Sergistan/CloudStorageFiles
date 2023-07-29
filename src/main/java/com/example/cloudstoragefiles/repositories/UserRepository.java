package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findUserByLogin(String login);

}
