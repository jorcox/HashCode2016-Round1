package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.Drone;
import entities.ItemType;
import entities.Order;
import entities.Warehouse;

public class AlgoritmoPoda {
	
	public static void poda(Drone[] drones, Warehouse[] warehouses, Order[] orders, int MaxPayload) {
		HashMap<Integer, Integer> costes = new HashMap<Integer, Integer>();
		int siguienteDron = 0;

		for (int i = 0; i < drones.length; i++){
			costes.put(i, 0);
		}
		// realizas busqueda en arbol atendiendo priemro a un pedido en concreto
		// los drones se turnaran segun se vaya haciendo la profundidad
		for (int i = 0; i < orders.length; i++) {
			int distancia = 0;
			// escoges el dron que lelva menos coste a cuestas
			siguienteDron = DroneMenor(costes);

			Order orderACompletar = orders[i];
			int pesoPedido = orderACompletar.getWeightload();
			if (pesoPedido < MaxPayload) {
				 distancia+=turnos(warehouses, orderACompletar, siguienteDron);
				// se puede cargar el pedido en un dron
				// mirar que se peude cargar de más

				/*
				 * distancia del siguiente drone a la warehouse/es con los
				 * objetos y al pedido
				 */
				/* y a los demas */
			} else {
				 distancia+=turnosSinCompletarPedido(warehouses, orderACompletar,MaxPayload, siguienteDron);

				// solo se puede hacer un poco del pedido
			}
			
			if (costes.containsKey(siguienteDron)) {
				costes.replace(siguienteDron, costes.get(siguienteDron) + distancia);
			} else {
				costes.put(siguienteDron, distancia);
			}
		}

	}

	public static int DroneMenor(HashMap<Integer, Integer> costes) {
		int menorCoste = costes.get(0);
		int droneMenor = 0;
		for (Integer drones : costes.keySet()) {
			int coste = costes.get(drones);
			if (menorCoste > coste) {
				menorCoste = coste;
				droneMenor = drones;
			}
		}
		return droneMenor;
	}

	public static int turnosSinCompletarPedido(Warehouse[] warehouses, Order order, int tope, int drone) {
		ArrayList<Warehouse> WarVistas= new ArrayList<Warehouse>();
		int distancia=0;
		boolean cabe=true;
		while (cabe && WarVistas.size() < warehouses.length) {
			Warehouse whareMasCercana = WhareMasCercana(warehouses, order,WarVistas);
			distancia+=distancia(whareMasCercana.getPos(),order.getPos());
			WarVistas.add(whareMasCercana);
			Map<ItemType, Integer> load = whareMasCercana.getLoad();

			for (int i = 0; i < order.getLoad().size(); i++) {
				for (ItemType itemType : order.getLoad().keySet()) {
					int num = order.getLoad().get(itemType);
					if (!load.containsKey(itemType)){
						load.put(itemType, 0);
					}
					int numTiene = load.get(itemType);
					int numCoge = num;
					if (numCoge > numTiene)
						numCoge = numTiene;
					
					tope-=itemType.getWeigh()*numCoge;
					cabe=tope>0;
					if(cabe && numCoge > 0){
						order.getLoad().replace(itemType, numCoge);
						load.replace(itemType, numCoge);
						System.out.println(drone + " L " + whareMasCercana.getId() + " " + itemType.getId() + " " + numCoge);
						System.out.println(drone + " D " + order.getId() + " " + itemType.getId() + " " + numCoge);
					}
				}
			}
		}
		return distancia;
	}
	
	public static int turnos(Warehouse[] warehouses, Order order, int drone) {
		ArrayList<Warehouse> WarVistas= new ArrayList<Warehouse>();
		int distancia=0;

		while (!orderVacio(order) ) {
			Warehouse whareMasCercana = WhareMasCercana(warehouses, order,WarVistas);
			if (whareMasCercana == null) break;
			distancia+=distancia(whareMasCercana.getPos(),
					order.getPos());
			WarVistas.add(whareMasCercana);
			Map<ItemType, Integer> load = whareMasCercana.getLoad();

			for (int i = 0; i < order.getLoad().size(); i++) {
				for (ItemType itemType : order.getLoad().keySet()) {
					int num = order.getLoad().get(itemType);
					int numTiene = load.get(itemType);
					int numCoge = num;
					if (numCoge > numTiene)
						numCoge = numTiene;
					
					if (numCoge > 0){
						order.getLoad().replace(itemType, numCoge);
						load.replace(itemType, numCoge);
						System.out.println(drone + " L " + whareMasCercana.getId() + " " + itemType.getId() + " " + numCoge);
						System.out.println(drone + " D " + order.getId() + " " + itemType.getId() + " " + numCoge);
					}
				}
			}
		}
		return distancia;
	}

	public static Warehouse WhareMasCercana(Warehouse[] warehouses, Order order,ArrayList<Warehouse> vistas) {
		int distancia = Integer.MAX_VALUE;
		Warehouse cercana = null;
		for (int i = 0; i < warehouses.length; i++) {
			if (distancia(warehouses[i].getPos(), order.getPos()) < distancia && !vistas.contains(warehouses[i])) {
				distancia = distancia(warehouses[i].getPos(), order.getPos());
				cercana = warehouses[i];
			}
		}
		return cercana;
	}

	public static Integer distancia(int[] pos, int[] pos2) {
		double a = Math.sqrt(Math.pow(pos[0] - pos2[0], 2) + Math.pow(pos[1] - pos2[1], 2));
		return ((int) a) + 1;
	}

	public static boolean orderVacio(Order order) {
		boolean vacia = true;
		for (ItemType itemType : order.getLoad().keySet()) {
			vacia = vacia && order.getLoad().get(itemType) <= 0;
		}
		return vacia;
	}
}
