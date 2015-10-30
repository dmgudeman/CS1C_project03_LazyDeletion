
public class LazySearchTreeTester
{

   public void stateOfTree(LazySearchTree tree)
   {
      System.out.println("\n=======================================");
      this.showMin(tree);
      this.showMax(tree);    
      this.showSizes(tree);
      System.out.println("=======================================\n");
      
      
      
      
   }
   public void showMin(LazySearchTree tree)
   {
      try
      {
         System.out.println( "The Min is: " + tree.findMin());
         System.out.println( "The MinHard is: " + tree.findMinHard());
      }
      catch (Exception e)
      {
         System.out.println( "The Min's are not found" );
      }
   }
   public void showMax(LazySearchTree tree)
   {
      try
      {
         System.out.println( "The Max is: " + tree.findMax());
         System.out.println( "The MaxHard is: " + tree.findMaxHard());
      }
      catch (Exception e)
      {
         System.out.println( "The Max's are not found" );
      }
   }
   public void showSizes(LazySearchTree tree)
   {
      System.out.println("The mSize is: " + tree.mSize);
      System.out.println("The mSizeHard is: " + tree.mSizeHard);
   }
}
