package model;

import java.io.Serializable;
import java.time.LocalTime;

public class OrderDeadline implements Serializable {
    private LocalTime deadlineTime;

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(LocalTime deadlineTime) {
        this.deadlineTime = deadlineTime;
    }
}
