package lt.debarz.taskmanagementapi.task.util;

import lt.debarz.taskmanagementapi.task.entity.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;


@Configuration
public class SpringDataRestConfiguration {

    @Bean
    public RepresentationModelProcessor<EntityModel<Task>> taskProcessor (EntityLinks links){
        return new RepresentationModelProcessor<EntityModel<Task>>(){

            @Override
            public EntityModel<Task> process(EntityModel<Task> model) {
                model.add(
                        links.linkFor(Task.class)
                                .slash("userTasks")
                                .withRel("userTask")
                );
                model.add(
                        links.linkFor(Task.class)
                                .slash("subTasks")
                                .slash(model.getContent().getId())
                                .slash("pageNumber")
                                .slash("pageSize")
                                .withRel("AllSubTasksByMainTaskId")
                );
                model.add(
                        links.linkFor(Task.class)
                                .slash("status")
                                .slash(model.getContent().getStatus())
                                .withRel("AllTasksByStatus")

                );
                model.add(
                        links.linkFor(Task.class)
                                .slash("totalTime")
                                .slash(model.getContent().getId())
                                .withRel("TaskWithTimeSpentOnTask")

                );
                model.add(
                        links.linkFor(Task.class)
                                .slash("allTasks")
                                .withRel("AllTaskModelsWithPages")

                );
                return model;
            }
        };
    }
}
