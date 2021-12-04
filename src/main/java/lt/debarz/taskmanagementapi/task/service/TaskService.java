package lt.debarz.taskmanagementapi.task.service;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.exception.EntityNotFoundException;
import lt.debarz.taskmanagementapi.task.entity.Status;
import lt.debarz.taskmanagementapi.task.entity.Task;
import lt.debarz.taskmanagementapi.task.model.TaskModel;
import lt.debarz.taskmanagementapi.task.model.TaskModelAssembler;
import lt.debarz.taskmanagementapi.task.repository.TaskRepository;
import lt.debarz.taskmanagementapi.user.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Todo: findTaskByName
// Todo: findAllByDescription
// Todo: findAllByTaskGroup
@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * get tasks by user
     */
    public List<TaskModel> getTasksByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return taskRepository.getAllByAssignee(user.getId()).stream()
                .map(task -> new TaskModelAssembler().toModel(task))
                .collect(Collectors.toList());
    }
    /**
     * Get sub-tasks by parent task id
     */
        public List<TaskModel> findAllSubTasksById(long taskId, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return taskRepository.findAllSubTasksByTaskId(taskId, page)
                .stream()
                .map(task -> new TaskModelAssembler().toModel(task))
                .collect(Collectors.toList());
    }
    /**
     * Get all tasks by task status
     */
    public List<TaskModel> findAllTasksByStatus(Status status){
        return taskRepository.findAllByStatus(status).stream()
                .map(task -> new TaskModelAssembler().toModel(task))
                .collect(Collectors.toList());
    }
    /**
     * Get task completion time HH:mm:ss
     */
    public TaskModel getTimeSpentOnTask(long id){
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        Task task = taskRepository.getTimeSpentOnTask(id);
        if(!task.getStatus().name().equals("Done")){
            throw new EntityNotFoundException(id);
        }
        return new TaskModelAssembler().toModel(task);
    }

    /**
     * Get tasks with pages
     */
    public List<TaskModel> getTaskModels(){
        PageRequest page = PageRequest.of(0, 12);
        List<Task> tasks = taskRepository.findAllTasksWhereTaskIDIsNullWithPage(page).getContent();
                return tasks.stream()
                        .map(task -> new TaskModelAssembler().toModel(task))
                        .collect(Collectors.toList());
    }

}
