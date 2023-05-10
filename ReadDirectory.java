import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DetailOfIndividual {
	
    private static final String INPUT_PROMPT = "Email address of the individual (or EXIT to quit): ";

    public static void getInformation(String sender, Map<String, Set<String>> senderToRecepientSet) {
    	
        if (!senderToRecepientSet.containsKey(sender)) {
        	
            System.out.println("Email address (" + sender + ") not found in the data set");
            
            return;
        }

        int sentCount = 0;
        
        int receivedCount = 0;

        for (Set<String> recipients : senderToRecepientSet.values()) {
        	
        	// when the sender reveived email from colleages
            if (recipients.contains(sender)) {
            	
                receivedCount++;
            }
            sentCount += recipients.contains(sender) ? 1 : 0;
        }

        System.out.printf("number of mail %s sent: %d%n", sender, sentCount);
        
        System.out.printf("number of mail %s received: %d%n", sender, receivedCount);
    }


    public static void getNumPeopleEmailedWith(String name, Map<String, Set<String>> connectionMap) {
    	
    	int teamCount = 0;
    	
        if (!connectionMap.containsKey(name)) {
        	
            return;
        }
        
        for (String member : connectionMap.get(name)) {
        	
        	// When it appears in other people's email list and they also sent email to them
            if (connectionMap.containsKey(member) && connectionMap.get(member).contains(name)) {
            	
            	teamCount ++;
            }
        }
        
        System.out.println(name + " is in a team with " + teamCount+ " individuals");
    }
    
    public static void printOutput(Map<String, Set<String>> adjList) {
    	
        Scanner scanner = new Scanner(System.in);
        
        // Is used for storing user input
        String emailAddress;
        
        do {
        	
            System.out.print(INPUT_PROMPT);
            
            emailAddress = scanner.nextLine();
            
            if (!emailAddress.equals("EXIT")) {
            	
                getInformation(emailAddress, adjList);
                
                getNumPeopleEmailedWith(emailAddress, adjList);
            }
            
        } while (!emailAddress.equals("EXIT"));
        // 
        scanner.close();
    }
    

}
