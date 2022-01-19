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
    public void display() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = this.topIndex; i < this.deck.length; i++) {
            final Card card = this.deck[i];
            sb.append(" ");
            sb.append(card);
        }
        sb.append(" ]");
        System.out.println(sb);
    }

    @Override
    public boolean isEmpty() {
        return this.topIndex > this.deck.length - 1;
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
        // move the top index forward by n
        this.topIndex += n;
        return cards;
    }
}
