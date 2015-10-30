public class tester
{
   public static void main(String[] args)
   {
      int k;
      LazySearchTreeTester tester = new LazySearchTreeTester();
      LazySearchTree<Integer> testTree = new LazySearchTree<Integer>();
      PrintObject<Integer> printObject = new PrintObject<Integer>();

      // testTree.traverse(printObject);
      // tester.showSizes(testTree);

      // System.out.println("\n\nShould not find anything");
      // tester.stateOfTree(testTree);
      testTree.insert(70);
      testTree.insert(69);
      testTree.insert(75);
      testTree.insert(74);
      testTree.insert(76);
      // testTree.insert(80);
      // testTree.insert(79);

      // System.out.println("\nTHE TREE TRAVERSAL");
      // testTree.traverse(printObject);

      // tester.stateOfTree(testTree);
      // System.out.println("find function should equal 60:   " +
      // testTree.find(60));
      tester.stateOfTree(testTree);
      testTree.traverse(printObject);

      System.out.println();
      testTree.showTreeHard(testTree);

      System.out.println("Soft removed 75");
      testTree.remove(75);

      // testTree.remove(70);
      // testTree.remove(80);
      tester.stateOfTree(testTree);
      System.out.print("Tree by PrintObject: ");
      testTree.traverse(printObject);
      System.out.println();
      testTree.showTreeHard(testTree);

      // System.out.println("Hard removed 75 ");
      // testTree.removeHard(75);

      // testTree.removeHard(80);

      // tester.stateOfTree(testTree);
      // System.out.print("Tree by PrintObject: ");
      // testTree.traverse(printObject);
      // System.out.println();
      // testTree.showTreeHard(testTree);

      testTree.collectGarbage();
      System.out.println("\nafter garbage collection: ");
      tester.stateOfTree(testTree);
      testTree.traverse(printObject);
      System.out.println();
      testTree.showTreeHard(testTree);

      testTree.remove(70);

      System.out.println("\nafter soft remove 70 (mRoot): ");
      tester.stateOfTree(testTree);
      testTree.traverse(printObject);
      System.out.println();
      testTree.showTreeHard(testTree);

      testTree.collectGarbage();
      System.out.println("\nafter garbage collection: ");
      tester.stateOfTree(testTree);
      testTree.traverse(printObject);
      System.out.println();
      testTree.showTreeHard(testTree);

   }

}
