package Lectures.lecture10.without_pattern.part5;

import java.util.Arrays;

public class SlotMachine {

    public static final int EMPTY_MACHINE = 1;
    public static final int WAITING_COIN = 2;
    public static final int WAITING_LEVER_PULL = 3;
    public static final int DRAWING_LOTS = 4;
    public static final int PAYING_PRIZE = 5;
    public static final int PAYING_EXTRA_PRIZE = 6;

    private int currentState = EMPTY_MACHINE;
    private int coinAmount;
    private Figure[] drawnResult;

    public SlotMachine(int coinAmount) {
        this.coinAmount = coinAmount;
        drawnResult = new Figure[3];
        // 100 coins is the prize. So, the machine needs at least 100 coins in order to be playable
        if (coinAmount >= 100) {
            currentState = WAITING_COIN;
        }
        for (int i = 0; i < drawnResult.length; i++) {
            drawnResult[i] = Figure.FLOWER;
        }
    }

    public void insertCoin() {
        if (currentState == WAITING_COIN) {
            currentState = WAITING_LEVER_PULL;
            coinAmount++;
            System.out.println("Coin inserted. Pull the lever!");

        } else if (currentState == EMPTY_MACHINE) {
            System.out.println("Machine not ready. Not enough coins to pay the prize.");

        } else if (currentState == WAITING_LEVER_PULL) {
            System.out.println("You have already inserted a coin. Pull the lever to play!");

        } else if (currentState == DRAWING_LOTS) {
            System.out.println("You have already inserted a coin and pulled the lever. Good luck!!!");

        } else if (currentState == PAYING_PRIZE || currentState == PAYING_EXTRA_PRIZE) {
            System.out.println("Wait the prize be payed to insert another coin.");
        }
    }

    public void ejectCoin() {
        if (currentState == WAITING_LEVER_PULL) {
            currentState = WAITING_COIN;
            coinAmount--;
            System.out.println("Coin ejection button pressed. Retrieving coin.");

        } else if (currentState == EMPTY_MACHINE) {
            System.out.println("You haven't inserted a coin yet.");

        } else if (currentState == WAITING_COIN) {
            System.out.println("You haven't inserted a coin yet.");

        } else if (currentState == DRAWING_LOTS) {
            System.out.println("Machine already drawing. Impossible to retrieve coin.");

        } else if (currentState == PAYING_PRIZE || currentState == PAYING_EXTRA_PRIZE) {
            System.out.println("Do you want the prize and your coin? No way Jose!");
        }
    }

    public void pullLever() {
        if (currentState == WAITING_LEVER_PULL) {
            currentState = DRAWING_LOTS;
            System.out.println("Drawing. Keep your fingers crossed!");
            boolean specialPrize = draw();
            boolean winner = isAWinner();
            if (winner) {
                if (specialPrize) {
                    currentState = PAYING_EXTRA_PRIZE;
                    payExtraPrize();
                } else {
                    currentState = PAYING_PRIZE;
                    payThePrize();
                }
            } else {
                System.out.println("Unfortunately it wasn't at this time, try again!");
                currentState = WAITING_COIN;
            }
        } else if (currentState == EMPTY_MACHINE) {
            System.out.println("You haven't inserted a coin yet, and the machine is empty.");

        } else if (currentState == WAITING_COIN) {
            System.out.println("You pulled the lever but haven't inserted a coin yet.");

        } else if (currentState == DRAWING_LOTS) {
            System.out.println("Machine already drawing. Wait!");

        } else if (currentState == PAYING_PRIZE || currentState == PAYING_EXTRA_PRIZE) {
            System.out.println("Wait for the prize and insert another coin before pulling the lever.");
        }
    }

    private boolean draw() {
        boolean specialPrize = false;
        if (Math.random() > 0.5) {
            specialPrize = true;
        }
        System.out.println("Applyed for the Special Prize? " + (specialPrize? "YEAP!" : "Nope."));
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
        return specialPrize;
    }

    private boolean isAWinner() {
        if (drawnResult[0].equals(drawnResult[1]) && drawnResult[0].equals(drawnResult[2])) {
            return true;
        }
        return false;
    }

    private void payThePrize() {
        System.out.println("Congratulation you have won! Paying the prize!");
        coinAmount -= 100;
        System.out.println("Prize payed successfully!");
        if (coinAmount < 100) {
            System.out.println("Machine out of coins.");
            currentState = EMPTY_MACHINE;
        } else {
            currentState = WAITING_COIN;
        }
    }

    private void payExtraPrize() {
        System.out.println("Congratulation you have won the Special Prize! \nYou won " + coinAmount + " coins");
        coinAmount = 0;
        System.out.println("Prize payed successfully!");
        currentState = EMPTY_MACHINE;
    }

    public void supplyMachine(int quantity) {
        System.out.println("Supplying machine with " + quantity + " coins");
        coinAmount += quantity;
        if (coinAmount >= 100) {
            currentState = WAITING_COIN;
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
}
