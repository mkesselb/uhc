package heatingClasses;

import java.util.List;

public class Room extends Structure{

	//needs windows and temp-sensor
	private List<Window> windows;
	
	private TemperatureSensor tempSensor;
	
	public Room(String name, List<Window> windows){
		super(name);
		this.windows = windows;
		this.tempSensor = new TemperatureSensor();
	}
	
	public List<Window> getWindows(){
		return this.windows;
	}
	
	public int getNumberWindows(boolean isOpen){
		int ow = 0;
		
		for(Window w : this.windows){
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