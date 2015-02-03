package heatingClasses;

import java.util.List;

public class Building extends Structure{
	
	public Building(String name, List<Floor> floors){
		super(name);
		this.subStructs = (List<Structure>)(List<?>) floors;
	}
	
	public void nextModelStep(){
		
		//TODO: get heating target from special building plan
		//maybe heating plan as two-dim array for days (7days future) and 8blocks (per 3h)
		double heatingTarget = 22;
		for(Structure s : this.subStructs){
			Floor f = (Floor) s;
			f.computeNextTemperature(heatingTarget);
		}
	}
}