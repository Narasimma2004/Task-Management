package com.gmn.taskManager.Repository;

import com.gmn.taskManager.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByName(String name);
}
