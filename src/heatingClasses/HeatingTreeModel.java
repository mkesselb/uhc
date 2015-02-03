package heatingClasses;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class HeatingTreeModel implements TreeModel{
	
	private Building root;
	
	public HeatingTreeModel(String csvPath){
		//initializes the tree from csv
		this.root = CsvImporter.parseFromCSV(csvPath);
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		//no tree changes in this model, no need for listeners
	}

	@Override
	public Structure getChild(Object struct, int childIndex) {
		return ((Structure) struct).getSubStruct(childIndex);
	}

	@Override
	public int getChildCount(Object struct) {
		if(struct instanceof Room){
			return 0;
		} else{
			return  ((Structure) struct).getSubStructs().size();
		}
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return ((Structure) parent).findSubStruct((Structure) child);
	}

	@Override
	public Object getRoot() {
		return this.root;
	}

	@Override
	public boolean isLeaf(Object node) {
		return (node instanceof Room);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		//no listeners needed, no changes to the model
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		//not needed, no changes in this model
	}
}