package lt.debarz.taskmanagementapi.user.controller;


import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.user.dto.UserDto;
import lt.debarz.taskmanagementapi.user.entity.User;
import lt.debarz.taskmanagementapi.user.service.UserService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RepositoryRestController
public class UserController {

    private UserService userService;

    @PostMapping("/api-data/login")
    public UserDto user(@AuthenticationPrincipal User user) {
        return new UserDto(user);
    }

    @GetMapping("/api-data/users/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/api-data/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/api-data/signup")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }

    @PostMapping("/api-data/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/api-data/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
}
