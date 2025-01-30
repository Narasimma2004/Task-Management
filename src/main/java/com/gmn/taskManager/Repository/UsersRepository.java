package com.gmn.taskManager.Repository;

import com.gmn.taskManager.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByName(String name);


    Users findByEMail(String s);

}
