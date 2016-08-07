import java.util.*;

public class MovieRunnerSimilarRatings {
    
    private void initialize(){
        String ratingsfile = "/data/ratings.csv";
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Num of raters: " + RaterDatabase.size());
        
        
        String moviefile = "/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());   
    }
    
    public void printAverageRatings(){
        initialize();
        ArrayList<Rating> movieRatings = FourthRatings.getAverageRatings(1);
        System.out.println("Num of movie with ratings: " + movieRatings.size());
        Collections.sort(movieRatings);

        for(Rating r : movieRatings){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            System.out.println( r.getValue() + "  " + movieTitle);
        }
    }
    
    
    public void printAverageRatingsByYearAfterAndGenre(){
        initialize();        
        AllFilters allCriteria = new AllFilters();
        allCriteria.addFilter(new YearAfterFilter(1980));
        allCriteria.addFilter(new GenreFilter("Romance"));
        
        ArrayList<Rating> movieRatingsByAllCriteria = FourthRatings.getAverageRatingsByFilter(1, allCriteria);
        System.out.println("Num of movie with ratings by allcriteria: " + movieRatingsByAllCriteria.size());
        Collections.sort(movieRatingsByAllCriteria);
        for(Rating r : movieRatingsByAllCriteria){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            String movieGenres = MovieDatabase.getGenres(r.getItem());
            int movieYear = MovieDatabase.getYear(r.getItem()); 
            System.out.println( r.getValue() + " " + movieYear + " " + movieTitle);
            System.out.println("\t" + movieGenres);
        }
    }
    
    public void printSimilarRatings(){
        initialize();
        String r_id = "71";
        int topSimilarRaters = 20;
        int minimalSimilarRaters = 5;
        ArrayList<Rating> recommendedMovies = FourthRatings.getSimilarRatings(r_id, topSimilarRaters, minimalSimilarRaters);
        System.out.println("Recommended Movies:\n" + MovieDatabase.getTitle(recommendedMovies.get(0).getItem()));
    }

    
    public void printSimilarRatingsByGenre(){
        initialize();
        String r_id = "964";
        int topSimilarRaters = 20;
        int minimalSimilarRaters = 5;
        GenreFilter genre = new GenreFilter("Mystery");
        ArrayList<Rating> recommendedMovies = FourthRatings.getSimilarRatings(r_id, topSimilarRaters, minimalSimilarRaters, genre);
        System.out.println("Recommended Movies:\n" + MovieDatabase.getTitle(recommendedMovies.get(0).getItem()));
    }
    
    
    public void printSimilarRatingsByDirector(){
        initialize();
        String r_id = "120";
        int topSimilarRaters = 10;
        int minimalSimilarRaters = 2;
        DirectorsFilter directors = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> recommendedMovies = FourthRatings.getSimilarRatings(r_id, topSimilarRaters, minimalSimilarRaters, directors);
        System.out.println("Recommended Movies:\n" + MovieDatabase.getTitle(recommendedMovies.get(0).getItem()));
    }

    
   public void printSimilarRatingsByGenreAndMinutes(){
        initialize();
        
        String r_id = "168";
        int topSimilarRaters = 10;
        int minimalSimilarRaters = 3;
        
        AllFilters multifilters = new AllFilters();
        multifilters.addFilter(new GenreFilter("Drama"));
        multifilters.addFilter(new MinutesFilter(80, 160));
                
        ArrayList<Rating> recommendedMovies = FourthRatings.getSimilarRatings(r_id, topSimilarRaters, minimalSimilarRaters, multifilters);
        System.out.println("Recommended Movies:\n" + MovieDatabase.getTitle(recommendedMovies.get(0).getItem()));
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        initialize();
        
        String r_id = "314";
        int topSimilarRaters = 10;
        int minimalSimilarRaters = 5;
        
        AllFilters multifilters = new AllFilters();
        multifilters.addFilter(new YearAfterFilter(1975));
        multifilters.addFilter(new MinutesFilter(70, 200));
                
        ArrayList<Rating> recommendedMovies = FourthRatings.getSimilarRatings(r_id, topSimilarRaters, minimalSimilarRaters, multifilters);
        System.out.println("Recommended Movies:\n" + MovieDatabase.getTitle(recommendedMovies.get(0).getItem()));
    }
}
