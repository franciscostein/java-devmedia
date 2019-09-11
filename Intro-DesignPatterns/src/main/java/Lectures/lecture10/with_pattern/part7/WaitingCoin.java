package Lectures.lecture10.with_pattern.part7;

public class WaitingCoin extends State {

    public WaitingCoin(SlotMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin() {
        machine.setState(machine.getWaitingLeverPull());
    }

    @Override
    public void ejectCoin() {
        System.out.println("You haven't inserted any coin");
    }

    @Override
    public void pullLever() {
        System.out.println("You pulled the lever without inserting a coin");
    }
}
