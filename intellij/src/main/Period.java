package main;

import java.time.LocalDateTime;

public abstract class Period {
    Long startTime;
    Long length;

    String periodType;

    public Period(long length) {
        this.startTime = System.currentTimeMillis();
        this.length = length;
    }

    public Long getTimeRemaining() {
        Long elapsedTimeMillis = System.currentTimeMillis() - startTime;
        Long elapsedSeconds = elapsedTimeMillis / (1000);
        Long timeRemaining = (length * 60) - elapsedSeconds;
        return timeRemaining;
    }

    public String timeRemainingMessage() {
        Long timeRemaining = getTimeRemaining();
        Long minutesRemaining = timeRemaining / 60;
        Long secondsRemaining = timeRemaining % 60;
        if (minutesRemaining < 0 || secondsRemaining < 0) {
            return periodType + " time is up!";
        } else {
            return periodType + " Period: " + minutesRemaining + ":" + secondsRemaining + " remaining";
        }
    }

    public boolean isOver() {
        if (this.getTimeRemaining() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return this.periodType;
    }

}
