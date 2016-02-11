package entities;

public class Order extends LoadPositioned{
	
	public Order(String[] load, ItemType[] itemTypes){
		super();
		for(int i = 0; i < load.length; i++){
			if (this.getLoad().get(itemTypes[Integer.parseInt(load[i])]) == null){
				this.getLoad().put(itemTypes[Integer.parseInt(load[i])], 0);
			}
			Integer n = this.getLoad().get(itemTypes[Integer.parseInt(load[i])]);
			this.getLoad().put(itemTypes[Integer.parseInt(load[i])], new Integer(n.intValue() + 1));
		}
	}
}