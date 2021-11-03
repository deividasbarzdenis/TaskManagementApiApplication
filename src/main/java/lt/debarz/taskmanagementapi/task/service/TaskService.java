package lt.debarz.taskmanagementapi.task.service;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.exception.EntityNotFoundException;
import lt.debarz.taskmanagementapi.task.mapper.TaskMapperImpl;
import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.repository.TaskRepository;
import lt.debarz.taskmanagementapi.user.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapperImpl taskMapper;


    /**
     * Get all tasks from repo and covert to dto:
     */
    public Set<TaskDto> getAllTasks() {
        return taskRepository
                .findAllTasksWhereTaskIDIsNull()
                .stream()
                .map(taskMapper::convertEntityToDto)
                .collect(Collectors.toSet());
    }

    /**
     * save task to db
     */
    public TaskDto saveTask(TaskDto taskDto) throws ParseException {
        Task task = taskMapper.convertDtoToEntity(taskDto);
        Task saveTask = taskRepository.save(task);
        taskDto.setId(saveTask.getId());
        return taskDto;
    }

    /**
     * get task form repo by id
     */
    public TaskDto getTaskById(long id) {
        Task task = getById(id);
        return taskMapper.convertEntityToDto(task);
    }

    /**
     * delete task form repo by id
     */
    public void deleteTask(long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
    /**
     * Update task
     */
    public TaskDto updateTask(TaskDto taskDto) {
        Long id = taskDto.getId();
        if (id == null) {
            throw new EntityNotFoundException(id);
        }
        Task task = getById(id);
        BeanUtils.copyProperties(taskDto, task);
        taskRepository.save(task);
        return taskDto;
    }
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
    // Todo: get done tasks with time spent
    // Todo: findTaskByName
    // Todo: findAllByDescription
    //Todo: findAllByTaskGroup


    //private getById task method
    private Task getById(long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
    // store task in Map<>:

}
