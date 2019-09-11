package Lectures.lecture10.without_pattern.part1;

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
}
