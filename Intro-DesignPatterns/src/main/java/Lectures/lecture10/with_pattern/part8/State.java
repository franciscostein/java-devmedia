package Lectures.lecture10.with_pattern.part8;

public abstract class State {

    protected SlotMachine machine;

    public State(SlotMachine machine) {
        this.machine = machine;
    }

    public abstract void insertCoin();

    public abstract void ejectCoin();

    public abstract void pullLever();
}
