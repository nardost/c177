package card_game;

public class Card {

    private final Rank rank;
    private final Suite suite;

    public Card(Rank rank, Suite suite) {
        this.rank = rank;
        this.suite = suite;
    }

    @Override
    public String toString() {
        return String.format("%s%s", rank.label, suite.label);
    }
}
