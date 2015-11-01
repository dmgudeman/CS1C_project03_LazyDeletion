

import java.util.ArrayList;

import cs1c.SongEntry;

/**
 * Class allows the user to access the song list by genre. Separate ArrayLists
 * are constructed to make quicker searching availble it that were to be
 * implemented.
 * 
 * @author davidgudeman
 *
 */
public class Genre
{
   public int index = 0;

   ArrayList<SongEntry> classical = new ArrayList<>();
   ArrayList<SongEntry> soul = new ArrayList<>();
   ArrayList<SongEntry> rock = new ArrayList<>();
   ArrayList<SongEntry> dance = new ArrayList<>();
   ArrayList<SongEntry> folk = new ArrayList<>();
   ArrayList<SongEntry> punk = new ArrayList<>();
   ArrayList<SongEntry> metal = new ArrayList<>();
   ArrayList<SongEntry> jazz = new ArrayList<>();

   /**
    * Extracts a list of the songs from a FoothillTunesStore object and
    * separates them into separate genres.
    * 
    * @param store
    */
   public Genre(FoothillTunesStore store)
   {
//      ArrayList<SongEntry> tunes = store.tunes;
//      for (SongEntry s : tunes)
//      {
//         if (s.getGenre().equals("classical"))
//         {
//            classical.add(s);
//         } else if (s.getGenre().equals("classic pop and rock"))
//         {
//            rock.add(s);
//         } else if (s.getGenre().equals("dance and electronica"))
//         {
//            dance.add(s);
//         } else if (s.getGenre().equals("folk"))
//         {
//            folk.add(s);
//         } else if (s.getGenre().equals("punk"))
//         {
//            punk.add(s);
//         } else if (s.getGenre().equals("metal"))
//         {
//            metal.add(s);
//         } else if (s.getGenre().equals("jazz and blues"))
//         {
//            jazz.add(s);
//         }
//      }
   }

   /**
    * Prints out the arraylists by taking in one of the genre lists
    * 
    * @param list
    */
   public void printArrayListTunes(ArrayList<SongEntry> list)
   {
      int counter = 1;
      for (SongEntry s : list)
      {
         System.out.println(s.getTitle() + ", " + s.getArtistName() + ", "
               + s.getGenre() + ", " + s.getDuration());
         counter++;
      }
      System.out.println("The list has " + counter + " elements.");
   }

   /**
    * prints out all the genres
    */
   public void printByGenre()
   {
      System.out.println("CLASSICAL:_________________________________");
      printArrayListTunes(classical);
      System.out.println();
      System.out.println("ROCK:_________________________________");
      printArrayListTunes(rock);
      System.out.println();
      System.out.println("DANCE AND ELECTRONIC___________________");
      printArrayListTunes(dance);
      System.out.println();
      System.out.println("FOLK:_________________________________");
      printArrayListTunes(folk);
      System.out.println();
      System.out.println("PUNK:_________________________________");
      printArrayListTunes(punk);
      System.out.println();
      System.out.println("METAL:_________________________________");
      printArrayListTunes(metal);
      System.out.println();
      System.out.println("JAZZ:_________________________________");
      printArrayListTunes(jazz);
      System.out.println();
   }
   public void printNumOfSongsInEachGenre()
   {   
      System.out.println("Number of Classical: " + classical.size());
      System.out.println("Number of rock: " + rock.size());
      System.out.println("Number of dance: " + dance.size());
      System.out.println("Number of folk: " + folk.size());
      System.out.println("Number of punk: " + punk.size());
      System.out.println("Number of metal: " + metal.size());
      System.out.println("Number of jazz: " + jazz.size());         
   }

}
