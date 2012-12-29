# Battleships coursework
## Assessment for the Software and Programming II module at Birkbeck, University of London

- Author: Jose B. Gomes
- Tutor: Keith Mannock
- Student Id: 12500741
- Date: 22 December 2012

## Dependencies

- [Lombok](http://projectlombok.org/)
- [jUnit](http://junit.org/)

## Directory structure assumed

### For playing only

> Desktop
>> bin
>>> battleships
>>>> *.class
>> lombok.jar
>> jUnit.jar


### For compiling code only

> Desktop
>> bin
>> src
>>> battleships
>>>> *.java
>> lombok.jar
>> jUnit.jar

## How to play

Download the contents of the bin directory to your desktop, as per [above](#for-playing-only), and run:

	java -classpath bin:lombok.jar:jUnit.jar battleships/BattleshipGame
	java -classpath bin:/Users/jbonigomes/Desktop/lombok/lombok.jar battleships/BattleshipGame

## How to compile

Download the contents of the src directory to your desktop, as per [above](#for-compiling-code-only), and run:

	javac -d bin -sourcepath src -classpath bin:lombok.jar:jUnit.jar src/battleships/<fileName>.java
	javac -d bin -sourcepath src -classpath bin:/Users/jbonigomes/Desktop/lombok/lombok.jar src/battleships/<fileName>.java

1.	The code above will automatically create the _**battleships**_ directory under _**bin**_
+	Once compiled, you may play the game as per instructions [above](#how-to-play)
+	This documentation assumes you have _**Java/javac**_ correctly installed in your environment
