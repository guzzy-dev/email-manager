package com.example.emailmanager.EmailManager.Contoller;

import com.example.emailmanager.EmailManager.Service.UserService;
import com.example.emailmanager.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RolesAllowed({"ROLE_ADMIN"})
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping(value = "/")
    ResponseEntity createUser(@RequestBody User user){
        Map<String, String> response = new HashMap<>();
        try {
            userService.save(user);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("ErrorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "/{username}")
    ResponseEntity findUserByUsername(@PathVariable String username){
        Map<String, String> response = new HashMap<>();
        try {
            User user = userService.getByUsername(username);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("ErrorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping(value = "/{username}/enable")
    ResponseEntity enableUser(@PathVariable String username){
        Map<String, String> response = new HashMap<>();
        try {
            User user = userService.getByUsername(username);
            user.setEnable(true);
            userService.save(user);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            e.printStackTrace();
            response.put("ErrorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping(value = "/{username}/disable")
    ResponseEntity disableUser(@PathVariable String username){
        Map<String, String> response = new HashMap<>();
        try {
            User user = userService.getByUsername(username);
            user.setEnable(false);
            userService.save(user);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            e.printStackTrace();
            response.put("ErrorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
