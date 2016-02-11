package entities;

import java.util.HashMap;
import java.util.Map;

public class LoadPositioned {

	private Map<ItemType, Integer> load;
	private int[] pos;
	
	public LoadPositioned(){
		load = new HashMap<ItemType, Integer>();
	}
	
	public LoadPositioned(String[] load, ItemType[] itemTypes){
		this.load = new HashMap<ItemType, Integer>();
		for(int i = 0; i < load.length; i++){
			this.getLoad().put(itemTypes[i], Integer.parseInt(load[i]));
		}
	}
	
	public void setPos(int x, int y){
		this.pos = new int[]{x, y};
	}
	
	public int[] getPos(){
		return this.pos;
	}
	
	public int getX(){
		return this.pos[0];
	}
	
	public int getY(){
		return this.pos[1];
	}
	
	public void setItemLoad(ItemType item, Integer num){
		load.put(item, num);
	}
	
	public Map<ItemType, Integer> getLoad(){
		return load;
	}
}
