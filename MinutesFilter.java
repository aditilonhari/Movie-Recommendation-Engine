public class MinutesFilter implements Filter{
    private int maxMinutes;
    private int minMinutes;
    
    public MinutesFilter(int minMin, int maxMin){
        minMinutes = minMin;
        maxMinutes = maxMin;
    }
    
    @Override
    public boolean satisfies(String id){
        int minutes = MovieDatabase.getMinutes(id);
        if(minutes>= minMinutes && minutes <= maxMinutes)
            return true;
        return false;
    }
}
