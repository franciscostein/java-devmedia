package Lectures.lecture10.with_pattern.part10;

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
        machine.getDrawing().draw();
        boolean winner = machine.getDrawing().isAWinner();
        if (winner) {
            machine.setState(machine.getPayingPrize());
            machine.getPayingPrize().payPrize();
        } else {
            System.out.println("It wasn't this time, try again!");
            machine.setState(machine.getWaitingCoin());
        }
    }
}
