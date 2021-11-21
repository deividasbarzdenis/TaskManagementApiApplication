package lt.debarz.taskmanagementapi.task.util;

import lt.debarz.taskmanagementapi.task.entity.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MyEnumConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        return Status.valueOf(source);
    }
}
