package Lectures.lecture10.with_pattern.part9;

public class EmptyMachine extends State {

    public EmptyMachine(SlotMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin() {
        System.out.println("Machine out of coins to pay the prize.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("You haven't inserted a coin yet.");
    }

    @Override
    public void pullLever() {
        System.out.println("You haven't inserted a coin yet and the machine is empty.");
    }
}
