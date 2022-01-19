package card_game;

public interface Deck {
    boolean isEmpty();
    int size();
    void display();
    void shuffle();
    Card[] draw(int n);
}
