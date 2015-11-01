
public class Marcher<E> extends LazySearchTree implements Traverser<E> 
{
   @Override
   public void visit(Object x)
   {
      this.insert((Comparable) x);
      
   }  
}
