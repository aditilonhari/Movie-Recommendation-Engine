import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public Map<String, EfficientRater> raterMap = new HashMap<String, EfficientRater>();
    public Map<String, ArrayList<String>> directorToMovieMap = new HashMap<String, ArrayList<String>>();
    public Map<String, ArrayList<Double>> moviesMap = new HashMap<String, ArrayList<Double>>();
    
    public ArrayList<EfficientRater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<EfficientRater> allRaters = new ArrayList<EfficientRater>();
        for (CSVRecord record : parser){
            String rater_id  = record.get("rater_id");
            if(raterMap.containsKey(rater_id)){
                EfficientRater rater = raterMap.get(rater_id);
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            }
            else{
                EfficientRater newRater = new EfficientRater(rater_id);
                newRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raterMap.put(rater_id, newRater);
                allRaters.add(newRater);
            }
        }
        return allRaters;
    }
    
    private void buildMoviesMap(ArrayList<EfficientRater> raterList){
        
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
    
    public ArrayList<Movie> loadMovies(String filename){
        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> moviesCollection = new ArrayList<Movie>();
        
        for (CSVRecord record : parser){
            Movie newMovie = new Movie(
                  record.get("id"),
                  record.get("title"),
                  record.get("year"),
                  record.get("genre"),
                  record.get("director"),
                  record.get("country"),
                  record.get("poster"),               
                  Integer.parseInt(record.get("minutes"))
            );
            moviesCollection.add(newMovie);
        }
        return moviesCollection;
    }
    
    private  void buildDirectorToMovieMap(ArrayList<Movie> movieList){
        
        for(Movie movie: movieList){
            String movieName = movie.getTitle();
            String directors = movie.getDirector();
            
            if(directors.indexOf(',') == -1){
                buildDirectorToMovieArray(directors, movieName);
            }
            else{
                int comma = directors.indexOf(',');
                while(true){
                    String director1 = directors.substring(0, comma);
                    buildDirectorToMovieArray(director1, movieName);
                    if(directors.indexOf(',', comma+1) != -1)
                        comma = directors.indexOf(',', comma+1);
                    else
                        break;
                }
                String director2 = directors.substring(comma+1);
                buildDirectorToMovieArray(director2, movieName);   
            }
        }
    }
    
    private void buildDirectorToMovieArray(String director, String moviename){
         director = director.trim();        
          if(directorToMovieMap.containsKey(director)){
              ArrayList<String> previousMovies = directorToMovieMap.get(director);
              previousMovies.add(moviename);
              directorToMovieMap.put(director, previousMovies);
          }
          else{
              ArrayList<String> moviesForOneDirector = new ArrayList<String>();
              moviesForOneDirector.add(moviename);
              directorToMovieMap.put(director, moviesForOneDirector);
          }
    }
    
     private int findRatingsForMovie(String moviename){
        if(moviesMap.containsKey(moviename)){
            return moviesMap.get(moviename).size();
        }
        return 0;
    }
    
    private int getMaxRating(ArrayList<EfficientRater> raterList){
        int max = 0;
        for(EfficientRater r  : raterList){
            if(r.numRatings() > max){
                max = r.numRatings();
            }
        }
        return max;
    }
    
    private int getNumOfRating(String id){
        if(raterMap.containsKey(id)){
            EfficientRater r = raterMap.get(id);
            return r.numRatings();
        }
        return 0;
    }
    
    public void testLoadMovies(){
        String filename = "/data/ratedmovies_short.csv";
        ArrayList<Movie> movieList = loadMovies(filename);
        System.out.println("Number of movies: " + movieList.size());
        
        // Count number of Comedy movies
        int countComedy=0;    
        for(Movie movie: movieList){
            String genres = movie.getGenres();
            if(genres.indexOf("Comedy") != -1){
                countComedy++;
            }
        }

        // Count number of movies greater than runtime of 150min
        int countLengthyMovies=0;    
        for(Movie movie: movieList){
            int movieLength = movie.getMinutes();
            if(movieLength > 150){
                countLengthyMovies++;
            }
        }
        
        // Calculate the max no of movies directed by a single director and list all directors with that max count
        buildDirectorToMovieMap(movieList);

        int max = 0;
        for(ArrayList<String> movies: directorToMovieMap.values()){
            if(movies.size() > max){
                max = movies.size();
            }
        } 

        int count =0; String indexKey = "";
        for(Map.Entry<String, ArrayList<String>> entry : directorToMovieMap.entrySet()){
            ArrayList<String> movies = entry.getValue();
            if(movies.size() == max){
                indexKey = entry.getKey();
                count++;
            }
        }
    }
    
     
    public void testLoadRaters(){
        String filename = "/home/neerad/Desktop/BlueJ Programming Assignments/Course5/data/ratings_short.csv";
        ArrayList<EfficientRater> ratersList = loadRaters(filename);
        System.out.println("Number of raters: " + ratersList.size());
        
        String rater_id = "193";
        System.out.println("Number of ratings for id: " + rater_id + " : " + getNumOfRating(rater_id));
        
        int maxRating = getMaxRating(ratersList);
        System.out.println("Max rating: " + maxRating);
        
        int countMax = 0;
        for(EfficientRater r : ratersList){
            if(r.numRatings() == maxRating){
                countMax++;
            }
        }
        System.out.println("Count for max rating: " + countMax);
        
        String movie = "1798709";
        buildMoviesMap(ratersList);
        System.out.println("Rating for given movie: " + movie + " : " + findRatingsForMovie(movie));
        
        System.out.println("Unique rated movies count: " + moviesMap.size());
        
    }
    
}
