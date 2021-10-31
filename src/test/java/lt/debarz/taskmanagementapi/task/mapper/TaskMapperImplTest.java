package lt.debarz.taskmanagementapi.task.mapper;

import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.model.TimeSpent;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskMapperImplTest {
    private final TaskMapperImpl taskMapperImpl = new TaskMapperImpl();

    @Test
    public void testToEntity() throws ParseException {
        Task task = new Task();
        Task task1 = new Task();
        TimeSpent timeSpent = new TimeSpent();
        TimeSpent timeSpent1 = new TimeSpent();

        task.setId(1L);
        task.setName("exp");
        task.setDescription("exp");
        task.setTaskGroup("exp");
        task.setStatus(Status.Open);
        timeSpent.setId(1L);
        timeSpent.setStartTime();
        timeSpent.setEndTime(task.getStatus().name());
        task.setTimeSpent(timeSpent);

        task1.setId(2L);
        task1.setName("exp1");
        task1.setDescription("exp1");
        task1.setTaskGroup("exp1");
        task1.setStatus(Status.Open);
        timeSpent1.setId(2L);
        timeSpent1.setStartTime();
        timeSpent1.setEndTime(task.getStatus().name());
        task1.setTimeSpent(timeSpent);

        task.addTask(task1);

        TaskDto taskDto = taskMapperImpl.convertEntityToDto(task);

        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getName(), task.getName());
        assertEquals(taskDto.getDescription(), task.getDescription());
        assertEquals(taskDto.getTaskGroup(), task.getTaskGroup());
        assertEquals(taskDto.getStatus(), task.getStatus().name());
        assertEquals(taskDto.getStartTime(), task.getTimeSpent().getStartTime());
        assertEquals(taskDto.getEndTime(), task.getTimeSpent().getEndTime());
        assertEquals(taskDto.getSubTasks().toString(), task.getSubTasks().toString());

    }

    @Test
    void testToDto() {

    }

}
