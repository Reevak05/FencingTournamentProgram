import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FencingTournamentProgram implements ActionListener {

    private JFrame frame;
    private JPanel panelInputFencers, panelShowBouts, panelInputScores, panelShowStats;
    private JTextField testFieldAddFencerName;
    private JButton buttonAddFencer;



    public FencingTournamentProgram() {
        //frame setup
        frame = new JFrame("Fencing Tournament Program");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //panel for fencer input
        panelInputFencers = new JPanel();
        panelInputFencers.setLayout(new BoxLayout(panelInputFencers, BoxLayout.Y_AXIS));

        testFieldAddFencerName = new JTextField();
        panelInputFencers.add(testFieldAddFencerName);
        buttonAddFencer = new JButton("add fencer");

        frame.add(panelInputFencers);


        //panel to tell user the bouts
        panelShowBouts = new JPanel();
        panelShowBouts.setLayout(new BoxLayout(panelShowBouts, BoxLayout.Y_AXIS));

        frame.add(panelShowBouts);


        //panel to enter scores
        panelInputScores = new JPanel();
        panelInputScores.setLayout(new BoxLayout(panelInputScores, BoxLayout.Y_AXIS));

        frame.add(panelInputScores);

        //panel to tell user the stats of the fencers
        panelShowStats = new JPanel();
        panelShowStats.setLayout(new BoxLayout(panelShowStats, BoxLayout.Y_AXIS));

        frame.add(panelShowStats);


        //frame setup
        frame.pack();
        frame.setVisible(true);



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
        System.out.println();

        //pool bout results entry
        for (int row = 0; row<pool1.getPoolBouts().length; row++) {
            for (int col = 0; col< pool1.getPoolBouts()[0].length; col++) {
                if (!pool1.getPoolBouts()[row][col].isSelfBout() && !pool1.getPoolBouts()[row][col].isBoutComplete()) {

                    //results input
                    System.out.println("results entry for: " + pool1.getPoolBouts()[row][col].getFencerLeft() + " v " + pool1.getPoolBouts()[row][col].getFencerRight());
                    System.out.println("enter the points scored by " + pool1.getPoolBouts()[row][col].getFencerLeft().getName());
                    int leftFencerPointsScored = input.nextInt();
                    System.out.println("enter the points scored by " + pool1.getPoolBouts()[row][col].getFencerRight().getName());
                    int rightFencerPointsScored = input.nextInt();

                    //results saving to pool bouts data
                    pool1.getPoolBouts()[row][col].enterResults(leftFencerPointsScored, rightFencerPointsScored, (leftFencerPointsScored >= rightFencerPointsScored) ? pool1.getPoolBouts()[row][col].getFencerLeft() : pool1.getPoolBouts()[row][col].getFencerRight());
                    pool1.getPoolBouts()[col][row].enterResults(rightFencerPointsScored,leftFencerPointsScored, (rightFencerPointsScored >= leftFencerPointsScored) ? pool1.getPoolBouts()[col][row].getFencerLeft() : pool1.getPoolBouts()[col][row].getFencerRight());

                    //individual fencer stats update
                    pool1.getPoolBouts()[row][col].getFencerLeft().incrementTouchesScored(leftFencerPointsScored);
                    pool1.getPoolBouts()[row][col].getFencerLeft().incrementTouchesReceived(rightFencerPointsScored);
                    pool1.getPoolBouts()[row][col].getFencerLeft().incrementBoutsFenced();
                    if (leftFencerPointsScored >= rightFencerPointsScored) {
                        pool1.getPoolBouts()[row][col].getFencerLeft().incrementBoutsWon();
                    }

                    pool1.getPoolBouts()[row][col].getFencerRight().incrementTouchesScored(rightFencerPointsScored);
                    pool1.getPoolBouts()[row][col].getFencerRight().incrementTouchesReceived(leftFencerPointsScored);
                    pool1.getPoolBouts()[row][col].getFencerRight().incrementBoutsFenced();
                    if (!(leftFencerPointsScored >= rightFencerPointsScored)) {
                        pool1.getPoolBouts()[row][col].getFencerRight().incrementBoutsWon();
                    }
                }
            }
        }

        System.out.println();
        pool1.printPoolBouts();

        System.out.println();
        pool1.printPoolStats();

    }

    public static void main(String[] args) {
        FencingTournamentProgram fencingTournamentProgram = new FencingTournamentProgram();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = e.getActionCommand();

        if (buttonName.equals("add fencer")) {

        }
    }
}
