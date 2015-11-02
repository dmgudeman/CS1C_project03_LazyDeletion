/**
 * One object of class MillionSongDataSubset parses a JSON data set and
 * stores each entry in an array.
 * 
 * @author CS1C, Foothill College, DavidGudeman
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cs1c.SongEntry;

public class FoothillTunesStore<E extends Comparable<? super E>> implements
      Iterable<SongEntry>
{
   private static final boolean ENABLE_DATA_OUTPUT = false;

   // ArrayList<SongEntry> tunes;
   private ArrayList<Genre> genres;
   public SongEntry[] arrayOfSongs;
   private JSONArray allSongs;
   LazySearchTree<E> tunes2;

   public FoothillTunesStore(String jsonFileName)
   {
      this.tunes2 = new LazySearchTree<>();

      // ArrayList<SongEntry> songEntryList = new ArrayList<>();
      String jsonFilePath = jsonFileName;
      JSONParser jsonParser = new JSONParser();
      try
      {
         // --------------------
         // parse the JSON file
         FileReader fileReader = new FileReader(jsonFilePath);
         JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
         JSONArray allSongs = (JSONArray) jsonObject.get("songs");
         System.out.println("Parsing JSON file...");
         // MillionSongDataSubset dataSet = new MillionSongDataSubset(allSongs);

         // --------------------
         // create an array of all the JSON objects
         arrayOfSongs = new SongEntry[allSongs.size()];
         System.out.println("arrayOfSongs length: " + arrayOfSongs.length);
         // tunes = new ArrayList<>();

         Iterator<?> iterator = allSongs.iterator();
         int counter = 0;
         while (iterator.hasNext() && counter < allSongs.size())
         {
            JSONObject currentJson = (JSONObject) iterator.next();
            String title = currentJson.get("title").toString();
            int duration = (int) Double.parseDouble(currentJson.get("duration")
                  .toString());
            String artist = currentJson.get("artist_name").toString();
            String genre = currentJson.get("genre").toString();

            SongEntry currentSong = new SongEntry(title, duration, artist,
                  genre);

           LazySearchTreeTester<E> tester = new LazySearchTreeTester<E>(
                 tunes2, true);
            
            tunes2.addSongEntry(currentSong);
         //  tester.stateOfTree();
            
            

            arrayOfSongs[counter++] = currentSong;
         }
         // makeListOfSongs(arrayOfSongs, songEntryList);
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      } catch (IOException e)
      {
         e.printStackTrace();
      } catch (ParseException e)
      {
         e.printStackTrace();
      }
      tunes2.setDEBUG(false);
   //   tunes2.showNode("Boppers Boogie Woogie");
      // System.out.println(tunes2.find("I want You"));
   }

   /**
    * returns the array of song entries
    */
   public void makeListOfSongs(SongEntry[] entries,
         ArrayList<SongEntry> songEntryList)
   {
      for (int i = 0; i < entries.length; i++)
      {
         songEntryList.add(entries[i]);
      }
   }

   /**
    * displays the array of song entries
    */
   public void printAllSongs()
   {
      for (SongEntry song : arrayOfSongs)
         System.out.println(song);
   }

   public void printArrayListTunes()
   {
      // int counter = 1;
      // for (SongEntry s : tunes)
      // {
      // System.out.println();
      // System.out.println("Song number " + counter);
      // System.out.println(s.getArtistName());
      // System.out.println(s.getDuration());
      // System.out.println(s.getGenre());
      // System.out.println(s.getTitle());
      // counter++;
      // }
   }

   public void printSubListTunes(ArrayList<SongEntry> song)
   {
      System.out.println("Number of found songs: " + song.size());
      for (SongEntry s : song)
      {
         System.out.println(s.toString());
      }
   }

   LazySearchTree<E> foundTitles;

   public void findSongByTitle(String title)
   {
      LazySearchTree<E> foundTitles = new LazySearchTree<E>();
      if (tunes2.mRoot.data.equals(title) && !tunes2.mRoot.deleted)
         foundTitles.insert((E) title);
      // findSongByTitle(tunes2.mRoot, title);<<<<<<<<<<<<<<<<<<<<<<<<
   }

   private LazySTNode<E> findSongByTitle(
         LazySTNode<E> root, String title)
   {
      try
      {
         if (root == null)
            return null;

         if (root.lftChild != null)
         {
            if (root.lftChild.data.equals(title) && !root.lftChild.deleted)
            {
               foundTitles.insert((E) title);
               root.lftChild = findSongByTitle(root.lftChild, title);
            }
         }
         if (root.rtChild != null)
         {
            if (root.rtChild.data.equals(title) && !root.rtChild.deleted)
            {
               foundTitles.insert((E) title);
               root.lftChild = findSongByTitle(root.lftChild, title);
            }
         }

         return root;

      } catch (Exception e)
      {
         System.out.println("Title not found");
         return null;
      }
   }

   public void printSong(SongEntry song)
   {
      System.out.println(song.getTitle());
   }

   public ArrayList<SongEntry> getTitles()
   {
      ArrayList<SongEntry> titles = new ArrayList<>();
      // for (SongEntry s : tunes)
      // {
      // titles.add(s);
      // }
      // System.out.println("titles is ArrayList<String> of size: "
      // + titles.size());
      return titles;
   }

   public LazySearchTree<SongEntry> getTitlesLT()
   {
      LazySearchTree<SongEntry> titlesLT = new LazySearchTree<SongEntry>();
      // for (SongEntry s : tunes)
      // {
      // titlesLT.insert(s);
      // }
      // System.out.println("titles is LazySearchTree of SongEntry nodes of size: "
      // + titlesLT.size());
      return titlesLT;
   }

   public Object getFirstNTitles(int numSongsToBuy, boolean enableRandomPurchase)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void groupSongsByGenre()
   {
      // TODO Auto-generated method stub

   }

   public void printNumOfSongsInEachGenre()
   {
      // TODO Auto-generated method stub

   }

//   public ArrayList<SongEntry> buySongByTitle(String title)
//   {
//      ArrayList<SongEntry> searchResult = new ArrayList<>();
//      // for(SongEntry se : tunes)
//      // {
//      // if (se.getTitle().equals(title))
//      // {
//      // searchResult.add(se);
//      // }
//      // }
//      return searchResult;
//   }
//   
//   public LazySearchTree<E> buySongByTitle(String title)
//   {
//      
//   }
   
  
   
//   private LazySTNode<E> buySongByTitle(LazySTNode<E> root, E x, SongEntry se)
//   {
//      
//   }
//   {
//      int compareResult; // avoid multiple calls to compareTo()
//
//      if (root == null)
//      {
//
//         mSize++;
//         mSizeHard++;
//         return new LazySTNode<E>(x, null, null, false, 1, se);
//      }
//      compareResult = x.compareTo(root.data);
//      if (compareResult < 0)
//      {
//         root.lftChild = insert(root.lftChild, x, se);
//
//      } else if (compareResult > 0)
//      {
//         root.rtChild = insert(root.rtChild, x, se);
//      }
//      if (DEBUG)
//      {
//         System.out.print("Adding ");
//
//      }
//      if (root.deleted)
//      {
//         mSize++;
//         root.deleted = false;
//      }
//      return root;
//   }

   @Override
   public Iterator iterator()
   {
      // TODO Auto-generated method stub
      return null;
   }

}
