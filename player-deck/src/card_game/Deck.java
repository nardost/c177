package card_game;

public interface Deck {
    boolean isEmpty();
    void display();
    void shuffle();
    Card[] draw(int n);
}
