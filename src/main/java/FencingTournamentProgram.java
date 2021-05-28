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
    private Pool pool1;
    private int poolResultsEntryPositionRow = 0, poolResultsEntryPositionCol = 0;

    /**
     * creates a new FencingTournamentProgram
     * executed as program setup
     */
    public FencingTournamentProgram() {
        //set up frame
        frame = new JFrame("Fencing Tournament Program");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up panel for fencer input
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


        //set up panel to tell user the bouts
        panelShowBouts = new JPanel();
        panelShowBouts.setLayout(new BoxLayout(panelShowBouts, BoxLayout.Y_AXIS));
        labelPanelShowBouts = new JLabel(" Bouts");
        labelPanelShowBouts.setFont(new Font(labelPanelShowBouts.getFont().getName(), Font.BOLD, 16));

        frame.add(panelShowBouts);


        //set up panel to enter scores
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


        //set up panel to tell user the stats of the fencers
        panelShowStats = new JPanel();
        panelShowStats.setLayout(new BoxLayout(panelShowStats, BoxLayout.Y_AXIS));
        labelPanelShowStats = new JLabel("Fencer Stats");
        labelPanelShowStats.setFont(new Font(labelPanelShowStats.getFont().getName(), Font.BOLD, 16));

        frame.add(panelShowStats);


        //finish setting up frame
        frame.setSize(650,250);
        frame.setVisible(true);
    }

    /**
     * the main method
     * run this to run the program
     */
    public static void main(String[] args) {
        new FencingTournamentProgram();
    }

    /**
     * this method changes the labels on the results entry panel to reflect the bout for which scores are being entered
     */
    public void setUpNextResultsInput(){
        //clear the previous entry from the text fields
        leftFencerPointsScoredText.setText("");
        rightFencerPointsScoredText.setText("");

        //if the current bout needs score entry, change the labels
        if (!pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].isSelfBout() && !pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].isBoutComplete()) {
            labelBoutForResultEntry.setText("results entry for: " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft() + " v " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight());
            labelResultEntryFencer1.setText("enter the points scored by " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerLeft().getName());
            labelResultEntryFencer2.setText("enter the points scored by " + pool1.getPoolBouts()[poolResultsEntryPositionRow][poolResultsEntryPositionCol].getFencerRight().getName());
        }

        //if current bout does not need score entry, move to the next bout and run the method again
        else {
            //since the bouts are organized in a grid,the next bout is either the current row and one column to the right or a row below and the first column
            //if the next bout is one column to the right
            if (poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length-1) {
                poolResultsEntryPositionCol++;
                setUpNextResultsInput();
            }
            //if the next bout is in the next row
            else if (poolResultsEntryPositionRow<pool1.getPoolBouts().length-1) {
                poolResultsEntryPositionRow++;
                poolResultsEntryPositionCol = 0;
                setUpNextResultsInput();
            }
        }
    }

    /**
     * this method adds the stats for each fencer in the pool in the stats panel
     */
    public void showStats() {
        panelShowStats.add(labelPanelShowStats);
        String[][] poolStats = pool1.getPoolStats();
        for (String[] fencerStats: poolStats) {
                panelShowStats.add(new JList<>(fencerStats));
        }
        updateBouts();
    }

    /**
     * this method updates the bouts in the bouts panel to reflect the results of the bouts once the user enters the results
     */
    public void updateBouts() {
        panelShowBouts.removeAll();
        panelShowBouts.add(labelPanelShowBouts);
        for (int row = 0; row < pool1.getPoolBouts().length; row++) {
            for (int col = row; col <pool1.getPoolBouts()[0].length; col++) {
                if (!pool1.getPoolBouts()[row][col].isSelfBout()) panelShowBouts.add(new JLabel(" " + pool1.getPoolBouts()[row][col].toString()));
            }
        }
        panelShowStats.updateUI();
    }

    /**
     * invoked when an action occurs
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = e.getActionCommand();

        //if the "add fencer" button is clicked, add the fencer data to the pool and clear the text from the text field
        if (buttonName.equals("add fencer")) {
            poolFencers.add(new Fencer(poolFencers.size(), textFieldAddFencerName.getText()));
            textFieldAddFencerName.setText("");
        }

        //once all the fencer names have been entered and the "create pool" button is pressed, the fencer input panel is removed, the bouts panel with a list of bouts is shown to the user, and the results entry panel is shown and populated with data for the first bout
        else if (buttonName.equals("create pool")) {
            //pool creation
            pool1 = new Pool(poolFencers);

            //show bouts to user
            updateBouts();

            //remove Fencer Input panel
            panelInputFencers.removeAll();

            //show the results entry panel and populate it with the data for the first bout for results entry
            if (pool1.getPoolBouts().length>1) {
                panelInputScores.add(labelPanelInputScores);
                panelInputScores.add(labelBoutForResultEntry);
                panelInputScores.add(labelResultEntryFencer1);
                panelInputScores.add(leftFencerPointsScoredText);
                panelInputScores.add(labelResultEntryFencer2);
                panelInputScores.add(rightFencerPointsScoredText);
                panelInputScores.add(buttonEnterResult);
                setUpNextResultsInput();
            }
            //if there is only one fencer, go straight to the fencer statistics panel and do not show the results entry panel
            else showStats();
        }

        //whenever a bout result is entered and the "enter result" button is pressed, the results of the bout are saved and the statistics for each fencer are updated
        else if (buttonName.equals("enter result")) {

            int leftFencerPointsScored = Integer.parseInt(leftFencerPointsScoredText.getText());
            int rightFencerPointsScored = Integer.parseInt(rightFencerPointsScoredText.getText());

            //bout result saving to pool bouts data
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

            //update the data shown on the bouts panel with the results of the bout
            updateBouts();

            //increment grid counter for next result entry
            //since the bouts are organized in a grid,the next bout is either the current row and one column to the right or a row below and the first column

            //if the next bout is one column to the right
            if (poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length-1) {
                poolResultsEntryPositionCol++;
            }
            //if the next bout is in the next row
            else if (poolResultsEntryPositionRow<pool1.getPoolBouts().length-1) {
                poolResultsEntryPositionRow++;
                poolResultsEntryPositionCol = 0;
            }

            //if there are more bouts remaining for results entry, change the data on the results entry panel to reflect the data from the next bout
            if (poolResultsEntryPositionRow<pool1.getPoolBouts().length && poolResultsEntryPositionCol<pool1.getPoolBouts()[0].length) setUpNextResultsInput();
            //if there are no more bouts remaining for results entry, show the fencer statistics panel and remove the results entry panel
            if (poolResultsEntryPositionRow==pool1.getPoolBouts().length-1 && poolResultsEntryPositionCol==pool1.getPoolBouts()[0].length-1) {
                showStats();
                panelInputScores.removeAll();
            }
        }
    }
}