import java.util.ArrayList;
import java.util.Scanner;

public class FencingTournamentProgram {

    public FencingTournamentProgram() {
        System.out.println("FencingTournamentProgram ready.");

        Scanner scan = new Scanner(System.in);

        //create fencers for pool and create pool
        ArrayList<Fencer> poolFencers = new ArrayList<>();

        System.out.println("Enter the number of fencers in this pool: ");
        int numberOfFencersInPool = scan.nextInt();

        for (int i = 0; i<numberOfFencersInPool; i++) {
            System.out.println("Enter the name of fencer " + i + ":");
            poolFencers.add(new Fencer(i, scan.next()));
        }
        Pool pool1 = new Pool(poolFencers);
    }

    public static void main(String[] args) {
        FencingTournamentProgram fencingTournamentProgram = new FencingTournamentProgram();
    }
}
