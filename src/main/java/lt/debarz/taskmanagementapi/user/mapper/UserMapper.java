package lt.debarz.taskmanagementapi.user.mapper;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.mapper.TaskMapperImpl;
import lt.debarz.taskmanagementapi.task.entity.Task;
import lt.debarz.taskmanagementapi.user.dto.UserDto;
import lt.debarz.taskmanagementapi.user.entity.Role;
import lt.debarz.taskmanagementapi.user.entity.User;
import lt.debarz.taskmanagementapi.user.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final TaskMapperImpl taskMapperImpl;


    public UserDto convertUserToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setRoles(user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet()));
        userDto.setTasks(user.getTasks().stream()
                .map(taskMapperImpl::getTaskDto)
                .collect(Collectors.toList()));
        return userDto;
    }

    public User convertUserDtoToUserEntity(UserDto userDTO) {
        User user = new User();
        Task task = new Task();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        Role role = roleRepository.getById(2L);
        user.addRole(role);
        user.setTasks(userDTO.getTasks().stream()
                .map(dto -> {
                    try {
                        return taskMapperImpl.getTask(task, dto);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList()));
        return user;
    }


}
