package lt.debarz.taskmanagementapi.user.repository;


import lt.debarz.taskmanagementapi.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
