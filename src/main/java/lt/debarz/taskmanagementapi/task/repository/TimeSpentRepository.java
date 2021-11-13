package lt.debarz.taskmanagementapi.task.repository;

import lt.debarz.taskmanagementapi.task.model.TimeSpent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TimeSpentRepository extends CrudRepository<TimeSpent, Long> {
}
