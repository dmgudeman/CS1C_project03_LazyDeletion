/**
 * Reads an input file that contains the users selections. 
 * Creates an object of type FoothillTunesStore which parses the JSON file via
 * an object of type MillionSongSubset in class cs1c.
 * 
 * Note:
 * We use our BinarySearchTree class called LazySearchTree to store our SongEntry objects in FoothillTunesStore class.
 * In a similar way, we modify the purchasedTunes attribute of our MyTunes class to be of type LazySearchTree.
 * 
 * @author Foothill College, [YOUR NAME HERE]
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cs1c.SongEntry;
import cs1c.TimeConverter;

public class MyTunes
{
   private static final int QUIT = 0;
   private static final int HELP_MENU = 1;
   private static final int LIST_SONG_TITLES = 2;
   private static final int LIST_SONGS_BY_GENRE = 3;
   private static final int BUY_SONG_TITLE = 4;
   private static final int CREATE_RANDOM_PLAYLIST = 5; /*
                                                         * finds the songs that
                                                         * meets the playlist
                                                         * time requirement
                                                         */
   private static final int ADD_SONG_TO_JUKEBOX = 6;
   private static final int ADD_VIP_SONG_TO_JUKEBOX = 7;

   private static final boolean ENABLE_RANDOM_PURCHASE = false;

   /* From previous project. */
   private static final double REGULAR_JUKEBOX_PRICE = 1;
   private static final boolean ENABLE_DEBUG_SELECTION = false;
   Marcher marcher = new Marcher();

   /*
    * Once a user purchases a song, they can make various selections such as add
    * the song to their play list, play the song, etc.
    * 
    * Note: modified to use our Binary Search Tree called "LazySearchTree".
    */
   private static LazySearchTree<SongEntry> purchasedTunes;

   /*
    * Where all songs from MillionSongSubset are stored.
    * 
    * Note: Need to modify to use our Binary Search Tree called
    * "LazySearchTree".
    */
   private FoothillTunesStore theStore;

   public MyTunes(FoothillTunesStore store)
   {
      this.theStore = store;
   }
   
   public FoothillTunesStore getStore()
   {
      return theStore;
   }

   /**
    * Show all songs that have been purchased.
    */
   public void showLibrary()
   {
      System.out.println(purchasedTunes);
   }

   /**
    * Selections user can make.
    */
   public static void printMenu()
   {
      System.out.println("\nMenu:");
      System.out.println("0. Quit");
      System.out.println("1. Output this menu");
      System.out.println("2. Show all song titles");
      System.out.println("3. Show all songs by genre ");
      System.out.println("4. Buy songs by title");
      System.out.println("5. Create a playlist");
      System.out.println("6. Play my song title (regular priority)");
      System.out.println("7. Play my song title first (high priority)");
   }

   private static ArrayList<String> readTestFile(String tunesTestFilePath)
   {
      // Define and initialize the ArrayList
      ArrayList<String> userInputList = new ArrayList<>(); // The ArrayList
                                                           // stores strings
      String filename = tunesTestFilePath;
      String inline; // Buffer to store the current line
      BufferedReader inFile;
      try
      {
         inFile = new BufferedReader(new FileReader(filename));
         try
         {
            while ((inline = inFile.readLine()) != null) // Read line-by-line,
                                                         // until end of file
            {
               userInputList.add(inline);
            }
            inFile.close(); // We've finished reading the file
         } catch (IOException e)
         {
            e.printStackTrace();
         }
      } catch (FileNotFoundException e1)
      {
         e1.printStackTrace();
      }
      return userInputList;
   }

   private void addSongs(LazySearchTree<SongEntry> searchResult)
   {
      searchResult.traverseHard(marcher);
   }

   /**
    * [TO COMPLETE]
    */
   public static void main(String[] args)
   {
      final String jsonFilePath = "resources/music_genre_subset.json";
      final String tunesTestFilePath = "resources/test_tunes.txt";

      FoothillTunesStore store = new FoothillTunesStore(jsonFilePath);
      LazySearchTree<SongEntry> storeTitles = store.getTitlesLT();
      System.out.println("Welcome! We have over " + storeTitles.sizeHard()
            + " in FoothillTunes Jukebox!");

     ArrayList<String> tunesTestFile =
      MyTunes.readTestFile(tunesTestFilePath);

      MyTunes.printMenu();

      MyTunes personalTunes = new MyTunes(store);
     
  ArrayList<String> linesInFile = MyTunes.readTestFile(tunesTestFilePath);
      int selection = -1;
      long startTime, estimatedTime;
      long containsTitleEstimatedTime = 0;
      int numCallsToContainsTitle = 0;

      /* From previous project */
      // JukeBox touchTunes = new JukeBox("TouchTunes", REGULAR_JUKEBOX_PRICE);

      for (int i = 0; i < linesInFile.size() && selection != QUIT; /*
                                                                    * no need to
                                                                    * increment
                                                                    * here
                                                                    */)
      {
         String line = linesInFile.get(i++);
         String[] tokens = line.split(" ");
         if (line.contains("selection"))
            selection = Integer.parseInt(tokens[1]);
         else
         {
            // invalid selection format
            System.out.printf("WARNING: Invalid selection %d at line %d\n",
                  selection, i);
            continue;
         }

         if (ENABLE_DEBUG_SELECTION)
            System.out.println("\nselected option:" + selection);
         switch (selection)
         {
         case QUIT:
            break;
         case HELP_MENU:
            MyTunes.printMenu();
            break;
         case LIST_SONG_TITLES:
            System.out.println("Number of titles in store = "
                  + purchasedTunes.sizeHard());
            personalTunes.showLibrary();
            break;
         case LIST_SONGS_BY_GENRE:
            // capture start time
            startTime = System.nanoTime();

            // implement grouping songs by genre
            store.groupSongsByGenre();

            // stop and calculate elapsed time
            estimatedTime = System.nanoTime() - startTime;

            // report algorithm time
            System.out.println("\nAlgorithm Elapsed Time: "
                  + TimeConverter.convertTimeToString(estimatedTime) + "\n");

            store.printNumOfSongsInEachGenre();
            break;
         case BUY_SONG_TITLE:
            String title = linesInFile.get(i++);
            if (ENABLE_DEBUG_SELECTION)
               System.out.println("selected song title: " + title);

            // implement searching for songs by title
            ArrayList<SongEntry> searchResult = store.buySongByTitle(title);
            // LazySearchTree<SongEntry> searchResultLT =
            // storeTitles.find(title);<<<<<<<<<<<<<<<<<<<<<<<<<<

            if (ENABLE_DEBUG_SELECTION)
            {
               System.out.println("Found " + searchResult.size() + " song(s):");
               System.out.println(searchResult);
            }

            // personalTunes.addSongs(searchResult);<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            break;

         case CREATE_RANDOM_PLAYLIST:
            // int numSongsToBuy = Integer.parseInt(linesInFile.get(i++));
            // System.out.println("selected number of songs to buy: " +
            // numSongsToBuy);
            //
            // personalTunes.addSongs(store.getFirstNTitles(numSongsToBuy,
            // ENABLE_RANDOM_PURCHASE));
            //
            // int lengthInMinutes = Integer.parseInt(linesInFile.get(i++));
            // int seconds = lengthInMinutes * 60;
            // System.out.println("selected playlist length (in seconds): " +
            // seconds);
            //
            // // capture start time
            // startTime = System.nanoTime();
            //
            // // From previous project
            // LazySearchTree<SongEntry> myPlayList =
            // personalTunes.makePlayList(seconds);
            //
            // // stop and calculate elapsed time
            // estimatedTime = System.nanoTime() - startTime;
            //
            // // output the result
            // int totalTime = 0;
            // for (SongEntry song : myPlayList)
            // {
            // totalTime += song.getDuration();
            // }
            // System.out.println("length of play list (in seconds): " +
            // totalTime);
            // System.out.println("songs in play list: " + myPlayList);
            //
            // // report algorithm time
            // // QUESTION: How much did we improve by using a BinarySearchTree
            // in our FoothillTunesStore?
            // System.out.println("\nAlgorithm Elapsed Time: "
            // + TimeConverter.convertTimeToString(estimatedTime) + "\n");
            // break;
         case ADD_SONG_TO_JUKEBOX:
            // title = linesInFile.get(i++);
            // System.out.println("\njukebox song title: " + title);
            //
            // // to simplify implementation we will assume the root node is
            // what the user wants
            // SongEntry songFound = personalTunes.containsTitle(title);
            // if (songFound == null)
            // {
            // System.out.println("Warning! requested song " + title +
            // "  not found.");
            // continue;
            // }
            //
            // touchTunes.addSong(songFound, REGULAR_JUKEBOX_PRICE);
            //
            // System.out.println(touchTunes);
            // break;
         case ADD_VIP_SONG_TO_JUKEBOX:
            // title = linesInFile.get(i++);
            // System.out.println("\njukebox VIP song title: " + title);
            // double pricePaid = Double.parseDouble(linesInFile.get(i++));
            //
            // // to simplify implementation we will assume the root node is
            // what the user wants
            // songFound = personalTunes.containsTitle(title);
            //
            // if (songFound == null)
            // {
            // System.out.println("Warning! requested song " + title +
            // "  not found.");
            // continue;
            // }
            //
            // touchTunes.addSong(songFound, pricePaid);
            // System.out.println(touchTunes);
            // break;
            // default:
            // System.out.println("ERROR : invalid selection.");
            // MyTunes.printMenu();
            // break;
            // } // switch
            //
            //
            // // From previous project
            // boolean playNextSong = touchTunes.determineIfSongIsPlayed();
            // if (playNextSong)
            // {
            // touchTunes.playSong();
         }
      } // for lines in the file
   } // main method
}
