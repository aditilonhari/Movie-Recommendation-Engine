import java.util.*;

public class DirectorsFilter implements Filter{
    ArrayList<String> myDirectors;
    
    public DirectorsFilter(String directors){
        int start = 0;
        int comma = directors.indexOf(',', start);
        myDirectors = new ArrayList<String>();
        
        while(comma != -1){
           myDirectors.add(directors.substring(start, comma));
           start = comma + 1;
           comma = directors.indexOf(',', start);
        }
        myDirectors.add(directors.substring(start));
    }
    
    @Override
    public boolean satisfies(String id){
     String directors = MovieDatabase.getDirector(id);
     for(String dir : myDirectors){
         int found = directors.indexOf(dir);
         if(found != -1){
             return true;
         }
     }
     return false;
    }
    
}
