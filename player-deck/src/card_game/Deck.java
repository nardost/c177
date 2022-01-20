package card_game;

public interface Deck {
    boolean isEmpty();
    int countRemaining();
    int size();
    void shuffle();
    Card[] draw(int n);
    void display();
    void displayDiscarded();
}
