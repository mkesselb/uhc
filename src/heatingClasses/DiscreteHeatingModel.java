package heatingClasses;

public class DiscreteHeatingModel implements HeatingModel{
	
	//this class models the heating in discrete steps of 3h blocks
	private Building rootStructure;
	
	public DiscreteHeatingModel(Building struct){
		this.rootStructure = struct;
	}
	
	public void nextModelStep(){
		//TODO: update intern discrete timetable
		rootStructure.nextModelStep();
	}
}