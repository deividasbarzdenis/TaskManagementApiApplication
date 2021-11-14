package lt.debarz.taskmanagementapi.user.security;

import lt.debarz.taskmanagementapi.task.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
public interface PreAuthorizedTaskRepository extends CrudRepository<Task, Long> {

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    void delete(Task task);

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    void deleteAll(Iterable<? extends Task> tasks);

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    void deleteAll();

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    <S extends Task> S save(S entity);

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    <S extends Task> Iterable<S> saveAll(Iterable<S> entities);
}
