package lt.debarz.taskmanagementapi.task.repository;

import lt.debarz.taskmanagementapi.task.entity.TimeSpent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface TimeSpentRepository extends JpaRepository<TimeSpent, Long> {
}
