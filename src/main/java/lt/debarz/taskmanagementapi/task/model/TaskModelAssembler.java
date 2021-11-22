package lt.debarz.taskmanagementapi.task.model;

import lt.debarz.taskmanagementapi.task.controller.TaskController;
import lt.debarz.taskmanagementapi.task.entity.Task;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TaskModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskModel> {

    public TaskModelAssembler() {
        super(TaskController.class, TaskModel.class);
    }

    @Override
    protected TaskModel instantiateModel(Task task) {
        return new TaskModel(task);
    }

    @Override
    public TaskModel toModel(Task task) {
        return createModelWithId(task.getId(), task);
    }
}
