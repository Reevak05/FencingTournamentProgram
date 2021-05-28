import java.util.ArrayList;

public class Pool {
    private ArrayList<Fencer> poolFencers;
    private Bout[][] poolBouts;

    /**
     * creates a new Pool
     * @param poolFencers an ArrayList of fencers who are competing in the pool
     */
    public Pool(ArrayList<Fencer> poolFencers) {
        this.poolFencers = poolFencers;

        poolBouts = new Bout[this.poolFencers.size()][this.poolFencers.size()];
        for (int i = 0; i <poolBouts.length; i++) {
            for (int j = 0; j <poolBouts[0].length; j++) {
                poolBouts[i][j] = new Bout(poolFencers.get(i), poolFencers.get(j));
            }
        }

    }

    /**
     * this method gets the statistics for each fencer in the pool and returns them in a String[][]
     * @return a 2D array of Strings, with a row for each fencer and each value in the row being a statistic for the fencer
     */
    public String[][] getPoolStats() {
        String[][] statsList = new String[this.poolFencers.size()][6];
        for (int i = 0; i < this.poolFencers.size(); i++) {
            statsList[i][0] = this.poolFencers.get(i).toString();
            statsList[i][1] = "bouts won: " + this.poolFencers.get(i).getBoutsWon();
            statsList[i][2] = "touches scored: " + this.poolFencers.get(i).getTouchesScored();
            statsList[i][3] = "touches received: " + this.poolFencers.get(i).getTouchesReceived();
            statsList[i][4] = "indicator: " + this.poolFencers.get(i).getIndicator();
            statsList[i][5] = "percentage of bouts won: " + (this.poolFencers.get(i).getWinPercentage() + "     ").substring(0,5) + "%";
        }
        return statsList;
    }

    /**
     * this method returns the Bout[][] in which pool bout data is stored
     * @return a 2D array of all the bouts in the pool
     */
    public Bout[][] getPoolBouts() {
        return poolBouts;
    }

}