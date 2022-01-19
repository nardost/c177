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
                System.out.println("Begin Do....");
                Deck deck = this.deckFactory.createDeck();
                deck.shuffle();
                deck.display();
                while (!deck.isEmpty()) {
                    deck.shuffle();
                    System.out.println(Arrays.toString(deck.draw(3)));
                    deck.display();
                    System.out.println(Arrays.toString(deck.draw(23)));
                    deck.display();
                    System.out.println(Arrays.toString(deck.draw(26)));
                    deck.display();
                }
                System.out.print("Do you want to play again? [Y/n]: ");
                final String response = scanner.nextLine();
                playAgain = "".equals(response) || response.toLowerCase().charAt(0) == 'y';
            } while (playAgain);
        }
    }
}
