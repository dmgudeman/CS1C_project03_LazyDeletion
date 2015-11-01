import cs1c.SongEntry;




public class MyTunesTester<E extends Comparable<? super E>>
{
   Boolean DEBUG = false;
 
   public static void main(String[] args)
   {
    
      final String jsonFilePath = "resources/music_genre_subset.json";
      FoothillTunesStore store = new FoothillTunesStore<>(jsonFilePath);  
      LazySearchTreeTester tester = new LazySearchTreeTester(store.tunes2, false);
      MyTunes personalTunes = new MyTunes(store);
   
      
      
   //   tester.stateOfTree();
      
   //    personalTunes.getStore().tunes2.showNode("Screams");
      
      String stringy = "Screams";
      System.out.println(personalTunes.getStore().tunes2.contains(stringy));
      if (personalTunes.getStore().tunes2.contains(stringy))
      {
         String temp =  (String) personalTunes.getStore().tunes2
               .find(stringy);
         System.out.println(temp);
     
         LazySearchTree tempTree = new LazySearchTree();
         LazySearchTreeTester tempTreeTester = new LazySearchTreeTester(tempTree, true);
        
         System.out.println(personalTunes.getStore().tunes2.find(personalTunes.getStore().tunes2.mRoot, stringy).getSongEntry().getArtistName());
         SongEntry songEntry = personalTunes.getStore().tunes2.find(personalTunes.getStore().tunes2.mRoot, stringy).getSongEntry();
     
tempTree.insert(tempTree.mRoot,  stringy,  songEntry );
System.out.println();
//tempTree.find(tempTree.mRoot, stringy).setSongEntry(songEntry);
System.out.println(tempTree.find(tempTree.mRoot, "Screams"));
tempTreeTester.stateOfTree();
       
System.out.println(tempTree.mSize);
    
      
       System.out.println("GGGGGGGGGGGOOOOOOOOOOOOOOOOOOTTTTTTTTTTTTT");
      }

     
   }

}
