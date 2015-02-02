package heatingClasses;

import java.util.List;

public class Floor extends Structure{
	
	private List<Room> rooms;
	
	public Floor(String name, List<Room> rooms){
		super(name);
		this.rooms = rooms;
	}

	public List<Room> getRooms(){
		return this.rooms;
	}
	
	public void addRoom(Room r){
		this.rooms.add(r);
	}
	
	public int getRoomNumberWindows(boolean isOpen){
		int ow = 0;
		
		for(Room r : this.rooms){
			ow += r.getNumberWindows(isOpen);
		}
		
		return ow;
	}
	
	public void computeNextTemperature(double heatingTarget){
		//TODO: get heating target from special floor plan
		for(Room r : this.rooms){
			r.computeNextTemperature(heatingTarget);
		}
	}
}