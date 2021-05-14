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

        this.printPoolBouts(); //debug

    }

    public void printPoolBouts() {
        for (Bout[] boutRow : this.poolBouts) {
            for (Bout bout : boutRow) {
                System.out.println(bout);
            }
        }
    }

    public Bout[][] getPoolBouts() {
        return poolBouts;
    }

}
