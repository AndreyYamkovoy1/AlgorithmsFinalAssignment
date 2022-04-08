import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Scanner;
import java.io.File; 
import java.io.FileWriter; 
public class FinalAssignment {
	
	
	
	int numTransfers = 0;
	int numStops = 0;
	int largestID = 0;
	
	FinalAssignment(String stopsFilename,String stopTimesFilename,String transfersFilename){	
		this.StopsList = getStops(this.StopsList,stopsFilename);	
		//this.StopsTimesList = getStopsTimes(this.StopsTimesList,stopTimesFilename);
		this.TransfersList = getTransfers(this.TransfersList,transfersFilename);
	}
	
	public class Stop{
		//stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station
		int stopOrderFromInput;
		int stop_id;
		int stop_code;
		String stop_name;
		String stop_desc;
		double stop_lat;
		double stop_lon;
		String zone_id;
		String stop_url;
		int location_type;
		String parent_station;
		
		
		Stop(int stopOrderFromInput,int stop_id, int stop_code,String stop_name,String stop_desc,double stop_lat,double stop_lon,
				String zone_id,String stop_url, int location_type,String parent_station)
		{
			this.stopOrderFromInput = stopOrderFromInput;
			this.stop_id = stop_id;
			this.stop_code = stop_code;
			this.stop_name = stop_name;
			this.stop_desc = stop_desc;
			this.stop_lat = stop_lat;
			this.stop_lon = stop_lon;
			this.zone_id = zone_id;
			this.stop_url = stop_url;
			this.location_type = location_type;
			this.parent_station = parent_station;
			
		}
	}
	public class StopTimes{
		//trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled
		int trip_id;
		String arrival_time;
		String departure_time;
		int stop_id;
		int stop_sequence;
		String stop_headsign;
		int pickup_type;
		int dropoff_type;
		double shape_dist_traveled;
		
