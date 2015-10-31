
public class marketBeta
{
   
   public static void main(String[] args)
   {
   
   LazySearchTree<String> invent = new LazySearchTree<String>();
   PrintObject<Integer> printObject = new PrintObject<Integer>();
   PrintObject<String> printString = new PrintObject<String>();
     invent.insert("thing");
     System.out.println(invent.mSize);

     System.out.println(invent.find("thing"));
     System.out.println(invent.find("thing").getClass());
     invent.insert("abacus");
     invent.find(invent.mRoot, "abacus").setItemCount(3);
    System.out.println( invent.find(invent.mRoot, "abacus").getItemCount());
     
    
     invent.traverse(printString);

     System.out.println();
     invent.traverse(printString);
     
   }
}
