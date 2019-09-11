package Lectures.lecture10.with_pattern.part9;

public class WaitingLeverPull extends State {

    public WaitingLeverPull(SlotMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin() {
        System.out.println("You have already inserted a coin. Pull the lever to play!");
    }

    @Override
    public void ejectCoin() {
        machine.setState(machine.getWaitingCoin());
        machine.takeCoinOut();
        System.out.println("Coin ejection button pressed. Retrieving coin.");
    }

    @Override
    public void pullLever() {
        machine.setState(machine.getDrawing());
        machine.draw();
        boolean winner = machine.isAWinner();
        if (winner) {
            machine.setState(machine.getPayingPrize());
            machine.getPayingPrize();
        } else {
            System.out.println("It wasn't this time, try again!");
            machine.setState(machine.getWaitingCoin());
        }
    }
}
