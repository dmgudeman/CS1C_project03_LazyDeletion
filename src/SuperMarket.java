/**
 * Assume that you are developing a feature of an application
 * for a super market to keep track of it's inventory.
 * A log file contains information about items added and bought.
 * As we read the log file, your implementation must update the inventory
 * to increment or decrement the number of a specific item.
 *
 * @author Foothill College, David M. Gudeman
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Define the class PrintObject to implement Traverser Note: You may modify
 * class PrintObject as you see fit.
 */
class PrintObject<E> implements Traverser<E>
{
   @Override
   public void visit(E x)
   {
      System.out.print(x + " ");
   }
};

/**
 * This class maintains an inventory of products by using a LazySearchTree of
 * String containing LazySTNodes. In order to keep track of the number of a
 * specific product that is in the inventory, a itemCount attribute was added to
 * the LazySTNode. Lazy deletion was utilized so if a product was ever ordered
 * it would be kept in inventory. If it as zero, the product (node) is
 * maintained in a deleted state of size zero. The collect garbage function will
 * delete this information. In order to maintain the lazy deletion the soft size
 * of the inventory (mSize) was adjusted in this class in two places.
 * 
 * @author davidgudeman
 *
 */
public class SuperMarket
{
   public static boolean SHOW_DETAILS = true;

   /* Define an attribute called "inventory" of type LazySearchTree */
   static LazySearchTree<String> inventory = new LazySearchTree<>();
   LazySearchTree<String>.LazySTNode<String> temp;
   PrintObject<String> printString = new PrintObject<String>();
   static LazySearchTreeTester<String> tester = new LazySearchTreeTester<>(
         inventory);
   // toggles a print method that shows the state of the inventory for debugging
   static boolean testerOn = false;

   public int getInventorySize()
   {
      return inventory.mSize;
   }
   String itemName = "";

   public <E> void addToInventory(String itemName)
   {
      if (inventory.containsHard(itemName))
      {
         temp = inventory.findHard(inventory.mRoot, itemName);
         if (temp.deleted)
         {
            temp.deleted = false;
            inventory.mSize++;
            temp.setItemCount(1);
         }
         temp.setItemCount(temp.getItemCount() + 1);
      } else
      {
         inventory.insert(itemName);
         inventory.findHard(inventory.mRoot, itemName).setItemCount(1);
      }
   }

   public void printInventory(String str)
   {
      System.out.println("\n" + str);
      if (inventory.mRoot != null)
      {
         if (!inventory.mRoot.deleted)
            System.out.print(inventory.mRoot.data + ":"
                  + inventory.mRoot.getItemCount() + " ");
         printInventory(inventory.mRoot);
      } else
      {
         System.out.println("The inventory is empty");
      }
   }

   protected void printInventory(LazySearchTree<String>.LazySTNode<String> root)
   {
      if (root.lftChild != null)
      {
         if (root.lftChild.getItemCount() > 0)
            System.out.print(root.lftChild.data + ":"
                  + root.lftChild.getItemCount() + " ");
         printInventory(root.lftChild);
      }
      if (root.rtChild != null)
      {
         if (root.rtChild.getItemCount() > 0)
            System.out.print(root.rtChild.data + ":"
                  + root.rtChild.getItemCount() + " ");
         printInventory(root.rtChild);
      }
   }

   public void removeFromInventory(String itemName)
   {
      if (inventory.containsHard(itemName))
      {
         temp = inventory.findHard(inventory.mRoot, itemName);
         if (temp.deleted)
         {
            System.out.println("\nYou tried to buy: " + itemName
                  + " \nWe are out of " + itemName
                  + ". Please contact us to get an "
                  + "estimate of when we will have it in stock.");
            SHOW_DETAILS = false;
            temp.setItemCount(0);
         } else
         {
            temp.setItemCount(temp.getItemCount() - 1);
            if (temp.getItemCount() <= 0)
            {
               temp.setItemCount(0);
               inventory.mSize--;
               temp.deleted = true;
            }
         }
      } else
      {
         SHOW_DETAILS = false;
         System.out.println("\nYou tried to buy: " + itemName
               + " \nWe have not stocked " + itemName
               + " yet. Please contact us if "
               + "you would like us to stock this product.");
      }
   }

   public static void main(String[] args)
   {
      final String FILENAME = "resources/inventory_log.txt"; // Directory path
                                                             // for Mac OS X
      // final String FILENAME = "resources\\registers.txt"; // Directory path
      // for Windows OS (i.e. Operating System)

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
            SHOW_DETAILS = true;
            lineNum++;
            line = input.nextLine();
            String[] tokens = line.split(" ");

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
                  market.printInventory("At line #" + lineNum + ": " + line);
               if (testerOn)
                  tester.stateOfTree();
            }

            // When an item is bought:
            // Decrement the count of the item.
            // If the item is out of stock,
            // remove the item from inventory.
            //
            // Note: buying an out of stock item, is invalid. Handle it
            // appropriately.
            else if (selection.equals("buy"))
            {
               try
               {
                  market.removeFromInventory(itemName);
                  if (SHOW_DETAILS)
                     market.printInventory("At line #" + lineNum + ": " + line);
                  if (testerOn)
                     tester.stateOfTree();

               } catch (java.util.NoSuchElementException ex)
               {
                  // Note: Ideally we'd print to the error stream,
                  // but to allow correct interleaving of the output
                  // we'll use the regular output stream.
                  System.out.printf("Warning: Item %s is out of stock.\n",
                        itemName);
               }
            } else
            {
               System.out
                     .println("Warning: Inventory selection not recognized!");
            }
         }
         input.close();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }

      // Display the inventory
      int totalInventorySize = market.getInventorySize();
      System.out.println("\n\nNumber of different items in stock: "
            + totalInventorySize);
      market.printInventory("Items in stock:");
 
      if (testerOn)
      {
         tester.stateOfTree();
         inventory.collectGarbage();
         tester.stateOfTree();
      }
   }

}
