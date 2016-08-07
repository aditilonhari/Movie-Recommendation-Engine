 import java.util.*;

public class EfficientRater {
    private String myID;
    private HashMap<String, Rating> myRatings;  //movieId->Ratings

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {  
       Rating newRating = new Rating(item, rating); 
       myRatings.put(item, newRating);
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(myRatings.containsKey(item)){
            return myRatings.get(item).getValue();
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> movieIdList = new ArrayList<String>();
        for(String movieId : myRatings.keySet()){
            movieIdList.add(movieId);
        }
        
        return movieIdList;
    }
   
    public String toString () {
        String result = "Rater [id=" + myID + ", ratings=" + myRatings + "]";
        return result;
    }
}
