import cs1c.SongEntry;

public class MyTunesTester<E extends Comparable<? super E>>
{
   Boolean DEBUG = false;
   static FoothillTunesStore<String> store;
   final static String jsonFilePath = "resources/music_genre_subset.json";
   LazySearchTreeTester tester = new LazySearchTreeTester(store.tunes2, false);
   static MyTunes personalTunes = new MyTunes(store);
  static String stringy = "Screams";
   public static void main(String[] args)
   {

      FoothillTunesStore store = new FoothillTunesStore<>(jsonFilePath);

      // tester.stateOfTree();

      // personalTunes.getStore().tunes2.showNode("Screams");

    
      System.out.println(personalTunes.getStore().tunes2.contains(stringy));
      

   }
   public void newMethod()
   {
      // MyTunes.getStore().LazysearchTree. contains(string))
   if (personalTunes.getStore().tunes2.contains(stringy))
   {
      String temp = (String) personalTunes.getStore().tunes2.find(stringy);
      System.out.println(temp);

     LazySearchTree tempTree = new LazySearchTree();
//      LazySearchTreeTester tempTreeTester = new LazySearchTreeTester(
//            tempTree, true);

//      System.out.println(personalTunes.getStore().tunes2
//            .find(personalTunes.getStore().tunes2.mRoot, stringy)
//            .getSongEntry().getArtistName());
      SongEntry songEntry = personalTunes.getStore().tunes2.find(
            personalTunes.getStore().tunes2.mRoot, stringy).getSongEntry();

      tempTree.insert(stringy, songEntry);
//      System.out.println();
      // tempTree.find(tempTree.mRoot, stringy).setSongEntry(songEntry);
//      System.out.println(tempTree.find(tempTree.mRoot, "Screams")
//            .getSongEntry().getArtistName());
//      tempTreeTester.stateOfTree();

//      System.out.println(tempTree.mSize);
//
//      System.out.println("GGGGGGGGGGGOOOOOOOOOOOOOOOOOOTTTTTTTTTTTTT");
   }
   }

   public void addSong(String title)
   {
      // MyTunes.getStore().LazysearchTree. contains(string))
      if (personalTunes.getStore().tunes2.contains(title))
      {
         String temp = (String) personalTunes.getStore().tunes2.find(title);
         LazySearchTree tempTree = new LazySearchTree();
         SongEntry songEntry = personalTunes.getStore().tunes2.find(
               personalTunes.getStore().tunes2.mRoot, title).getSongEntry();
         tempTree.insert(title, songEntry);
         
      }
   }
}
