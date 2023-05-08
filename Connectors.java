import java.io.*;
import java.util.*;

public class Connectors {
    
    public Map<String, Set<String>> graph;
    
    public Map<String, Integer> dfsNum;
    
    public Map<String, Integer> back;
    
    public Set<String> connectors;
    
    public int num;
    
    public PrintWriter writer;
    
    public void findConnectors(Map<String, Set<String>> graph, String filename) {
    	
        this.graph = graph;
        
        dfsNum = new HashMap<>();
        
        back = new HashMap<>();
        
        connectors = new HashSet<>();
        
        num = 0;
        
        writer = null;
        
        if (filename != null) {
        	
            try {
            	
                writer = new PrintWriter(new FileWriter(filename));
                
            } catch (IOException e) {
            	
                e.printStackTrace();
            }
        }
        
        Set<String> visited = new HashSet<>();
        
        for (String v : graph.keySet()) {
        	
            if (!visited.contains(v)) {
            	
                dfs(v, v, visited);
            }
        }
        
        for (String v : connectors) {
            
            if (writer != null) {
            	// output them into a text file
                writer.println(v);
            }
        }
        
        if (writer != null) {
        	
            writer.close();
        }
    }
    
    // Applying depth-first search
    public void dfs(String v, String u, Set<String> visited) {
    	
        visited.add(v);
        
        dfsNum.put(v, num);
        
        back.put(v, num);
        
        num++;
        
        int children = 0;
        
        boolean isConnector = false;
        
        Set<String> neighbors = ReadDirectory.connectionMap.get(v); // Get the set of neighbors for this vertex
        
        if (neighbors == null) {
        	
            return; // If the set is null, return immediately to avoid a NullPointerException
        }
        for (String w : neighbors) {
        	
            if (!visited.contains(w)) {
            	
                children++;
                
                dfs(w, v, visited);
                
                if (dfsNum.get(v) <= back.get(w)) {
                	
                    isConnector = true;
                    
                } else {
                	// Only store the minimu
                    back.put(v, Math.min(back.get(v), back.get(w)));
                }
                
            } else if (!w.equals(u)) {
            	
                back.put(v, Math.min(back.get(v), dfsNum.get(w)));
            }
        }
        if ((u.equals(v) && children > 1) || (!u.equals(v) && isConnector)) {
        	
            connectors.add(v);
        }
        
    }
    
    // Create adjacency list to store their relation
    public void createConnectorList(Map<String, Set<String>> senderToRecepientSet) {
    	
    	 for (String key : ReadDirectory.connectionMap.keySet()) {
         	
             Set<String> neighbors = ReadDirectory.connectionMap.get(key);
             
             // Creating new hashset
             senderToRecepientSet.put(key, new HashSet<>(neighbors));
         }
    	 
    }

}
