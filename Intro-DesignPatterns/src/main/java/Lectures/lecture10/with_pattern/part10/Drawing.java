package Lectures.lecture10.with_pattern.part10;

import java.util.Arrays;

public class Drawing extends State {

    private Figure[] drawnResult;

    public Drawing(SlotMachine machine) {
        super(machine);
        drawnResult = new Figure[3];
        for (int i = 0; i < drawnResult.length; i++) {
            drawnResult[i] = Figure.FLOWER;
        }
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

    @Override
    public String toString() {
        return "Drawing{" +
                "drawnResult=" + Arrays.toString(drawnResult) +
                '}';
    }
}
