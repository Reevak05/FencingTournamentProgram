public class Bout {

    private Fencer fencerLeft, fencerRight;
    private boolean boutComplete, isSelfBout;
    private int fencerLeftPointsScored, fencerRightPointsScored;
    private Fencer victor;

    public Bout(Fencer fencerLeft, Fencer fencerRight) {
        this.fencerLeft = fencerLeft;
        this.fencerRight = fencerRight;
        this.boutComplete = false;
        this.isSelfBout = (fencerLeft.equals(fencerRight));
    }

    //boutVictor is either "left" or "right", corresponding to the fencer that won the bout
    public void enterResults(int fencerLeftPointsScored, int fencerRightPointsScored, String boutVictor) {
        this.fencerLeftPointsScored = fencerLeftPointsScored;
        this.fencerRightPointsScored = fencerRightPointsScored;
        this.victor = (boutVictor.equals("left")) ? fencerLeft : fencerRight;
        this.boutComplete = true;
    }

    public Fencer getFencerLeft() {
        return fencerLeft;
    }

    public Fencer getFencerRight() {
        return fencerRight;
    }

    public boolean isBoutComplete() {
        return boutComplete;
    }

    public boolean isSelfBout() {
        return isSelfBout;
    }

    public int getFencerLeftPointsScored() {
        return fencerLeftPointsScored;
    }

    public int getFencerRightPointsScored() {
        return fencerRightPointsScored;
    }

    public Fencer getVictor() {
        return victor;
    }

    @Override
    public String toString() {
        if (isSelfBout) return "self bout: " + fencerLeft;
        else if (boutComplete) return "bout complete: true, fencer left: " + this.fencerLeft + " points scored: " + this.fencerLeftPointsScored + " v fencer right: " + this.fencerRight + "points scored: " + this.fencerRightPointsScored + "victor: " + victor;
        else return "bout complete: false, fencer left: " + this.fencerLeft + " v fencer right: " + this.fencerRight;
    }
}
