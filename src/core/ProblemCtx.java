package core;

import java.util.List;

import entities.*;

public class ProblemCtx {

	private static Drone[] drones;
	private static Warehouse[] warehouses;
	private static Order[] orders;
	private static ItemType[] itemTypes;
	private static int[] map = new int[2];
	
	/**
	 * args[0] => input path
	 */
	public static void main(String[] args){
		final int DRONE_CAPACITY, MAX_TURNS, MAX_PAYLOAD;
		FileManager f = new FileManager(args[0]);
		List<String> currentLine;
		// generic
		currentLine = FileManager.split(f.readNextLine());
		map[0] = Integer.parseInt(currentLine.get(0));
		map[1] = Integer.parseInt(currentLine.get(1));
		drones = new Drone[Integer.parseInt(currentLine.get(2))];
		warehouses = new Warehouse[Integer.parseInt(currentLine.get(3))];
		orders = new Order[Integer.parseInt(currentLine.get(4))];
		// product types
		currentLine = FileManager.split(f.readNextLine());
		itemTypes = new ItemType[Integer.parseInt(currentLine.get(0))];
		currentLine = FileManager.split(f.readNextLine());
		for(int i = 0; i < itemTypes.length; i++){
			itemTypes[i] = new ItemType(i, Integer.parseInt(currentLine.get(i)));
		}
		// warehouses
		currentLine = FileManager.split(f.readNextLine());
		warehouses = new Warehouse[Integer.parseInt(currentLine.get(0))];
		for(int i = 0; i < warehouses.length; i++){
			currentLine = FileManager.split(f.readNextLine());
			int[] whPos = new int[]{Integer.parseInt(currentLine.get(0)), Integer.parseInt(currentLine.get(1))};
			currentLine = FileManager.split(f.readNextLine());
			String[] currLine = new String[currentLine.size()];
			warehouses[i] = new Warehouse(currentLine.toArray(currLine), itemTypes);
		}
		// orders
		currentLine = FileManager.split(f.readNextLine());
		orders = new Order[Integer.parseInt(currentLine.get(0))];
		for(int i=0; i<orders.length; i++){
			
		}
	}
}
