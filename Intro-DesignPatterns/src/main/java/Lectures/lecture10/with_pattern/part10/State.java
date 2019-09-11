package Lectures.lecture10.with_pattern.part10;

public abstract class State {

    protected SlotMachine machine;

    public State(SlotMachine machine) {
        this.machine = machine;
    }

    public abstract void insertCoin();

    public abstract void ejectCoin();

    public abstract void pullLever();

    public void draw() {}

    public void payPrize() {}

    public boolean isAWinner() {
        return false;
    }
}
