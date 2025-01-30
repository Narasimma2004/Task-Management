package com.gmn.taskManager.Service;

import com.gmn.taskManager.DTO.LoginDTO;
import com.gmn.taskManager.Entity.Users;
import com.gmn.taskManager.Repository.UsersRepository;
import com.gmn.taskManager.Response.JWTResponse;
import com.gmn.taskManager.Response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);


    public ResponseEntity<LoginResponse> addUser(Users users) {
        try{
            Users user = usersRepository.findByName(users.getName());
            if(user==null)
            {
                if(!users.getName().isEmpty())
                {
                    if(users.geteMail().endsWith("@gmail.com"))
                    {
                        if(users.getPassword().length()>=6)
                        {
                            users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
                            usersRepository.save(users);
                            int userId = usersRepository.findByName(users.getName()).getUserId();
                            String tableName="user_"+users.getName().replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase()+userId;

                            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName +
                                    "(task_id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                                    "task_name VARCHAR(255), " +
                                    "task_status VARCHAR(255), " +
                                    "startDate DATE, " +
                                    "endDate DATE)";

                            jdbcTemplate.execute(createTableQuery);
                            return ResponseEntity.ok(new LoginResponse(true,"Sign-up Successfully",users));
                        }
                        return ResponseEntity.ok(new LoginResponse(false,"Password must be 6 Character and Above",null));

                    }
                    return ResponseEntity.ok(new LoginResponse(false,"Enter Valid Email Address",null));
                }
                return ResponseEntity.ok(new LoginResponse(false,"Username is Empty",null));
            }
            return ResponseEntity.ok(new LoginResponse(false,"User Already Signed in",null));
        }
        catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse(false, "Unauthorized", null));
        }

    }


    public ResponseEntity<LoginResponse> login(LoginDTO loginDTO) {
        Users users = usersRepository.findByEMail(loginDTO.geteMail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getName(),loginDTO.getPassword()));


        if(authentication.isAuthenticated()) {
            String accessToken = jwtService.generateToken(users.getName());
            String refreshToken = jwtService.generateRefreshToken(users.getName());

            return ResponseEntity.ok(new LoginResponse(true, "Login Successfully", new JWTResponse(accessToken, refreshToken)));
        }
        return ResponseEntity.ok(new LoginResponse(false,"UnAuthorized",null));
    }
}
