package lt.debarz.taskmanagementapi.task.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "time_spent")
public class TimeSpent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @OneToOne
    @MapsId
    @JoinColumn(name="task_id")
    private Task task;

    public void setStartTime() {
        this.startTime = new Date();
    }
    public void setEndTime(String status) throws ParseException {
        if (status.equals("Done")){
            this.endTime = new Date();
        }
        this.endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("0000-00-00 00:00:00");
    }
}
