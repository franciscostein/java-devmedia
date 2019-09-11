package Lectures.lecture10.with_pattern.part9;

public class PayingPrize extends State {

    public PayingPrize(SlotMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin() {
        System.out.println("Waiting the prize be paid");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Do you want the prize and your coin? No way Jose!");
    }

    @Override
    public void pullLever() {
        System.out.println("Wait for the prize and insert another coin before pulling the lever.");
    }

    public void payThePrize() {
        System.out.println("Congratulation you have won! Paying the prize!");
        machine.setCoinAmount(machine.getCoinAmount() - 100);
        System.out.println("Prize payed successfully!");
        if (machine.getCoinAmount() < 100) {
            System.out.println("Machine out of coins.");
            machine.setState(machine.getEmptyMachine());
        } else {
            machine.setState(machine.getWaitingCoin());
        }
    }
}
