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

	public String[] getOverviewColumnNames(){
		String[] columnNames = new String[] {"floor", "window status"};
		return columnNames;
	}
	
	public Object[][] getOverviewTableContent(){
		Object[][] tableContent = new Object[this.subStructs.size()][2];
		
		for (int i = 0; i < this.subStructs.size(); i++){
			tableContent[i][0]= this.subStructs.get(i).toString();	
			tableContent[i][1] = ((Floor) this.subStructs.get(i)).getRoomNumberWindows(true)+"/"+(((Floor) this.subStructs.get(i)).getRoomNumberWindows(true)+((Floor) this.subStructs.get(i)).getRoomNumberWindows(false));
		}
		
		return tableContent;
	}
}