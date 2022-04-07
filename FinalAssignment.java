import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FinalAssignment {
	
	public class Stop{
		//stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station
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
		
		
		Stop(int stop_id, int stop_code,String stop_name,String stop_desc,double stop_lat,double stop_lon,
				String zone_id,String stop_url, int location_type,String parent_station)
		{
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
					Stop newStop = new Stop(new_stop_id,new_stop_code,new_stop_name,new_stop_desc,new_stop_lat,new_stop_lon,new_zone_id,new_stop_url,
							new_location_type,new_parent_station);
					System.out.println("Stop id: "+new_stop_id+" , Stop code: "+new_stop_code+" , Stop name: "+new_stop_name+" , Stop desc: "+
							new_stop_desc+" , Stop lat: "+new_stop_lat+" , Stop lon: "+new_stop_lon+" , zone id: "+new_zone_id+" , stop url"+
							new_stop_url+" , location type: "+new_location_type+" , Parent station: "+new_parent_station);
					stops.add(newStop);
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
		
		
		return stops;
		
	}
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
	FinalAssignment(String stopsFilename,String stopTimesFilename){
		//ArrayList<Stop> StopsList = new ArrayList<Stop>();
		//StopsList = getStops(StopsList,stopsFilename);
		//ArrayList<StopTimes> StopsTimesList = new ArrayList<StopTimes>();
		//StopsTimesList = getStopsTimes(StopsTimesList,stopTimesFilename);
		ArrayList<Transfer> TransfersList = new ArrayList<Transfer>();
		TransfersList = getTransfers(TransfersList,"transfers.txt");
	}
	
		
	
	public static void main(String[] args) throws FileNotFoundException {
    	FinalAssignment test = new FinalAssignment("stops.txt","stop_times.txt");
    	System.out.print("Finished reading stops");
    }
}
