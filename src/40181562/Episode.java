import java.util.ArrayList;

/*
 * Class used to store an instance of an episode
 */
public class Episode {
	
	/*
	 * Instance variables
	 */
	private String season;
	private String episode;
	private String title;
	
	/*
	 * Constructor
	 */
	public Episode(String season, String episode, String title) {
		this.season = season;
		this.episode = episode;
		this.title = title;
	}
	
	/*
	 * Empty constructor
	 */
	public Episode() {
		this.season = "";
		this.episode = "";
		this.title = "";
	}

	/*
	 * Method for getting the season
	 */
	public String getSeason() {
		return season;
	}

	/*
	 * Method for setting the season
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/*
	 * Method for getting the episode number
	 */
	public String getEpisode() {
		return episode;
	}

	/*
	 * Method for setting the episode number
	 */
	public void setEpisode(String episode) {
		this.episode = episode;
	}

	/*
	 * Method for getting the episode title
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * Method for setting the episode title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * Method for getting an ArrayList of type String containing all the elements of an Episode object
	 */
	public ArrayList<String> getArray() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.season);
		list.add(this.episode);
		list.add(this.title);
		return list;
	}
	
	
}
