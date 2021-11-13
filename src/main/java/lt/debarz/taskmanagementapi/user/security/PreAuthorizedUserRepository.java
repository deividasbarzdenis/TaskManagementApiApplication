package lt.debarz.taskmanagementapi.user.security;

import lt.debarz.taskmanagementapi.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface PreAuthorizedUserRepository extends CrudRepository<User, Long> {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll(Iterable<? extends User> users);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll();
}
