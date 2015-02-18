package heatingClasses;

import java.util.ArrayList;
import java.util.List;

public class Building extends Structure{
	
	@SuppressWarnings("unchecked")
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
		String[] columnNames = new String[] {"Floor", "Open windows"};
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

	@Override
	public List<String> getSuboptimalConditions() {
		List<String> suboptFloors = new ArrayList<String>();
		
		for(Structure s : this.subStructs){
			List<String> sub = s.getSuboptimalConditions();
			if(sub.size() > 0){
				suboptFloors.add(s.getClass().getSimpleName() + "-" + s.getName());
			}
		}
		
		return suboptFloors;
	}
}