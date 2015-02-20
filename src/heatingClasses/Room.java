package heatingClasses;

import java.util.ArrayList;
import java.util.List;

public class Room extends Structure{

	//needs temp-sensor
	private TemperatureSensor tempSensor;
	
	@SuppressWarnings("unchecked")
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
		return Math.round(this.tempSensor.getCurrentTemperature());
	}
	
	public void computeNextTemperature(double heatingTarget){
		//TODO: get heating target from special room plan
		this.tempSensor.computeNextTemperature((double)this.getNumberWindows(true) / this.getNumberWindows(false), 
				heatingTarget);
	}
	
	public String[] getOverviewColumnNames(){
		String[] columnNames = new String[] {"Window", "Status"};
		return columnNames;
	}
	
	public Object[][] getOverviewTableContent(){
		Object[][] tableContent = new Object[this.subStructs.size()][2];
		
		for (int i = 0; i < this.subStructs.size(); i++){
			tableContent[i][0]= this.subStructs.get(i).getName();
			if(((Window) this.subStructs.get(i)).isOpen()){
				tableContent[i][1] = "open";
			} else {
				tableContent[i][1] = "closed";
			}
		}
		
		return tableContent;
	}

	@Override
	public List<String> getSuboptimalConditions() {
		List<String> subopt = new ArrayList<String>();
		
		//1st check open windows
		for(Structure s : this.subStructs){
			Window w = (Window) s;
			if(w.isOpen()){
				subopt.add(w.getName() + " open");
			}
		}
		
		//2nd check jumps in programmed heating
		for(int i = 0; i < heatingPlan.length; i++){
			for(int j = 0; j < heatingPlan[i].length; j++){
				if(heatingPlan[i][j] != -1 
						&& j < (heatingPlan[i].length - 1) 
						&& Math.abs(heatingPlan[i][j] - heatingPlan[i][j+1]) > 9){
					String suboptString = "Jump in Heating: " + Structure.heatingPlanColumns[i+1] + "," 
							+ Structure.heatingPlanTimeBlocks[j] + " / " 
							+ Structure.heatingPlanTimeBlocks[j+1];
					subopt.add(suboptString);
				}
			}
		}
		
		return subopt;
	}
}