package entities;

public class Warehouse extends LoadPositioned{
	
	public Warehouse(String[] load, ItemType[] itemTypes){
		for(int i = 0; i < load.length; i++){
			this.getLoad().put(itemTypes[i], Integer.parseInt(load[i]));
		}
	}
}
