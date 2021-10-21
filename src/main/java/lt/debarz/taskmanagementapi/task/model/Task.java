package lt.debarz.taskmanagementapi.task.model;

import lombok.*;
import lt.debarz.taskmanagementapi.user.model.User;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    private String group;
    private Status status;
    private User assignee;
    private TimeSpent timeSpent;

    private Task parentTask;
    private Set<Task> subTasks;

}
