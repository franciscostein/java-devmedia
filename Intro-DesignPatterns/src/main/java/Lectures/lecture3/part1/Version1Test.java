package Lectures.lecture3.part1;

public class Version1Test {

    public static void main(String[] args) {
        System.out.println("Details of the tennis player: ");
        TennisPlayer guga = new TennisPlayer("Gustavo Kuerten");
        guga.train();
        guga.compete();
        guga.defineTactics();

        System.out.println("\nDetails of the football player: ");
        FootballPlayer ronaldinho = new FootballPlayer("Ronaldo Gaúcho");
        ronaldinho.train();
        ronaldinho.compete();
        ronaldinho.defineTactics();
    }
}
