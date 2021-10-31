package lt.debarz.taskmanagementapi.task.model;

import lombok.*;
import lt.debarz.taskmanagementapi.user.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy="task")
    @PrimaryKeyJoinColumn
    private TimeSpent timeSpent;

    @ManyToOne
    private Task task;

    @OneToMany(mappedBy="task")
    private List<Task> subTasks = new ArrayList<>();

    @ManyToOne
    private User assignee;

    public void setTimeSpent(TimeSpent timeSpent) {
        if (timeSpent != null) {
            this.timeSpent = timeSpent;
            timeSpent.setTask(this);
        }
    }
    public Task addTask(Task subTask){
        subTask.setTask(this);
        this.subTasks.add(subTask);
        return this;
    }

    // need to for testing purpose
    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", subTasks=" + subTasks +
                ", startTime=" + timeSpent.getStartTime() +
                ", endTime=" + timeSpent.getEndTime() +
                '}';
    }
}
