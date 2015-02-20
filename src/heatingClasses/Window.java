package heatingClasses;

import java.util.ArrayList;
import java.util.List;

public class Window extends Structure{
	
	private boolean isOpen;
	
	public Window(String name, boolean isOpen){
		super(name);
		this.isOpen = isOpen;
		this.subStructs = new ArrayList<Structure>();
	}
	
	public boolean toggleOpen(){
		this.isOpen = !this.isOpen;
		return this.isOpen;
	}
	
	public boolean isOpen(){
		return this.isOpen;
	}
	
	public void setOpen(boolean isOpen){
		this.isOpen = isOpen;
	}

	@Override
	public String[] getOverviewColumnNames() {
		return null;
	}

	@Override
	public Object[][] getOverviewTableContent() {
		return null;
	}
	
	@Override
	public List<String> getSuboptimalConditions(){
		return null;
	}
}