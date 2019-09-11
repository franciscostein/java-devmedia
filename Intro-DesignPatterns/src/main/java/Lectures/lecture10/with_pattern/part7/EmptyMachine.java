package Lectures.lecture10.with_pattern.part7;

public class EmptyMachine extends State {

    public EmptyMachine(SlotMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin() {

    }

    @Override
    public void ejectCoin() {

    }

    @Override
    public void pullLever() {

    }
}
