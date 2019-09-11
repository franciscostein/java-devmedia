package Lectures.lecture10.with_pattern.part9;

import java.util.Arrays;

public class SlotMachine {

    public static final int WAITING_COIN = 2;
    public static final int WAITING_LEVER_PULL = 3;
    public static final int DRAWING_LOTS = 4;
    public static final int PAYING_PRIZE = 5;

    private State emptyMachine;
    private State waitingCoin;
    private State waitingLeverPull;
    private State drawing;
    private State payingPrize;

    private int coinAmount;
    private Figure[] drawnResult;
    private State currentState = emptyMachine;

    public SlotMachine(int coinAmount) {
        emptyMachine = new EmptyMachine(this);
        waitingCoin = new WaitingCoin(this);
        waitingLeverPull = new WaitingLeverPull(this);
        drawing = new Drawing(this);
        payingPrize = new PayingPrize(this);

        this.coinAmount = coinAmount;
        drawnResult = new Figure[3];
        // 100 coins is the prize. So, the machine needs at least 100 coins in order to be playable
        if (coinAmount >= 100) {
            currentState = waitingCoin;
        }
        for (int i = 0; i < drawnResult.length; i++) {
            drawnResult[i] = Figure.FLOWER;
        }
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void ejectCoin() {
        currentState.ejectCoin();
    }

    public void pullLever() {
        currentState.pullLever();
    }

    public void draw() {
        System.out.println("Drawn result..");
        int result = 0;

        for (int i = 0; i < drawnResult.length; i++) {
            result = (int) (Math.random() * 10 + 1);

            if (result >= 1 && result <= 3) {
                drawnResult[i] = Figure.FLOWER;

            } else if (result >= 3 && result <= 6) {
                drawnResult[i] = Figure.COIN;

            } else {
                drawnResult[i] = Figure.CLOVER;
            }
            //System.out.println(drawnResult[i] + " ");
        }
    }

    public boolean isAWinner() {
        if (drawnResult[0].equals(drawnResult[1]) && drawnResult[0].equals(drawnResult[2])) {
            return true;
        }
        return false;
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
                "currentState=" + currentState +
                ", coinAmount=" + coinAmount +
                ", drawnResult=" + Arrays.toString(drawnResult) +
                '}' +
                "\n";
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

    public void takeCoinOut() {
        coinAmount--;
    }
}
