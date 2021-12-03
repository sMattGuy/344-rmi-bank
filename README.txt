To compile this use
javac src/*.java -d bin

First, you must run the server, use
java -cp bin Server hostname port

Then, run the client
java -cp bin Client hostname port


full example
javac src/*.java -d bin
java -cp bin Server localhost 3000
java -cp bin Client localhost 3000

!IMPORTANT!
If you have a firewall, it may attempt to block this program

If you have any question I am available anytime at
matthew.flammia71@qmail.cuny.edu