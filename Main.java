import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Connectors myConnectors = new Connectors();
			
		File maildir = new File(args[0]);
		
		if(maildir.exists()) {
					
			String fileName = args[1];
			
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));
			
			ReadDirectory.processDirectory(maildir);
	        
	    	ReadDirectory.linkConnection(ReadDirectory.connectionList);
	    	
	    	Map<String, Set<String>> adjList = new HashMap<>();
	    	
	        myConnectors.createConnectorList(adjList);
	    	
	    	List<String> connectors = new ArrayList<>();
	    	
	    	myConnectors.findConnectors(adjList, args[1]);
	    	
	    	DetailOfIndividual.printOutput(adjList);
	    	
	       // Print the connectors to stdout
	    	for (String connectorNames : connectors) {
		    	
		    	writer.println(connectorNames);

			  }
	    	
	    	writer.close();
			
		}else {
			
			System.out.println("Directory doesn't exist");
		}

    }

}
