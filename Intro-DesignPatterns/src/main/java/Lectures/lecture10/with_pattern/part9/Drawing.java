package Lectures.lecture10.with_pattern.part9;

public class Drawing extends State {

    public Drawing(SlotMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin() {
        System.out.println("You have already inserted a coin and pulled the lever. Good luck!!!");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Machine already drawing. Impossible to retrieve coin.");
    }

    @Override
    public void pullLever() {
        System.out.println("Machine already drawing. Wait!");
    }
}
