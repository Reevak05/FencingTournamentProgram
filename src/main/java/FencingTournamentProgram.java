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
        pool1.printPoolBouts();

        //pool bout results entry
        for (int row = 0; row<pool1.getPoolBouts().length; row++) {
            for (int col = 0; col< pool1.getPoolBouts()[0].length; col++) {
                if (!pool1.getPoolBouts()[row][col].isSelfBout() && !pool1.getPoolBouts()[row][col].isBoutComplete()) {
                    System.out.println("results entry for: " + pool1.getPoolBouts()[row][col]);
                    System.out.println("enter the points scored by " + pool1.getPoolBouts()[row][col].getFencerLeft().getName());
                    int leftFencerPointsScored = input.nextInt();
                    System.out.println("enter the points scored by " + pool1.getPoolBouts()[row][col].getFencerRight().getName());
                    int rightFencerPointsScored = input.nextInt();
                    pool1.getPoolBouts()[row][col].enterResults(leftFencerPointsScored, rightFencerPointsScored, (leftFencerPointsScored >= rightFencerPointsScored) ? pool1.getPoolBouts()[row][col].getFencerLeft() : pool1.getPoolBouts()[row][col].getFencerRight());
                    pool1.getPoolBouts()[col][row].enterResults(rightFencerPointsScored,leftFencerPointsScored, (rightFencerPointsScored >= leftFencerPointsScored) ? pool1.getPoolBouts()[col][row].getFencerLeft() : pool1.getPoolBouts()[col][row].getFencerRight());
                }
            }
        }

        pool1.printPoolBouts();

    }

    public static void main(String[] args) {
        FencingTournamentProgram fencingTournamentProgram = new FencingTournamentProgram();
    }
}
