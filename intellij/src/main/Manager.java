package main;

public class Manager {
    int periodLengths[];

    boolean currentlyWorkPeriod = true;
    Period currentPeriod;
    public Manager(int workPeriodLength, int breakPeriodLength) {
        this.periodLengths = new int[]{workPeriodLength, breakPeriodLength};
    }

    public void startSession() {
        this.currentPeriod = new WorkPeriod(periodLengths[0]);
    }

    public boolean nextPeriod() {
        if (this.currentPeriod.isOver()) {
            if (currentlyWorkPeriod) {
                this.currentlyWorkPeriod = false;
                this.currentPeriod = new BreakPeriod(periodLengths[1]);
            } else {
                this.currentlyWorkPeriod = true;
                this.currentPeriod = new WorkPeriod(periodLengths[0]);
            }
            return true;
        }
        return false;
    }

    public String getCurrentPeriod() {
        return this.currentPeriod.toString();
    }
}
