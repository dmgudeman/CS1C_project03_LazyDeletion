



public class LazySearchTreeTester<E extends Comparable<? super E>>
{
   LazySearchTree<E> tree = new LazySearchTree<E>();
   public static boolean DEBUG = false;
   
    public LazySearchTreeTester(LazySearchTree<E> testTree, boolean dbg)
   {
      super();
      this.tree = testTree;
      DEBUG = dbg;
   }
    public LazySearchTreeTester(LazySearchTree<E> testTree)
    {
       super();
       this.tree = testTree;
       DEBUG = true;
    }

    public void stateOfTree()
    {
       if(DEBUG)
       {
       System.out.println("\n=======================================");
       this.showMin();
       this.showMax();    
       this.showSizes();
       this.showMroot();

       System.out.println("=======================================\n");  
       }
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
      try
      {
      System.out.println("The mSize is: " + tree.mSize);
      System.out.println("The mSizeHard is: " + tree.mSizeHard);
      } catch (Exception e)
      {
         System.out.println("The tree is null");
      }
   }
   public void showMroot()
   {
      try
      {
      System.out.println("The mRoot is: " + tree.mRoot.data);
     
      } catch (Exception e)
      {
         System.out.println("The mRoot is null.");
      }
   }
   
   
  
}
