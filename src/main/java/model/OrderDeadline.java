package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderDeadline implements Serializable {
    private LocalDateTime deadlineTime;

    public LocalDateTime getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(LocalDateTime deadlineTime) {
        this.deadlineTime = deadlineTime;
    }
}
