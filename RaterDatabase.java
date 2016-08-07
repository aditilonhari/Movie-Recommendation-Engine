package Course5;

/**
 * This class is an efficient way to get information about raters.
 * 
 * @author Aditi
 * @version 1.1
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import Course5.StepOneStarterProgram.*;

public class RaterDatabase {
    // ourRaters - maps a rater ID String to a Rater object that includes all the movie ratings made by this rater
    private static HashMap<String,EfficientRater> ourRaters;
     
    // initializes the HashMap ourRaters if it does not exist
    private static void initialize() {
    	// this method is only called from addRatings 
	if (ourRaters == null) {
	   ourRaters = new HashMap<String,EfficientRater>();
	}
    }
 
    // initializes the rater database
    public static void initialize(String filename) {
	if (ourRaters == null) {
	   ourRaters= new HashMap<String,EfficientRater>();
	   addRatings(filename);
	}
    }	
 
    // adds rater ratings to the database from a file	
    public static void addRatings(String filename) {
        initialize(); 
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
           String id = rec.get("rater_id");
           String item = rec.get("movie_id");
           String rating = rec.get("rating");
           addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }
    
    // adds one rater and their movie rating to the database
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        EfficientRater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID); 
        } 
        else { 
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
         }
         rater.addRating(movieID,rating);
    } 
	         
   // returns a Rater that has this ID
   public static EfficientRater getRater(String id) {
    	initialize();
    	
    	return ourRaters.get(id);
    }
    
    // returns an ArrayList of Raters from the database
    public static ArrayList<EfficientRater> getRaters() {
    	initialize();
    	ArrayList<EfficientRater> list = new ArrayList<EfficientRater>(ourRaters.values());
    	
    	return list;
    }
 
    // returns the number of raters in the database
    public static int size() {
	    return ourRaters.size();
    }       
}
