package Orders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TestProcessedOrders {
	 class OrderProcessor {
	/**
	* Instance variables
	*/
	private Scanner inFile = null;
	private PrintWriter outFile = null;
	/**
	* Instance constants
	*/
	private static final double TAX_RATE = 0.02;
	private static final double SHIP_RATE = 0.05;
	/**
	* Default constructor. Opens the input and output files with default files.
	*/
	public OrderProcessor() {
		openFiles("hard coded default input file path", "hard coded default input file path");
		}
		/**
		* constructor. Opens input and output files with values passed in by running
		job.
		* @param inFilePath
		* @param outFilePath  에린
		*/
		public OrderProcessor(String inFilePath, String outFilePath) {
			this.openFiles(inFilePath,outFilePath);
		}
		
		private void openFiles(String inFilePath, String outFilePath) {
			try {
				inFile = new Scanner (new FileReader(inFilePath));
				outFile = new PrintWriter(outFilePath);
			} 
			
			catch (FileNotFoundException e) {
				System.out.println("Unable to open input/output order file, " +
				inFilePath + ", " + outFilePath);
				System.out.println(e.getMessage());
			}
		}
		/**
		* Closes the input and output files.
		*/
		private void closeFiles() {
			inFile.close();
			outFile.close();
		}
		
		public void processOrders() {
			String nextLine;
			StringTokenizer st;
			String orderId;
			String partNum;
			Double price;
			Integer quantity;
			nextLine = inFile.nextLine();
			while(inFile.hasNext()) {
				nextLine = inFile.nextLine();
				 st = new StringTokenizer(nextLine, "|");
				 orderId = st.nextToken();
				 partNum = st.nextToken();
				 price = new Double(st.nextToken());
				 quantity = new Integer(st.nextToken());
				 writeOutput(orderId, partNum, price, quantity);
			}
			closeFiles();
		}
		
		private void writeOutput(String orderId, String partNum, Double price, Integer
		quantity) {
			double itemAmount = price.doubleValue() * quantity.doubleValue();
			double taxAmount = itemAmount * TAX_RATE;
			double shipAmount = itemAmount * SHIP_RATE;
			double totalAmount = itemAmount + taxAmount + shipAmount;
			outFile.write("Order Id: \t" + orderId + "\n");
			outFile.write("Part Num: \t" + partNum + "\n");
			outFile.write("Price: \t" + price.toString() + "\n");
			outFile.write("Quantity: \t" + quantity.toString() + "\n");
			outFile.write("Tax: \t" + taxAmount + "\n");
			outFile.write("Ship: \t" + shipAmount + "\n");
			outFile.write("Total: \t" + totalAmount + "\n");
			outFile.write("\n\n");
			outFile.flush();
		}
	
/**
* The main method to start the program from the command line.
* @param args
*/
	public void main(String[] args) {
		OrderProcessor orderProcessor = new OrderProcessor("Orders.txt",
		"OrdersProcessed.txt");
		System.out.println("Start processing orders.");
		orderProcessor.processOrders();
		System.out.println("Finished processing orders.");
		}
	 }
}