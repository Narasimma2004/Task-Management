package com.gmn.taskManager.Controller;

import com.gmn.taskManager.DTO.LoginDTO;
import com.gmn.taskManager.Entity.Users;
import com.gmn.taskManager.Response.LoginResponse;
import com.gmn.taskManager.Service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/unsecure")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> addUser(@RequestBody Users users)
    {
        return usersService.addUser(users);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO)
    {
        return usersService.login(loginDTO);
    }
}
