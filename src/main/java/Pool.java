import java.util.ArrayList;

public class Pool {
    private ArrayList<Fencer> poolFencers;
    private Bout[][] poolBouts;

    public Pool(ArrayList<Fencer> poolFencers) {
        this.poolFencers = poolFencers;

        poolBouts = new Bout[this.poolFencers.size()][this.poolFencers.size()];
        for (int i = 0; i <poolBouts.length; i++) {
            for (int j = 0; j <poolBouts[0].length; j++) {
                poolBouts[i][j] = new Bout(poolFencers.get(i), poolFencers.get(j));
            }
        }

    }

    public void printPoolBouts() {
        for (Bout[] boutRow : this.poolBouts) {
            for (Bout bout : boutRow) {
                System.out.println(bout);
            }
        }
    }

    public void printPoolStats() {
        int boutsWon = 0;
        int touchesScored = 0;
        int touchesReceived = 0;

        for (Bout[] boutRow : this.poolBouts) {
            System.out.println(boutRow[0].getFencerLeft());
            for (Bout bout : boutRow) {
                if (!bout.isSelfBout() && bout.isBoutComplete()){
                    if (bout.getVictor().equals(bout.getFencerLeft())) boutsWon++;
                    touchesScored += bout.getFencerLeftPointsScored();
                    touchesReceived += bout.getFencerRightPointsScored();
                }
            }
            int indicator = touchesScored-touchesReceived;
            double winPercentage = boutsWon/(double)boutRow.length;
            System.out.println("bouts won: " + boutsWon);
            System.out.println("touches scored: " + touchesScored);
            System.out.println("touches received: " + touchesReceived);
            System.out.println("indicator: " + indicator);
            System.out.println("percentage of bouts won: " + winPercentage);
            System.out.println();
        }

    }

    public Bout[][] getPoolBouts() {
        return poolBouts;
    }

}
