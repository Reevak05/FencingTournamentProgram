public class Bout {

    private Fencer fencerLeft, fencerRight;
    private boolean boutComplete, isSelfBout, isTie;
    private int fencerLeftPointsScored, fencerRightPointsScored;
    private Fencer victor;

    public Bout(Fencer fencerLeft, Fencer fencerRight) {
        this.fencerLeft = fencerLeft;
        this.fencerRight = fencerRight;
        this.boutComplete = false;
        this.isSelfBout = (fencerLeft.equals(fencerRight));
    }

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
