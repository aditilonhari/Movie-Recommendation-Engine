import java.util.*;

public class FourthRatings {
    
     private static double getAverageByID(String movieId, int minimalRaters){
        double average = 0.0; 
        int totalRaters = 0;
        double totalRating = 0;
        for(EfficientRater r : RaterDatabase.getRaters()){
            if(r.hasRating(movieId)){
                totalRaters++;
                totalRating += r.getRating(movieId);
            }
        }
        if(totalRaters >= minimalRaters)
            average = totalRating/totalRaters;
        return average;    
    }
    
    public static ArrayList<Rating> getAverageRatings(int minimalRaters){
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
    
    
    public static ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
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
    
    private static double dotProduct(EfficientRater me, EfficientRater r){
        double finalProduct = 0;
        ArrayList<String> movieIdsForMe = me.getItemsRated();
        for(String movieId : movieIdsForMe){
            if(r.hasRating(movieId)){
                double newRatingScaleForR = r.getRating(movieId) - 5;
                double newRatingScaleForMe = me.getRating(movieId) - 5;
                
                finalProduct += (newRatingScaleForR * newRatingScaleForMe);
            
            }
        }
        
        return finalProduct;
    }
    
    private static ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        EfficientRater givenRater = RaterDatabase.getRater(id);
        
        for(EfficientRater individualRater : RaterDatabase.getRaters()){
            if(!individualRater.getID().equals(givenRater.getID())){
                double product = dotProduct(givenRater, individualRater);
                if(product > 0){
                    Rating similarRating = new Rating(individualRater.getID(), product);
                    similarities.add(similarRating);
                }
            }
        }
        
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public static ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> similarities = getSimilarities(id);
        
        for(String movieId : movies){
            int weightedAvg = 0, weightedTotal = 0, raterTotal = 0;
            for(int i=0; i<numSimilarRaters; i++){
                Rating r = similarities.get(i);
                String r_id = r.getItem();
                EfficientRater r_obj = RaterDatabase.getRater(r_id);
                if(r_obj.hasRating(movieId)){
                    weightedTotal += (r.getValue() * r_obj.getRating(movieId)); 
                    raterTotal++;
                }
            }
            if(raterTotal >= minimalRaters){
                weightedAvg = weightedTotal/raterTotal;
                Rating weightedRatingForMovie = new Rating(movieId, weightedAvg);
                similarRatings.add(weightedRatingForMovie);
            }

        }
        Collections.sort(similarRatings, Collections.reverseOrder());

        return similarRatings;
    }
    
    public static ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatings(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
}
