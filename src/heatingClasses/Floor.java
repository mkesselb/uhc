package heatingClasses;

import java.util.List;

public class Floor extends Structure{
	
	public Floor(String name, List<Room> rooms){
		super(name);
		this.subStructs = (List<Structure>)(List<?>) rooms;
	}
	
	public int getRoomNumberWindows(boolean isOpen){
		int ow = 0;
		
		for(Structure s : this.subStructs){
			Room r = (Room) s;
			ow += r.getNumberWindows(isOpen);
		}
		
		return ow;
	}
	
	public void computeNextTemperature(double heatingTarget){
		//TODO: get heating target from special floor plan
		for(Structure s : this.subStructs){
			Room r = (Room) s;
			r.computeNextTemperature(heatingTarget);
		}
	}
}