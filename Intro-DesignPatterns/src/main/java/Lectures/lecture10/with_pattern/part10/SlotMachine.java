package Lectures.lecture10.with_pattern.part10;

import java.util.Arrays;

public class SlotMachine {

    private State emptyMachine;
    private State waitingCoin;
    private State waitingLeverPull;
    private State drawing;
    private State payingPrize;

    private int coinAmount;
    private State currentState = emptyMachine;

    public SlotMachine(int coinAmount) {
        emptyMachine = new EmptyMachine(this);
        waitingCoin = new WaitingCoin(this);
        waitingLeverPull = new WaitingLeverPull(this);
        drawing = new Drawing(this);
        payingPrize = new PayingPrize(this);

        this.coinAmount = coinAmount;
        // 100 coins is the prize. So, the machine needs at least 100 coins in order to be playable
        if (coinAmount >= 100) {
            currentState = waitingCoin;
        }
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getEmptyMachine() {
        return emptyMachine;
    }

    public State getWaitingCoin() {
        return waitingCoin;
    }

    public State getWaitingLeverPull() {
        return waitingLeverPull;
    }

    public State getDrawing() {
        return drawing;
    }

    public State getPayingPrize() {
        return payingPrize;
    }

    public void insertCoin() {
        coinAmount++;
    }

    public void ejectCoin() {
        currentState.ejectCoin();
    }

    public void pullLever() {
        currentState.pullLever();
    }

    public void takeCoinOut() {
        coinAmount--;
    }

    public void supplyMachine(int quantity) {
        System.out.println("Supplying machine with " + quantity + " coins");
        coinAmount += quantity;
        if (coinAmount >= 100) {
            currentState = waitingCoin;
        }
    }

    @Override
    public String toString() {
        return "SlotMachine{" +
                "emptyMachine=" + emptyMachine +
                ", waitingCoin=" + waitingCoin +
                ", waitingLeverPull=" + waitingLeverPull +
                ", drawing=" + drawing +
                ", payingPrize=" + payingPrize +
                ", coinAmount=" + coinAmount +
                ", currentState=" + currentState +
                '}' + "\n";
    }
}
