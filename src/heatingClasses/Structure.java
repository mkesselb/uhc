package heatingClasses;

import java.util.ArrayList;
import java.util.List;

public abstract class Structure {
	
	public static final String[] heatingPlanColumns = {"Time blocks", "Mo", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
	
	public static final String[] heatingPlanTimeBlocks = {"0-3", "3-6", "6-9", "9-12", "12-15", "15-18", "18-21", "21-24"};
	
	private String name;
	
	protected List<Structure> subStructs;
	
	protected int[][] heatingPlan = new int[7][8];
	
	public Structure(String name){
		this.name = name;
		for(int i = 0; i < heatingPlan.length; i++){
			for(int j = 0; j < heatingPlan[i].length; j++){
				//indicating no heating is scheduled
				heatingPlan[i][j] = -1;
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Structure> getSubStructs(){
		return this.subStructs;
	}
	
	public Structure getSubStruct(int index){
		return this.subStructs.get(index);
	}
	
	public void addSubStruct(Structure struct){
		this.subStructs.add(struct);
	}
	
	public int findSubStruct(Structure struct){
		for(Structure s : this.subStructs){
			if(s.equals(struct)){
				return subStructs.indexOf(s);
			}
		}
		
		return -1;
	}
	
	public String toString(){
		return this.name;
	}
	
	public List<String> getDefaultHeatingConflicts(int temp){
		List<String> conflicts = new ArrayList<String>();
		
		for(int i = 0; i < heatingPlan.length; i++){
			conflicts.addAll(getHeatingConflicts(i, 0, 7, temp));
		}
		
		return conflicts;
	}
	
	//this methods returns the conflicts that are present.
	//after calling this method, the user has to decide which of the conflicts shall be
	//overridden and which not
	public List<String> getHeatingConflicts(int dayIndex, int fromCell, int toCell, int temp){
		List<String> conflicts = new ArrayList<String>();
		
		for(int j = fromCell; j <= toCell; j++){
			if(heatingPlan[dayIndex][j] != -1 && heatingPlan[dayIndex][j] != temp){
				conflicts.add(dayIndex + "," + j + "," + this.getClass().getSimpleName() + "-" + this.toString() + "," + heatingPlan[dayIndex][j]);
			}
		}
		
		for(Structure s : this.subStructs){
			if(!(s instanceof Window)){
				conflicts.addAll(s.getHeatingConflicts(dayIndex, fromCell, toCell, temp));
			}
		}
		
		return conflicts;
	}
	
	public void applyDefaultHeatingModel(int temp, List<String> conflictOverride){
		for(int i = 0; i < heatingPlan.length; i++){
			applyHeatingModel(i, 0, 7, temp, conflictOverride);
		}
	}
	
	//at this point, conflictOverride only contains the conflicts that shall be overridden
	// -> that is, after the user was presented with the list of check boxes 
	public void applyHeatingModel(int dayIndex, int fromCell, int toCell,
			int temp, List<String> conflictOverride){
		Conflicts c = new Conflicts(this.toString(), conflictOverride);
		
		for(int j = fromCell; j <= toCell; j++){
			if(heatingPlan[dayIndex][j] == -1){
				heatingPlan[dayIndex][j] = temp;
			} else{
				//check if conflict shall be overridden or not
				if(c.checkOverride(dayIndex, j, this.getClass().getSimpleName() + "-" + this.toString())){
					heatingPlan[dayIndex][j] = temp;
				}
			}
		}
		
		//also apply heating model on sub-structs
		for(Structure s : this.subStructs){
			if(!(s instanceof Window)){
				s.applyHeatingModel(dayIndex, fromCell, toCell, temp, conflictOverride);
			}
		}
	}
	
	public Object[][] getHeatingPlan(){
		Object[][] plan = new Object[8][10];
		
		for (int i = 0; i<heatingPlan.length; i++){
			for(int j = 0; j<heatingPlan[i].length; j++){
				if(heatingPlan[i][j] == -1){
					plan[j][i+1] = "-";
				} else {
					plan[j][i+1] = heatingPlan[i][j];
				}
			}
		}
		for(int i = 0; i < plan.length; i++){
			plan[i][0] = Structure.heatingPlanTimeBlocks[i];
		}
		
		return plan;
	}
	
	abstract public String[] getOverviewColumnNames();
	
	abstract public Object[][] getOverviewTableContent();
	
	abstract public List<String> getSuboptimalConditions();
}