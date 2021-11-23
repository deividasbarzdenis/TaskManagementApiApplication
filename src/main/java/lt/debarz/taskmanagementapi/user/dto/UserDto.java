package lt.debarz.taskmanagementapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.user.entity.Role;
import lt.debarz.taskmanagementapi.user.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;

    @NotBlank
    @Size(min=3, max=20)
    private String username;

    @NotBlank
    @Size(min=3, max=20)
    private String password;

    @Size(min=3, max=20)
    private String lastname;

    @Size(min=3, max=20)
    private String name;

    private String email;

    private String phone;

    private Set<String> roles = new HashSet<>();

    private List<TaskDto> tasks = new ArrayList<>();

    //response after successful login
    public UserDto(User user) {
        this.id = user.getId();
        this.username=user.getUsername();
        this.name=user.getName();
        this.lastname =user.getLastname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.roles=user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }
}
