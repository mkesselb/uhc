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
	
	public String[] getOverviewColumnNames(){
		String[] columnNames = new String[] {"Room","Temperature", "Open windows"};
		return columnNames;
	}
	
	public Object[][] getOverviewTableContent(){
		Object[][] tableContent = new Object[this.subStructs.size()][3];
		
		for (int i = 0; i < this.subStructs.size(); i++){
			tableContent[i][0]= this.subStructs.get(i).toString();	
			tableContent[i][1] = ((Room) this.subStructs.get(i)).getCurrentTemperature();
			tableContent[i][2] = ((Room) this.subStructs.get(i)).getNumberWindows(true)+"/"+(((Room) this.subStructs.get(i)).getNumberWindows(true)+((Room) this.subStructs.get(i)).getNumberWindows(false));
		}
		
		return tableContent;
	}
	
}