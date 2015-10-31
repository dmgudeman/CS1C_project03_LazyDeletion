



public class LazySearchTreeTester<E extends Comparable<? super E>>
{
   LazySearchTree<E> tree = new LazySearchTree<E>();
   
   

   
   
   
    public LazySearchTreeTester(LazySearchTree<E> testTree)
   {
      super();
      this.tree = testTree;
   }

    public void stateOfTree()
    {
       System.out.println("\n=======================================");
       this.showMin();
       this.showMax();    
       this.showSizes();

       System.out.println("=======================================\n");   
    }
 void showMin()
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
   
   
   public void showMax()
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
   public void showSizes()
   {
      System.out.println("The mSize is: " + tree.mSize);
      System.out.println("The mSizeHard is: " + tree.mSizeHard);
   }
   
  
}
