We have created bash script to run and test the project on terminal

Do this on the main project folder

---For Mac and Linux (recommanded)---- 
"bash macText" for text version of the program
"bash macGUI" for the GUI version
"bash JUnitTest" to run j unit tests

---For Windows---- 
"bash winTextfor text version of the program
"bash winGUIfor the GUI version
bash script JUnitTest only works on Mac and Linux computers


---Notes about testing---
Because of the location of thest files and unit testing, the usual javac *.java won't compile
Junit tests are in the package of the objects they are going to be testing
- i.e. "BoardTest.java" is in the package board
Test document is included for extra manual testing


---other notes---
Bash script would both compile and run the programsw
- bash script would save all .class files in the bin folder
if the scripts failed to run and you have to manually compile everything, run java MenuScreen from the game package and java BattleShipGUI for GUI

"javadoc" folder includes all javadoc documents in HTML form