		StopTimes(int trip_id,String arrival_time,String departure_time,int stop_id,int stop_sequence,String stop_headsign,int pickup_type,int dropoff_type,double shape_dist_traveled){
			this.trip_id = trip_id;
			this.arrival_time = arrival_time;
			this.departure_time = departure_time;
			this.stop_id = stop_id;
			this.stop_sequence = stop_sequence;
			this.stop_headsign = stop_headsign;
			this.pickup_type = pickup_type;
			this.dropoff_type = dropoff_type;
			this.shape_dist_traveled = shape_dist_traveled;
		}
	}
	public class Transfer{
		//from_stop_id,to_stop_id,transfer_type,min_transfer_time
		int from_stop_id;
		int to_stop_id;
		int transfer_type;
		int transfer_time;
		
		
		Transfer(int from_stop_id,int to_stop_id,int transfer_type,int transfer_time){
			this.from_stop_id = from_stop_id;
			this.to_stop_id = to_stop_id;
			this.transfer_type = transfer_type;
			this.transfer_time = transfer_time;
		}
	}
	public ArrayList<Transfer> getTransfers(ArrayList<Transfer> transfers,String transfersFilename){
		try {
			FileInputStream fstream = new FileInputStream(transfersFilename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int fileLineNum = 0;
			// Loop that reads every line, breaks it into different pieces and assigns to stop class values
			while((strLine = br.readLine()) != null) {
				System.out.println(fileLineNum);
				if(fileLineNum > 0) {
					int new_from_stop_id;
					int new_to_stop_id;
					int new_transfer_type;
					int new_transfer_time;
					//String resultStr = strLine.replaceAll("[ ]+", " ").trim();
					String[] values = strLine.split(",");
					if(values[0].equals(" ")){
						new_from_stop_id = -1;
					}
					else {
						new_from_stop_id = Integer.parseInt(values[0]);
					}
					
					if(values[1].equals(" ")){
						new_to_stop_id = -1;
					}
					else {
						new_to_stop_id = Integer.parseInt(values[1]);
					}
				
					if(values[2].equals(" ")){
						new_transfer_type = -1;
					}
					else {
						new_transfer_type = Integer.parseInt(values[2]);
					}
					
					if(values.length == 3) {
						new_transfer_time = -1;
					}
					else {
						new_transfer_time = Integer.parseInt(values[3]);
					}
					Transfer newTransfer = new Transfer(new_from_stop_id,new_to_stop_id,new_transfer_type,new_transfer_time);
					System.out.println("From stop id: "+new_from_stop_id+" , To stop id: "+new_to_stop_id+" , Transfer type: "+new_transfer_type+" , transfer time: "+
							new_transfer_time);
					transfers.add(newTransfer);
				}
				else if(fileLineNum == 0){
					
				}
				fileLineNum++;
				
			}
			numTransfers = fileLineNum-1;
			System.out.println("Number of transfers(edges): "+numTransfers);
			in.close();
		}
		//Error catcher
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		
		
		
		
		return transfers;
	}
	public ArrayList<Stop> getStops(ArrayList<Stop> stops,String stopsFileName){
		try {
			FileInputStream fstream = new FileInputStream(stopsFileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int fileLineNum = 0;
			int i =0;
			// Loop that reads every line, breaks it into different pieces and assigns to stop class values
			while((strLine = br.readLine()) != null) {
				System.out.println(fileLineNum);
				if(fileLineNum > 0) {
					
					int new_stop_id;
					int new_stop_code;
					String new_stop_name;
					String new_stop_desc;
					double new_stop_lat;
					double new_stop_lon;
					String new_zone_id;
					String new_stop_url;
					int new_location_type;
					String new_parent_station;
					//String resultStr = strLine.replaceAll("[ ]+", " ").trim();
					String[] values = strLine.split(",");
					if(values[0].equals(" ")){
						new_stop_id = -1;
					}
					else {
						new_stop_id = Integer.parseInt(values[0]);
						if(new_stop_id > largestID){
							largestID = new_stop_id;
						}
					}
					
					if(values[1].equals(" ")){
						new_stop_code = -1;
					}
					else {
						new_stop_code = Integer.parseInt(values[1]);
					}
					
					new_stop_name = values[2];
					new_stop_desc = values[3];
					if(values[4].equals(" ")){
						new_stop_lat = -1;
					}
					else {
						new_stop_lat = Double.parseDouble(values[5]);
					}
					if(values[5].equals(" ")){
						new_stop_lon = -1;
					}
					else {
						new_stop_lon = Double.parseDouble(values[5]);
					}
					new_zone_id = values[6];
					new_stop_url = values[7];
					if(values[8].equals(" ")){
						new_location_type = -1;
					}
					else {
						new_location_type = Integer.parseInt(values[8]);
					}
					if(values.length == 10) {
						new_parent_station = values[9];
					}
					else {
						new_parent_station = " ";
					}
					Stop newStop = new Stop(i-1,new_stop_id,new_stop_code,new_stop_name,new_stop_desc,new_stop_lat,new_stop_lon,new_zone_id,new_stop_url,
							new_location_type,new_parent_station);
					System.out.println("Stop id: "+new_stop_id+" , Stop code: "+new_stop_code+" , Stop name: "+new_stop_name+" , Stop desc: "+
							new_stop_desc+" , Stop lat: "+new_stop_lat+" , Stop lon: "+new_stop_lon+" , zone id: "+new_zone_id+" , stop url"+
							new_stop_url+" , location type: "+new_location_type+" , Parent station: "+new_parent_station);
					stops.add(newStop);
				}
				else if(fileLineNum == 0){
					
				}
				i++;
				fileLineNum++;
				
			}
			numStops = fileLineNum-1;
			System.out.println("Number of Stops(Vertex): "+numStops);
			in.close();
		}
		//Error catcher
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		
		
		return stops;
		
	}
	ArrayList<Stop> StopsList = new ArrayList<Stop>();
	ArrayList<StopTimes> StopsTimesList = new ArrayList<StopTimes>();
	ArrayList<Transfer> TransfersList = new ArrayList<Transfer>();
	public ArrayList<StopTimes> getStopsTimes(ArrayList<StopTimes> stopTimes,String stopTimesFileName){
		try {
			FileInputStream fstream = new FileInputStream(stopTimesFileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int fileLineNum = 0;
			
			// Loop that reads every line, breaks it into different pieces and assigns to stop class values
			while((strLine = br.readLine()) != null) {
				System.out.println(fileLineNum);
				if(fileLineNum > 0) {
					int new_trip_id;
					String new_arrival_time;
					String new_departure_time;
					int new_stop_id;
					int new_stop_sequence;
					String new_stop_headsign;
					int new_pickup_type;
					int new_dropoff_type;
					double new_shape_dist_traveled;
					//String resultStr = strLine.replaceAll("[ ]+", " ").trim();
					String[] values = strLine.split(",");
					if(values[0].equals(" ")){
						new_trip_id = -1;
					}
					else {
						new_trip_id = Integer.parseInt(values[0]);
					}
					new_arrival_time = values[1];
					new_departure_time = values[2];
					if(values[3].equals(" ")){
						new_stop_id = -1;
					}
					else {
						new_stop_id = Integer.parseInt(values[3]);
					}
					if(values[4].equals(" ")){
						new_stop_sequence = -1;
					}
					else {
						new_stop_sequence = Integer.parseInt(values[4]);
					}
					new_stop_headsign = values[5];
					
					if(values[6].equals(" ")){
						new_pickup_type = -1;
					}
					else {
						new_pickup_type = Integer.parseInt(values[6]);
					}
					if(values[7].equals(" ")){
						new_dropoff_type = -1;
					}
					else {
						new_dropoff_type= Integer.parseInt(values[7]);
					}
					if(values.length == 9) {
						new_shape_dist_traveled = Double.parseDouble(values[8]);
					}
					else {
						new_shape_dist_traveled = -1;
					}
					StopTimes newStopTimes = new StopTimes(new_trip_id,new_arrival_time,new_departure_time,new_stop_id,new_stop_sequence,new_stop_headsign,new_pickup_type,new_dropoff_type,new_shape_dist_traveled);
					System.out.println("Trip id: "+new_trip_id+" , Arrival time: "+new_arrival_time+" , Departure time: "+new_departure_time+" , Stop id: "+
							new_stop_id+" , Stop sequence: "+new_stop_sequence+" , Stop headsign: "+new_stop_headsign+" , pickup type: "+new_pickup_type+" , dropoff type: "+
							new_dropoff_type+" , shape dist travelled: "+new_shape_dist_traveled);
					stopTimes.add(newStopTimes);
				}
				else if(fileLineNum == 0){
					
				}
				fileLineNum++;
				
			}
			in.close();
		}
		//Error catcher
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		
		
		return stopTimes;
		
	}
	
	
	
	
	
	public void initializeMatrix(int numStops,double[][] graph,double[][] distance,int[][] next){
		System.out.println("Initializing matrix");
		for(int i = 0; i < numStops; i++){
			for(int j = 0; j < numStops; j++){
				double val = graph[i][j];
				distance[i][j] = val;
				if (graph[i][j] == Double.MAX_VALUE) {
					next[i][j] = -1;
				}  
				else {
					next[i][j] = j;
				}
	           
			}
		}
		System.out.println("Matrix end");
	}		
	public class ShortestPathResult{
		Vector<Integer> path;
		double sum;
		ShortestPathResult(Vector<Integer> path,double sum){
			this.path = path;
			this.sum = sum;
		}
	}
	public ShortestPathResult getShortestPath(int u , int v,int[][] next,double[][] distance){
		System.out.println("Getting shortest path");
		if (next[u][v] == -1)
	        return null;
		Vector<Integer> path = new Vector<Integer>();
		double sum = distance[u][v];
		 int id = 0;
		 id = StopsList.get(u).stop_id;
	    path.add(id);
	   
	    while (u != v)
	    {
	    	
	        u = next[u][v];
	        for(int i = 0;i<numStops;i++) {
	        	id = StopsList.get(u).stop_id;
	        }
	        path.add(id);
	    }
	   
	    ShortestPathResult result = new ShortestPathResult(path,sum);
	    return result;
	}
	 void floydWarshall(int V,double[][] distance,int[][] next)
	 {
		 System.out.print("Calculating floyd warshall algh");
	     for(int k = 0; k < V; k++){
	    	 System.out.println(k);
	    	 for(int i = 0; i < V; i++){
	    		 for(int j = 0; j < V; j++){
	    			 if(distance[i][k] == Double.MAX_VALUE || distance[k][j] == Double.MAX_VALUE){
	    				 continue;
	    			 } 
	    			 if(distance[i][j] > distance[i][k] + distance[k][j]){
	    				 distance[i][j] = distance[i][k] + distance[k][j];
	    				 next[i][j] = next[i][k];
	    			 }
	    		 }
	    	 }
	     }
	 }
	public double calcWeight(boolean fromTransfers,boolean fromStopTimes,Transfer transfer){
		double result = 0;
		if(fromTransfers){
			if(transfer.transfer_type == 0){
				result = 2;
			}
			else if(transfer.transfer_type == 2){
				result = transfer.transfer_time/100;
			}
		}
		if(fromStopTimes){
			result = 1;
		}
		return result;
	}
	public double[][] makeGraph(int size,double[][] graph){
		
		
		int x = 0;
		int y = 0;
		double weight = 0;
		//Populate 2d array with values from input
		for(int i = 0;i < numStops;i++) {
			for(int j = 0;j < numStops;j++){
				graph[i][j] = Double.MAX_VALUE;
				
			}
		}
		for(int i = 0;i < numStops;i++) {
			
				graph[i][i] = 0;
			
		}
		
    	for(int i = 0;i<numTransfers;i++){
    		x = TransfersList.get(i).from_stop_id;
    		y = TransfersList.get(i).to_stop_id;
    		int m = 0;
    		int n = 0;
    		for(int j = 0;j<numStops;j++){
    			if(StopsList.get(j).stop_id == x) {
    				m = StopsList.get(j).stopOrderFromInput;
    			}
    			if(StopsList.get(j).stop_id == y) {
    				n = StopsList.get(j).stopOrderFromInput;
    			}
    		}
    		
    		weight = calcWeight(true,false,TransfersList.get(i));
    		System.out.println("m: "+m+" n: "+n+" weight: "+weight);
    		System.out.println("x: "+x+" y: "+ y);
    		graph[m][n] = weight;
    	}
    			
		return graph;
	}
	void printPath(ShortestPathResult result)
	{
	    int n = result.path.size();
	    for(int i = 0; i < n - 1; i++)
	    System.out.print(result.path.get(i) + " -> ");
	    System.out.print(result.path.get(n - 1) + "\n");
	    System.out.println("Path cost: "+result.sum);
	}
	/*
	public int getStopIndex(String request){
		int result = 0;
		System.out.print(request);
		if(input.hasNextLine()) {
			String inputString = input.nextLine();
			System.out.print(inputString);
		}
		*/
		public int getStopIndex(int inputId) {
		int index = 0;
		for(int i = 0;i< numStops;i++) {
			if(StopsList.get(i).stop_id == inputId) {
				index = StopsList.get(i).stopOrderFromInput;
				return index;
			}
		}
		return index;
	}
	public String createFile(){
		String newFileName = "";
		try {
			newFileName = "results.txt";
			File newFile = new File(newFileName);
			if (newFile.createNewFile()) {
		        System.out.println("File created: " + newFile.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		}
		catch(IOException e){
			System.out.println("Error with creating file");
		}
		return newFileName;
	}
	public void writeToFile(String newFileName){
		try {
    		FileWriter writer = new FileWriter(newFileName);
    		writer.write("test");
    		writer.close();
    		System.out.println("Wrote to the file");
    	}
    	catch(IOException e){
    		System.out.println("Error with writer");
    	}
	}
	public static void main(String[] args) throws FileNotFoundException {
    	FinalAssignment test = new FinalAssignment("smallStops","stop_times.txt","smallTransfers");
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Choose which function you want to use: \n Input 1 for ShortestPath between two points \n Input 2 to Search for bus stop by name \n Input 3 to search for all trips with a given arrival time");
    	if(input.nextInt() == 1) {
    		double[][] graph = new double[test.numStops][test.numStops];
        	graph = test.makeGraph(test.numStops,graph);
        	double[][] distance = new double [test.numStops][test.numStops];
        	int[][] next = new int [test.numStops][test.numStops];
        	test.initializeMatrix(test.numStops, graph,distance,next);
        	test.floydWarshall(test.numStops,distance,next);
        	
        	String newFile = test.createFile();
        	test.writeToFile(newFile);
        	
        	
        	
        	
        	ShortestPathResult result;
        	int inputFrom = 0;
        	int inputTo = 0;
        	System.out.print("Input two stops one at a time");
        	inputFrom = input.nextInt();
        	inputTo = input.nextInt();
        	
        	//inputFrom = 30;
        	//inputTo = 20;
        	int vertex1 = test.getStopIndex(inputFrom);
        	int vertex2 = test.getStopIndex(inputTo);
        	System.out.println("Shortest path from "+inputFrom+ " to "+inputTo);
        	result = test.getShortestPath(vertex1,vertex2,next,distance);
        	test.printPath(result);
        	
    	}
    	
    	input.close();
    }
}
