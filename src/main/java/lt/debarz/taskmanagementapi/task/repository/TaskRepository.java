package lt.debarz.taskmanagementapi.task.repository;

import lt.debarz.taskmanagementapi.task.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT '*' FROM Task u WHERE u.Id = task_id")
    List<Task> findAllSubTasks(@Param("task") Task task, Pageable pageable);
}
