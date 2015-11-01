/**
 * Define the class PrintObject to implement Traverser Note: You may modify
 * class PrintObject as you see fit.
 */
class PrintObject<E> implements Traverser<E>
{
   @Override
   public void visit(E x)
   {
      System.out.print(x + " ");
   }
};