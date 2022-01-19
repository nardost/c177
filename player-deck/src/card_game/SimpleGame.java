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
                final Card[] discardedCards = new Card[deck.size()];
                deck.shuffle();
                deck.display();
                int playedCardsCount = 0;
                while (!deck.isEmpty()) {
                    final int numberOfCardsDrawn;
                    System.out.print("How many cards do you want to draw? ");
                    numberOfCardsDrawn = scanner.nextInt();
                    final Card[] drawnCards = deck.draw(numberOfCardsDrawn);
                    System.arraycopy(drawnCards, 0, discardedCards, playedCardsCount, drawnCards.length);
                    playedCardsCount += drawnCards.length;
                    System.out.println("Drawn:     " + Arrays.toString(drawnCards));
                    final Card[] temp = new Card[playedCardsCount];
                    System.arraycopy(discardedCards, 0, temp, 0, playedCardsCount);
                    System.out.println("Discarded: " + Arrays.toString(temp));
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
