 public class GenreFilter implements Filter {
    private String myGenre;
	
	public GenreFilter(String genre) {
		myGenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
	    String genres = MovieDatabase.getGenres(id);
	    if(genres.indexOf(myGenre) != -1)
	       return true;
	    return false;
	}

}
