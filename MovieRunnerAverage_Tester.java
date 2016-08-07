 import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        String moviefile = "/data/ratedmoviesfull.csv";
        String ratingsfile = "/data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        
        System.out.println("Num of movies: " + sr.getMovieSize());
        System.out.println("Num of rater: " + sr.getRaterSize());
        System.out.println("Avg rating: " + sr.getAverageRatings(12));      
    }
    
    public void getAverageRatingOneMovie(){
        String moviefile = "/data/ratedmoviesfull.csv";
        String ratingsfile = "/data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        
        String title = "Vacation";
        boolean found = false;
        ArrayList<Rating> result = sr.getAverageRatings(1);
        for(Rating r : result){
            if(r.getItem().equals(title)){
                System.out.println("Avg rating for given movie: " + r.getValue());
                found = true;
            }
        }
        if(!found)
            System.out.println("No such Title");
    }
    
}
