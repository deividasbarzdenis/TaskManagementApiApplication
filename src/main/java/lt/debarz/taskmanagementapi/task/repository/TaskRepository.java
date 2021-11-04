package lt.debarz.taskmanagementapi.task.repository;

import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT u FROM Task u WHERE u.task.Id = :id")
    List<Task> findAllSubTasksByTaskId(@Param("id") long taskId, Pageable pageable);

    @Query("SELECT u FROM Task u WHERE u.task.Id is null")
    Set<Task> findAllTasksWhereTaskIDIsNull();

    @Query("SELECT u " +
            "FROM Task " +
            "u JOIN TimeSpent s " +
            "ON u.Id = :id " +
            "AND s.task.Id = :id " +
            "where u.status = 'Done'")
    Task getTimeSpentOnTask(@Param("id") long taskI);

    Task findTaskByName(String name);

    List<Task> findAllByStatus(Status status);

    List<Task> findAllByDescription(String description);

    List<Task> findAllByTaskGroup(String group);

    @Query("SELECT '*' FROM Task u WHERE u.assignee.id = :assigneeId")
    List<Task> getAllByAssignee(@Param("assigneeId") long assigneeId);

}
