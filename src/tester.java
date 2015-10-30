public class tester
{
   public static void main(String[] args)
   {
      int k;
      LazySearchTreeTester tester = new LazySearchTreeTester();
      LazySearchTree<Integer> testTree = new LazySearchTree<Integer>();
      PrintObject<Integer> printObject = new PrintObject<Integer>();

  //    testTree.traverse(printObject);
  //    tester.showSizes(testTree);

   //   System.out.println("\n\nShould not find anything");
  //    tester.stateOfTree(testTree);
      testTree.insert(70);
      testTree.insert(69);
      testTree.insert(68);
      testTree.insert(67);
      testTree.insert(72);
      testTree.insert(80);
      testTree.insert(79);

  //    System.out.println("\nTHE TREE TRAVERSAL");
  //    testTree.traverse(printObject);

 //     tester.stateOfTree(testTree);
      // System.out.println("find function should equal 60:   " +
      // testTree.find(60));
      tester.stateOfTree(testTree);
      System.out.println("Soft removed 67 ");
      testTree.remove(67);
      tester.stateOfTree(testTree);
      System.out.println("Hard removed 67 ");
      testTree.removeHard(67);
      tester.stateOfTree(testTree);
      // testTree.remove(70);
      // testTree.remove(81);
    
      tester.stateOfTree(testTree);
      testTree.traverse(printObject);
   

      testTree.collectGarbage();
      System.out.println("\nafter garbage collection: ");
      tester.stateOfTree(testTree);
      testTree.traverse(printObject);
      System.out.println();
      testTree.traversey(testTree.mRoot);

   }

}
