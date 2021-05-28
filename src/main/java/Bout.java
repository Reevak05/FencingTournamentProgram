public class Bout {

    private Fencer fencerLeft, fencerRight;
    private boolean boutComplete, isSelfBout, isTie;
    private int fencerLeftPointsScored, fencerRightPointsScored;
    private Fencer victor;

    /**
     * creates a new Bout
     * @param fencerLeft the left fencer in the bout
     * @param fencerRight the right fencer in the bout
     */
    public Bout(Fencer fencerLeft, Fencer fencerRight) {
        this.fencerLeft = fencerLeft;
        this.fencerRight = fencerRight;
        this.boutComplete = false;
        this.isSelfBout = (fencerLeft.equals(fencerRight));
    }

    /**
     * saves the results of the bout
     * @param fencerLeftPointsScored the number of points scored by the left fencer
     * @param fencerRightPointsScored the number of points scored by the right fencer
     */
    public void enterResults(int fencerLeftPointsScored, int fencerRightPointsScored) {
        this.fencerLeftPointsScored = fencerLeftPointsScored;
        this.fencerRightPointsScored = fencerRightPointsScored;
        this.boutComplete = true;
        if (fencerLeftPointsScored>fencerRightPointsScored) {
            this.victor = this.fencerLeft;
            this.isTie = false;
        }
        else if (fencerLeftPointsScored<fencerRightPointsScored) {
            this.victor = this.fencerRight;
            this.isTie = false;
        }
        else this.isTie = true;
    }

    /**
     * returns the left fencer
     * @return the left fencer
     */
    public Fencer getFencerLeft() {
        return fencerLeft;
    }

    /**
     * returns the right fencer
     * @return the right fencer
     */
    public Fencer getFencerRight() {
        return fencerRight;
    }

    /**
     * returns whether the bout is complete
     * @return whether the bout is complete
     */
    public boolean isBoutComplete() {
        return boutComplete;
    }

    /**
     * returns whether the bout is between the same fencer
     * @return whether the bout is between the same fencer
     */
    public boolean isSelfBout() {
        return isSelfBout;
    }

    /**
     * returns the bout data formatted as a string
     * @return the bout data formatted as a string
     */
    @Override
    public String toString() {
        if (boutComplete && !isTie) {
            return "victor: " + this.victor + "; fencer left: " + this.fencerLeft + " (" + this.fencerLeftPointsScored + ") v fencer right: " + this.fencerRight + " (" + this.fencerRightPointsScored + ")";
        }
        else if (boutComplete && isTie) {
            return "tie; fencer left: " + this.fencerLeft + " (" + this.fencerLeftPointsScored + ") v fencer right: " + this.fencerRight + " (" + this.fencerRightPointsScored + ")";
        }
        else {
            return "incomplete; fencer left: " + this.fencerLeft + " v fencer right: " + this.fencerRight;
        }
    }
}