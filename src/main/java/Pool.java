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

    public String[][] getPoolStats() {
        String[][] statsList = new String[this.poolFencers.size()][6];
        for (int i = 0; i < this.poolFencers.size(); i++) {
            statsList[i][0] = this.poolFencers.get(i).toString();
            statsList[i][1] = "bouts won: " + this.poolFencers.get(i).getBoutsWon();
            statsList[i][2] = "touches scored: " + this.poolFencers.get(i).getTouchesScored();
            statsList[i][3] = "touches received: " + this.poolFencers.get(i).getTouchesReceived();
            statsList[i][4] = "indicator: " + this.poolFencers.get(i).getIndicator();
            statsList[i][5] = "percentage of bouts won: " + this.poolFencers.get(i).getWinPercentage() + "%";
        }
        return statsList;
    }


    public Bout[][] getPoolBouts() {
        return poolBouts;
    }

}
