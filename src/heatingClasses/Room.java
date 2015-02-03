package heatingClasses;

import java.util.List;

public class Room extends Structure{

	//needs temp-sensor
	private TemperatureSensor tempSensor;
	
	public Room(String name, List<Window> windows){
		super(name);
		this.subStructs = (List<Structure>)(List<?>) windows;
		this.tempSensor = new TemperatureSensor();
	}
	
	public int getNumberWindows(boolean isOpen){
		int ow = 0;
		
		for(Structure s : this.subStructs){
			Window w = (Window) s;
			if(w.isOpen() == isOpen){
				ow++;
			}
		}
		
		return ow;
	}
	
	public double getCurrentTemperature(){
		return this.tempSensor.getCurrentTemperature();
	}
	
	public void computeNextTemperature(double heatingTarget){
		//TODO: get heating target from special room plan
		this.tempSensor.computeNextTemperature((double)this.getNumberWindows(true) / this.getNumberWindows(false), 
				heatingTarget);
	}
}