
public class MarketTester
{

   public static void main(String[] args)
   {
     
      PrintObject<Integer> printObject = new PrintObject<Integer>();
      PrintObject<String> printString = new PrintObject<String>();
      

       SuperMarket sm = new SuperMarket();
       sm.addToInventory("abacus");
       sm.addToInventory("banana");
       sm.addToInventory("banana");
       sm.addToInventory("banana");
       
       System.out.println(sm.inventory.mSize);
    //   sm.addToInventory("abacus");
       System.out.println(sm.inventory.mSize);
       System.out.println(sm.inventory.find(sm.inventory.mRoot, "abacus").getItemCount());
       System.out.println(sm.inventory.find(sm.inventory.mRoot, "banana").getItemCount());
       sm.printInventory();
   }

}
