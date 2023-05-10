# Assignment3

This assingment consists of four different classes: main, ReadDirectory, Connectors, and DetailOfIndividual.
I started by reading all the files' content by using ht efunction processDirectory. It's a recursive function that will traverse
through all the file in that directory. 
The function getEmail will read the email from the line that starts with "From: " until it reaches the line that starts with
"Subjects: " which indicates the end of the emails the person is sending to. 
Hence, I store the emails into a hashset which the key is the sender and the emails receipients will be the values associated 
to the key.
For the connectors class it uses depth-first search algorithm to traverse all the nodes
