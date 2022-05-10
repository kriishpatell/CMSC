package Labs.Lab3;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;
import java.util.List;
import java.util.ArrayList;

public class SortingLab {
    public static void main(String[] args)
    {
        Bridges bridges = new Bridges(3, "kriishpatell", "666459817853");
        DataSource ds = bridges.getDataSource();
        List<ActorMovieIMDB> movieData = null;

        try {
            movieData = ds.getActorMovieIMDBData(Integer.MAX_VALUE);
            //ds.getSongsByArtist();
        } catch (Exception e) {
            System.out.println("Unable to connect to Bridges");
        }

        for(int i = 0; i < 5; i++) {
            ActorMovieIMDB entry = movieData.get(i);
            System.out.println("" + i + ".  " + entry.getActor() + " was in " + entry.getMovie());
        }

        ArrayList<ActorMovieIMDB> filteredMovieList = new ArrayList<>();

        System.out.println("\nThese are the actors of \"Being John Malkovich\"");
        for(ActorMovieIMDB movie : movieData){
        if(movie.getMovie().equals("Being_John_Malkovich_(1999)")){
            System.out.println(movie.getActor());
            filteredMovieList.add(movie);
            }
        } 
        
        System.out.println("\nThese are the actors of \"Being John Malkovich\" sorted by first name");
        filteredMovieList.sort(new ActorComparator());
        for(ActorMovieIMDB movie : filteredMovieList){
            System.out.println(movie.getActor());
        }
    }
}



