javac -d "bin" -cp .:junit-4.12.jar:hamcrest-core-1.3.jar game/*.java
javac -d "bin" -cp .:junit-4.12.jar:hamcrest-core-1.3.jar gui/*.java
javac -d "bin" -cp .:junit-4.12.jar:hamcrest-core-1.3.jar players/*.java
javac -d "bin" -cp .:junit-4.12.jar:hamcrest-core-1.3.jar board/*.java
javac -d "bin" -cp .:junit-4.12.jar:hamcrest-core-1.3.jar saveload/*.java
java -cp "bin" game.MenuScreen

