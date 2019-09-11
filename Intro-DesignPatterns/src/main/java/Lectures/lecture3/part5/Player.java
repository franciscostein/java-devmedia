package Lectures.lecture3.part5;

public abstract class Player {

    private RunningBehavior runningBehavior;

    String name;

    protected Player(String name, RunningBehavior runningBehavior) {
        this.name = name;
        this.runningBehavior = runningBehavior;
    }

    public void train() {
        System.out.println(name + " trains hard");
    }

    public void compete() {
        System.out.println(name + " is very competitive");
    }

    public void performRunning() {
        runningBehavior.run();
    }

    public void setRunningBehavior(RunningBehavior runningBehavior) {
        this.runningBehavior = runningBehavior;
    }

    public abstract void defineTactics();
}
