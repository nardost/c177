package card_game;

public interface Deck {
    boolean isEmpty();
    int size();
    int countRemaining();
    void shuffle();
    Card[] draw(int n);
    void display();
    void displayDiscarded();
}
