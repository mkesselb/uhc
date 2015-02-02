package heatingClasses;

import java.util.List;

public class Building extends Structure{
	
	private List<Floor> floors;
	
	public Building(String name, List<Floor> floors){
		super(name);
		this.floors = floors;
	}

	public List<Floor> getFloors(){
		return this.floors;
	}
	
	public Floor getFloor(int index){
		return this.floors.get(index);
	}
	
	public void addFloor(Floor f){
		this.floors.add(f);
	}
	
	public void nextModelStep(){
		
		//TODO: get heating target from special building plan
		//maybe heating plan as two-dim array for days (7days future) and 8blocks (per 3h)
		double heatingTarget = 22;
		for(Floor f : this.floors){
			f.computeNextTemperature(heatingTarget);
		}
	}
}