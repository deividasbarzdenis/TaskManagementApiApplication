package lt.debarz.taskmanagementapi.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lt.debarz.taskmanagementapi.task.model.Task;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(min = 3, max = 200)
    private String username;

    @Size(min=3, max=254)
    private String lastname;

    @Size(min=3, max=254)
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Size(min=3, max=254)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(min=3, max=254)
    private String email;

    @RestResource(exported = false)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignee")
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="User_Roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Role> roles= new HashSet<>();

    public void addRole(Role role){
        roles.add(role);
    }

    public User addTask(Task task){
        task.setAssignee(this);
        this.tasks.add(task);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
