import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;

public class ReadDirectory {
	
	public static List<List<String>> connectionList = new ArrayList<>();

	private static List<String> receipientsList = new ArrayList<>();
	
	public static Map<String, Set<String>> connectionMap = new HashMap<>();

	private static void getEmail(String filePath) {
		
		boolean reachedTo = false;
		
		// Start reading the file
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	    	
	        String line;
	        
	        while ((line = reader.readLine()) != null) {
	        	
	        	if(line.startsWith("From: ")) {
	        		
	        		// Only extract the part excluding "From: "
	        		line = line.substring(6);
	        		
	        		// Removing white space
	        		String trimmedString = line.trim();
	        		
	        		storeEmailNames(trimmedString, receipientsList);
	        		
	        	}
	        	
	        	else if(line.startsWith("To: ")) {
	        		
	        		// When we are reading the part after first line of email list
	        		reachedTo = true;
	        		
	        		line = line.substring(4);
	        		
	        		// Removing trailing part
	        		String[] names = line.trim().split("\\s*,\\s*");
	        		
	        		for(String name : names) {
	        			
        				storeEmailNames(name, receipientsList);
	        				
	        		}
	        		
	        	}
	        	
	        	// Stop reading the part at the line that starts with subject
	        	if(line.startsWith("Subject: ")) {
	        		
	        		reachedTo = false;
	        		
	        		break;
	        		
	        	}
	        	
	        	// In between "To:" and "Subject:"
	        	if (reachedTo) {
	        		
	        		String[] names = line.trim().split("\\s*,\\s*");
	        		
	        		for(String name : names) {
	        			
	        			storeEmailNames(name, receipientsList);
	        				
	        		}
				}
	        }
	        
	        // Resetting the receipientslist avoid repetition
    		connectionList.add(new ArrayList<>(receipientsList));
    		
    		receipientsList.clear();
    		
	    } catch (IOException e) {
	    	
	        e.printStackTrace();
	    }
	    
	}

	// Reading all the text files.
    public static void processDirectory(File directory) {
    	
        if (directory.isDirectory()) {
        	
            File[] files = directory.listFiles();
            
            if (files != null) {
            	
                for (File file : files) {
                	
                    if (file.isDirectory()) {
                    	
                        // Recursively call this function to process subdirectories
                        processDirectory(file);
                        
                    } else if (file.isFile()) {
                    	
                        // Process the text file here
                       getEmail(file.getAbsolutePath());
                    }
                }
            }
        }
    }
    
    // Extracts the part before @
    private static void storeEmailNames(String line, List<String> storeHere) {
    	
		storeHere.add(line);
    		
    }
    public static void linkConnection(List<List<String>> connectionList) {
    	
    	Map<String, Set<String>> map = new HashMap<>();

    	for (List<String> list : connectionList) {
    		
    		// Jump to next element
    	    if (list.isEmpty()) {
    	    	
    	        continue;
    	    }
    	    // The first element is the sender
    	    String key = list.get(0);
    	    
    	    map.computeIfAbsent(key, k -> new HashSet<>()).addAll(list.subList(1, list.size()));
    	}

    	connectionMap = map;
    }
    
}
