package lt.debarz.taskmanagementapi.user.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.debarz.taskmanagementapi.user.model.View;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "role_name")
    @JsonView(value = {View.UserView.External.class, View.UserView.Internal.Post.class,
            View.UserView.Internal.PUT.class, View.UserView.Internal.Patch.class})
    private String roleName;

    @Override
    public String getAuthority() {
        return "ROLE_"+roleName;
    }
}
