import java.util.HashMap;

public class Fencer {

    private final int number;
    private final String name;

    private int boutsWon;
    private int boutsFenced;
    private int touchesScored;
    private int touchesReceived;

    public Fencer(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getBoutsWon() {
        return boutsWon;
    }

    public void incrementBoutsWon(int increment) {
        this.boutsWon += increment;
    }

    public void incrementBoutsWon() {
        this.boutsWon += 1;
    }

    public int getBoutsFenced() {
        return boutsFenced;
    }

    public void incrementBoutsFenced(int increment) {
        this.boutsFenced += increment;
    }

    public void incrementBoutsFenced() {
        this.boutsFenced += 1;
    }

    public int getTouchesScored() {
        return touchesScored;
    }

    public void incrementTouchesScored(int increment) {
        this.touchesScored += increment;
    }

    public int getTouchesReceived() {
        return touchesReceived;
    }

    public void incrementTouchesReceived(int increment) {
        this.touchesReceived += increment;
    }

    public int getIndicator() {
        return this.touchesScored-this.touchesReceived;
    }

    public double getWinPercentage() {
        return (double)this.boutsWon/this.boutsFenced*100;
    }

    public boolean equals(Fencer otherFencer) {
        return (this.number == otherFencer.getNumber());
    }

    @Override
    public String toString() {
        return "name: " + this.name + ", number: " + this.number;
    }


}
