# FencingTournamentProgram

## Summary
This program prompts the user to input the names of the fencers who will be competing in the pool. The program then generates bouts in a pool format, where every fencer fences every other fencer in the pool. Once each bout is complete, the user can input the results of the bouts. The program then calculates and displays the results of the bouts and the statistics for each fencer in the pool, including bouts won, touches scored, touches received, indicator, and win percentage.

## Operation Instructions
To run the program, run `main()`in `FencingTournamentProgram.java`from the main branch.

## File Descriptions
`README.md` includes a summary, operation instructions, file descriptions, and sources for the program. 

`FencingTournamentProgram.java` facilitates rendering instructions for Swing, along with general management of the pool data.

`Pool.java` contains the data for the pool and related methods;

`Bout.java` contains data for bouts, including the left fencer, right fencer, points scored by each fencer, the victor, and whether the bout has been completed.

`Fencer.java` contains data for each fencer, including name, number, bouts won, bouts fenced, touches scored, touches received, indicator, win percentage, and a variety of related methods.

## Sources
I referred to my past projects in case I needed to recall how to do something in Swing.

I used the [Java Swing documentation](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) to find methods and their parameters.

I looked through the classes in the Swing package to find methods and their parameters.

I also used StackOverflow a few times to find methods, but I did not mindlessly copy code from it.
* [`setFont()` method](https://stackoverflow.com/questions/22847148/java-text-formatting-bold)
* [`removeAll()` method](https://stackoverflow.com/questions/16869812/how-to-remove-all-children-components-of-a-container)

