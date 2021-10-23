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

    @Column(name="task_group")
    private String taskGroup;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private TimeSpent timeSpent;

    @ManyToOne
    private Task task;

    @OneToMany(mappedBy="task")
    private Set<Task> subTasks;

    @ManyToOne
    private User assignee;
}
