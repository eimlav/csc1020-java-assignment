import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

/*
 * Class for storing the details of a TVSeries and manipulating its properties
 */
public class TVSeries {
	/*
	 * Instance variables
	 */
	private int ID;
	private String title;
	private ArrayList<String> genres;
	private String parentalRating;
	private double averageReview;
	private ArrayList<ArrayList<String>> episodeList;
	private ArrayList<String> actors;
	private ArrayList<Double> reviews;
	private LocalDate launchDate;
	private LocalDate endDate;
	private int seasons;
	
	/*
	 * Default Constructor
	 */
	public TVSeries(int iD, String title, ArrayList<String> genres, String parentalRating, double averageReview, ArrayList<String> actors, 
			ArrayList<Double> reviews, LocalDate launchDate, LocalDate endDate, int seasons, ArrayList<ArrayList<String>> episodeList) {
		this.ID = iD;
		this.title = title;
		this.genres = genres;
		this.parentalRating = parentalRating;
		this.averageReview = averageReview;
		this.episodeList = episodeList;
		this.actors = actors;
		this.reviews = reviews;
		this.launchDate = launchDate;
		this.endDate = endDate;
		this.seasons = seasons;
	}

	/*
	 * Empty constructor
	 * Used when user is adding a TV series
	 */
	public TVSeries() {
		this.ID = -1;
		this.title = "";
		this.genres = null;
		this.parentalRating = "";
		this.averageReview = -1;
		this.actors = null;
		this.episodeList = new ArrayList<ArrayList<String>>();
		this.reviews = null;
		this.launchDate = null;
		this.endDate = null;
		this.seasons = -1;
	}
	
	/*
	 * Method for getting seasons
	 */
	public int getSeasons() {
		return seasons;
	}

	/*
	 * Method for setting seasons
	 */
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	/*
	 * Method for getting end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/*
	 * Method for setting end date
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/*
	 * Method for getting launch date
	 */
	public LocalDate getLaunchDate() {
		return launchDate;
	}

