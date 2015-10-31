/**
 * Assume that you are developing a feature of an application
 * for a super market to keep track of it's inventory.
 * A log file contains information about items added and bought.
 * As we read the log file, your implementation must update the inventory
 * to increment or decrement the number of a specific item.
 *
 * @author Foothill College, [YOUR NAME HERE]
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Define the class PrintObject to implement Traverser
 * Note: You may modify class PrintObject as you see fit.
 */
class PrintObject<E> implements Traverser<E>
{


   @Override
   public void visit(E x)
   {
      System.out.print( x + " ");
      
   }
};


/**
 * Builds a binary search tree of items in the inventory.
 * Updates the inventory as the log file is read.
 * @param <LazySTNode>
 */
public class SuperMarket
{
  
   
	public static final boolean SHOW_DETAILS = true;

	/* Define an attribute called "inventory" of type LazySearchTree */
	LazySearchTree<String> inventory = new LazySearchTree<>();
	PrintObject<String> printString = new PrintObject<String>();
	public int getInventorySize()
	{	return inventory.sizeHard(); }
	
	String itemName = "";
	public void addToInventory(String itemName)
	{ 
	   if(inventory.contains(itemName))
	   {
	      Integer temp = inventory.find(inventory.mRoot, itemName).getItemCount();
         inventory.find(inventory.mRoot, itemName).setItemCount(temp+1);
	    
	    
	   } else
	   {
	      inventory.insert(itemName);
	      System.out.println("INSERTING" + itemName);
	      Integer temp = inventory.find(inventory.mRoot, itemName).getItemCount();
	      inventory.find(inventory.mRoot, itemName).setItemCount(temp+1);
	   }
	}
	
	public void printInventory(String str)
	{
	     System.out.println(str);
	}
	
	public void removeFromInventory(String itemName)
	{
	  
	   if(inventory.containsHard(itemName))
      {
	      Integer temp = inventory.findHard(inventory.mRoot, itemName).getItemCount();
	      if (temp <=1)
	        inventory.removeHard(itemName);
	      else 
	         inventory.findHard(inventory.mRoot, itemName).setItemCount(temp-1);
	      
      }
	}
	public static void main(String[] args) 
	{
		final String FILENAME = "resources/inventory_log.txt";	// Directory path for Mac OS X
		//final String FILENAME = "resources\\registers.txt";	// Directory path for Windows OS (i.e. Operating System)

		SuperMarket market = new SuperMarket();
		 PrintObject<String> printString = new PrintObject<String>();

		File infile = new File(FILENAME);

		try 
		{
			Scanner input = new Scanner(infile);

			String line = "";
			int lineNum = 0;			
			while (input.hasNextLine()) 
			{
				lineNum++;
				line = input.nextLine(); 
				String [] tokens = line.split(" ");

				String selection = tokens[0];
				String itemName = tokens[1];

				// When an item is added:
				// If the item is not in our inventory, 
				// create a new entry in our inventory.
				// Otherwise, increment the count of the item.
				if (selection.equals("add"))
				{
					market.addToInventory(itemName);
					if (SHOW_DETAILS)
					   market.inventory.traverse(printString);
						market.printInventory("At line #" + lineNum + ": " + line);
				}
				
				// When an item is bought: 
				// Decrement the count of the item.
				// If the item is out of stock, 
				// remove the item from inventory.
				//
				// Note: buying an out of stock item, is invalid. Handle it appropriately.
				else if (selection.equals("buy"))
				{
					try
					{
						market.removeFromInventory(itemName);
						if (SHOW_DETAILS)
							market.printInventory("At line #" + lineNum + ": " + line);
					}
					catch (java.util.NoSuchElementException ex)
					{
						// Note: Ideally we'd print to the error stream,
						// but to allow correct interleaving of the output
						// we'll use the regular output stream.
						System.out.printf("Warning: Item %s is out of stock.\n", itemName);
					}
				}
				else
				{
					System.out.println("Warning: Inventory selection not recognized!");
				}		

			}
			input.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 

		// Display the inventory
		int totalInventorySize = market.getInventorySize();
		System.out.println("Number of different items in stock : " + totalInventorySize);
		market.printInventory("\nItems in stock:");
	}

}
