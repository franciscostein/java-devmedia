package Lectures.lecture10.without_pattern.part2;

public class SlotMachine {

    public static final int EMPTY_MACHINE = 1;
    public static final int WAITING_COIN = 2;
    public static final int WAITING_LEVER_PULL = 3;
    public static final int DRAWING_LOTS = 4;
    public static final int PAYING_PRIZE = 5;

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

        } else if (currentState == PAYING_PRIZE) {
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

        } else if (currentState == PAYING_PRIZE) {
            System.out.println("Do you want the prize and your coin? No way Jose!");
        }
    }

    public void pullLever() {
        if (currentState == WAITING_LEVER_PULL) {
            currentState = DRAWING_LOTS;
            System.out.println("Drawing. Keep your fingers crossed!");
            draw();
            boolean winner = isAWinner();
            if (winner) {
                currentState = PAYING_PRIZE;
                payPrize();
            } else {
                currentState = WAITING_COIN;
            }

        } else if (currentState == EMPTY_MACHINE) {
            System.out.println("You haven't inserted a coin yet, and the machine is empty.");

        } else if (currentState == WAITING_COIN) {
            System.out.println("You pulled the lever but haven't inserted a coin yet.");

        } else if (currentState == DRAWING_LOTS) {
            System.out.println("Machine already drawing. Wait!");

        } else if (currentState == PAYING_PRIZE) {
            System.out.println("Wait for the prize and insert another coin before pulling the lever.");
        }
    }

    private void draw() {

    }

    private boolean isAWinner() {
        return false;
    }

    private void payPrize() {

    }
}
