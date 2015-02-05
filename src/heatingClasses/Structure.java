package heatingClasses;

import java.util.ArrayList;
import java.util.List;

public abstract class Structure {
	
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
	
	public List<String> getDefaultHeatingConflicts(){
		List<String> conflicts = new ArrayList<String>();
		
		for(int i = 0; i < heatingPlan.length; i++){
			conflicts.addAll(getHeatingConflicts(i, 0, 8));
		}
		
		return conflicts;
	}
	
	//this methods returns the conflicts that are present.
	//after calling this method, the user has to decide which of the conflicts shall be
	//overridden and which not
	public List<String> getHeatingConflicts(int dayIndex, int fromCell, int toCell){
		List<String> conflicts = new ArrayList<String>();
		
		for(int j = fromCell; j < toCell; j++){
			if(heatingPlan[dayIndex][j] != -1){
				conflicts.add(dayIndex + "," + j + "," + this.toString() + "," + heatingPlan[dayIndex][j]);
			}
		}
		
		return conflicts;
	}
	
	public void applyDefaultHeatingModel(int temp, List<String> conflictOverride){
		for(int i = 0; i < heatingPlan.length; i++){
			applyHeatingModel(i, 0, 8, temp, conflictOverride);
		}
	}
	
	//at this point, conflictOverride only contains the conflicts that shall be overridden
	// -> that is, after the user was presented with the list of check boxes 
	public void applyHeatingModel(int dayIndex, int fromCell, int toCell,
			int temp, List<String> conflictOverride){
		Conflicts c = new Conflicts(this.toString(), conflictOverride);
		
		for(int j = fromCell; j < toCell; j++){
			if(heatingPlan[dayIndex][j] == -1){
				heatingPlan[dayIndex][j] = temp;
			} else{
				//check if conflict shall be overridden or not
				if(c.checkOverride(dayIndex, j)){
					heatingPlan[dayIndex][j] = temp;
				}
			}
		}
	}
	
	public int[][] getHeatingPlan(){
		return this.heatingPlan;
	}
}