	/*
	 * Method for setting launch date
	 */
	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}

	/*
	 * Method for getting reviews
	 */
	public ArrayList<Double> getReviews() {
		return reviews;
	}

	/*
	 * Method for getting reviews
	 */
	public void setReviews(ArrayList<Double> reviews) {
		this.reviews = reviews;
	}

	/*
	 * Method for getting ID
	 */
	public int getID() {
		return ID;
	}

	/*
	 * Method for setting ID
	 */
	public void setID(int iD) {
		this.ID = iD;
	}	

	/*
	 * Method for getting title
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * Method for setting title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * Method for getting genres
	 */
	public ArrayList<String> getGenres() {
		return genres;
	}

	/*
	 * Method for setting genres
	 */
	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}

	/*
	 * Method for getting parental rating
	 */
	public String getParentalRating() {
		return parentalRating;
	}

	/*
	 * Method for setting parental rating
	 */
	public void setParentalRating(String parentalRating) {
		this.parentalRating = parentalRating;
	}

	/*
	 * Method for getting average review
	 */
	public double getAverageReview() {
		return averageReview;
	}

	/*
	 * Method for setting average review
	 */
	public void setAverageReview(double averageReview) {
		this.averageReview = averageReview;
	}

	/*
	 * Method for getting actors
	 */
	public ArrayList<String> getActors() {
		return actors;
	}

	/*
	 * Method for setting actors
	 */
	public void setActors(ArrayList<String> actors) {
		this.actors = actors;
	}
	
	/*
	 * Method for getting episode list
	 */
	public ArrayList<ArrayList<String>> getEpisodeList() {
		return episodeList;
	}

	/*
	 * Method for setting episode list
	 */
	public void setEpisodeList(ArrayList<ArrayList<String>> episodeList) {
		this.episodeList = episodeList;
	}	
	
	/*
	 * Method for printing out all details of a TVSeries object in a concise format
	 */
	public String fullReport() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String lDate = "", eDate = "", averageReviewString;
		
		if(this.averageReview == -1)
			averageReviewString = "N/A";
		else
			averageReviewString = String.valueOf(this.averageReview);
			
		if(this.launchDate == null)
			lDate = "N/A";
		else
		{
			lDate = this.launchDate.format(formatter);
		}
			
		
		if(this.endDate == null)
			eDate = "N/A";
		else {
			eDate= this.endDate.format(formatter);
		}
					
		return String.format("Title: %1$s\nGenres: %2$s\nParental Rating: %3$s\nAverage Review Rating: %4$s\nActors: %5$s\nReviews: %6$s\nLaunch Date: %7$s\nEnd Date: %8$s\nSeasons: %9$d\nEpisode List: %10$s", this.title, stringListToString(this.genres), this.parentalRating, averageReviewString, stringListToString(this.actors), reviewsToString(), lDate, eDate, this.seasons, episodeListToString());
	}
	
	/*
	 * Method for getting a series report
	 * Used when adding a TV series as not all data from TVSeries object is relevant e.g. reviews
	 */
	public String addSeriesReport() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String lDate = "", eDate = "";			
		
		if(this.launchDate == null)
			lDate = "N/A";
		else
		{
			lDate = this.launchDate.format(formatter);
		}
			
		
		if(this.endDate == null)
			eDate = "N/A";
		else {
			eDate= this.endDate.format(formatter);
		}
					
		return String.format("Title: %1$s\nGenres: %2$s\nParental Rating: %3$s\nActors: %4$s\nLaunch Date: %5$s\nEnd Date: %6$s\nSeasons: %7$d\nEpisode List: %8$s", this.title, stringListToString(this.genres), this.parentalRating, stringListToString(this.actors), lDate, eDate, this.seasons, episodeListToString());
	}
	
	/*
	 * Method for converting a String ArrayList to a single String
	 */
	public String stringListToString(ArrayList<String> e) {
		String output = "";
		if(e != null) {
			for(String item : e) {		
				output = output + item + ", ";
			}
			output = output.substring(0, output.length() - 2);
		}
		else
			output = "N/A";
		
		return output;
	}
	
	/*
	 * Method for converting reviews to a single String
	 */
	public String reviewsToString() {
		String output = "";
		if(this.reviews != null) {
			if(this.reviews.size() != 0) {
				for(Double item : this.reviews) {		
					output = output + item + ", ";
				}
				output = output.substring(0, output.length() - 2);
			}	
			else
				output = "N/A";
		}
		else
			output = "N/A";
		
		return output;
	}
	
	/*
	 * Method for converting episodes to a single String
	 */
	public String episodeListToString() {
		String output = "\n";
		if(this.episodeList != null) {
			if(this.episodeList.size() != 0) {
				for(ArrayList<String> episode : this.episodeList) {		
					output = output + String.format("- S%1$sE%2$s: %3$s\n", episode.get(0), episode.get(1), episode.get(2));								
				}
				output = output.substring(0, output.length() - 1);
			}
			else
				output = "N/A";
				
		}
		else
			output = "N/A";
		
		return output;
	}
	
	/*
	 * Method for retrieving number of episodes
	 */
	public int getNumberOfEpisodes() {
		return this.episodeList.size();
	}
	
	/*
	 * Method for calculating the average review rating
	 */
	public void calculateAverageReview() {
		double sumRatings = 0;
		double result = 0;
		if(this.reviews != null) {
			if(this.reviews.size() != 0) {
				for(double rating : this.reviews)
					sumRatings += rating;
				result = sumRatings / (this.reviews.size());
				result = Math.round(result*10.0)/10.0;
				this.averageReview = result;
			}						
		}
		else
			this.averageReview = -1;
	}
	
	/*
	 * Method for getting an ID when adding a new TV series
	 * Requires SeriesLibrary to prevent duplication of IDs
	 */
	public void addTVSeriesID(SeriesLibrary seriesLibrary) {
		ArrayList<Integer> seriesIDs = new ArrayList<Integer>();
		for(TVSeries i: seriesLibrary.getShowArray())
			seriesIDs.add(i.getID());
		
		Collections.sort(seriesIDs);
				
		int currentMaxID = seriesIDs.get(seriesLibrary.getShowArray().size() - 1);

		this.setID(currentMaxID + 1);			
	}
	
	/*
	 * Method for adding a new title
	 */
	public void addTVSeriesTitle(Scanner in) {
		String input = "";
		boolean verification = true;
		while(verification) {
			System.out.println("Enter title: ");
			input = in.nextLine();
			if(Utilities.checkForNull(input)) {
				Utilities.printInvalid();			
			}
			else {
				if(!Utilities.checkForPercentage(input)) {
					System.out.println("~ TITLE SET");				
					verification = false;
				}											
			}
		}

		this.setTitle(input);
	}
	
	public void addTVSeriesGenres(Scanner in) {
		String input = "";
		
		System.out.println("Enter genres one at time by entering the appropriate number. \nA minimum of one genre is required:");
		ArrayList<String> genreArray = new ArrayList<String>();
		ArrayList<String> selectedGenres = new ArrayList<String>();
		
		for(Genre p : Genre.values())
			genreArray.add(p.toString());
		
		int counter = 1;
		for(String genre : genreArray) {
			System.out.println(counter + ") " + genre);
			counter++;
		}
			
		boolean verification = true;
		while(verification) {
			System.out.println("\nEnter genre number\nor press enter on an empty line to continue to next step: ");
			input = in.nextLine();
			if(Utilities.checkForNull(input)) {
				if(selectedGenres.size() > 0)
					verification = false;
				else
					System.out.println("\nYou must enter at least one genre");				
			}
			else if (Utilities.checkForInt(input)){
				boolean genreAlreadyAdded = false;
				for(String eachGenre : selectedGenres) {						
					if(genreArray.get(Integer.parseInt(input) - 1).equals(eachGenre)) {
						genreAlreadyAdded = true;
						break;
					}
				}
				
				if(genreAlreadyAdded) {
					System.out.println("\nYou already selected that genre");
				}
				else if(Integer.parseInt(input) > genreArray.size() || Integer.parseInt(input) < 1)
					Utilities.printInvalid();
				else {
					selectedGenres.add(genreArray.get(Integer.parseInt(input) - 1));
					System.out.println("~ GENRE ADDED");
				}
				
			}
			else {
				Utilities.printInvalid();	
			}
		}
		verification = true;
		
		System.out.println("~ GENRES SET");
		
		this.setGenres(selectedGenres);
	}
		
	/*
	 * Method for adding a new season amount
	 */
	public void addTVSeriesSeasons(Scanner in) {
		String input = "";
		int seasons = 0;
		
		boolean verification = true;
		while(verification) {
			System.out.println("\nEnter number of seasons: ");
			input = in.nextLine();
			if(Utilities.checkForInt(input)) {				
				seasons = Integer.parseInt(input);
				if(seasons > -1) 
					verification = false;
				else
					Utilities.printInvalid();				
			}				
			else {
				seasons = 0;
				Utilities.printInvalid();
			}
				
		}
		
		this.setSeasons(seasons);
	}
	
	/*
	 * Method for adding/editing actors
	 */
	public void editTVSeriesActors(boolean type, boolean method, Scanner in) {
		/*
		 * method
		 * false = add actor
		 * true = remove actor
		 */
		String input, typeString;
		boolean verification = true;
		ArrayList<String> selectedActors = this.getActors();
		
		if(type)
			typeString = "\nor press enter on an empty line to return to actor edit menu:";
		else 
			typeString = "\nor press enter on an empty line to continue to next step:";
			
		if(type && method == false)
			System.out.println("\n*=*=* ADD/EDIT ACTOR *=*=*");
		else if(type && method == true)
			System.out.println("\n*=*=* REMOVE ACTOR *=*=*");
		
		if(selectedActors == null) {
			selectedActors = new ArrayList<String>();
		}
		if(method == false) {
			System.out.println("Enter names of actors one at a time to add them.");			
			while(verification) {
				System.out.println("\nEnter actor name" + typeString);
				input = in.nextLine();
				if(Utilities.checkForNull(input)) {
					if(selectedActors.size() > 0)
						verification = false;
					else
						System.out.println("\nYou must enter at least one actor");				
				}				
				else {
					if(!Utilities.checkForPercentage(input)) {
						int actorAddedIndex = -1, counter = 0;
						boolean actorAlreadyAdded = false;
						if(selectedActors.size() != 0) {
							for(String eachActor : selectedActors) {						
								if(input.toUpperCase().equals(eachActor.toUpperCase())) {
									actorAddedIndex = counter;
									actorAlreadyAdded = true;
									break;
								}
								counter++;
							}
						}
						else 
							actorAlreadyAdded = false;
						
						if(actorAlreadyAdded) {
							System.out.println("\nYou already added that actor\nDo you wish to edit their name?\nEnter 'y' for yes or 'n' for no");
							boolean editActorVerification = true;
							while(editActorVerification) {
								input = in.nextLine();
								if(Utilities.checkForNull(input))
									Utilities.printInvalid();
								else if(input.toLowerCase().equals("y")) {
									System.out.println("\nEnter new name of actor: ");
									input = in.nextLine();
									selectedActors.set(actorAddedIndex, input);
									System.out.println("~ ACTOR EDITED");
									break;
								}
								else if(input.toLowerCase().equals("n"))
									break;
							}
							break;
						}
						else {
							selectedActors.add(input);
							System.out.println("~ ACTOR ADDED");
						}
					}																			
				}
				
			}
			verification = true;
		}
		else {											
			if(selectedActors.size() == 1) 
				System.out.println("Cannot remove actor. There must be at least 1 actor present\n");
			else {
				while(verification) {
					System.out.println("\nEnter appropriate number of actor you wish to remove\nor press enter on an empty line to return to actor edit menu: ");
					int counter = 1;
					for(String actorString : selectedActors) {
						System.out.println(counter + ") " + actorString);
						counter++;
					}
					
					input = in.nextLine();
					if(Utilities.checkForNull(input)) 
						break;
					else if(Utilities.checkForInt(input)) {
						int actorIndex = Integer.parseInt(input) - 1;
						if(actorIndex > -1 && actorIndex < selectedActors.size()) {
							selectedActors.remove(actorIndex);
							System.out.println("~ ACTOR REMOVED\n");							
							break;
						}
						else
							Utilities.printInvalid();
					}					
					else
						Utilities.printInvalid();	
				}				
			}										
		}	
		
		this.setActors(selectedActors);
	}
	
	/*
	 * Method for editing the episodes of a TV series/adding episodes for a new TV series
	 */
	public void editTVSeriesEpisodeList(boolean type, Scanner in) {
		int currentSeasons = this.getSeasons();		
		String input;
		String typeString, typeBreak = "";
		if(type == false) {
			typeString = "adding a TV series";
			typeBreak = "\n";
		}			
		else
			typeString = "the edit menu";
		
		boolean verification = true;
		while(verification) {
			System.out.println(typeBreak + "Do you wish to add/edit or remove an episode?\nEnter 'add' to add/edit an episode\nor 'remove' to remove an episode\nor 'list' to list all episodes\nor press enter on an empty line to return to " + typeString + ":");
			input = in.nextLine();
			if(Utilities.checkForNull(input))
				return;
			else if(input.toLowerCase().equals("add")) {								
				System.out.println("\n*=*=* ADD/EDIT EPISODE *=*=*");
				boolean episodeAddSeasonVerification = true;
				while(episodeAddSeasonVerification) {
					System.out.println("Enter season number\nor press enter on an empty line to return to the episode list edit menu");
					input = in.nextLine();
					if(Utilities.checkForNull(input)) {
						System.out.println("\n*=*=* EDIT EPISODE LIST *=*=*");
						break;
					}						
					else if(Utilities.checkForInt(input)) {
						String season = input;
						if(Integer.parseInt(season) > currentSeasons) {
							if(type)
								System.out.println("\nSeason " + season + " does not exist. Please go to Seasons in the edit menu to change the seasons");
							else
								System.out.println("\nSeason " + season + " does not exist.");
						}
						else if(Integer.parseInt(season) < 1)
							Utilities.printInvalid(true);
						
						else {
							boolean episodeAddEpisodeVerification = true;
							while(episodeAddEpisodeVerification) {
								System.out.println("\nEnter episode number\nor press enter on an empty line to cancel: ");
								input = in.nextLine();
								if(Utilities.checkForNull(input)) {
									System.out.println("\n*=*=* EDIT EPISODE LIST *=*=*");
									episodeAddSeasonVerification = false;
									break;
								}									
								else if(Utilities.checkForInt(input)) {
									String episode = input;
									int counter = 0;
									boolean episodeExists = false;
									for(ArrayList<String> episodeArray : episodeList) {										
										if(episodeArray.get(0).equals(season) && episodeArray.get(1).equals(episode)) {
											episodeExists = true;
											break;
										}
										counter++;
									}
									
									if(episodeExists) {
										System.out.println("\nThis episode already exists. Do you wish to change the title?\nEnter 'y' for yes or 'n' for no:");
										boolean episodeAddEpisodeExistsVerification = true;
										while(episodeAddEpisodeExistsVerification) {
											input = in.nextLine();
											if(Utilities.checkForNull(input))
												Utilities.printInvalid();
											else if(input.toLowerCase().equals("y")) {
												System.out.println("\nEnter new title\nor press enter on an empty line to cancel: ");
												input = in.nextLine();
												if(Utilities.checkForNull(input)) {
													System.out.println("\n*=*=* EDIT EPISODE LIST *=*=*");
													episodeAddSeasonVerification = false;
													episodeAddEpisodeVerification = false;
													break;
												}
													
												Episode episodeObject = new Episode(episodeList.get(counter).get(0), episodeList.get(counter).get(1), input);
												episodeList.set(counter, episodeObject.getArray());
												
												System.out.println("\n~ EPISODE OVERWRITTEN\n");	
												
												episodeAddEpisodeVerification = false;												
												episodeAddEpisodeExistsVerification = false;
											}
											else if(input.toLowerCase().equals("n")) {												
												episodeAddEpisodeVerification = false;
												break;												
											}
											else
												Utilities.printInvalid();
										}
									}
									else {										
										while(true) {
											System.out.println("\nEnter title\nor press enter on an empty line to cancel: ");
											input = in.nextLine();
											if(Utilities.checkForNull(input)) {
												episodeAddEpisodeVerification = false;
												break;
											}												
											if(!Utilities.checkForPercentage(input)) {
												Episode episodeObject = new Episode(season, episode, input);
												episodeList.add(episodeObject.getArray());			
												
												System.out.println("\n~ EPISODE ADDED\n");
												
												episodeAddEpisodeVerification = false;
												//episodeAddSeasonVerification = false;
												
												episodeList = sortEpisodes(episodeList);
												break;
											}
										}																				
									}
								}
								else
									Utilities.printInvalid();
							}
						}																		
					}
					else
						Utilities.printInvalid();
				}
			}
			else if(input.toLowerCase().equals("remove")) {
				System.out.println("\n*=*=* REMOVE EPISODE *=*=*");
				if(episodeList == null) 
					System.out.println("\nNo episodes exist. There is nothing to remove\n");									
				else if(episodeList.isEmpty())
					System.out.println("\nNo episodes exist. There is nothing to remove\n");	
				else {
					boolean episodeRemoveSeasonVerification = true;
					while(episodeRemoveSeasonVerification) {
						System.out.println("Enter season number\nor press enter on an empty line to return to the episode list edit menu");
						input = in.nextLine();
						if(Utilities.checkForNull(input)) {
							System.out.println("\n*=*=* EDIT EPISODE LIST *=*=*");
							break;							
						}
							
						if(Utilities.checkForInt(input)) {
							String season = input;
							if(Integer.parseInt(season) > currentSeasons) {			
								if(type)
									System.out.println("\nSeason " + season + " does not exist. Please go to Seasons in the edit menu to change the seasons");
								else
									System.out.println("\nSeason " + season + " does not exist.");
							}
							else if(Integer.parseInt(season) < 1)
								Utilities.printInvalid(true);
							else {
								boolean episodeRemoveEpisodeVerification = true;
								while(episodeRemoveEpisodeVerification) {
									System.out.println("\nEnter episode number\nor press enter on an empty line to cancel: ");
									input = in.nextLine();
									if(Utilities.checkForNull(input)) {
										System.out.println("\n*=*=* EDIT EPISODE LIST *=*=*");
										episodeRemoveSeasonVerification = false;
										break;
									}										
									if(Utilities.checkForInt(input)) {
										if(Integer.parseInt(input) > 0) {
											String episode = input;
											int counter = 0;
											boolean episodeExists = false;
											for(ArrayList<String> episodeArray : episodeList) {
												if(episodeArray.get(0).equals(season) && episodeArray.get(1).equals(episode)) {
													episodeExists = true;
													break;
												}
												counter++;
											}
											
											if(episodeExists) {														
												episodeList.remove(counter);
												System.out.println("\n~ EPISODE REMOVED\n");
												episodeRemoveSeasonVerification = false;
												break;
											}
											else {										
												System.out.println("\nEpisode not found. Enter 'list' if you wish to view all existing episodes while in episode list menu\n\n*=*=* EDIT EPISODE LIST *=*=*");
												episodeRemoveSeasonVerification = false;
												break;
											}
										}
										else
											Utilities.printInvalid();
									}
									else
										Utilities.printInvalid();
								}
							}																		
						}
						else
							Utilities.printInvalid();
					}
				}							
			}
			else if(input.toLowerCase().equals("list")) {
				System.out.println("\nDisplaying list of all episodes:");
				String output = "";
				if(episodeList != null) {
					for(ArrayList<String> episode : episodeList) {
						output = output + String.format("- S%1$sE%2$s: %3$s\n", episode.get(0), episode.get(1), episode.get(2));								
					}										
				}
				else
					output = "No episodes exist\n";
				
				System.out.println(output);
			}
			else
				Utilities.printInvalid();
		}
		
		this.setEpisodeList(episodeList);
	}
	
	/*
	 * Method for editing a TV series genres
	 */
	public void editTVSeriesGenres(boolean type, Scanner in) {
		/*
		 * false = add
		 * true = remove
		 */
		ArrayList<String> selectedGenres = this.getGenres();
		String input;
		if(type == false) {
			System.out.println("\nEnter genres one at time by entering the appropriate number\nor press enter on an empty line to return to genre edit menu");
			ArrayList<String> genreArray = new ArrayList<String>();
			
			for(Genre p : Genre.values())
				genreArray.add(p.toString());
			
			int counter = 1;
			for(String genre : genreArray) {
				System.out.println(counter + ") " + genre);
				counter++;
			}
			
			boolean verification = true;
			while(verification) {
				System.out.println("\nEnter genre number\nor press enter on an empty line to return to genre edit menu: ");
				input = in.nextLine();
				if(Utilities.checkForNull(input)) {
					if(selectedGenres.size() > 0)
						verification = false;
					else
						System.out.println("\nYou must enter at least one genre");				
				}
				else if (Utilities.checkForInt(input)){
					boolean genreAlreadyAdded = false;
					for(String eachGenre : selectedGenres) {						
						if(genreArray.get(Integer.parseInt(input) - 1).equals(eachGenre)) {
							genreAlreadyAdded = true;
							break;
						}
					}
					
					if(genreAlreadyAdded) {
						System.out.println("\nYou already added that genre");
					}
					else if(Integer.parseInt(input) > genreArray.size() || Integer.parseInt(input) < 1)
						Utilities.printInvalid();
					else {
						selectedGenres.add(genreArray.get(Integer.parseInt(input) - 1));
						System.out.println("~ GENRE ADDED");
					}
					
				}
				else {
					Utilities.printInvalid(true);		
				}
			}
			verification = true;																																	
		}
		else {											
			if(selectedGenres.size() == 1) 
				System.out.println("\nCannot remove genre. There must be at least 1 genre present\n");
			else {
				System.out.println("\nEnter appropriate number of genre you wish to remove\nor 'back' to return to genre edit menu: ");
				int counter = 1;
				for(String genreString : selectedGenres) {
					System.out.println(counter + ") " + genreString);
					counter++;
				}
				
				boolean verification = true;
				while(verification) {
					input = in.nextLine();
					if(Utilities.checkForNull(input)) 
						break;
					else if(Utilities.checkForInt(input)) {
						int genreIndex = Integer.parseInt(input) - 1;
						if(genreIndex > -1 && genreIndex < selectedGenres.size()) {
							selectedGenres.remove(genreIndex);
							break;
						}
					}
					else
						Utilities.printInvalid();	
				}
				
			}										
		}						
		
		this.setGenres(selectedGenres);
	}
	
	/*
	 * Method for getting a parental rating
	 */
	public void editParentalRating(boolean type, Scanner in) {
		/*
		 * false = add
		 * true = edit
		 */
		String input = "";
		boolean verification = true;
		while(verification) {
			String[] parentalRatings = {"U", "PG", "12", "15", "18", "TBC"};			
			String startMessage = "Enter parental rating from the following list: ";
			if(type == true) {
				startMessage = "Enter parental rating from the following list\nor press enter on an empty line to return to edit menu: ";
			}
			
			System.out.println(startMessage);
			for(String i : parentalRatings)
				System.out.println(i);
			
			input = in.nextLine();
			if(Utilities.checkForNull(input) && type == true)
				return;
			else if(Utilities.checkForNull(input)) {
				Utilities.printInvalid();				
			}
			else {
				boolean validParentalRating = false;
				
				for(String i : parentalRatings) {	
					try {
						Integer.parseInt(i);
						if(input.equals(i))
							validParentalRating = true;
					}
					catch(Exception e) {
						if(input.toUpperCase().equals("U") || input.toUpperCase().equals("PG") || input.toUpperCase().equals("TBC")) {
							input = input.toUpperCase();
							validParentalRating = true;
						}							
					}								
				}
							
				if(validParentalRating == false)
					Utilities.printInvalid();
				else {
					System.out.println("~ PARENTAL RATING SET");					
					verification = false;			
				}
				
			}
		}
	
		this.setParentalRating(input);				
	}
	
	/*
	 * Method for editing a TV series reviews
	 */
	public void editTVSeriesReviews(Scanner in) {
		ArrayList<Double> selectedReviews = this.getReviews();
		String input;
		boolean verification = true;
		while(verification) {
			if(selectedReviews == null || selectedReviews.size() == 0) {
				System.out.println("\nCannot remove any reviews. No reviews exist");
				verification = false;
			}						
			else {
				System.out.println("Enter appropriate number of review you wish to remove\nor press enter on an empty line to return to edit menu: ");
				int counter = 1;
				for(Double review : selectedReviews) {
					String reviewString = String.valueOf(review);
					System.out.println(counter + ") " + reviewString);
					counter++;
				}
				
				input = in.nextLine();
				if(Utilities.checkForNull(input)) 
					return;
				else if(Utilities.checkForInt(input)) {
					int reviewIndex = Integer.parseInt(input) - 1;
					if(reviewIndex > -1 && reviewIndex < selectedReviews.size()) {
						selectedReviews.remove(reviewIndex);		
						System.out.println("~ REVIEW REMOVED\n");
						if(selectedReviews.size() == 0) {
							selectedReviews = null;
							verification = false;
						}						
					}
				}
				else
					Utilities.printInvalid(true);	
			}				
		}
		
		this.setReviews(selectedReviews);
	}
	
	/*
	 * Method for editing the launch date or end date of a TV series
	 */
	public void editTVSeriesDate(boolean type, Scanner in) {
		LocalDate oriLaunchDate = this.launchDate;
		LocalDate oriEndDate = this.endDate;
		boolean verification = true;
		String input;
		String dateType;
		LocalDate selectedDate = null;
		if(type == false) {
			dateType = "launch date";
		}
		else
			dateType = "end date";
		
		while(verification) {
			System.out.println("Do you wish to add/edit or remove " + dateType + "?\nEnter 'add' to add/edit " + dateType + "\nor 'remove' to remove " + dateType + "\nor press enter on an empty line to return to edit menu:");
			input = in.nextLine();
			if(Utilities.checkForNull(input)) 
				break;
			else if(input.toLowerCase().equals("add")) {
				boolean dateVerification = true;
				while(dateVerification)
				{												
					LocalDate launchDate = oriLaunchDate;
					LocalDate endDate = oriEndDate;
					
					if(type == false) {
						launchDate = addDate('l', in);
						
						if(launchDate == null)
							break;
						
						endDate = oriEndDate;
						selectedDate = launchDate;
					}
					else {
						launchDate = oriLaunchDate;
						endDate = addDate('e', in);
						
						if(endDate == null)
							break;
						
						selectedDate = endDate;
					}
															
					if(type == false) {
						if(endDate != null) {
							if(endDate.isAfter(launchDate) || endDate == launchDate) {							
								System.out.println("\n~ " + dateType.toUpperCase() + " SET");
								if(type == false) {
									this.setLaunchDate(launchDate);
								}
								else
									this.setEndDate(endDate);
								
								dateVerification = false;
							}								
							else {								
								System.out.println("\nLaunch date must be on or before end date");																		
							}								
						}
						else {						
							System.out.println("\n~ " + dateType.toUpperCase() + " SET");
							if(type == false) {
								this.setLaunchDate(launchDate);
							}
							else
								this.setEndDate(endDate);
							dateVerification = false;
						}		
					}
					else {
						if(launchDate != null) {
							if(selectedDate.isAfter(launchDate) || selectedDate == launchDate) {							
								System.out.println("\n~ " + dateType.toUpperCase() + " SET");	
								if(type == false) {
									this.setLaunchDate(launchDate);
								}
								else
									this.setEndDate(endDate);
								dateVerification = false;
							}								
							else {								
								System.out.println("\nEnd date must be on or before launch date");																		
							}								
						}
						else {						
							System.out.println("\n~ " + dateType.toUpperCase() + " SET");
							if(type == false) {
								this.setLaunchDate(launchDate);
							}
							else
								this.setEndDate(endDate);
							dateVerification = false;
						}	
					}																																		
				}	
				System.out.println("");
			}
			else if(input.toLowerCase().equals("remove")) {
				if(type == false) {
					selectedDate = oriLaunchDate;
				}
				else
					selectedDate = oriEndDate;
								
				if(selectedDate != null) {					
					System.out.println("\n~ " + dateType.toUpperCase() + " REMOVED");
					if(type == false) {
						this.setLaunchDate(null);
					}
					else
						this.setEndDate(null);			
				}
				else {
					System.out.println("\nThere is no existing " + dateType + "\n");
				}							
			}
			else
				Utilities.printInvalid();
			
			System.out.println("\n*=*=* EDIT " + dateType.toUpperCase() + " *=*=*");
		}
		
		
	}
	
	/*
	 * Method for editing the season number of a TV series
	 */
	public void editTVSeriesSeasons(Scanner in) {
		int seasons = 0, currentSeasons = this.getSeasons();
		ArrayList<ArrayList<String>> episodeList = this.episodeList;
		String input = "";
		ArrayList<ArrayList<String>> newEpisodeList = null;
		
		boolean verification = true;
		while(verification) {
			System.out.println("Enter new number of seasons\nor press enter on an empty line to return to the edit menu:");
			input = in.nextLine();
			if(Utilities.checkForInt(input)) {				
				seasons = Integer.parseInt(input);
				if(seasons > -1) {
					// Get number of seasons
					if(episodeList == null)
						currentSeasons = 0;
					
					if(currentSeasons == seasons) {
						System.out.println("\nThere are already " + seasons + " seasons");
						return;
					}						
					else if(currentSeasons > seasons) {
						System.out.println("\nWarning: There are currently " + currentSeasons + " seasons. You have entered to change this to " + seasons + ".\nAny episodes stored in the episode list after season " + seasons + " will be deleted.\nDo you wish to continue? Enter 'y' for yes or 'n' for no:");								
						
						boolean verifySeasons = true;
						while(verifySeasons) {
							input = in.nextLine();
							if(Utilities.checkForNull(input)) {
								Utilities.printInvalid();
							}								
							else if(input.toLowerCase().equals("y")) {
								if(episodeList != null) {																			
									newEpisodeList = episodeList;
									ArrayList<ArrayList<String>> removeEpisodeList = new ArrayList<ArrayList<String>>();

									for(ArrayList<String> episode : newEpisodeList) {
										if(Integer.parseInt(episode.get(0)) > seasons) {
											removeEpisodeList.add(episode);
										}
									}
									
									newEpisodeList.removeAll(removeEpisodeList);										
									
									String episodesRemovedString = "";
									if(removeEpisodeList.size() > 0)
										episodesRemovedString = " AND EPISODES CHANGED";
									
									System.out.println("~ SEASONS SET" + episodesRemovedString);										
									
									this.setSeasons(seasons);
									this.setEpisodeList(newEpisodeList);
									return;
									
								}	
								else {
									System.out.println("~ SEASONS SET");										
									
									this.setSeasons(seasons);
									return;
								}
									
							}
							else if(input.toLowerCase().equals("n")) {
								return;								
							}
							else
								Utilities.printInvalid();
						}
					}
					else if(seasons > currentSeasons) {
						System.out.println("~ SEASONS SET");
						this.setSeasons(seasons);
						return;
					}
				}							
				else
					Utilities.printInvalid();
						
			}	
			else if(input.toLowerCase().equals("back")) {
				
			}
			else if(Utilities.checkForNull(input)) {
				return;
			}
			else {						
				Utilities.printInvalid();
			}
				
		}							
	}
	
	/*
	 * Method for adding a launch/end date
	 */
	public static LocalDate addDate(char type, Scanner in) {
		boolean verification = true;
		String input = "", typeDate = "", typeDateUp = "";
		int year = 1874, month = 1, day = 1;
		
		if(type == 'l')
			typeDate = "launch";			
		else if(type == 'e')
			typeDate = "end";
		else
			typeDate = "search";
		
		typeDateUp = typeDate.toUpperCase();
		if(type == 'l' || type == 'e') {
			while(verification) {					
				System.out.println("\nIs " + typeDate + " date known? Enter 'y' for yes or 'n' for no:");
				input = in.nextLine();
				if(input.toLowerCase().equals("n")) {
					return null;
				}
				else if(input.toLowerCase().equals("y"))
					verification = false;
				else
					Utilities.printInvalid();
			}
			verification = true;
		}
				
		while(verification) {									
			System.out.println("\nEnter " + typeDate + " year: ");
			input = in.nextLine();
			if(Utilities.checkForNull(input)) {
				Utilities.printInvalid();				
			}
			else if(Utilities.checkForInt(input)){
				if((Integer.parseInt(input) < 3000) && (Integer.parseInt(input) > 1873)){
					year = Integer.parseInt(input);
					verification = false;
					System.out.println("~ " + typeDateUp + " YEAR SET");
				}			
				else 
					Utilities.printInvalid();																							
			}
			else {
				Utilities.printInvalid();
			}
		}
		verification = true;
		
		while(verification) {
			System.out.println("\nEnter " + typeDate + " month (as number): ");
			input = in.nextLine();
			if(Utilities.checkForNull(input)) {
				Utilities.printInvalid();			
			}
			else if(Utilities.checkForInt(input)){
				if(Integer.parseInt(input) < 13 && Integer.parseInt(input) > 0) {
					month = Integer.parseInt(input);
					verification = false;
					System.out.println("~ " + typeDateUp + " MONTH SET");
				}			
				else 
					Utilities.printInvalid();																			
			}
			else {
				Utilities.printInvalid();
			}
		}
		verification = true;
		
		while(verification) {
			System.out.println("\nEnter " + typeDate + " day (as number): ");
			input = in.nextLine();
			if(Utilities.checkForNull(input)) {
				Utilities.printInvalid();		
			}
			else if(Utilities.checkForInt(input)){
				Month monthClass;
				try{
					monthClass = new Month(month, year);
					if((Integer.parseInt(input) < monthClass.getDays() + 1) && (Integer.parseInt(input) > 0)) {
						day = Integer.parseInt(input);
						verification = false;
						System.out.println("~ " + typeDateUp + " DAY SET");
					}		
					else
						Utilities.printInvalid();
				}
				catch(Exception e) {
					Utilities.printInvalid();
				}																
			}
			else {
				Utilities.printInvalid();
			}
		}
		verification = true;
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		String dateString = "";
		String monthString = "";
		String yearString = String.valueOf(year);
		
		if(month < 10)
			monthString = "0" + month;
		else
			monthString = String.valueOf(month);
		
		String dayString = "";
		if(day < 10)
			dayString = "0" + day;
		else
			dayString = String.valueOf(day);
		
		dateString = yearString + "-" + monthString + "-" + dayString;		
		LocalDate outputDate = LocalDate.parse(dateString, dateFormat);
		
		return outputDate;
	}
	
	/*
	 * Method for sorting a list of episodes
	 */
	public ArrayList<ArrayList<String>> sortEpisodes(ArrayList<ArrayList<String>> episodeList) {
		ArrayList<String> list = new ArrayList<String>();
		String megaString = new String();

		for(ArrayList<String> i : episodeList)
		{
			list.add(i.get(0) + "%" + i.get(1) + "%" + i.get(2) + "%");			
		}
		
		Collections.sort(list);
		
		ArrayList<ArrayList<String>> newEpisodeList = new ArrayList<ArrayList<String>>();
		
		for(String i : list) {
			Episode eps = new Episode();
			
			eps.setSeason(i.substring(0, 1));
			eps.setEpisode(i.substring(2, 3));
			eps.setTitle(i.substring(4, i.length() - 1));		
			newEpisodeList.add(eps.getArray());											
		}
		
		String episodeSeason = newEpisodeList.get(0).get(0);
		String episodeNo = newEpisodeList.get(0).get(1);
		String episodeTitle = newEpisodeList.get(0).get(2);
		megaString = String.format("%1$s%%%2$s%%%3$s%%%4$s%%", megaString, episodeSeason, episodeNo, episodeTitle);
		
		for(ArrayList<String> episode : newEpisodeList) {
			if(episode != newEpisodeList.get(0)) {
				episodeSeason = episode.get(0);
				episodeNo = episode.get(1);
				episodeTitle = episode.get(2);
				
				megaString = String.format("%1$s%2$s%%%3$s%%%4$s%%", megaString, episodeSeason, episodeNo, episodeTitle);
			}						
		}		
		
		ArrayList<Integer> episodeBreaks = new ArrayList<Integer>();
		int index = 0;
		for(char i : megaString.toCharArray()) {
			if(i == '%') {
				episodeBreaks.add(index);
			}
			index++;						
		}										
		
		Episode eps = new Episode();
		newEpisodeList = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < episodeBreaks.size() - 1; i += 3) {												
			eps.setSeason(megaString.substring(episodeBreaks.get(i) + 1, episodeBreaks.get(i + 1)));
			eps.setEpisode(megaString.substring(episodeBreaks.get(i + 1) + 1, episodeBreaks.get(i + 2)));
			eps.setTitle(megaString.substring(episodeBreaks.get(i + 2) + 1, episodeBreaks.get(i + 3)));		
			newEpisodeList.add(eps.getArray());
		}
				
		return newEpisodeList;
		
	}
}
