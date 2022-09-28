package com.example.toDo.repo;

import com.example.toDo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByLogin(String login);
}
