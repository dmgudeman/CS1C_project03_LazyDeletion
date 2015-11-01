
import cs1c.SongEntry;

public class LazySTNode<E extends Comparable<? super E>>
   {
      // use public access so the tree or other classes can access members
      public LazySTNode<E> lftChild, rtChild;
      public E data;
      public LazySTNode<E> myRoot; // needed to test for certain error
      public boolean deleted;
      Integer itemCount;
      SongEntry songEntry;

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
