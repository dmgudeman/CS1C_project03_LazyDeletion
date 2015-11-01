/**
 * This class is used to test the LazySearchTree. It populates a LazySearchTree
 * with Integers and then tests the methods. It can be constructed with or
 * without the DEBUG flag which is used to toggle the debugging behavior of the
 * client class.
 * 
 * @author David M Gudeman
 * @date November 1, 2015
 * @param <E>
 */

public class LazySearchTreeTester<E extends Comparable<? super E>>
{
   LazySearchTree<E> tree = new LazySearchTree<E>();

   // Functor to allow traversal of tree in methods with traverse in the name
   PrintObject<E> printObject = new PrintObject<E>();

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

   /**
    * Collates and formats the testing methods in this class. Does not
    * explicitly show tree.
    */
   public void stateOfTree()
   {
      if (DEBUG)
      {
         System.out.println("\n=======================================");
         this.showMin();
         this.showMax();
         this.showSizes();
         this.showMroot();
         System.out.println("=======================================\n");
      }
   }

   /**
    * Collates and formats the testing methods in this class. Explicitly shows
    * the tree both in the soft(lazy deletion) state and the hard state.
    */
   public void stateOfTreeWithShowTree()
   {
      if (DEBUG)
      {
         System.out.println("\n=======================================");
         this.showMin();
         this.showMax();
         this.showSizes();
         this.showMroot();
         showTreeSoft();
         showTreeHard();
         System.out.println("=======================================\n");
      }
   }

   /**
    * Calls a method in LazySearchTree to traverse the tree by uses the function
    * class to print out the data from each non-deleted node.
    */
   void showTreeSoft()
   {
      System.out.println("----------Soft Tree------------------");
      tree.traverseSoft(printObject, tree.mRoot);
      System.out.println("\n-------------------------------------");

   }

   /**
    * Calls a method in LazySearchTree to traverse the tree by uses the function
    * class to print out the data from nodes.
    */
   void showTreeHard()
   {
      System.out.println("----------Hard Tree------------------");
      tree.traverseHard(printObject, tree.mRoot);
      System.out.println("\n-------------------------------------");

   }

   /**
    * Tests the findMin and findMinHard methods
    */
   void showMin()
   {
      try
      {
         System.out.println("The Min is: " + tree.findMin());
      } catch (Exception e)
      {
         System.out.println("The findMin is null");
      }
      try
      {
         System.out.println("The MinHard is: " + tree.findMinHard());
      } catch (Exception e)
      {
         System.out.println("The findMinHard is null");
      }
   }

   /**
    * Tests the findMax and findMaxHard methods
    */
   public void showMax()
   {
      try
      {
         System.out.println("The Max is: " + tree.findMax());
      } catch (Exception e)
      {
         System.out.println("The findMax is null");
      }
      try
      {
         System.out.println("The MaxHard is: " + tree.findMaxHard());
      } catch (Exception e)
      {
         System.out.println("The findMaxHard is null");
      }
   }

   /**
    * Shows the mSize and mSizeHard attributes
    */
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

   // deprecated methods saved to finish testing rest of the program
   // public void showTreeHard()
   // {
   // System.out.println("-----------Hard Tree------------------");
   //
   // if (tree.mRoot != null)
   // {
   // showTreeHard(tree.mRoot);
   // System.out.print("" + tree.mRoot.data);
   // } else
   // {
   // System.out.println("The tree is empty");
   // }
   // System.out.println("\n--------------------------------------");
   // }

   // protected void showTreeHard(LazySTNode<E> root)
   // {
   // if (root.lftChild != null)
   // {
   // showTreeHard(root.lftChild);
   // System.out.print(root.lftChild.data + " ");
   // }
   // if (root.rtChild != null)
   // {
   // showTreeHard(root.rtChild);
   // System.out.print(root.rtChild.data + " ");
   // }
   // }
   /**
    * public function calls the protected function of same name. This allows the
    * discovery of a node by the use of a string to find a node by its data
    * attribute if that is a string.
    * 
    * @param root
    * @param x
    * @return
    */
   public void findNodeByString(String str)
   {
      findNodeByString(tree.mRoot, str);
   }

   /**
    * private function called by public function of same name. This allows the
    * discovery of a node by the use of a string to find a node by its data
    * attribute if that is a string.
    * 
    * @param root
    * @param x
    * @return
    */
   protected LazySTNode<E> findNodeByString(LazySTNode<E> root, String x)
   {
      if (root == null)
         return null;
      int compareResult = x.compareTo((String) root.data);
      if (compareResult < 0)
         return findNodeByString(root.lftChild, x);
      if (compareResult > 0)
         return findNodeByString(root.rtChild, x);
      if (compareResult == 0)
         return root;
      return null;
   }

   /**
    * The public face of a public/protected pair of methods. Shows the data from
    * the songEntry node within the LazySTNode. The node is found by calling the
    * function with the data attribute of the LazySTNode. In this case it needs
    * to be a string.
    * 
    * @param str
    */
   public void showNodeWithSongEntry(String str)
   {
      LazySTNode<E> temp = findNodeByString(tree.mRoot, str);
      showNodeWithSongEntry(temp);

   }

   /**
    * The protected face of a public/protected pair of methods. Shows the data
    * from the songEntry node within the LazySTNode. The node is found by
    * calling the function with the data attribute of the LazySTNode. In this
    * case it needs to be a string.
    * 
    * @param str
    */
   public void showNodeWithSongEntry(LazySTNode<E> node)
   {
      System.out
            .println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out
            .println("showNode(LazySTNode<E> node) line 615 LazySearchTree");
      System.out
            .println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("                   node.data: " + node.data);
      System.out.println("   node.songEntry.getTitle(): "
            + node.getSongEntry().getTitle());
      System.out.println("  node.songEntry.getArtist(): "
            + node.getSongEntry().getArtistName());
      System.out.println("node.songEntry.getDuration(): "
            + node.getSongEntry().getDuration());
      System.out.println("   node.songEntry.getGenre(): "
            + node.getSongEntry().getGenre());
      System.out
            .println("++++++++++++++++++++++++++++++++++++++++++++++++++++");

   }

}
