public class Fencer {

    private final int number;
    private final String name;
    private int boutsWon;
    private int boutsFenced;
    private int touchesScored;
    private int touchesReceived;

    /**
     * creates a new Fencer
     * @param number the name of the fencer
     * @param name the number of the fencer (unique and used for identification)
     */
    public Fencer(int number, String name) {
        this.number = number;
        this.name = name;
    }

    /**
     * returns the number of the fencer
     * @return the number of the fencer
     */
    public int getNumber() {
        return number;
    }

    /**
     * returns the name of the fencer
     * @return the name of the fencer
     */
    public String getName() {
        return name;
    }

    /**
     * returns the number of bouts won by the fencer
     * @return the number of bouts won by the fencer
     */
    public int getBoutsWon() {
        return boutsWon;
    }

    /**
     * increases the number of bouts won by the fencer by 1
     */
    public void incrementBoutsWon() {
        this.boutsWon += 1;
    }

    /**
     * increases the number of bouts fenced by the fencer by 1
     */
    public void incrementBoutsFenced() {
        this.boutsFenced += 1;
    }

    /**
     * returns the number of touches scored by the fencer
     * @return the number of touches scored by the fencer
     */
    public int getTouchesScored() {
        return touchesScored;
    }

    /**
     * increase the number of touches scored by the fencer
     * @param increment additional number of touches scored
     */
    public void incrementTouchesScored(int increment) {
        this.touchesScored += increment;
    }

    /**
     * returns the number of touches scored against the fencer
     * @return the number of touches scored against the fencer
     */
    public int getTouchesReceived() {
        return touchesReceived;
    }

    /**
     * increase the number of touches scored against the fencer
     * @param increment additional number of touches received
     */
    public void incrementTouchesReceived(int increment) {
        this.touchesReceived += increment;
    }

    /**
     * returns the indicator of the fencer, which is calculated by subtracting the number of touches received from the number of touches scored
     * @return the indicator of the fencer
     */
    public int getIndicator() {
        return this.touchesScored-this.touchesReceived;
    }

    /**
     * returns the percentage of bouts won by the fencer out of all the bouts they have fenced
     * @return the percentage of bouts won by the fencer
     */
    public double getWinPercentage() {
        return (double)this.boutsWon/this.boutsFenced*100;
    }

    /**
     * returns whether the two fencers are the same
     * @param otherFencer the fencer to compare the current fencer to
     * @return whether the two fencers are the same
     */
    public boolean equals(Fencer otherFencer) {
        return (this.number == otherFencer.getNumber());
    }

    /**
     * returns the name of the fencer when a value of the fencer is needed as a string
     * @return the name of the fencer
     */
    @Override
    public String toString() {
        return this.name;
    }
}