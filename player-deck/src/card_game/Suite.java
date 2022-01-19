package card_game;

public enum Suite {

    CLUBS(String.format("%c", 0x2663)),
    DIAMONDS(String.format("%c", 0x2666)),
    HEARTS(String.format("%c", 0x2665)),
    SPADES(String.format("%c", 0x2660));

    public final String label;

    Suite(String label) {
        this.label = label;
    }
}
