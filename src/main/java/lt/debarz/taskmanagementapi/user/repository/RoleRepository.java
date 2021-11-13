package lt.debarz.taskmanagementapi.user.repository;


import lt.debarz.taskmanagementapi.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, Long> {

}
