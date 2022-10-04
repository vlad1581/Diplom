package com.example.toDo.repo;

import com.example.toDo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Long> {
     User findByUsername(String username);
}
