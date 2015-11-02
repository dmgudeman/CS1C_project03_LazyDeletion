
import cs1c.SongEntry;
/**
 * The node class that LazySearchTree utilizes. Each node has left and right 
 * child, a data attribute of E type, an Integer to keep track of how many
 * times in a tree that it is used without making a new node. This was important
 * for the Supermarket inventory. Tne Nodes have a SongEntry node attribute
 * to facilitate implementation of the FootTunesStore applications. The
 * deleted attribute allows for implementation of lazy deletion. 
 * @author David M. Gudeman
 * @date November 1, 2015
 * @param <E>
 */
public class LazySTNode<E extends Comparable<? super E>>
   {
      // use public access so the tree or other classes can access members
      public LazySTNode<E> lftChild, rtChild;
      public E data;
      
      // node to make sure that you are working with the correct tree. Not
      // implemented in the program due to time constraints
      public LazySTNode<E> myRoot; // needed to test for certain error
      public boolean deleted;
      Integer itemCount;
      SongEntry songEntry;

      /**
       * Node constructor taking arguments
       * @param d
       * @param lft
       * @param rt
       * @param del
       * @param itmCnt
       * @param sngNtry
       */
      public LazySTNode(E d, LazySTNode<E> lft, LazySTNode<E> rt, boolean del,
            Integer itmCnt, SongEntry sngNtry)
      {          
         lftChild = lft;
         rtChild = rt;
         data = d;
         deleted = del;
         itemCount = itmCnt;
         songEntry = sngNtry;                   
      }

      /**
       * default constructor sets the nodes to null, deleted to false and
       * itemCount to 0.
       */
      public LazySTNode()
      {
         this(null, null, null, false, 0, null);
      }

      // function stubs -- for use only with AVL Trees when we extend
      public int getHeight()
      {
         return 0;
      }

      public boolean setHeight(int height)
      {
         return true;
      }

      public Integer getItemCount()
      {
         return itemCount;
      }

      public void setItemCount(Integer itmCnt)
      {
         itemCount = itmCnt;
      }
      public void setSongEntry(SongEntry se)
      {
         this.data = (E) songEntry.getTitle();
         this.songEntry = se;
      }
      
      public SongEntry getSongEntry()
      {
         return this.songEntry;
      }

   }
