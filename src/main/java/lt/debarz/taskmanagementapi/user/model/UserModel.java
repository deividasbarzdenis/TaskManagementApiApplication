package lt.debarz.taskmanagementapi.user.model;

import lombok.Getter;
import lt.debarz.taskmanagementapi.task.entity.Task;
import lt.debarz.taskmanagementapi.user.entity.Role;
import lt.debarz.taskmanagementapi.user.entity.User;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserModel extends RepresentationModel<UserModel> {
    private final String username;
    private  String password;
    private final String lastname;
    private final String name;
    private final String email;
    private final String phone;
    private final Set<String> roles;
    private final List<Task> tasks;

    public UserModel(User user){
        this.username=user.getUsername();
        this.name=user.getName();
        this.lastname =user.getLastname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.roles=user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
        this.tasks = user.getTasks().stream()
                .map(Task::getTask)
                .collect(Collectors.toList());
    }
}
