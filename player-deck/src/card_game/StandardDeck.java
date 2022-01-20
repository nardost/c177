package card_game;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The standard 52-card deck
 */
public class StandardDeck implements Deck {

    private final Card[] deck;
    private int topIndex = 0;

    public StandardDeck() {
        final Card[] cards = new Card[Rank.values().length * Suite.values().length];
        final AtomicInteger index = new AtomicInteger(0);
        EnumSet.allOf(Rank.class).forEach(
                rank -> EnumSet.allOf(Suite.class).forEach(
                        suite -> cards[index.getAndIncrement()] = new Card(rank, suite)
                )
        );
        this.deck = cards;
    }

    @Override
    public boolean isEmpty() {
        return this.topIndex > this.deck.length - 1;
    }

    @Override
    public int countRemaining() {
        return this.deck.length - this.topIndex;
    }

    @Override
    public int size() {
        return this.deck.length;
    }

    @Override
    public void shuffle() {
        final List<Card> cardList = Arrays.asList(this.deck);
        Collections.shuffle(cardList);
    }

    @Override
    public Card[] draw(int n) {
        // Too few cards available
        if(topIndex + n > deck.length) return new Card[0];
        // enough cards available
        final Card[] cards = new Card[n];
        System.arraycopy(this.deck, topIndex, cards, 0, n);
        // advance top index by n
        this.topIndex += n;
        return cards;
    }

    @Override
    public void display() {
        final int numberOfRemainingCards = countRemaining();
        final Card[] remainingCards = new Card[numberOfRemainingCards];
        System.arraycopy(this.deck, this.topIndex, remainingCards, 0, numberOfRemainingCards);
        System.out.println(Arrays.toString(remainingCards));
    }

    @Override
    public void displayDiscarded() {
        final Card[] discarded = new Card[this.topIndex];
        System.arraycopy(this.deck, 0, discarded, 0, this.topIndex);
        System.out.println(Arrays.toString(discarded));
    }
}
