import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;


public class FencingTournamentProgram implements ActionListener {

    private JFrame frame;
    private JPanel panelInputFencers, panelShowBouts, panelInputScores, panelShowStats;
    private JLabel labelPanelInputFencers, labelAddFencerName, labelPanelShowBouts, labelPanelInputScores, labelBoutForResultEntry, labelResultEntryFencer1, labelResultEntryFencer2, labelPanelShowStats;
    private JTextField textFieldAddFencerName, leftFencerPointsScoredText, rightFencerPointsScoredText;
    private JButton buttonAddFencer, buttonCreatePool, buttonEnterResult;
    private ArrayList<Fencer> poolFencers = new ArrayList<>();
    private ArrayList<Pool> pools;
    private Pool pool1;
    private int poolResultsEntryPositionRow = 0, poolResultsEntryPositionCol = 0;



    public FencingTournamentProgram() {
        //frame setup
        frame = new JFrame("Fencing Tournament Program");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //panel for fencer input
        panelInputFencers = new JPanel();
        panelInputFencers.setLayout(new BoxLayout(panelInputFencers, BoxLayout.Y_AXIS));
        labelPanelInputFencers = new JLabel("Fencer Input");
        panelInputFencers.add(labelPanelInputFencers);

        labelAddFencerName = new JLabel("enter fencer name:");
        panelInputFencers.add(labelAddFencerName);

        textFieldAddFencerName = new JTextField();
        panelInputFencers.add(textFieldAddFencerName);

        buttonAddFencer = new JButton("add fencer");
        buttonAddFencer.addActionListener(this);
        panelInputFencers.add(buttonAddFencer);

        buttonCreatePool = new JButton("create pool");
        buttonCreatePool.addActionListener(this);
        panelInputFencers.add(buttonCreatePool);

        frame.add(panelInputFencers);


        //panel to tell user the bouts
        panelShowBouts = new JPanel();
        panelShowBouts.setLayout(new BoxLayout(panelShowBouts, BoxLayout.Y_AXIS));
        labelPanelShowBouts = new JLabel("Bouts");
        panelShowBouts.add(labelPanelShowBouts);

        frame.add(panelShowBouts);


        //panel to enter scores
        panelInputScores = new JPanel();
        panelInputScores.setLayout(new BoxLayout(panelInputScores, BoxLayout.Y_AXIS));
        labelPanelInputScores = new JLabel("Bout Results Input");
        panelInputScores.add(labelPanelInputScores);

        labelBoutForResultEntry = new JLabel("results entry - not for use yet");
        panelInputScores.add(labelBoutForResultEntry);
        labelResultEntryFencer1 = new JLabel("fencer 1");
        panelInputScores.add(labelResultEntryFencer1);
        rightFencerPointsScoredText = new JTextField();
        panelInputScores.add(rightFencerPointsScoredText);
        labelResultEntryFencer2 = new JLabel("fencer 2");
        panelInputScores.add(labelResultEntryFencer2);
        leftFencerPointsScoredText = new JTextField();
        panelInputScores.add(leftFencerPointsScoredText);
        buttonEnterResult = new JButton("enter result");
        buttonEnterResult.addActionListener(this);
        panelInputScores.add(buttonEnterResult);

        frame.add(panelInputScores);

        //panel to tell user the stats of the fencers
        panelShowStats = new JPanel();
        panelShowStats.setLayout(new BoxLayout(panelShowStats, BoxLayout.Y_AXIS));
        labelPanelShowStats = new JLabel("Fencer Stats");
        panelShowStats.add(labelPanelShowStats);

        frame.add(panelShowStats);


        //frame setup
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new FencingTournamentProgram();
    }

    //results entry setup
    public void setUpNextResultsInput(){
        System.out.println(poolResultsEntryPositionRow + " row");
        System.out.println(poolResultsEntryPositionCol + " col");
        if (!pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].isSelfBout() && !pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].isBoutComplete()) {


            labelBoutForResultEntry.setText("results entry for: " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft() + " v " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight());
            labelResultEntryFencer1.setText("enter the points scored by " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().getName());
            labelResultEntryFencer2.setText("enter the points scored by " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().getName());

        }
        //move to next spot on grid if current bout does not need score entry
        else {
            if (poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length-1) {
                poolResultsEntryPositionCol++;
                setUpNextResultsInput();

            }
            else if (poolResultsEntryPositionRow<pool1.getPoolBouts().length-1) {
                poolResultsEntryPositionRow++;
                poolResultsEntryPositionCol = 0;
                setUpNextResultsInput();

            }
        }
    }

    public void showStats() {
        String[][] poolStats = pool1.getPoolStats();
        for (String[] fencerStats: poolStats) {
                panelShowStats.add(new JList<>(fencerStats));
        }
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
            poolFencers.add(new Fencer(poolFencers.size(), textFieldAddFencerName.getText()));
            textFieldAddFencerName.setText("");
        }

        else if (buttonName.equals("create pool")) {
            //pool creation
            pool1 = new Pool(poolFencers);
            for (Bout[] boutRow : pool1.getPoolBouts()) {
                for (Bout bout : boutRow) {
                    if (!bout.isSelfBout()) panelShowBouts.add(new JLabel(bout.toString()));
                }
            }

            //results entry setup
            setUpNextResultsInput();
        }

        else if (buttonName.equals("enter result")) {

            int leftFencerPointsScored = Integer.parseInt(leftFencerPointsScoredText.getText());
            int rightFencerPointsScored = Integer.parseInt(rightFencerPointsScoredText.getText());

            //result saving to pool bouts data
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].enterResults(leftFencerPointsScored, rightFencerPointsScored, (leftFencerPointsScored >= rightFencerPointsScored) ? pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft() : pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight());
            pool1.getPoolBouts()[poolResultsEntryPositionCol][poolResultsEntryPositionRow].enterResults(rightFencerPointsScored,leftFencerPointsScored, (rightFencerPointsScored >= leftFencerPointsScored) ? pool1.getPoolBouts()[poolResultsEntryPositionCol][poolResultsEntryPositionRow].getFencerLeft() : pool1.getPoolBouts()[poolResultsEntryPositionCol][poolResultsEntryPositionRow].getFencerRight());

            //individual fencer stats update - left fencer
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementTouchesScored(leftFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementTouchesReceived(rightFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementBoutsFenced();
            if (leftFencerPointsScored >= rightFencerPointsScored) {
                pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementBoutsWon();
            }

            //individual fencer stats update - right fencer
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementTouchesScored(rightFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementTouchesReceived(leftFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementBoutsFenced();
            if (!(leftFencerPointsScored >= rightFencerPointsScored)) {
                pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementBoutsWon();
            }

            //increment grid counter for next result entry
            if (poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length-1) {
                poolResultsEntryPositionCol++;
            }
            else if (poolResultsEntryPositionRow<pool1.getPoolBouts().length-1) {
                poolResultsEntryPositionRow++;
                poolResultsEntryPositionCol = 0;
            }
            if (poolResultsEntryPositionRow<pool1.getPoolBouts().length && poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length) setUpNextResultsInput();
            if (poolResultsEntryPositionRow==pool1.getPoolBouts().length-1 && poolResultsEntryPositionCol==pool1.getPoolBouts()[0].length-1) showStats();
        }
    }
}
