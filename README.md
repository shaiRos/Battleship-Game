(Winter 2018) Group project for the course: Intro to Computer Science for CS Majors II
Battleship game created with Java and JavaFX. A class diagram is included for an overview. 

# **How to Run** 
###### *Requires java to run!*

## For Mac and Linux 
```
macGUI - gui version of the game
macText - text version of the game

double click on the shell script to run it or in the command line:
$./macGUI.sh
$./macText.sh

"bash JUnitTest" to run j unit tests
```
## For Windows
```
winGUI - gui version of the game
winText - text version of the game

double click on the batch file to run it or in the command line:
> winGUI
> winText

bash script JUnitTest only works on Mac and Linux computers
```
## Javadoc (documentation)
in the javadoc folder, open up index.html

## Notes about testing
Because of the location of these files and unit testing, the usual javac *.java won't compile. Junit tests are in the package of the objects they are going to be testing
- i.e. "BoardTest.java" is in the package board
Test document is included for extra manual testing


###### ---other notes---
Bash script would both compile and run the programs
- bash script would save all .class files in the bin folder
if the scripts failed to run and you have to manually compile everything, run java MenuScreen from the game package and java BattleShipGUI for GUI

"javadoc" folder includes all javadoc documents in HTML form
