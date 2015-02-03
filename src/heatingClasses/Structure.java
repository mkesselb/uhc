package heatingClasses;

import java.util.List;

public abstract class Structure {
	
	private String name;
	
	protected List<Structure> subStructs;
	
	protected int[][] heatingPlan = new int[7][8];
	
	public Structure(String name){
		this.name = name;
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
}