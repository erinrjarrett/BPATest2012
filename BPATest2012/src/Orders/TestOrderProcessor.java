package Orders;

public class TestOrderProcessor {
/**
* The main method to start the program from the command line.
* @param args
*/
	public static void main(String[] args) {
		OrderProcessor orderProcessor = new OrderProcessor("Orders.txt",
		"OrdersProcessed.txt");
		System.out.println("Start processing orders.");
		orderProcessor.processOrders();
		System.out.println("Finished processing orders.");
		}
}