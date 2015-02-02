package heatingClasses;

public abstract class Structure {
	
	private String name;
	
	public Structure(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}