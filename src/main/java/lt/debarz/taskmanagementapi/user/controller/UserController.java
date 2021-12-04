package lt.debarz.taskmanagementapi.user.controller;


import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.controller.TaskController;
import lt.debarz.taskmanagementapi.task.model.TaskModel;
import lt.debarz.taskmanagementapi.user.dto.UserDto;
import lt.debarz.taskmanagementapi.user.entity.User;
import lt.debarz.taskmanagementapi.user.model.UserModel;
import lt.debarz.taskmanagementapi.user.model.UserModelAssembler;
import lt.debarz.taskmanagementapi.user.service.UserService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@AllArgsConstructor
@RepositoryRestController
@BasePathAwareController
public class UserController {

    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<UserDto> user() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        UserDto userDto = new UserDto(user);
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<EntityModel<UserModel>> user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        UserModel userModel = new UserModelAssembler().toModel(user);
        EntityModel<UserModel> model = EntityModel.of(userModel, linkTo(methodOn(UserController.class)
                .user())
                .withRel("/login"));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

//    @GetMapping("/users/{id}")
//    public UserDto getUser(@PathVariable long id) {
//        return userService.getUserById(id);
//    }
//
//    @GetMapping("/users")
//    public List<UserDto> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(userService.createUser(userDto));
//    }
//
//    @PostMapping("/users/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
//        return userService.updateUser(userDto);
//    }
//
//    @DeleteMapping("/users/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteUser(@PathVariable long id){
//        userService.deleteUser(id);
//    }
}
