package core;

import java.util.List;

import entities.*;

public class ProblemCtx {

	public static Drone[] drones;
	public static Warehouse[] warehouses;
	public static Order[] orders;
	public static ItemType[] itemTypes;
	public static int[] map = new int[2];
	public static int maxPayload;
	public static int maxTurns;
	
	/**
	 * args[0] => input path
	 */
	public static void main(String[] args){
		System.out.println(args[0]);
		FileManager f = new FileManager(args[0]);
		List<String> currentLine;
		// generic
		currentLine = FileManager.split(f.readNextLine());
		map[0] = Integer.parseInt(currentLine.get(0));
		map[1] = Integer.parseInt(currentLine.get(1));
		drones = new Drone[Integer.parseInt(currentLine.get(2))];
		maxTurns = Integer.parseInt(currentLine.get(3));
		maxPayload = Integer.parseInt(currentLine.get(4));
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
			currentLine.toArray(currLine);
			warehouses[i] = new Warehouse(currLine, itemTypes);
			warehouses[i].setPos(whPos[0], whPos[1]);
		}
		// orders
		currentLine = FileManager.split(f.readNextLine());
		orders = new Order[Integer.parseInt(currentLine.get(0))];
		for(int i=0; i<orders.length; i++){
			currentLine = FileManager.split(f.readNextLine());
			int[] orderPos = new int[]{Integer.parseInt(currentLine.get(0)), Integer.parseInt(currentLine.get(1))};
			currentLine = FileManager.split(f.readNextLine());
			String[] currLine = new String[Integer.parseInt(currentLine.get(0))];
			currentLine = FileManager.split(f.readNextLine());
			currentLine.toArray(currLine);
			orders[i] = new Order(currLine, itemTypes);
			orders[i].setPos(orderPos[0], orderPos[1]);
		}
		
		AlgoritmoPoda.poda(drones, warehouses, orders, maxPayload);
		//printProblem();
	}
	
	public static void printProblem(){
		System.out.println("MAP: " + map[0] + "x" + map[1]);
		System.out.println("DRONES: " + drones.length);
		System.out.println("MAX TURNS: " + maxTurns);
		System.out.println("MAX PAYLOAD: " + maxPayload);
		System.out.println("PRODUCT TYPES: ");
		for(ItemType i : itemTypes){
			System.out.println("Id: " + i.getId() + "; Weigh: " + i.getWeigh());
		}
		System.out.println("WAREHOUSES: ");
		for(Warehouse w : warehouses){
			System.out.println("Location at: [" + w.getX() + ", " + w.getY() + "]");
			for(ItemType it : w.getLoad().keySet()){
				System.out.println("Item: " + it.getId() + "; Load: " + w.getLoad().get(it));
			}
		}
		System.out.println("ORDERS");
		for(Order o : orders){
			System.out.println("Deliver to: [" + o.getX() + ", " + o.getY() + "]");
			for(ItemType it : o.getLoad().keySet()){
				System.out.println("Item: " + it.getId() + "; Load: " + o.getLoad().get(it));
			}
		}
	}
}