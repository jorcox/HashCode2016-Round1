package entities;

public class Order extends LoadPositioned{
	
	public Order(String[] load, ItemType[] itemTypes){
		for(int i = 0; i < load.length; i++){
			if (this.getLoad().get(itemTypes[i]) == null){
				this.getLoad().put(itemTypes[i], 0);
			}
			this.getLoad().put(itemTypes[i], this.getLoad().get(itemTypes[Integer.parseInt(load[i])]) + 1);
		}
	}
}
