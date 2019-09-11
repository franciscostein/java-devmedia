package Lectures.lecture3.part2;

public class Version2Test {

    public static void main(String[] args) {
        System.out.println("Details of the tennis player: ");
        TennisPlayer guga = new TennisPlayer("Gustavo Kuerten");
        guga.train();
        guga.compete();
        guga.defineTactics();
        guga.run();

        System.out.println("\nDetails of the football player: ");
        FootballPlayer ronaldinho = new FootballPlayer("Ronaldo Gaúcho");
        ronaldinho.train();
        ronaldinho.compete();
        ronaldinho.defineTactics();
        ronaldinho.run();

        System.out.println("\nDetails of the card player: ");
        CardPlayer bob = new CardPlayer("Bob Strong Deck");
        bob.train();
        bob.compete();
        bob.defineTactics();
        bob.run();

        System.out.println("\nDetails of the chess player: ");
        ChessPlayer kasparov = new ChessPlayer("Guerry Kasparov");
        kasparov.train();
        kasparov.compete();
        kasparov.defineTactics();
        kasparov.run();

        System.out.println("\nDetails of the golf player: ");
        GolfPlayer weir = new GolfPlayer("Mike Weir");
        weir.train();
        weir.compete();
        weir.defineTactics();
        weir.run();
    }
}
