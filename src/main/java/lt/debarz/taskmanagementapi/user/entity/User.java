package lt.debarz.taskmanagementapi.user.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import lt.debarz.taskmanagementapi.task.entity.Task;
import lt.debarz.taskmanagementapi.user.model.View;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@RestResource(rel = "users", path = "users")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(min = 3, max = 200)
    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.Post.class,
            View.UserView.Internal.PUT.class, View.UserView.Internal.Patch.class})
    private String username;

    @Size(min=3, max=254)
    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.Post.class,
            View.UserView.Internal.PUT.class, View.UserView.Internal.Patch.class})
    private String lastname;

    @Size(min=3, max=254)
    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.Post.class,
            View.UserView.Internal.PUT.class, View.UserView.Internal.Patch.class})
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Size(min=3, max=254)
    @JsonView(value = {View.UserView.Internal.Post.class, View.UserView.Internal.PUT.class,
            View.UserView.Internal.Patch.class})
    private String password;

    @Size(min=3, max=254)
    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.Post.class,
            View.UserView.Internal.PUT.class, View.UserView.Internal.Patch.class})
    private String email;

    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.Post.class,
            View.UserView.Internal.PUT.class, View.UserView.Internal.Patch.class})
    private String phone;

    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.class})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignee")
    private List<Task> tasks = new ArrayList<>();

    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.Post.class,
            View.UserView.Internal.PUT.class, View.UserView.Internal.Patch.class})
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="User_Roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
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
