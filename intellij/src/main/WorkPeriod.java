package main;

public class WorkPeriod extends Period{

    public WorkPeriod(long length) {
        super(length);
        this.periodType = "Work";
    }
}
