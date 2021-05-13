public class Bout {

    private Fencer fencer1, fencer2;
    private boolean boutComplete;
    private int fencer1PointsScored, fencer2PointsScored;
    private Fencer victor;

    public Bout(Fencer fencer1, Fencer fencer2) {
        this.fencer1 = fencer1;
        this.fencer2 = fencer2;
    }

    //boutVictor is either 1 or 2, corresponding to the fencer that won the bout
    public void enterResults(int fencer1PointsScored, int fencer2PointsScored, int boutVictor) {
        this.fencer1PointsScored = fencer1PointsScored;
        this.fencer2PointsScored = fencer2PointsScored;
        this.victor = (boutVictor==1) ? fencer1 : fencer2;
        this.boutComplete = true;
    }
}
