package heatingClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter {
	
	private CsvImporter(){
		//hide constructor of class, offers only static methods
	}
	
	public static Building parseFromCSV(String csvPath){
		
		Building bd = null;
		String line = "";
		try(BufferedReader b = new BufferedReader(new FileReader(csvPath))){
			while((line = b.readLine()) != null){
				String[] p = line.split(",");
				if(line.startsWith("b")){
					//parse building
					bd = new Building(p[1], new ArrayList<Floor>());
				}
				if(line.startsWith("f")){
					//parse floor
					Floor f = new Floor(p[1], new ArrayList<Room>());
					bd.addFloor(f);
				}
				if(line.startsWith("r")){
					//parse room
					List<Window> ws = new ArrayList<Window>();
					for(int i = 0; i < Integer.parseInt(p[2]); i++){
						Window w = new Window("Window " + (i+1), false);
						ws.add(w);
					}
					Room r = new Room(p[1], ws);
					bd.getFloor(bd.getFloors().size()-1).addRoom(r);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bd;
	}
}