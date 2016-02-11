package core;

import java.util.List;

import entities.*;

public class ProblemCtx {

	public static Drone[] drones;
	public static Warehouse[] warehouses;
	public static Order[] orders;
	public static ItemType[] itemTypes;
	public static int[] map = new int[2];
	
	/**
	 * args[0] => input path
	 */
	public static void main(String[] args){
		final int MAX_TURNS, MAX_PAYLOAD;
		FileManager f = new FileManager(args[0]);
		List<String> currentLine;
		// generic
		currentLine = FileManager.split(f.readNextLine());
		map[0] = Integer.parseInt(currentLine.get(0));
		map[1] = Integer.parseInt(currentLine.get(1));
		drones = new Drone[Integer.parseInt(currentLine.get(2))];
		MAX_TURNS = Integer.parseInt(currentLine.get(3));
		MAX_PAYLOAD = Integer.parseInt(currentLine.get(4));
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
			warehouses[i].setPos(whPos[0], whPos[1]);
		}
		// orders
		currentLine = FileManager.split(f.readNextLine());
		orders = new Order[Integer.parseInt(currentLine.get(0))];
		for(int i=0; i<orders.length; i++){
			currentLine = FileManager.split(f.readNextLine());
			int[] orderPos = new int[]{Integer.parseInt(currentLine.get(0)), Integer.parseInt(currentLine.get(1))};
			currentLine = FileManager.split(f.readNextLine());
			String[] currLine = new String[currentLine.size()];
			orders[i] = new Order(currentLine.toArray(currLine), itemTypes);
			orders[i].setPos(orderPos[0], orderPos[1]);
		}
	}
}
