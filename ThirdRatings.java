import java.util.*;

public class ThirdRatings {
    public ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
       this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String movieId, int minimalRaters){
        double average = 0.0; 
        int totalRaters = 0;
        double totalRating = 0;
        for(EfficientRater r : myRaters){
            if(r.hasRating(movieId)){
                totalRaters++;
                totalRating += r.getRating(movieId);
            }
        }
        if(totalRaters >= minimalRaters)
            average = totalRating/totalRaters;
        return average;    
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> allRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieId : movies){
            double avg = getAverageByID(movieId, minimalRaters);
            if(avg != 0){
                Rating avgRating = new Rating(movieId, avg);
                allRatings.add(avgRating);
            }
        }
        return allRatings;
    }
    
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> allRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movieId : movies){
            double avg = getAverageByID(movieId, minimalRaters);
            if(avg != 0){
                Rating avgRating = new Rating(movieId, avg);
                allRatings.add(avgRating);
            }
        }
        return allRatings;
    }
}
