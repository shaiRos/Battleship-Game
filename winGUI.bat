:: Compiling and running the game in a windows machine
echo Compiling!

javac -d "bin" -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" game/*.java
javac -d "bin" -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" gui/*.java
javac -d "bin" -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" players/*.java
javac -d "bin" -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" board/*.java

echo running the game in gui version...

java -cp "bin" gui.BattleShipGUI
