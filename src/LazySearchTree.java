import java.util.NoSuchElementException;
/**
 * This is a binary search tree the implements lazy deletion. It does this
 * through the use of the boolean attribute "deleted" in the LazySTNode object. When
 * an item is removed the attribute is changed to true.  Later garabage collection
 * methods actually delete the node changing it from a soft deletion to a hard
 * deletion, explaining the naming convention for the families of methods such
 * as insert and remove. 
 */

import cs1c.SongEntry;

public class LazySearchTree<E extends Comparable<? super E>> implements
      Cloneable
{

   protected static boolean DEBUG = false;
   protected int mSize;
   protected LazySTNode<E> mRoot;
   protected int mSizeHard;

   // LazySearchTreeTester<E> tester = new LazySearchTreeTester<>(this);

   /**
    * The constructor calls the clear method which sets the mRoot to null and
    * the mSize, mHardSizw to zero.
    */
   public LazySearchTree()
   {
      clear();
   }

   public void clear()
   {
      mSize = 0;
      mSizeHard = 0;
      mRoot = null;
   }

   public boolean empty()
   {
      return (mSize == 0);
   }

   public int size()
   {
      return mSize;
   }

   public int showHeight()
   {
      return findHeight(mRoot, -1);
   }

   public int sizeHard()
   {
      return mSizeHard;
   }

   // The public half of the different find, contains and insert methods
   //===================================================================
   public E findMin()
   {
      LazySTNode<E> resultNode;
      resultNode = findMin(mRoot);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }

   public E findMinHard()
   {
      LazySTNode<E> resultNode;
      resultNode = findMinHard(mRoot);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }

   public E findMax()
   {
      LazySTNode<E> resultNode;
      resultNode = findMax(mRoot);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }

   public E findMaxHard()
   {
      LazySTNode<E> resultNode;
      resultNode = findMaxHard(mRoot);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }

   public E find(E x)
   {
      LazySTNode<E> resultNode;
      resultNode = find(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();

      return resultNode.data;
   }

   public E findHard(E x)
   {
      LazySTNode<E> resultNode;
      resultNode = findHard(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();

      return resultNode.data;
   }

   public boolean contains(E x)
   {
      return find(mRoot, x) != null;
   }

   public boolean containsHard(E x)
   {
      return findHard(mRoot, x) != null;
   }

   public boolean insert(E data)
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, data);
      return (mSize != oldSize);
   }

   public boolean insert(E data, SongEntry se)
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, data, se);
      return (mSize != oldSize);
   }

   public boolean remove(E x)
   {
      int oldSize = mSize;
      remove(mRoot, x);
      return (mSize != oldSize);
   }

   /**
    * This is the public part of a public/protected pair of methods. It accepts
    * no arguments but calls the protected method with the mRoot node to define
    * the starting point of the recursive protected method of the same name.
    */
   public void collectGarbage()
   {
      mRoot = collectGarbage(mRoot);
   }

   /**
    * This is the public part of a public/protected pair of methods. It accepts
    * an arguments of type E. It calls the protected method, adding the mRoot
    * node as an argument to define the starting point of the recursive
    * protected method of the same name.
    * 
    * @param E
    *           x
    */
   public boolean removeHard(E x)
   {
      int oldSize = mSize;
      LazySTNode<E> temp = removeHard(mRoot, x);
      if (temp.equals(mRoot))
         mRoot = temp;
      return (mSize != oldSize);
   }

   /**
    * The public portion of a public/protected method pair. This accepts an
    * argument of type F. Type F is a Functor class that implements the
    * Traverser interface. It contains only one function and is designed to
    * traverse the tree returning nodes that are not soft deleted.
    * 
    * @param func
    */
   public <F extends Traverser<? super E>> void traverseSoft(F func)
   {
      traverseSoft(func, mRoot);
      if (mSize == 0)
         System.out.println("The soft list is empty.");
   }

   public <F extends Traverser<? super E>> void traverseHard(F func)
   {
      if (mRoot == null)
         System.out.println("The hard list is empty.");
      traverseHard(func, mRoot);
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
      if (root.lftChild == null)
         return root;
      return findMinHard(root.lftChild);

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

      // when it hits (unwinds to) the bottom, the last node's left child will
      // be null so it falls through this filter. Otherwise it is returned to
      // the
      // findMax call immediately above. When a null it hit - the base case -
      // it starts returning the results off the stack sequentially, winding
      // back up.
      if (putativeRtChild_Max != null)
      {
         return putativeRtChild_Max;
      }

      // if the putativeRtChild_Max has fallen through a not-null filter
      // need to check it to see if it has been soft deleted so...
      if (root.deleted)
      {
         // need to check the lftChild since the right node could be extant
         // but soft deleted. Normally this step would not be performed
         // because in non-lazy deletion the right node would be null
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
         return new LazySTNode<E>(x, null, null, false, 1, null);
      }

      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
      {
         root.lftChild = insert(root.lftChild, x);

      } else if (compareResult > 0)
      {
         root.rtChild = insert(root.rtChild, x);
      }

      return root;
   }

   /**
    * The protected part of the insert method that takes into account the
    * need to add a songEntry node to the LazySTNode
    * @param root
    * @param x
    * @param se
    * @return
    */
   protected LazySTNode<E> insert(LazySTNode<E> root, E x, SongEntry se)
   {
      int compareResult; // avoid multiple calls to compareTo()

      if (root == null)
      {

         mSize++;
         mSizeHard++;
         return new LazySTNode<E>(x, null, null, false, 1, se);
      }
      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
      {
         root.lftChild = insert(root.lftChild, x, se);

      } else if (compareResult > 0)
      {
         root.rtChild = insert(root.rtChild, x, se);
      }
      if (DEBUG)
      {
         System.out.print("Adding ");

      }
      if (root.deleted)
      {
         mSize++;
         root.deleted = false;
      }
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

   /**
    * Protected method of collect garbage, calls the removeHard function
    * to do the heavy lifting of removal after this method provides the 
    * recursive functionality to traverse the tree.
    * @param root
    * @return
    */
   protected LazySTNode<E> collectGarbage(LazySTNode<E> root)
   {
      if (root == null)
         return null;

      // look for nodes marked deleted in each child sub-tree and lastly
      // the root of the whole tree. Return the mRoot because it could
      // have been changed.
      if (root.lftChild != null)
         root.lftChild = collectGarbage(root.lftChild);
      if (root.rtChild != null)
         root.rtChild = collectGarbage(root.rtChild);
      if (root.deleted)
         root = removeHard(root, root.data);
      return root;
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

      // The program recurses above here until it matches the data
      // Now check to see if it has both children
      else if (root.lftChild != null && root.rtChild != null)
      {
         // if the node has not been previously marked as deleted
         // we need to adjust mSize as it is removed here
         if (!root.deleted)
            mSize--;

         // find the smallest node in the right subtree to replace the
         // root node with
         putativeRightChild_min = findMinHard(root.rtChild);

         // replace the data and deleted values with the min node values
         root.data = putativeRightChild_min.data;
         root.deleted = putativeRightChild_min.deleted;

         // mark the min node as deleted
         putativeRightChild_min.deleted = true;

         // go through and hard remove it
         root.rtChild = removeHard(root.rtChild, root.data);

      } else
      // if there is only one child node
      {
         // if the node has not been previously marked as deleted
         // we need to adjust mSize as it is removed here
         if (!root.deleted)
            mSize--;

         // replace the deleted node with the next lower one on the tree
         root = (root.lftChild != null) ? root.lftChild : root.rtChild;

         // adjust the Hard count
         mSizeHard--;
      }

      return root;
   }

   /**
    * The protected portion of a public/protected method pair. This accepts an
    * argument of type F. Type F is a Functor class that implements the
    * Traverser interface. It contains only one function and is designed to
    * traverse the tree returning nodes that are not soft deleted.
    * 
    * @param func
    * @param LazySTNode
    *           <E>
    */
   protected <F extends Traverser<? super E>> void traverseSoft(F printObject,
         LazySTNode<E> treeNode)
   {
      if (treeNode == null)
         return;
      traverseSoft(printObject, treeNode.lftChild);
      if (!treeNode.deleted)
         printObject.visit(treeNode.data);
      traverseSoft(printObject, treeNode.rtChild);

   }

   /**
    * The protected portion of a public/protected method pair. This accepts an
    * argument of type F and a node. Type F is a Functor class that implements
    * the Traverser interface. It contains only one function and is designed to
    * traverse the tree returning nodes that are not soft deleted.
    * 
    * @param func
    * @param LazySTNode
    *           <E>
    */
   protected <F extends Traverser<? super E>> void traverseHard(F printObject,
         LazySTNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      traverseHard(printObject, treeNode.lftChild);
      printObject.visit(treeNode.data);
      traverseHard(printObject, treeNode.rtChild);
   }

   /**
    * The protected portion of a public/protected method pair. This accepts an
    * argument of type E and a node. It uses the E type argument and compareTo
    * to recursively check every node in the tree and returns it if it is not
    * deleted.
    * 
    * @param func
    * @param LazySTNode
    *           <E>
    */
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

   /**
    * The protected portion of a public/protected method pair. This accepts an
    * argument of type E and a node. It uses the E type argument and compareTo
    * to recursively check every node in the tree and returns it.
    * 
    * @param func
    * @param LazySTNode
    *           <E>
    */
   protected LazySTNode<E> findHard(LazySTNode<E> root, E x)
   {
      int compareResult; // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         return findHard(root.lftChild, x);
      if (compareResult > 0)
         return findHard(root.rtChild, x);
      if (compareResult == 0)
         return root;
      return null;
   }

   protected LazySTNode<E> cloneSubtree(LazySTNode<E> root)
   {
      LazySTNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new LazySTNode<E>(root.data, cloneSubtree(root.lftChild),
            cloneSubtree(root.rtChild), root.deleted, root.itemCount,
            root.songEntry);
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

   public void addSongEntry(SongEntry se)
   {
      String title = se.getTitle();

      insert((E) title, se);
   }

   public void setDEBUG(boolean dbg)
   {
      this.DEBUG = dbg;
   }

}
