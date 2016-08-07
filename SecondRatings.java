import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    private Map<String, ArrayList<Double>> moviesMap;
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
        
        buildMoviesMap(myRaters);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
     
    private void buildMoviesMap(ArrayList<EfficientRater> raterList){
        moviesMap = new HashMap<String, ArrayList<Double>>();
        for(EfficientRater eachRater : raterList){
            ArrayList<String> movieIds = eachRater.getItemsRated();
            for(String movieId: movieIds){
               double rating = eachRater.getRating(movieId);
               if(moviesMap.containsKey(movieId)){
                   ArrayList<Double> tempRating = moviesMap.get(movieId); 
                   tempRating.add(rating);
                   moviesMap.put(movieId, tempRating);
               }
               else{
                   ArrayList<Double> ratings = new ArrayList<Double>();
                   ratings.add(rating);
                   moviesMap.put(movieId, ratings);
               }
            }
        }
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double average = 0.0;
        
        if(moviesMap.containsKey(id)){
            ArrayList<Double> ratingsById = moviesMap.get(id);
         
            if(ratingsById.size() >= minimalRaters){
                double totalRating = 0;
                for(double rating : ratingsById){
                    totalRating += rating;
                }
                double avg = totalRating/ratingsById.size();
                return avg;
            }   
        }
        return average;    
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> result = new ArrayList<Rating>();
        
        Set<Map.Entry<String, ArrayList<Double>>> moviesSet = moviesMap.entrySet();
        for(Map.Entry<String, ArrayList<Double>> movieId : moviesSet){
                double avgRating = getAverageByID(movieId.getKey() ,minimalRaters);
                if(avgRating != 0){
                    Rating r = new Rating(getTitle(movieId.getKey()), avgRating);
                    result.add(r);
                }
        }
        Collections.sort(result);
        return result;
    }
    
    public String getTitle(String id){
        String result = "Id was not found";
        
        for(Movie m : myMovies){
            String movieId = m.getID().trim();
            if(movieId.equals(id)){
                result = m.getTitle();
                return result;
            }
        }
        return result;
    }
    
    public String getID(String title){
        String id = "NO SUCH TITLE";
    
        for(Movie m : myMovies){
            String movieTitle = m.getTitle();
            if(movieTitle.equals(title)){
                id = m.getID();
                return id;
            }
        }
        return id;
    }
}
