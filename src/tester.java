public class tester
{
   public static void main(String[] args)
   {

      LazySearchTree<Integer> testTree = new LazySearchTree<Integer>();
      LazySearchTreeTester<Integer> tester = new LazySearchTreeTester(testTree);

      System.out.println("Tree populated by insertion:");
      testTree.insert(70);
      testTree.insert(69);
      testTree.insert(75);
      testTree.insert(74);
      testTree.insert(76);
      testTree.insert(80);
      testTree.insert(79);
      tester.stateOfTreeWithShowTree();

      System.out.println("find function should equal 69:   "
            + testTree.find(69));

      System.out.println("Soft removed 75, 79 and 80");
      testTree.remove(75);
      testTree.remove(79);
      testTree.remove(80);
      tester.stateOfTreeWithShowTree();
      
      System.out.println("Insert 75, a node that was soft removed");
      testTree.insert(75);
      tester.stateOfTreeWithShowTree();

      System.out.println("Hard removed 75 ");
      testTree.removeHard(75);
      tester.stateOfTreeWithShowTree();

      System.out.println("\nafter garbage collection: ");
      testTree.collectGarbage();    
      tester.stateOfTreeWithShowTree();

      System.out.println("\nafter soft remove 70 (mRoot): ");
      testTree.remove(70);
      tester.stateOfTreeWithShowTree();

      System.out.println("\nafter garbage collection: ");
      testTree.collectGarbage();    
      tester.stateOfTreeWithShowTree();

      System.out.println("\nafter soft remove all nodes: ");
      testTree.remove(69);
      testTree.remove(76);
      testTree.remove(74);
      tester.stateOfTreeWithShowTree();

      System.out.println("\nafter garbage collection: ");
      testTree.collectGarbage();    
      tester.stateOfTreeWithShowTree();
   }

}
