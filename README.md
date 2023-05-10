# Assignment3

For this assignment, we are asked to find the connection of individuals in a directory that has several email textfile. We are assigned to find the connectors and output them to a text file.

In order to complete this assignment I constructed four different Java classes: Main, ReadDirectory, Connectors, and DetailOfIndividual.

ReadDirectory: Traverses through all the email files and add the necessary part to a HashSet with the sender as the key and recipients as the values.

  -> The function getEmail will read the email from the line that starts with "From: " until it reaches the line that starts with "Subjects: " which indicates the end of the emails the person is sending. 

Connectors: This class will be visiting all the nodes and find the connectors, finally output the connectors to a text file as required. 

DetailOfIndividual: It finds how many emails have been exchanged email with the sender etc.

Main: it gathers all the necessary functions and makes the program work.

Time complexity: The depth-first search function has a time complexity of O(V + E). The process directory has a time complexity of O(n) where n is the number of files. 

I started by reading all the files' content by using the function process directory. It's a recursive function that will traverse through all the files in that directory. 
