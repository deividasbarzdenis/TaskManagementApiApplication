package lt.debarz.taskmanagementapi.user.repository;


import lt.debarz.taskmanagementapi.user.model.Role;
import lt.debarz.taskmanagementapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

    @RestResource
    List<Role> findAll();
}
