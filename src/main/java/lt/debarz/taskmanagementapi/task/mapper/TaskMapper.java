package lt.debarz.taskmanagementapi.task.mapper;

import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;

@Mapper
public interface TaskMapper {

    TaskMapper TASK_MAPPER = Mappers.getMapper(TaskMapper.class);

    TaskDto convertEntityToDto(Task task);
    Task convertDtoToEntity(TaskDto taskDto) throws ParseException;
}
