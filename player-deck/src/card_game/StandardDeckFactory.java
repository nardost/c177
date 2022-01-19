package card_game;

/**
 * Factory method that creates the standard 52-card deck
 */
public class StandardDeckFactory extends DeckFactory {
    @Override
    public Deck createDeck() {
        return new StandardDeck();
    }
}
