package lt.debarz.taskmanagementapi.task.mapper;

import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.model.TimeSpent;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.stream.Collectors;


@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto convertEntityToDto(Task task) {
        if (task == null) {
            return null;
        }
        return TaskDto.builder()
                .Id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .taskGroup(task.getTaskGroup())
                .status(task.getStatus().name())
                .startTime(task.getTimeSpent().getStartTime())
                .endTime(task.getTimeSpent().getEndTime())
                .subTasks(task.getSubTasks().stream()
                        .map(this::getTaskDto)
                        .collect(Collectors.toSet()))
                .build();

    }

    @Override
    public Task convertDtoToEntity(TaskDto taskDto) throws ParseException {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setTaskGroup(taskDto.getTaskGroup());
        task.setStatus(Status.valueOf(taskDto.getStatus()));
        task.setTimeSpent(addSpentTime(taskDto));
        task.setSubTasks(taskDto.getSubTasks().stream()
                .map(dto -> {
            try {
                return getTask(task, dto);
            } catch (ParseException e) {
                e.printStackTrace();
            }
                    return null;
                }).collect(Collectors.toSet()));
        return task;
    }

    @Override
    public Task convertPartOfDtoToEntity(TaskDto taskDto, Task task) throws ParseException {
        if(taskDto.getName() != null){
            task.setName(taskDto.getName());
        }
        if(taskDto.getDescription() != null){
            task.setDescription(taskDto.getDescription());
        }
        if(taskDto.getTaskGroup() != null){
            task.setTaskGroup(taskDto.getTaskGroup());
        }
        if(taskDto.getStatus() != null){
            task.setStatus(Status.valueOf(taskDto.getStatus()));
        }
        if(taskDto.getStatus() != null && taskDto.getStatus().equals("Done")){
            task.setTimeSpent(addSpentTime(taskDto));
        }
        return task;
    }


    private TimeSpent addSpentTime(TaskDto taskDto) throws ParseException {
        TimeSpent timeSpent = new TimeSpent();
        timeSpent.setStartTime();
        timeSpent.setEndTime(taskDto.getStatus());
        return timeSpent;
    }

    public Task getTask(Task task, TaskDto taskDto) throws ParseException {
        Task subTask = new Task();
        subTask.setName(taskDto.getName());
        subTask.setDescription(taskDto.getDescription());
        subTask.setTaskGroup(taskDto.getTaskGroup());
        subTask.setStatus(Status.valueOf(taskDto.getStatus()));
        subTask.setTimeSpent(addSpentTime(taskDto));
        task.addTask(subTask);
        return subTask;
    }

    public TaskDto getTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setTaskGroup(task.getTaskGroup());
        taskDto.setStatus(task.getStatus().name());
        taskDto.setStartTime(task.getTimeSpent().getStartTime());
        taskDto.setEndTime(task.getTimeSpent().getEndTime());
        return taskDto;
    }

}
