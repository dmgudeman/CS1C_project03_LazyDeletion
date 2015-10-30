import java.util.NoSuchElementException;

public class LazySearchTree<E extends Comparable<? super E>> implements
      Cloneable
{

   protected class LazySTNode<E extends Comparable<? super E>>
   {
      // use public access so the tree or other classes can access members
      public LazySTNode<E> lftChild, rtChild;
      public E data;
      public LazySTNode<E> myRoot; // needed to test for certain error
      public boolean deleted;

      public LazySTNode(E d, LazySTNode<E> lft, LazySTNode<E> rt, boolean del)
      {
         lftChild = lft;
         rtChild = rt;
         data = d;
         deleted = del;
      }

      public LazySTNode()
      {
         this(null, null, null, false);

      }

      // function stubs -- for use only with AVL Trees when we extend
      public int getHeight()
      {
         return 0;
      }

      boolean setHeight(int height)
      {
         return true;
      }
   }

   protected static boolean DEBUG = false;
   protected int mSize;
   protected LazySTNode<E> mRoot;
   protected int mSizeHard;

   public LazySearchTree()
   {
      clear();
   }

   public boolean empty()
   {
      return (mSize == 0);
   }

   public int size()
   {
      return mSize;
   }

   public void clear()
   {
      mSize = 0;
      mSizeHard = 0;
      mRoot = null;
   }

   public int showHeight()
   {
      return findHeight(mRoot, -1);
   }

   public int sizeHard()
   {
      return mSizeHard;
   }

   public E findMin()
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMin(mRoot).data;
   }

   public E findMinHard()
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMinHard(mRoot).data;
   }

   public E findMax()
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMax(mRoot).data;
   }

   public E findMaxHard()
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMaxHard(mRoot).data;
   }

   public E find(E x)
   {
      LazySTNode<E> resultNode;
      resultNode = find(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();

      return resultNode.data;
   }

   public boolean contains(E x)
   {
      return find(mRoot, x) != null;
   }

   public boolean insert(E x)
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, x);
      return (mSize != oldSize);
   }

   public boolean remove(E x)
   {
      int oldSize = mSize;
      remove(mRoot, x);
      return (mSize != oldSize);
   }

   public boolean collectGarbage()
   {
      this.collectGarbage(mRoot);
      return true;
   }

   public boolean removeHard(E x)
   {
      int oldSize = mSize;
      removeHard(mRoot, x);
      return (mSize != oldSize);
   }

   public <F extends Traverser<? super E>> void traverse(F func)
   {
      traverse(func, mRoot);
   }

   public Object clone() throws CloneNotSupportedException
   {
      LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
      newObject.clear(); // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;

      return newObject;
   }

   // private helper methods ----------------------------------------
   protected LazySTNode<E> findMin(LazySTNode<E> root)
   {
      // sorry for the lengthy name but helps me conceptualize this process.
      LazySTNode<E> putativeLeftChild_Min;

      if (root == null)
         return null;

      // march down the left side of tree until the lftChild becomes null
      // assigning it to putativeLeftChild_Min node sequentially putting it
      // on a stack - unwinding down the tree.
      putativeLeftChild_Min = findMin(root.lftChild);

      // when it hits the bottom it the last nodes left child will be null
      // so it falls through this filter. Otherwise it is returned to the
      // findMin call immediately above. When this is done winding back up
      // the min falls through the the last return root statement.
      if (putativeLeftChild_Min != null)
      {
         return putativeLeftChild_Min;
      }

      // if the putativeLeftChild_Min has fallen through a not-null filter
      // need to check it to see if it has been soft deleted so...
      if (root.deleted)
      {
         // need to check the rtChild since the left node could be extant
         // but "deleted" and if it were hard deleted it would not be here
         return findMin(root.rtChild);
      } else
      {
         return root;
      }
   }

   protected LazySTNode<E> findMinHard(LazySTNode<E> root)
   {
      if (root == null)
         return null;

      if (root.lftChild != null)
      {
         return findMinHard(root.lftChild);
      } else
      {
         return root;
      }
   }

   protected LazySTNode<E> findMax(LazySTNode<E> root)
   {
      // sorry for the lengthy name but helps me conceptualize this process.
      LazySTNode<E> putativeRtChild_Max;

      if (root == null)
         return null;

      // march down the right side of tree until the rtChild becomes null
      // assigning it to putativeRtChild_Max node sequentially putting it
      // on a stack - unwinding down the tree.
      putativeRtChild_Max = findMax(root.rtChild);

      // when it hits the bottom it the last nodes left child will be null
      // so it falls through this filter. Otherwise it is returned to the
      // findMax call immediately above. When this is done winding back up
      // the max falls through the the last return root statement.
      if (putativeRtChild_Max != null)
      {
         return putativeRtChild_Max;
      }

      // if the putativeRtChild_Max has fallen through a not-null filter
      // need to check it to see if it has been soft deleted so...
      if (root.deleted)
      {
         // need to check the lftChild since the right node could be extant
         // but "deleted" and if it were hard deleted it would not be here
         return findMax(root.lftChild);
      } else
      {
         return root;
      }
   }

   protected LazySTNode<E> findMaxHard(LazySTNode<E> root)
   {
      if (root == null)
         return null;

      if (root.rtChild != null)
      {
         return findMaxHard(root.rtChild);
      } else
      {
         return root;
      }
   }

   protected LazySTNode<E> insert(LazySTNode<E> root, E x)
   {
      int compareResult; // avoid multiple calls to compareTo()

      if (root == null)
      {
         mSize++;
         mSizeHard++;
         return new LazySTNode<E>(x, null, null, false);
      }

      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         root.lftChild = insert(root.lftChild, x);
      else if (compareResult > 0)
         root.rtChild = insert(root.rtChild, x);

      return root;
   }

   protected LazySTNode<E> remove(LazySTNode<E> root, E x)
   {
      if (root == null)
         return null;
      if (find(x) != null)
      {
         find(root, x).deleted = true;
         mSize--;
         return root;
      } else
      {
         return null;
      }
   }

   protected LazySTNode<E> collectGarbage(LazySTNode<E> mRoot)
   {

      if (mRoot.rtChild != null)
      {
         collectGarbage(mRoot.rtChild);
      }
      if (mRoot.lftChild != null)
      {
         collectGarbage(mRoot.lftChild);
      }
      if (mRoot.deleted)
      {
        mRoot =  removeHard(mRoot, mRoot.data);

      }
      return mRoot;
      // System.out.println(mRoot.data);

      // removeHard(mRoot);
      // removeHard(mRoot.lftChild);
      // removeHard(mRoot.rtChild);

   }

   protected LazySTNode<E> removeHard(LazySTNode<E> root, E x)
   {
      int compareResult; // avoid multiple calls to compareTo()
      LazySTNode<E> putativeRightChild_min;
         
      if (root == null)
         return null;

      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         root.lftChild = removeHard(root.lftChild, x);
      else if (compareResult > 0)
         root.rtChild = removeHard(root.rtChild, x);

      // found the node
      else if (root.lftChild != null && root.rtChild != null)
      {
        if(!root.deleted) mSize--;
         
            putativeRightChild_min = remove(root, (findMin(root.rtChild).data));
       //  putativeRightChild_min = findMin(root.rtChild);
         root.data = putativeRightChild_min.data;
         root.deleted = putativeRightChild_min.deleted;
         mSizeHard--;
       
       //  putativeRightChild_min.deleted = true;
       //  root.deleted = findMinHard(root.rtChild).deleted;
      //   root.rtChild = this.removeHard(root, root.data);
      } else
      {
       if (!root.deleted) mSize--;
         root = (root.lftChild != null) ? root.lftChild : root.rtChild;
        mSizeHard--;
      }
    
      return root;
   }

   protected <F extends Traverser<? super E>> void traverse(F func,
         LazySTNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      traverse(func, treeNode.lftChild);
      if (!treeNode.deleted)
         func.visit(treeNode.data);
      traverse(func, treeNode.rtChild);
   }

   protected LazySTNode<E> find(LazySTNode<E> root, E x)
   {
      int compareResult; // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         return find(root.lftChild, x);
      if (compareResult > 0)
         return find(root.rtChild, x);
      if (!root.deleted)
         return root; // found
      return null;
   }

   protected LazySTNode<E> cloneSubtree(LazySTNode<E> root)
   {
      LazySTNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new LazySTNode<E>(root.data, cloneSubtree(root.lftChild),
            cloneSubtree(root.rtChild), root.deleted);
      return newNode;
   }

   protected int findHeight(LazySTNode<E> treeNode, int height)
   {
      int leftHeight, rightHeight;
      if (treeNode == null)
         return height;
      height++;
      leftHeight = findHeight(treeNode.lftChild, height);
      rightHeight = findHeight(treeNode.rtChild, height);
      return (leftHeight > rightHeight) ? leftHeight : rightHeight;
   }

   protected void printNode(LazySTNode<E> node)
   {
      if (node != null)
      {
         System.out.println("node.data: " + node.data);
         if (node.rtChild != null)
            System.out.println("\tnode.rtChild: " + node.rtChild.data);
         if (node.lftChild != null)
            System.out.println("\tnode.lftChild: " + node.lftChild.data);
         System.out.println("\tnode.deleted: " + node.deleted);
         System.out.println();
      } else
      {
         System.out.println("this node is null.");
      }

   }

   public void traversey(LazySTNode<E> root)
   { // Each child of a tree is a root of its subtree.
      if (root.lftChild != null)
      {
         traversey(root.lftChild);
      }
      System.out.print(root.data + ", ");
      if (root.rtChild != null)
      {
         traversey(root.rtChild);
      }
   }

   class DeleteObject<E> implements Traverser<E>
   {
      public void visit(E x)
      {

      }

   }

}
