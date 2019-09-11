package Lectures.lecture3.part5;

/*
*           Encapsular o que varia, strategy pattern
* */
public class Version5Test {

    public static void main(String[] args) {
        System.out.println("Details of the tennis player: ");
        TennisPlayer guga = new TennisPlayer("Gustavo Kuerten", new RunFast());
        guga.train();
        guga.compete();
        guga.defineTactics();
        guga.performRunning();

        System.out.println("\nDetails of the football player: ");
        FootballPlayer ronaldinho = new FootballPlayer("Ronaldo Gaúcho", new RunFast());
        ronaldinho.train();
        ronaldinho.compete();
        ronaldinho.defineTactics();
        ronaldinho.performRunning();

        System.out.println("\nDetails of the card player: ");
        CardPlayer bob = new CardPlayer("Bob Strong Deck", new RunNoWay());
        bob.train();
        bob.compete();
        bob.defineTactics();
        bob.performRunning();

        System.out.println("\nDetails of the chess player: ");
        ChessPlayer kasparov = new ChessPlayer("Guerry Kasparov", new RunNoWay());
        kasparov.train();
        kasparov.compete();
        kasparov.defineTactics();
        kasparov.performRunning();

        System.out.println("\nDetails of the golf player: ");
        GolfPlayer weir = new GolfPlayer("Mike Weir", new RunNoWay());
        weir.train();
        weir.compete();
        weir.defineTactics();
        weir.performRunning();

        System.out.println("\nTesting the runtime behavior after change\n");
        ronaldinho.setRunningBehavior(new RunNoWay());
        ronaldinho.train();
        ronaldinho.compete();
        ronaldinho.defineTactics();
        ronaldinho.performRunning();

        System.out.println("");

        weir.setRunningBehavior(new RunFast());
        weir.train();
        weir.compete();
        weir.defineTactics();
        weir.performRunning();
    }
}
