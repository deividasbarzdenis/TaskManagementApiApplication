package lt.debarz.taskmanagementapi.task.controller;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)

class TaskControllerTest {

//    @MockBean
//    TaskService taskService;
//
//    @MockBean
//    TaskMapperImpl taskMapper;
//
//    @Autowired
//    private MockMvc mockMvc;

//    @Test
//    public void testGetAllTasks() throws Exception {
//
//        Task task = new Task();
//        Task task1 = new Task();
//        TimeSpent timeSpent = new TimeSpent();
//        TimeSpent timeSpent1 = new TimeSpent();
//
//        task.setId(1L);
//        task.setName("exp");
//        task.setDescription("exp");
//        task.setTaskGroup("exp");
//        task.setStatus(Status.Open);
//        timeSpent.setId(1L);
//        timeSpent.setStartTime();
//        timeSpent.setEndTime(task.getStatus().name());
//        task.setTimeSpent(timeSpent);
//
//        task1.setId(2L);
//        task1.setName("exp1");
//        task1.setDescription("exp1");
//        task1.setTaskGroup("exp1");
//        task1.setStatus(Status.Open);
//        timeSpent1.setId(2L);
//        timeSpent1.setStartTime();
//        timeSpent1.setEndTime(task.getStatus().name());
//        task1.setTimeSpent(timeSpent);
//
//        task.addTask(task1);
//
//        TaskDto taskDto = taskMapper.convertEntityToDto(task);
//
//        List<TaskDto> tasks = Collections.singletonList(taskDto);
//
//        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);
//
//        mockMvc.perform(get("/api/tasks"))
//                .andExpect(status().isOk());
//
//    }
}
