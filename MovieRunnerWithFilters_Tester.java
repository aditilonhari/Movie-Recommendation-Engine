 import java.util.*;

public class MovieRunnerWithFilters {
    
    
    public void printAverageRatings(){
        String ratingsfile = "/data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Num of raters: " + tr.getRaterSize());
        
        String moviefile = "/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> movieRatings = tr.getAverageRatings(35);
        System.out.println("Num of movie with ratings: " + movieRatings.size());
        Collections.sort(movieRatings);

        for(Rating r : movieRatings){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            System.out.println( r.getValue() + "  " + movieTitle);
        }
    }
    
    public void printAverageRatingsByYear(){
        String ratingsfile = "/data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Num of raters: " + tr.getRaterSize());
        
        String moviefile = "/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());
        
        YearAfterFilter year = new YearAfterFilter(2000);
        ArrayList<Rating> movieRatingsAfterYear = tr.getAverageRatingsByFilter( 20,year);
        System.out.println("Num of movie with ratings after year: " + movieRatingsAfterYear.size());
        Collections.sort(movieRatingsAfterYear);
        for(Rating r : movieRatingsAfterYear){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            int movieYear = MovieDatabase.getYear(r.getItem()); 
            System.out.println( r.getValue() + " " + movieYear + " " + movieTitle);
        }
    }
    
    public void printAverageRatingsByGenre(){
        String ratingsfile = "/data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Num of raters: " + tr.getRaterSize());
        
        String moviefile = "/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());
        
        GenreFilter genre = new GenreFilter("Comedy");
        ArrayList<Rating> movieRatingsForGenre = tr.getAverageRatingsByFilter( 20,genre);
        System.out.println("Num of movie with ratings for genre: " + movieRatingsForGenre.size());
        Collections.sort(movieRatingsForGenre);
        for(Rating r : movieRatingsForGenre){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            String movieGenres = MovieDatabase.getGenres(r.getItem()); 
            System.out.println( r.getValue() + " " + movieTitle);
            System.out.println("\t" + movieGenres);
        }
    }
    
    
    public void printAverageRatingsByMinutes(){
        String ratingsfile = "/data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Num of raters: " + tr.getRaterSize());
        
        String moviefile = "/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());
        
        int min = 105;
        int max = 135;
        MinutesFilter time = new MinutesFilter(min, max);
        ArrayList<Rating> movieRatingsByMin = tr.getAverageRatingsByFilter(5,time);
        System.out.println("Num of movie with ratings by time: " + movieRatingsByMin.size());
        Collections.sort(movieRatingsByMin);
         for(Rating r : movieRatingsByMin){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            int movieTime = MovieDatabase.getMinutes(r.getItem()); 
            System.out.println( r.getValue() + " Time: " + movieTime + " "+ movieTitle);
        }
    }
    
    public void printAverageRatingsByDirectors(){
        String ratingsfile = "/data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Num of raters: " + tr.getRaterSize());
        
        String moviefile = "/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());
        
        String dir = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter dirs = new DirectorsFilter(dir);
        ArrayList<Rating> movieRatingsByDir = tr.getAverageRatingsByFilter( 4,dirs);
        System.out.println("Num of movie with ratings by directors: " + movieRatingsByDir.size());
        Collections.sort(movieRatingsByDir);
	for(Rating r : movieRatingsByDir){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            String movieDirectors = MovieDatabase.getDirector(r.getItem()); 
            System.out.println( r.getValue() + " " + movieTitle);
            System.out.println("\t" + movieDirectors);
        }
    }

    
    
    public void printAverageRatingsByYearAfterAndGenre(){
        String ratingsfile = "/home/neerad/Desktop/BlueJ Programming Assignments/Course5/data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Num of raters: " + tr.getRaterSize());
        
        String moviefile = "/home/neerad/Desktop/BlueJ Programming Assignments/Course5/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());
        
        AllFilters allCriteria = new AllFilters();
        allCriteria.addFilter(new YearAfterFilter(1990));
        allCriteria.addFilter(new GenreFilter("Drama"));
        
        ArrayList<Rating> movieRatingsByAllCriteria = tr.getAverageRatingsByFilter(8, allCriteria);
        System.out.println("Num of movie with ratings by allcriteria: " + movieRatingsByAllCriteria.size());
        Collections.sort(movieRatingsByAllCriteria);
        /*for(Rating r : movieRatingsByAllCriteria){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            String movieGenres = MovieDatabase.getGenres(r.getItem());
            int movieYear = MovieDatabase.getYear(r.getItem()); 
            System.out.println( r.getValue() + " " + movieYear + " " + movieTitle);
            System.out.println("\t" + movieGenres);
        }*/
    }
    
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        String ratingsfile = "/home/neerad/Desktop/BlueJ Programming Assignments/Course5/data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Num of raters: " + tr.getRaterSize());
        
        String moviefile = "/home/neerad/Desktop/BlueJ Programming Assignments/Course5/data/ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        System.out.println("Num of movies: " + MovieDatabase.size());      
        
        AllFilters allCriteria = new AllFilters();
        allCriteria.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        allCriteria.addFilter(new MinutesFilter(90, 180));
        
        ArrayList<Rating> movieRatingsByAllCriteria = tr.getAverageRatingsByFilter(3, allCriteria);
        System.out.println("Num of movie with ratings by allcriteria: " + movieRatingsByAllCriteria.size());
        Collections.sort(movieRatingsByAllCriteria);
        /*for(Rating r : movieRatingsByAllCriteria){
            String movieTitle = MovieDatabase.getTitle(r.getItem());
            String movieDirectors = MovieDatabase.getDirector(r.getItem());
            int movieTime = MovieDatabase.getMinutes(r.getItem()); 
            System.out.println( "\n\n" + r.getValue() + " Time: " + movieTime + " " + movieTitle);
            System.out.println("\t" + movieDirectors);
        }*/
    }
    
}
