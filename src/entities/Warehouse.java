package entities;

public class Warehouse extends LoadPositioned{
	
	private int id;
	
	public Warehouse(int id, String[] load, ItemType[] itemTypes){
		super(load, itemTypes);
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public Warehouse(String[] load, ItemType[] itemTypes){
		for(int i = 0; i < load.length; i++){
			this.getLoad().put(itemTypes[i], Integer.parseInt(load[i]));
		}
	}
}
