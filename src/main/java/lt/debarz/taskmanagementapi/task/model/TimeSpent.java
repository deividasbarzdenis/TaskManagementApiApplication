package lt.debarz.taskmanagementapi.task.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    //Attribute for task spend completing the task.
    //Attribute is not saved to repo, but can be serialized.
    @Transient
    private Date taskCompletionTime;

    @OneToOne
    @MapsId
    @JoinColumn(name = "task_id")
    private Task task;

    public void setStartTime() {
        this.startTime = new Date();
    }

    public void setEndTime(String status) throws ParseException {
        if (status.equals("Done")) {
            this.endTime = new Date();
        }
        this.endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("0000-00-00 00:00:00");
    }

    public Date getTaskCompletionTime(){
        long diff = this.endTime.getTime() - this.startTime.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
        String completeTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date newDate = null;
        try {
            newDate = sdf.parse(completeTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
