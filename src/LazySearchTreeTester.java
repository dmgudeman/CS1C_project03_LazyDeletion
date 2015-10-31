



public class LazySearchTreeTester<E extends Comparable<? super E>>
{
  

   public void stateOfTree(LazySearchTree<E> tree)
   {
      System.out.println("\n=======================================");
      this.showMin(tree);
      this.showMax(tree);    
      this.showSizes(tree);

      System.out.println("=======================================\n");   
   }
   
   
   public void showMin(LazySearchTree<E> tree)
   {
      try
      {
         System.out.println( "The Min is: " + tree.findMin());
      }
      catch (Exception e)
      {
         System.out.println( "The findMin is null" );
      }
      try
      {
         System.out.println( "The MinHard is: " + tree.findMinHard());
      }
      catch (Exception e)
      {
         System.out.println("The findMinHard is null");
      }
   }
   
   
   public void showMax(LazySearchTree<E> tree)
   {
      try
      {
         System.out.println( "The Max is: " + tree.findMax());
      }
      catch (Exception e)
      {
         System.out.println( "The findMax is null" );
      }
      try
      {
         System.out.println( "The MaxHard is: " + tree.findMaxHard());
      }
      catch (Exception e)
      {
         System.out.println("The findMaxHard is null");
      }
   }
   public void showSizes(LazySearchTree<E> tree)
   {
      System.out.println("The mSize is: " + tree.mSize);
      System.out.println("The mSizeHard is: " + tree.mSizeHard);
   }
   
  
}
