# Assignment3

For this assignement we are asked to find the connection of individuals in a directory that has several email textfile. We are assigned to find the connectors and output it to a textfile.

In order to complete this assingment I contructed four different java classes: Main, ReadDirectory, Connectors, and DetailOfIndividual.

ReadDirectory: Traverses through all the email files and add the necessary part to a HashSet with the sender as key and receipients as the values.

  -> The function getEmail will read the email from the line that starts with "From: " until it reaches the line that starts with "Subjects: " which indicates the end of the emails the person is sending to. 

Connectors: This class will be visiting all the nodes and the find the connectors, finally output the connectors to a textfile as required. 

DetailOfIndividual: It finds how many emails that have exchange email withe the sender etc.

Main: it gathers all the necessary functions and make the program work.

Time complexity: The depth-first search function it has a time complexity of O(V + E). The process directory has a time complexity of O(n) where n is the number of files. 

I started by reading all the files' content by using the function processDirectory. It's a recursive function that will traverse through all the file in that directory. 

