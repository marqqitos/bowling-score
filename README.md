# bowling-score
To compile the code:
Inside the "bowling-score" folder run:

mvn package

If the build is succesful then:

1) Open a console in "bowling-score" root directory
2) Run the following command:

java -cp "target/bowling-score-1.0-SNAPSHOT.jar" "main.App" "txtFile"

Example: 
java -cp "target/bowling-score-1.0-SNAPSHOT.jar" "main.App" "testFiles/initialTest.txt"

Test cases are inside "testFiles" folder

//////////////////////////////////////////////////////////////////////////////////////

I completed the BONUS task, I have used stream and lambdas function of Java 8
I couldn't do automated Integration Test, I ran out of time