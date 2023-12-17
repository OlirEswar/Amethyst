package main;

public class BreakPeriod extends Period{
    PomodoroGame game;
    public BreakPeriod(long length) {
        super(length);
        this.periodType = "Break";
        this.game = new PomodoroGame();
    }
}
