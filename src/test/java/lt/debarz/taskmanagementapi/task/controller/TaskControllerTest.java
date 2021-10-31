package lt.debarz.taskmanagementapi.task.controller;

import com.google.common.collect.Lists;
import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.model.TimeSpent;
import lt.debarz.taskmanagementapi.task.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;




@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @MockBean
    TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/tasks")
    public void testGetAllTasks() throws Exception {


    }
}
