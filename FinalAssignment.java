import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FinalAssignment {
	ArrayList<Stop> StopsList = new ArrayList<Stop>();
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
	
	
	
	FinalAssignment(String stopsFilename){
		try {
			FileInputStream fstream = new FileInputStream(stopsFilename);
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
					StopsList.add(newStop);
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
		
		
	}
	
		
	
	public static void main(String[] args) throws FileNotFoundException {
    	FinalAssignment test = new FinalAssignment("stops.txt");
    	System.out.print("Finished reading stops");
    }
}
