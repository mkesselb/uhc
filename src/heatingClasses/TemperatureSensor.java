package heatingClasses;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor {
	
	private static final double heatingPerTimestep = 1.25;
	
	private List<Double> temperatures;
	
	public TemperatureSensor(){
		this.temperatures = new ArrayList<Double>();
		this.temperatures.add(19 + Math.random()*3);
	}
	
	public List<Double> getTemperatures(){
		return this.temperatures;
	}
	
	public double getCurrentTemperature(){
		return this.temperatures.get(this.temperatures.size()-1);
	}
	
	public void computeNextTemperature(double fractionOpenWindows, double heatingTarget){
		//from previous temperature, heat into direction of heating target, factoring in the fraction of open windows
		double prevTemp = this.getCurrentTemperature();
		
		if(prevTemp == heatingTarget){
			temperatures.add(prevTemp);
			return;
		}
		
		double heatingDiff = prevTemp - heatingTarget;
		
		double newTemp;
		if(heatingDiff > 0){
			//too hot
			newTemp = prevTemp - fractionOpenWindows - (heatingPerTimestep/2);
			newTemp = (newTemp <= heatingTarget ? heatingTarget : newTemp);
		} else{
			//too cold, more heating
			newTemp = prevTemp - fractionOpenWindows + heatingPerTimestep;
			newTemp = (newTemp >= heatingTarget ? heatingTarget : newTemp);
		}
		
		temperatures.add(newTemp);
	}
}