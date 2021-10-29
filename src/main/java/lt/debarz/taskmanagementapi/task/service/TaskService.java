package lt.debarz.taskmanagementapi.task.service;

import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.mapper.TaskMapperImpl;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapperImpl taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapperImpl taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    /**
     * Get all tasks:
     */
    public List<Task> getAllTasks() {
        return taskRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
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

}
