import java.awt.*;
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
        labelPanelInputFencers.setFont(new Font(labelPanelInputFencers.getFont().getName(), Font.BOLD, 16));
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
        labelPanelShowBouts.setFont(new Font(labelPanelShowBouts.getFont().getName(), Font.BOLD, 16));

        frame.add(panelShowBouts);


        //panel to enter scores
        panelInputScores = new JPanel();
        panelInputScores.setLayout(new BoxLayout(panelInputScores, BoxLayout.Y_AXIS));
        labelPanelInputScores = new JLabel("Bout Results Input");
        labelPanelInputScores.setFont(new Font(labelPanelInputScores.getFont().getName(), Font.BOLD, 16));

        labelBoutForResultEntry = new JLabel("results entry - not for use yet");
        labelResultEntryFencer1 = new JLabel("fencer 1");
        rightFencerPointsScoredText = new JTextField();
        labelResultEntryFencer2 = new JLabel("fencer 2");
        leftFencerPointsScoredText = new JTextField();
        buttonEnterResult = new JButton("enter result");
        buttonEnterResult.addActionListener(this);

        frame.add(panelInputScores);

        //panel to tell user the stats of the fencers
        panelShowStats = new JPanel();
        panelShowStats.setLayout(new BoxLayout(panelShowStats, BoxLayout.Y_AXIS));
        labelPanelShowStats = new JLabel("Fencer Stats");
        labelPanelShowStats.setFont(new Font(labelPanelShowStats.getFont().getName(), Font.BOLD, 16));

        frame.add(panelShowStats);


        //frame setup
        frame.setSize(600,300);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new FencingTournamentProgram();
    }

    //results entry setup
    public void setUpNextResultsInput(){
        leftFencerPointsScoredText.setText("");
        rightFencerPointsScoredText.setText("");
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
        panelShowStats.add(labelPanelShowStats);
        String[][] poolStats = pool1.getPoolStats();
        //show fencer stats
        for (String[] fencerStats: poolStats) {
                panelShowStats.add(new JList<>(fencerStats));
        }
        updateBouts();
    }

    public void updateBouts() {
        //update bout result display
        panelShowBouts.removeAll();
        panelShowBouts.add(labelPanelShowBouts);
        for (int row = 0; row < pool1.getPoolBouts().length; row++) {
            for (int col = row; col <pool1.getPoolBouts()[0].length; col++) {
                if (!pool1.getPoolBouts()[row][col].isSelfBout()) panelShowBouts.add(new JLabel(pool1.getPoolBouts()[row][col].toString()));
            }
        }
        panelShowStats.updateUI();
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

            //show bouts to user
            updateBouts();

            //remove Fencer Input pane
            panelInputFencers.removeAll();

            //results entry setup
            panelInputScores.add(labelPanelInputScores);
            panelInputScores.add(labelBoutForResultEntry);
            panelInputScores.add(labelResultEntryFencer1);
            panelInputScores.add(rightFencerPointsScoredText);
            panelInputScores.add(labelResultEntryFencer2);
            panelInputScores.add(leftFencerPointsScoredText);
            panelInputScores.add(buttonEnterResult);
            setUpNextResultsInput();
        }

        else if (buttonName.equals("enter result")) {

            int leftFencerPointsScored = Integer.parseInt(leftFencerPointsScoredText.getText());
            int rightFencerPointsScored = Integer.parseInt(rightFencerPointsScoredText.getText());

            //result saving to pool bouts data
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].enterResults(leftFencerPointsScored, rightFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionCol][poolResultsEntryPositionRow].enterResults(rightFencerPointsScored,leftFencerPointsScored);

            //individual fencer stats update - left fencer
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementTouchesScored(leftFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementTouchesReceived(rightFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementBoutsFenced();
            if (leftFencerPointsScored > rightFencerPointsScored) {
                pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().incrementBoutsWon();
            }

            //individual fencer stats update - right fencer
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementTouchesScored(rightFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementTouchesReceived(leftFencerPointsScored);
            pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementBoutsFenced();
            if (rightFencerPointsScored > leftFencerPointsScored) {
                pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().incrementBoutsWon();
            }

            updateBouts();

            //increment grid counter for next result entry
            if (poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length-1) {
                poolResultsEntryPositionCol++;
            }
            else if (poolResultsEntryPositionRow<pool1.getPoolBouts().length-1) {
                poolResultsEntryPositionRow++;
                poolResultsEntryPositionCol = 0;
            }
            if (poolResultsEntryPositionRow<pool1.getPoolBouts().length && poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length) setUpNextResultsInput();
            if (poolResultsEntryPositionRow==pool1.getPoolBouts().length-1 && poolResultsEntryPositionCol==pool1.getPoolBouts()[0].length-1) {
                showStats();
                panelInputScores.removeAll();
            }
        }
    }
}