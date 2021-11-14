package lt.debarz.taskmanagementapi.task.service;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.exception.EntityNotFoundException;
import lt.debarz.taskmanagementapi.task.mapper.TaskMapperImpl;
import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.repository.TaskRepository;
import lt.debarz.taskmanagementapi.user.model.User;
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
    private final TaskMapperImpl taskMapper;

    /**
     * get tasks by user
     */
    public List<TaskDto> getTasksByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return taskRepository.getAllByAssignee(user.getId()).stream()
                .map(taskMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }
    /**
     * Get sub-tasks by parent task id
     */
        public List<TaskDto> findAllSubTasksById(long taskId, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return taskRepository.findAllSubTasksByTaskId(taskId, page)
                .stream()
                .map(taskMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }
    /**
     * Get all tasks by task status
     */
    public List<TaskDto> findAllTasksByStatus(Status status){
        return taskRepository.findAllByStatus(status).stream()
                .map(taskMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }
    /**
     * Get task completion time HH:mm:ss
     */
    public TaskDto getTimeSpentOnTask(long id){
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        Task task = taskRepository.getTimeSpentOnTask(id);
        if(!task.getStatus().name().equals("Done")){
            throw new EntityNotFoundException(id);
        }
        return taskMapper.convertEntityToDto(task);
    }

    //private getById task method
    private Task getById(long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

}
