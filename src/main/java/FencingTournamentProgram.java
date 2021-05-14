import java.util.ArrayList;
import java.util.Scanner;

public class FencingTournamentProgram {

    public FencingTournamentProgram() {
        System.out.println("FencingTournamentProgram ready.");

        Scanner input = new Scanner(System.in);

        //create fencers for pool and create pool
        ArrayList<Fencer> poolFencers = new ArrayList<>();

        System.out.println("Enter the number of fencers in this pool: ");
        int numberOfFencersInPool = input.nextInt();

        for (int i = 0; i<numberOfFencersInPool; i++) {
            System.out.println("Enter the name of fencer " + i + ":");
            poolFencers.add(new Fencer(i, input.next()));
        }
        Pool pool1 = new Pool(poolFencers);

        //pool bout results entry
        for (Bout[] boutRow :pool1.getPoolBouts()) {
            for (Bout bout : boutRow) {
                System.out.println("enter the points scored by the left fencer: ");
                int leftFencerPointsScored = input.nextInt();
                System.out.println("enter the points scored by the left fencer: ");
                int rightFencerPointsScored = input.nextInt();
                bout.enterResults(leftFencerPointsScored, rightFencerPointsScored, (leftFencerPointsScored>=rightFencerPointsScored) ? bout.getFencerLeft() : bout.getFencerRight());
            }
        }
    }

    public static void main(String[] args) {
        FencingTournamentProgram fencingTournamentProgram = new FencingTournamentProgram();
    }
}
