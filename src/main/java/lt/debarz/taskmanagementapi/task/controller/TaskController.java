package lt.debarz.taskmanagementapi.task.controller;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.entity.Status;
import lt.debarz.taskmanagementapi.task.model.TaskModel;
import lt.debarz.taskmanagementapi.task.service.TaskService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RepositoryRestController
public class TaskController {

    private final TaskService taskService;

    /**
     * Get tasks by user
     */
    @GetMapping(path = "/userTasks", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TaskModel>> getTasksByUser() {
        List<TaskModel> taskModels = taskService.getTasksByUserId();
        CollectionModel<TaskModel> tasksCollection = CollectionModel.of(taskModels,
                linkTo(methodOn(TaskController.class)
                        .getTasksByUser())
                        .withRel("userTasks"));
        return new ResponseEntity<>(tasksCollection, HttpStatus.OK);
    }

    /**
     * Get sub-tasks by task id
     */
    @GetMapping(path ="subTasks/{taskId}/{pageNumber}/{pageSize}", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TaskModel>> getAllSubTasksByMainTaskId(@PathVariable long taskId,
                                                                                 @PathVariable int pageNumber,
                                                                                 @PathVariable int pageSize) {
        List<TaskModel> taskModels = taskService.findAllSubTasksById(taskId, pageNumber, pageSize);
        CollectionModel<TaskModel> tasksCollection = CollectionModel.of(taskModels,
                linkTo(methodOn(TaskController.class)
                        .getAllSubTasksByMainTaskId(taskId, pageNumber, pageSize))
                        .withRel("subTasks/{taskId}/{pageNumber}/{pageSize}"));
        return new ResponseEntity<>(tasksCollection, HttpStatus.OK);
    }

    /**
     * Get tasks by status
     */
    @GetMapping(path ="/status/{status}", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TaskModel>> getAllTasksByStatus(@PathVariable("status") Status status) {
        List<TaskModel> taskModels = taskService.findAllTasksByStatus(status);
        CollectionModel<TaskModel> tasksCollection = CollectionModel.of(taskModels,
                linkTo(methodOn(TaskController.class)
                        .getAllTasksByStatus(status))
                        .withRel("/status/{status}"));
        return new ResponseEntity<>(tasksCollection, HttpStatus.OK);
    }

    /**
     * Get task total completion time  with all data
     */
    @GetMapping(path ="/totalTime/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<TaskModel>> getTaskWithTimeSpentOnTask(@PathVariable long id) {
        TaskModel taskModel = taskService.getTimeSpentOnTask(id);
        EntityModel<TaskModel> model = EntityModel.of(taskModel, linkTo(methodOn(TaskController.class)
                .getTaskWithTimeSpentOnTask(id))
                .withRel("/totalTime/{id}"));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * Get all task with pages
     */
    @GetMapping(path = "/allTasks", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TaskModel>> getTaskModelsWithPages() {
        List<TaskModel> taskModels = taskService.getTaskModels();
        CollectionModel<TaskModel> tasksCollection = CollectionModel.of(taskModels,
                linkTo(methodOn(TaskController.class).getTaskModelsWithPages()).withRel("allTasks"));
        return new ResponseEntity<>(tasksCollection, HttpStatus.OK);
    }
}
