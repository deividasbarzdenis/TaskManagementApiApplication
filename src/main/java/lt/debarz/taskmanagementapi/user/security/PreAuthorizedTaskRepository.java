package lt.debarz.taskmanagementapi.user.security;

import lt.debarz.taskmanagementapi.task.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
public interface PreAuthorizedTaskRepository extends CrudRepository<Task, Long> {

}
