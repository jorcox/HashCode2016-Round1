package entities;

public class ItemType {

	private int id;
	private int weigh;
	
	public ItemType(int id, int weigh){
		this.id = id;
		this.weigh = weigh;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getWeigh(){
		return this.weigh;
	}
}
