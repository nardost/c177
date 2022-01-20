package card_game;

import java.util.Arrays;
import java.util.Scanner;

public class SimpleGame implements Game {

    private final DeckFactory deckFactory;

    public SimpleGame() {
        this.deckFactory = new StandardDeckFactory();
    }

    @Override
    public void play() {
        try (final Scanner scanner = new Scanner(System.in)) {
            boolean playAgain;
            do {
                System.out.println("Begin new game...");
                final Deck deck = this.deckFactory.createDeck();
                deck.shuffle();
                deck.display();
                while (!deck.isEmpty()) {
                    final int numberOfCardsDrawn;
                    System.out.print("How many cards do you want to draw? ");
                    numberOfCardsDrawn = scanner.nextInt();
                    final Card[] drawnCards = deck.draw(numberOfCardsDrawn);
                    System.out.println("Drawn:     " + Arrays.toString(drawnCards));
                    System.out.print("Discarded: ");
                    deck.displayDiscarded();
                }
                System.out.println("Game over!...");
                System.out.print("Do you want to play again? [Y/n]: ");
                final String response = scanner.next();
                playAgain = "".equals(response) || response.toLowerCase().charAt(0) == 'y';
            } while (playAgain);
            System.out.println("Good bye!");
        }
    }
}
