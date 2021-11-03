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

    Task findTaskByName(String name);

    List<Task> findAllByStatus(Status status);

    List<Task> findAllByDescription(String description);

    List<Task> findAllByTaskGroup(String group);

    @Query("SELECT '*' FROM Task u WHERE u.assignee.id = :assigneeId")
    List<Task> getAllByAssignee(@Param("assigneeId") long assigneeId);

    // Todo: get "Done" task time spent dto have to have one more attribute
    // Todo: get all "Done" tasks with time spent on task

}
