package heatingClasses;

import java.util.List;

public class Conflicts {
	
	private int[][] conflictPlan = new int[7][8];
	
	public Conflicts(String structureName, List<String> conflicts){
		for(String s : conflicts){
			String[] p = s.split(",");
			if(p[2].equals(structureName)){
				conflictPlan[Integer.parseInt(p[0])][Integer.parseInt(p[1])] = 1;
			}
		}
	}
	
	//checks the index [i, j] to see whether the conflict shall be overridden
	public boolean checkOverride(int i, int j){
		return (conflictPlan[i][j] == 1);
	}
}