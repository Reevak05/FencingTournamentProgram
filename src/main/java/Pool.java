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
        for (Fencer fencer : this.poolFencers) {
            System.out.println(fencer);
            System.out.println("bouts won: " + fencer.getBoutsWon());
            System.out.println("touches scored: " + fencer.getTouchesScored());
            System.out.println("touches received: " + fencer.getTouchesReceived());
            System.out.println("indicator: " + fencer.getIndicator());
            System.out.println("percentage of bouts won: " + fencer.getWinPercentage() + "%");
            System.out.println();
        }
    }


    public Bout[][] getPoolBouts() {
        return poolBouts;
    }

}
