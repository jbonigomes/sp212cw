# Battleships coursework

### Assessment for the Software and Programming II module at Birkbeck, University of London

- Author: Jose B. Gomes
- Tutor: Keith Mannock
- Student Id: 12500741
- Date: 22 December 2012

## Dependencies

- [Lombok](http://projectlombok.org/)
- [jUnit](http://junit.org/)
- [Hamcrest.core](https://code.google.com/p/hamcrest/downloads/list)

## Directory structure

	sp212cw/
		tests/
			battleships/
				*.java
		bin/
			battleships/
				*.class
		src/
			battleships/
				*.java
		lombok.jar
		jUnit.jar
		hamcrest.jar

## Play

	java -classpath bin:lombok.jar battleships/BattleshipGame

## Compile

	javac -d bin -sourcepath src -classpath bin:lombok.jar src/battleships/*.java

## Compile Tests

	javac -d bin -sourcepath tests -classpath bin:junit.jar tests/battleships/*.java

## Run Tests

	java -classpath bin:junit.jar:hamcrest.jar org.junit.runner.JUnitCore battleships.OceanTest


> This documentation assumes you have _**Java/javac**_ correctly installed in your environment <br>
> Run the commands above from the root folder, from the directory structure above, it would be sp212cw
