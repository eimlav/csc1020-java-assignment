import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

/*
 * Class used to store an array of TVSeries objects and provide a UI to add/edit objects to this array
 */
public class SeriesLibrary {
	
	/*
	 * Instance variables
	 */
	private ArrayList<TVSeries> showArray = new ArrayList<TVSeries>();	
	public Scanner in = new Scanner(System.in);
	public int seriesID = 1;
	public ArrayList<ArrayList<String>> sortedList = new ArrayList<ArrayList<String>>();
	public char sortType = 'A';
	
	/*
	 * Default constructor
	 */
	public SeriesLibrary() {
		
	}	

	/*
	 * Method for retrieving array of all TV series
	 */
	public ArrayList<TVSeries> getShowArray() {
		return showArray;
	}

	/*
	 * Method for setting array of all TV series
	 */
	public void setShowArray(ArrayList<TVSeries> showArray) {
		this.showArray = showArray;
	}
	
	/*
	 * Method for adding a TV series
	 */
	public void addSeries(TVSeries show) {
		this.showArray.add(show);
	}
	
	/*
	 * Method for retrieving a TV series
	 */
	public TVSeries getShow(int index) {
		return this.showArray.get(index);
	}		
	
	/*
	 * Method for reading in TV series from text file
	 */
	public void readInTVSeries() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("src\\tv_shows.txt"));
			
			boolean readingShows = true;
			
			while(readingShows) {
				TVSeries show = new TVSeries();						
				String input = in.readLine();
				if(input != null) {
					/*
					 * Reading in ID and title
					 */										
					if(input.length() == 0)
					{
						readingShows = false;
						break;
					}
					
					show.setID(Integer.parseInt(input.substring(0, input.indexOf("%"))));
					show.setTitle(input.substring(input.indexOf("%") + 1,input.length()));
					
					/*
					 * Reading in genres
					 */
					ArrayList<String> genres = new ArrayList<String>();
					input = in.readLine();
					
					ArrayList<Integer> genreBreaks = new ArrayList<Integer>();
					int index = 0;
					for(char i : input.toCharArray()) {
						if(i == '%') {
							genreBreaks.add(index);
						}
						index++;						
					}
							
					for(int i = 0; i < genreBreaks.size() - 1; i++) {					
						genres.add(input.substring(genreBreaks.get(i) + 1, genreBreaks.get(i + 1)));																			
					}
					
					show.setGenres(genres);	
					
					/*
					 * Reading in parental guidance rating
					 */
					show.setParentalRating(in.readLine());
					
					/*
					 * Reading in average review
					 */
					double averageReview;
					input = in.readLine();
					if(Utilities.checkForNull(input))
						averageReview = -1;
					else
						averageReview = Double.parseDouble(input);
					
					show.setAverageReview(averageReview);
								
					/*
					 * Reading in actors
					 */
					ArrayList<String> actors = new ArrayList<String>();
					input = in.readLine();
					
					ArrayList<Integer> actorBreaks = new ArrayList<Integer>();
					index = 0;
					for(char i : input.toCharArray()) {
						if(i == '%') {
							actorBreaks.add(index);
						}
						index++;						
					}
							
					for(int i = 0; i < actorBreaks.size() - 1; i++) {					
						actors.add(input.substring(actorBreaks.get(i) + 1, actorBreaks.get(i + 1)));					
					}
					
					show.setActors(actors);
					
					/*
					 * Reading in reviews
					 */
					ArrayList<Double> reviews = new ArrayList<Double>();
					input = in.readLine();
					
					if(input.length() > 0) {
						ArrayList<Integer> reviewBreaks = new ArrayList<Integer>();
						index = 0;
						for(char i : input.toCharArray()) {
							if(i == '%') {
								reviewBreaks.add(index);
							}
							index++;						
						}
						
						for(int i = 0; i < reviewBreaks.size() - 1; i++) {					
							reviews.add(Double.parseDouble(input.substring(reviewBreaks.get(i) + 1, reviewBreaks.get(i + 1))));																			
						}
					}
					else
						reviews = null;												
					
					show.setReviews(reviews);
					
					/*
					 * Reading in launch date
					 */
					LocalDate date;
					DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
					input = in.readLine();		
					int dateLength = input.length();				
					if(dateLength > 0)
					{									
						date = LocalDate.parse(input, dateFormat);
						show.setLaunchDate(date);
					}
					else
						show.setLaunchDate(null);
					
					/*
					 * Reading in end date
					 */
					date = null;
					input = in.readLine();
					dateLength = input.length();
					if(dateLength > 0)
					{
						date = LocalDate.parse(input, dateFormat);
						show.setEndDate(date);
					}				
					else
						show.setEndDate(null);
					
					/*
					 * Reading in amount of seasons
					 */
					input = in.readLine();
					show.setSeasons(Integer.parseInt(input));
					
					/*
					 * Reading in episodes
					 */
					ArrayList<ArrayList<String>> episodeList = new ArrayList<ArrayList<String>>();
					
					Episode eps = new Episode();
					input = in.readLine();

					if(input != null) {
						if(input.length() > 0 ) {
							ArrayList<Integer> episodeBreaks = new ArrayList<Integer>();
							index = 0;
							for(char i : input.toCharArray()) {
								if(i == '%') {
									episodeBreaks.add(index);
								}
								index++;						
							}										
							
							for(int i = 0; i < episodeBreaks.size() - 1; i += 3) {												
								eps.setSeason(input.substring(episodeBreaks.get(i) + 1, episodeBreaks.get(i + 1)));
								eps.setEpisode(input.substring(episodeBreaks.get(i + 1) + 1, episodeBreaks.get(i + 2)));
								eps.setTitle(input.substring(episodeBreaks.get(i + 2) + 1, episodeBreaks.get(i + 3)));		
								episodeList.add(eps.getArray());
							}
						
							episodeList = show.sortEpisodes(episodeList);							
						}						
					}															
										
					show.setEpisodeList(episodeList);							
					
					/*
					 * Adding show to seriesLibrary
					 */									
					this.addSeries(show);										
					
				}
				else
				{					
					readingShows = false;
					break;
				}										
			}																	
			
			in.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
			throw new IllegalStateException("Error", e);
		}	
	}
	
	/*
	 * Method for browsing TV series
	 */
	public void browseTVSeries() {
		System.out.println("\n*=*=*=* BROWSE TV SERIES *=*=*=*");
		
		listTVSeries('A');

		boolean verification = true;
		
		while(verification) {			
			System.out.println("\nEnter appropriate number of selected series to view details of series \nor 'sort' to change sorting of series\nor 'list' to display a list of series \nor 'search' to search for a series\nor press enter on an empty line to return to main menu: ");
			String input = in.nextLine();
			if(Utilities.checkForNull(input))
				break;
			else if(Utilities.checkForInt(input)) {				
				int intInput = Integer.parseInt(input);				
				if(intInput <= this.getShowArray().size() && intInput > 0)
				{
					int seriesIndex = 0;
					int counter = 0;
					int currentSeriesID = Integer.parseInt(sortedList.get(intInput - 1).get(0));
					for(TVSeries i : this.getShowArray()) {
						if(i.getID() == currentSeriesID) {
							seriesIndex = counter;
							break;
						}
						else
							counter++;
					}
					
					System.out.println("\n*=*=* DISPLAYING DETAILS FOR '" + this.getShow(seriesIndex).getTitle().toUpperCase() + "' *=*=*\n" + this.getShow(seriesIndex).fullReport());
					System.out.println("\nPress enter to continue...");
					in.nextLine();
					System.out.println("\n*=*=*=* BROWSE TV SERIES *=*=*=*");
				}
				else
					Utilities.printInvalid();
			}
			else if(input.toLowerCase().equals("sort")) {
				System.out.println("\n*=*=* SORT TV SERIES *=*=*\nEnter appropriate number of sort type:\n1) Standard\n2) Hexicological\n3) Launch date\n4) Average review rating\nor press enter on an empty line to return to browse menu");
				boolean sortVerification = true;
				while(sortVerification) {
					input = in.nextLine();
					if(Utilities.checkForNull(input))
						break;
					if(Utilities.checkForInt(input)) {
						switch(input) {
							case "1":
								listTVSeries('A');
								sortVerification = false;
								break;
							case "2":
								listTVSeries('B');
								sortVerification = false;
								break;
							case "3":
								listTVSeries('C');
								sortVerification = false;
								break;
							case "4":
								listTVSeries('D');
								sortVerification = false;
								break;
							default:
								Utilities.printInvalid();
								break;
						}												
					}
					else
						Utilities.printInvalid();
				}
			}
			else if(input.toLowerCase().equals("list")) {
				listTVSeries(sortType);
			}			
			else if(input.toLowerCase().equals("search")) {
				searchForTVSeriesMenu();
				System.out.println("\n*=*=* BROWSE TV SERIES *=*=*");
			}
			else {
				Utilities.printInvalid();
			}
		}				
	}
	
	/*
	 * Method for searching for a TV series
	 */
	public void searchForTVSeriesMenu() {
		System.out.println("\n*=*=*=* SEARCH *=*=*=*\n1) Title\n2) Genres\n3) Parental Rating\n4) Average Review Rating\n5) Actor\n6) Launch Date\n7) End Date\n8) Season\n9) Episode");
		boolean searchVerification = true;
		while(searchVerification) {	
			System.out.println("\nEnter appropriate number of parameter to search by:\nor 'list' to display all parameters\nor press enter on an empty line to return to browse menu: ");
			String input = in.nextLine();
			if(Utilities.checkForNull(input))
				break;
			else if(input.toLowerCase().equals("list"))
				System.out.println("\n1) Title\n2) Genres\n3) Parental Rating\n4) Average Review Rating\n5) Actors\n6) Launch Date\n7) End Date\n8) Seasons\n9) Episodes");
			else if(Utilities.checkForInt(input)){
				int intInput = Integer.parseInt(input);
				
				if(intInput >= 1 && intInput <= 9) {
					int searchSeriesID = searchForTVSeries(intInput);
					if(searchSeriesID == -1) {
						System.out.println("\nNo results found");							
					}											
					else if(searchSeriesID > -1) {
						int seriesIndex = 0;
						int counter = 0;
						int currentSeriesID = searchSeriesID;
						for(TVSeries i : this.getShowArray()) {
							if(i.getID() == currentSeriesID) {
								seriesIndex = counter;
								break;
							}
							else
								counter++;
						}
						
						System.out.println("\n*=*=* DISPLAYING DETAILS FOR '" + this.getShow(seriesIndex).getTitle().toUpperCase() + "' *=*=*\n" + this.getShow(seriesIndex).fullReport());
						System.out.println("\nPress enter to continue...");
						in.nextLine();						
						break;
					}
					else {
						System.out.println("\n*=*=* SEARCH *=*=*");
					}						
				}		
				else
					Utilities.printInvalid();
																													
			}
			else
				Utilities.printInvalid();
				
		}
	}
	
	/*
	 * Method for searching for a TV series
	 */
	public int searchForTVSeries(int parameter) {
		int result = -1;
		boolean exactMatch = false;
		boolean verification = true;
		String input, parameterString = "", query = "";
		ArrayList<TVSeries> matches = new ArrayList<TVSeries>();							
		ArrayList<ArrayList<String>> propertyListPerShow = new ArrayList<ArrayList<String>>();
		
		if(parameter == 1)
		{
			parameterString = "Title";
			System.out.println("\n*=*=* SEARCH BY TITLE *=*=*\nEnter search query (Ensure correct spelling/format)\nor press enter on an empty line to return to search menu: ");
			input = in.nextLine();
			if(Utilities.checkForNull(input))
				return -2;
			query = input;
			for(TVSeries i : this.getShowArray()) {
				if(i.getTitle().toLowerCase().equals(input)) {
					matches.add(i);
					exactMatch = true;
				}
				else if(i.getTitle().toLowerCase().contains(input))
					matches.add(i);
			}
			
		}
		else if(parameter == 2) {	
			parameterString = "Genre";
			ArrayList<String> genreArray = new ArrayList<String>();				
			for(Genre p : Genre.values())
				genreArray.add(p.toString());
			
			System.out.println("\n*=*=* SEARCH BY GENRE *=*=*");
			int counter = 1;
			for(String genre : genreArray) {
				System.out.println(counter + ") " + genre);
				counter++;
			}
			
			while(verification) {
				System.out.println("\nEnter appropriate number of genre you wish to search by\nor press enter on an empty line to return to search menu: ");
				input = in.nextLine();
				if(Utilities.checkForNull(input))
					return -2;
				else if(Utilities.checkForInt(input)) {
					int intInput = Integer.parseInt(input);
					if(intInput >= 1 && intInput <= 26) {
						String selectedGenre = genreArray.get(Integer.parseInt(input) - 1);
						query = selectedGenre;
						
						for(TVSeries i : this.getShowArray()) {
							for(String genre : i.getGenres())
							{
								if(genre.contains(selectedGenre))
									matches.add(i);
							}	
						}
																								
						break;
					}				
					else
						Utilities.printInvalid();
				}
				else
					Utilities.printInvalid();
			}								
			
		}
		else if(parameter == 3) {
			parameterString = "Parental Rating";
			System.out.println("\n*=*=* SEARCH BY PARENTAL RATING *=*=*");
			String[] parentalRatings = {"U","PG","12","15","18","TBC"};
			int counter = 1;
			for(String parentalRating : parentalRatings) {
				System.out.println(counter + ") " + parentalRating);
				counter++;
			}
			while(verification) {
				System.out.println("\nEnter appropriate number of parental rating you wish to search by\nor press enter on an empty line to return to search menu: ");
				input = in.nextLine();
				if(Utilities.checkForNull(input))
					return -2;
				else if(Utilities.checkForInt(input)) {
					int intInput = Integer.parseInt(input);
					if(intInput >= 1 && intInput <= 6) {
						String selectedParentalRating = parentalRatings[intInput - 1];
						query = selectedParentalRating;
						
						for(TVSeries i : this.getShowArray()) {
							if(i.getParentalRating().toLowerCase().contains(selectedParentalRating))
								matches.add(i);
						}
						
						break;
					}				
					else
						Utilities.printInvalid();
				}
				else
					Utilities.printInvalid();
			}															
		}
		else if(parameter == 4) {
			parameterString = "Average Review Rating";
			System.out.println("\n*=*=* SEARCH BY AVERAGE REVIEW RATING *=*=*\n1) An exact average review rating\n2) Range of average review ratings\n\nEnter appropriate number of search type\nor press enter on an empty line to return to search menu:");
			while(verification) {
				double review = -1; 
				input = in.nextLine();
				if(Utilities.checkForNull(input))
					return -2;
				else if(Utilities.checkForInt(input)) {
					int intInput = Integer.parseInt(input);
					if(intInput == 1) {
						System.out.println("\n*=*=* SEARCH BY AVERAGE REVIEW RATING | EXACT *=*=*\nEnter search value (value from 0.0 to 10.0)");
						while(true) {
							input = in.nextLine();
							if(!Utilities.checkForNull(input)) {
								if(Utilities.checkForDouble(input)) {						
									review = Double.parseDouble(input);
									query = String.valueOf(review);
									if(review >= 0 && review <= 10)
									{
										query = String.valueOf(review);
										
										for(TVSeries i : this.getShowArray()) {
											if(i.getAverageReview() == review)
												matches.add(i);
										}						
										verification = false;
										break;
									}
									else
										Utilities.printInvalid();
								}															
								else {
									Utilities.printInvalid();						
								}								
							}
							else {
								Utilities.printInvalid();		
							}
						}	
						
					}
					else if(intInput == 2){		
						System.out.println("\n*=*=* SEARCH BY AVERAGE REVIEW RATING | RANGE *=*=*\nBoundary values are inclusive in search\nEnter search value (value from 0.0 to 10.0)");
						double reviews[] = new double[2];
						String typeReview = "\nLower";
						for(int i = 0; i < 2; i++) {
							System.out.println(typeReview + " boundary average review rating: ");
							while(true) {
								input = in.nextLine();
								if(!Utilities.checkForNull(input)) {
									if(Utilities.checkForDouble(input)) {						
										reviews[i] = Double.parseDouble(input);	
										typeReview = "\nHigher";
										verification = false;
										break;
									}															
									else {
										Utilities.printInvalid();						
									}								
								}
								else {
									Utilities.printInvalid();		
								}
							}
							
						}
						
						query = "Range Search; " + reviews[0] + "; " + reviews[1];
						
						for(TVSeries i : this.getShowArray()) {
							if(i.getAverageReview() >= reviews[0] && i.getAverageReview() <= reviews[1])
								matches.add(i);
						}
						
						break;
					}
					else
						Utilities.printInvalid();
				}											
			}						
		}
		else if(parameter == 5) {
			parameterString = "Actors";
			System.out.println("\n*=*=* SEARCH BY ACTOR *=*=*\nEnter name of actor\nor press enter on an empty line to return to search menu: ");
			input = in.nextLine();
			if(Utilities.checkForNull(input))
				return -2;
			query = input;
			for(TVSeries i : this.getShowArray()) {
				boolean seriesAdded = false;
				ArrayList<String> tempActorList = new ArrayList<String>();
				for(String actor : i.getActors())
				{										
					if(actor.toLowerCase().contains(input.toLowerCase())) {
						if(!seriesAdded) {
							tempActorList.add(actor);
							matches.add(i);
							seriesAdded = true;
						}
						else
							tempActorList.add(actor);													
					}													
				}	
				if(tempActorList.size() > 0)
					propertyListPerShow.add(tempActorList);
			}						
		}
		else if(parameter == 6) {
			parameterString = "Launch Date";
			System.out.println("\n*=*=* SEARCH BY LAUNCH DATE *=*=*\n1) An exact date\n2) Range of dates\n\nEnter appropriate number of search type\nor press enter on an empty line to return to search menu:");
			while(verification) {
				input = in.nextLine();
				if(Utilities.checkForNull(input))
					return -2;
				else if(Utilities.checkForInt(input)) {
					int intInput = Integer.parseInt(input);
					if(intInput == 1) {		
						System.out.println("\n*=*=* SEARCH BY LAUNCH DATE | EXACT *=*=*");
						LocalDate searchLaunchDate = TVSeries.addDate('s', in);
						query = String.valueOf(searchLaunchDate);
						for(TVSeries i : this.getShowArray()) {
							if(searchLaunchDate.equals(i.getLaunchDate()))
								matches.add(i);
						}	
						break;
					}
					else if(intInput == 2) {						
						System.out.println("\n*=*=* SEARCH BY LAUNCH DATE | RANGE *=*=*\nBoundary values are not inclusive in search\nFirst date: ");
						LocalDate searchLaunchDateOne = TVSeries.addDate('s', in);
						query = "Range Search; " + String.valueOf(searchLaunchDateOne) + "; ";
						System.out.println("\nSecond date: ");
						LocalDate searchLaunchDateTwo = TVSeries.addDate('s', in);
						query += searchLaunchDateTwo;
						for(TVSeries i : this.getShowArray()) {
							if(i.getLaunchDate() != null) {
								if(i.getLaunchDate().isAfter(searchLaunchDateOne) && i.getLaunchDate().isBefore(searchLaunchDateTwo))
									matches.add(i);								
							}							
						}
						break;
					}
					else
						Utilities.printInvalid();
				}
				else
					Utilities.printInvalid();
			}
				
		}
		else if(parameter == 7) {
			parameterString = "End Date";
			System.out.println("\n*=*=* SEARCH BY END DATE *=*=*\n1) An exact date\n2) Range of dates\n\nEnter appropriate number of search type\nor press enter on an empty line to return to search menu:");
			while(verification) {
				input = in.nextLine();
				if(Utilities.checkForNull(input))
					return -2;
				else if(Utilities.checkForInt(input)) {
					int intInput = Integer.parseInt(input);
					if(intInput == 1) {		
						System.out.println("*=*=* SEARCH BY END DATE | EXACT *=*=*");
						LocalDate searchEndDate = TVSeries.addDate('s', in);
						query = String.valueOf(searchEndDate);
						for(TVSeries i : this.getShowArray()) {
							if(searchEndDate.equals(i.getEndDate()))
								matches.add(i);
						}	
						break;
					}
					else if(intInput == 2) {						
						System.out.println("*=*=* SEARCH BY END DATE | RANGE *=*=*\nBoundary values are not inclusive in search\nFirst date: ");
						LocalDate searchEndDateOne = TVSeries.addDate('s', in);
						query = "Range Search; " + String.valueOf(searchEndDateOne) + "; ";
						System.out.println("\nSecond date: ");
						LocalDate searchEndDateTwo = TVSeries.addDate('s', in);
						query += searchEndDateTwo;
						for(TVSeries i : this.getShowArray()) {
							if(i.getEndDate() != null) {
								if(i.getEndDate().isAfter(searchEndDateOne) && i.getEndDate().isBefore(searchEndDateTwo))
									matches.add(i);								
							}							
						}
						break;
					}
					else
						Utilities.printInvalid();
				}
				else
					Utilities.printInvalid();
			}
		}
		else if(parameter == 8) {
			parameterString = "Season";
			System.out.println("\n*=*=* SEARCH BY SEASON *=*=*\nEnter season amount\nor press enter on an empty line to return to search menu: ");
			while(verification) {
				input = in.nextLine();
				if(Utilities.checkForNull(input))
					return -2;
				else if(Utilities.checkForInt(input)) {
					query = input;
					int intInput = Integer.parseInt(input);
					for(TVSeries i : this.getShowArray()) {
						if(i.getSeasons() == intInput) 
							matches.add(i);																		
					}
					break;
				}		
				else
					Utilities.printInvalid();
			}						
		}
		else if(parameter == 9) {
			parameterString = "Episode";
			System.out.println("\n*=*=* SEARCH BY EPISODE *=*=*\nEnter name of episode\n or press enter on an empty line to return to search menu: ");
			input = in.nextLine();
			if(Utilities.checkForNull(input))
				return -2;
			query = input;
			for(TVSeries i : this.getShowArray()) {
				boolean seriesAdded = false;
				ArrayList<String> tempEpisodeList = new ArrayList<String>();
				for(ArrayList<String> episode : i.getEpisodeList()) {										
					if(episode.toString().toLowerCase().contains(input.toLowerCase())) {
						if(!seriesAdded) {
							tempEpisodeList.add("- S" + episode.get(0) + "E" + episode.get(1) + ": " + episode.get(2));
							seriesAdded = true;
							matches.add(i);
						}
						else
							tempEpisodeList.add("- S" + episode.get(0) + "E" + episode.get(1) + ": " + episode.get(2));						
					}						
				}
				
				if(tempEpisodeList.size() > 0)
					propertyListPerShow.add(tempEpisodeList);
			}			
		}
		
		System.out.println("\nSearch Query:\n" + parameterString + "; '" + query + "';");
		
		if(matches.size() == 1 && exactMatch == true) {
			result = matches.get(0).getID();
		}
		else if(matches.size() > 0) {
			System.out.println("\n" + matches.size() + " result(s) found.\n\nEnter appropriate number to select a series\nor press enter on an empty line to return to search menu:");
			int counter = 1;

			for(TVSeries match : matches) {
				String extraString = "";
				if(parameter == 2) {
					String genreString = "| Genres: ";					
					for(String genre : match.getGenres()){
						genreString += "\n- " + genre;
					}	
					extraString = genreString;
				}
				else if(parameter == 3) {

					extraString = "| Rated: " + match.getParentalRating();
				}
				else if(parameter == 4) {

					extraString = "| Average Review Rating: " + match.getAverageReview();
				}
				else if(parameter == 5) {									

					extraString = "| Featuring: ";
					for(String actor : propertyListPerShow.get(counter - 1)){
						extraString += "\n- " + actor;
					}					
				}
				else if(parameter == 6) {
					
					extraString = "| Launch Date: " + match.getLaunchDate();
				}
				else if(parameter == 7) {

					extraString = "| End Date: " + match.getEndDate();
				}
				else if(parameter == 8) {

					extraString = "| Seasons: " + match.getSeasons();
				}
				else if(parameter == 9) {

					extraString = " | Episodes:";
					for(String episode : propertyListPerShow.get(counter - 1)) {
						extraString += "\n" + episode;
					}
				}
				
				if(!(counter == matches.size()))
					extraString += "\n";
				
				if(match.getLaunchDate() != null) 
					System.out.printf("%1$d) %2$s (%3$d) %4$s\n", counter, match.getTitle(), match.getLaunchDate().getYear(), extraString);
				else
					System.out.printf("%1$d) %2$s %3$s\n", counter, match.getTitle(), extraString);
				counter++;
			}				

			verification = true;
			while(verification) {
				input = in.nextLine();
				if(Utilities.checkForInt(input)) {				
					int intInput = Integer.parseInt(input);				
					if(intInput <= matches.size() && intInput > 0) {					
						result = matches.get(intInput - 1).getID();	
						break;
					}
					else
						Utilities.printInvalid();
				}
				else if(Utilities.checkForNull(input)) {					
					result = -2;
					break;
				}					
				else
					Utilities.printInvalid();
			}			
		}
		
		return result;
	}
		
	/*
	 * Method for listing each TV series
	 */
	public void listTVSeries(char sortTypeIn) {		
		/*
		 * sortType:
		 * - 'A' = Standard sorting by order in SeriesLibrary
		 * - 'B' = Sort by hexicological order
		 * - 'C' = Sort by launch date
		 * - 'D' = Sort by average review rating
		 */
		sortedList = new ArrayList<ArrayList<String>>();
		
		if(sortTypeIn == 'A') {
			System.out.println("\n*=*=* TV SERIES *=*=*");
			for(TVSeries i : this.getShowArray())
			{
				System.out.print(seriesID + ") " + i.getTitle() + "\n");
				ArrayList<String> seriesIDAndTitle = new ArrayList<String>();
				seriesIDAndTitle.add(String.valueOf(i.getID()));
				seriesIDAndTitle.add(i.getTitle());		
				sortedList.add(seriesIDAndTitle);
				seriesID++;
			}
			
			seriesID = 1;
			
			sortType = 'A';
		}
		else if(sortTypeIn == 'B') {
			ArrayList<String> list = new ArrayList<String>();
			
			for(TVSeries i : this.getShowArray())
			{
				list.add(i.getTitle()+ "%" +i.getID());				
			}
			
			Collections.sort(list);
			
			System.out.println("\n*=*=* TV SERIES SORTED HEXICOLOGICALLY *=*=*");
			for(String i : list)
			{							
				ArrayList<String> seriesIDAndTitle = new ArrayList<String>();
				
				seriesIDAndTitle.add(i.substring(i.indexOf("%") + 1, i.length()));
				seriesIDAndTitle.add(i.substring(0, i.indexOf("%")));
				sortedList.add(seriesIDAndTitle);
				System.out.print(seriesID + ") " + i.substring(0, i.indexOf("%")) + "\n");
				seriesID++;
			}
			seriesID = 1;

			sortType = 'B';
		}
		else if(sortTypeIn == 'C') {
			ArrayList<String> list = new ArrayList<String>();
			
			for(TVSeries i : this.getShowArray())
			{
				list.add(i.getLaunchDate()+ "%" +i.getID());				
			}
			
			Collections.sort(list);
			
			System.out.println("\n*=*=* TV SERIES SORTED BY LAUNCH DATE (Earliest -> Latest) *=*=*");
			for(String i : list)
			{							
				ArrayList<String> seriesIDAndLaunch = new ArrayList<String>();
				
				seriesIDAndLaunch.add(i.substring(i.indexOf("%") + 1, i.length()));
				seriesIDAndLaunch.add(i.substring(0, i.indexOf("%")));
				sortedList.add(seriesIDAndLaunch);
				
				int seriesIndex = 0;
				int currentSeriesID = Integer.parseInt(i.substring(i.indexOf("%") + 1, i.length()));
				int counter = 0;
				for(TVSeries series : this.getShowArray()) {
					if(series.getID() == currentSeriesID) {
						seriesIndex = counter;
						break;
					}
					else
						counter++;
				}
				if(this.getShow(seriesIndex).getLaunchDate() == null) 
					System.out.print(seriesID + ") " + this.getShow(seriesIndex).getTitle() + " (Launch date N/A)\n");
				else
					System.out.print(seriesID + ") " + this.getShow(seriesIndex).getTitle() + " - " + this.getShow(seriesIndex).getLaunchDate() + "\n");
				seriesID++;
			}
			seriesID = 1;

			sortType = 'C';
		}
		else if(sortTypeIn == 'D') {
			ArrayList<String> list = new ArrayList<String>();

			for(TVSeries i : this.getShowArray())
			{
				list.add(i.getAverageReview()+ "%" +i.getID());				
			}
			
			Collections.sort(list);
			Collections.reverse(list);
			
			System.out.println("\n*=*=* TV SERIES SORTED BY AVERAGE REVIEW RATING *=*=*");
			for(String i : list)
			{							
				ArrayList<String> seriesIDAndAvReview = new ArrayList<String>();
				
				seriesIDAndAvReview.add(i.substring(i.indexOf("%") + 1, i.length()));
				seriesIDAndAvReview.add(i.substring(0, i.indexOf("%")));
				sortedList.add(seriesIDAndAvReview);
				
				int seriesIndex = 0;
				int currentSeriesID = Integer.parseInt(i.substring(i.indexOf("%") + 1, i.length()));
				int counter = 0;
				for(TVSeries series : this.getShowArray()) {
					if(series.getID() == currentSeriesID) {
						seriesIndex = counter;
						break;
					}
					else
						counter++;
				}
				if(this.getShow(seriesIndex).getAverageReview() == -1.0) 
					System.out.print(seriesID + ") " + this.getShow(seriesIndex).getTitle() + " - (Average review rating N/A)\n");
				else
					System.out.print(seriesID + ") " + this.getShow(seriesIndex).getTitle() + " - " + this.getShow(seriesIndex).getAverageReview() + "\n");
				seriesID++;
			}
			seriesID = 1;

			sortType = 'D';
		}
		else
			System.out.println("\nInvalid sort type: " + sortTypeIn);
	}
	
	/*
	 * Method for adding a new TV series via console input
	 */
	public void addTVSeries() {
		System.out.println("\n*=*=*=* ADD TV SERIES *=*=*=*");
		
		boolean verification = true;
		TVSeries newTVSeries = new TVSeries();
		String input;
		
		/*
		 * Assigning ID
		 */		
		newTVSeries.addTVSeriesID(this);
				
		/*
		 * Assigning title
		 */		
		System.out.println("\n*=*=* ADD TITLE *=*=*");
		newTVSeries.addTVSeriesTitle(in);
		
		/*
		 * Assigning genres
		 */
		System.out.println("\n*=*=* ADD GENRES *=*=*");
		newTVSeries.addTVSeriesGenres(in);
		
		/*
		 * Assigning parental rating
		 */
		System.out.println("\n*=*=* ADD PARENTAL RATING *=*=*");
		newTVSeries.editParentalRating(false, in);
		
		/*
		 * Assigning actors
		 */
		System.out.println("\n*=*=* ADD ACTORS *=*=*");
		newTVSeries.editTVSeriesActors(false, false, in);
		System.out.println("~ ACTORS SET");
		
		/*
		 * Assigning launch date and end date
		 */		
		System.out.println("\n*=*=* ADD LAUNCH DATE *=*=*");
		LocalDate launchDate = TVSeries.addDate('l', in);
		LocalDate endDate = null;
		System.out.println("\n*=*=* ADD END DATE *=*=*");
		while(verification)
		{
			endDate = TVSeries.addDate('e', in);
			if(endDate != null && launchDate != null) {
				if(endDate.isAfter(launchDate) || endDate == launchDate)
					verification = false;
				else
					System.out.println("\nEnd date must be on or after launch date");
			}		
			else
				verification = false;
		}
		verification = true;
		
		newTVSeries.setLaunchDate(launchDate);
		newTVSeries.setEndDate(endDate);	
		
		/*
		 * Assigning number of seasons
		 */					
		System.out.println("\n*=*=* ADD SEASONS *=*=*");
		newTVSeries.addTVSeriesSeasons(in);
		
		/*
		 * Assigning episodes
		 */		
		if(newTVSeries.getSeasons() > 0) {
			System.out.println("\n*=*=* ADD EPISODES *=*=*");
			newTVSeries.editTVSeriesEpisodeList(false, in);
		}									
		System.out.println("\n*=*=* DISPLAYING DETAILS FOR '" + newTVSeries.getTitle().toUpperCase() + "' *=*=*\n" + newTVSeries.addSeriesReport());		
		
		System.out.println("\nDo you wish to add this TV series?\nEnter 'y' for yes or 'n' for no");
		while(verification) {
			input = in.nextLine();
			if(Utilities.checkForNull(input))
				Utilities.printInvalid();
			else if(input.toLowerCase().equals("y")) {
				this.addSeries(newTVSeries);
				System.out.println("\n*=*=* '" + newTVSeries.getTitle().toUpperCase() + "' ADDED TO LIBRARY *=*=*");
				break;
			}
			else if(input.toLowerCase().equals("n")) {
				System.out.println("\n*=*=* '" + newTVSeries.getTitle().toUpperCase() + "' NOT ADDED TO LIBRARY *=*=*");
				break;
			}
			else 
				Utilities.printInvalid();				
		}		
		
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
	
	/*
	 * Method for getting a parental rating
	 */
	public String getParentalRating(boolean type) {
		String input = "";
		boolean verification = true;
		while(verification) {
			String[] parentalRatings = {"U", "PG", "12", "15", "18", "TBC"};			
			String startMessage = "\nEnter parental rating from the following list: ";
			if(type == true) {
				startMessage = "Enter parental rating from the following list\nor press enter on an empty line to return to edit menu: ";
			}
			
			System.out.println(startMessage);
			for(String i : parentalRatings)
				System.out.println(i);
			
			input = in.nextLine();
			if(Utilities.checkForNull(input) && type == true)
				break;
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
		verification = true;
		return input;				
	}
	
	/*
	 * Method for writing all TV series stored in instance of SeriesLibrary to a text file
	 */
	public void writeTVSeriesToFile() {
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("src\\tv_shows.txt"));
			String megaString = "";
			
			for(TVSeries series : this.getShowArray()) {
				
				/*
				 * Adding ID and title
				 */
				megaString = String.format("%1$s\n%2$s%%%3$s\n", megaString, series.getID(), series.getTitle());
			
				/*
				 * Adding genre(s)
				 */
				megaString = String.format("%1$s%%%2$s%%", megaString, series.getGenres().get(0));

				if(series.getGenres().size() > 1) {
					for(String genre : series.getGenres()) {
						if(!(genre == series.getGenres().get(0)))							
							megaString = String.format("%1$s%2$s%%", megaString, genre);						
					}
				}
			
				/*
				 * Adding parental rating
				 */
				megaString = String.format("%1$s\n%2$s", megaString, series.getParentalRating());
			
				/*
				 * Adding average review
				 */
				String averageReview = "";
				if(Utilities.checkForNull(String.valueOf(series.getAverageReview()))) {
					// do nothing
				}
				else
				{
					averageReview = String.valueOf(series.getAverageReview());
				}
				
				megaString = String.format("%1$s\n%2$s\n", megaString, averageReview);
			
				/*
				 * Adding actors
				 */
				megaString = String.format("%1$s%%%2$s%%", megaString, series.getActors().get(0));

				if(series.getActors().size() > 1) {
					for(String actor : series.getActors()) {
						if(!(actor == series.getActors().get(0)))							
							megaString = String.format("%1$s%2$s%%", megaString, actor);						
					}
				}
			
				/*
				 * Adding reviews
				 */								
				if(series.getReviews() != null) {
					megaString = String.format("%1$s\n%%%2$s%%", megaString, series.getReviews().get(0));
					
					if(series.getReviews().size() > 1) {
						for(double review : series.getReviews()) {
							String reviewString = String.valueOf(review);
							if(!(review == series.getReviews().get(0)))							
								megaString = String.format("%1$s%2$s%%", megaString, reviewString);						
						}
					}										
					
				}		
				else {
					megaString = String.format("%1$s\n", megaString);
				}
				
				/*
				 * Adding launch date
				 */
				if(series.getLaunchDate() != null) {
					megaString = String.format("%1$s\n%2$s", megaString, series.getLaunchDate());
				}
				else 
					megaString = String.format("%1$s\n", megaString, series.getLaunchDate());
				
				/*
				 * Adding end date
				 */
				if(series.getEndDate() != null) {
					megaString = String.format("%1$s\n%2$s", megaString, series.getEndDate());
				}
				else 
					megaString = String.format("%1$s\n", megaString, series.getEndDate());
				
				/*
				 * Adding seasons
				 */
				megaString = String.format("%1$s\n%2$s\n", megaString, series.getSeasons());
		
				/*
				 * Adding episodes
				 */
				if(series.getEpisodeList() != null && series.getEpisodeList().size() != 0) {
					String episodeSeason = series.getEpisodeList().get(0).get(0);
					String episodeNo = series.getEpisodeList().get(0).get(1);
					String episodeTitle = series.getEpisodeList().get(0).get(2);
					megaString = String.format("%1$s%%%2$s%%%3$s%%%4$s%%", megaString, episodeSeason, episodeNo, episodeTitle);
					
					for(ArrayList<String> episode : series.getEpisodeList()) {
						if(episode != series.getEpisodeList().get(0)) {
							episodeSeason = episode.get(0);
							episodeNo = episode.get(1);
							episodeTitle = episode.get(2);
							
							megaString = String.format("%1$s%2$s%%%3$s%%%4$s%%", megaString, episodeSeason, episodeNo, episodeTitle);
						}						
					}																							
				}
				else {
					megaString = String.format("%1$s", megaString);
				}												
			}
		
			/*
			 * Writing out megaString to tv_shows.txt
			 */			
			out.write(megaString.substring(1, megaString.length()));			
			out.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			throw new IllegalStateException("Error in writing to file: ", e);
		}
	}
	
	/*
	 * Method for displaying menu for editing a TV series
	 */
	public void editTVSeriesMain() {
		System.out.println("\n*=*=*=* EDIT TV SERIES *=*=*=*");
		
		listTVSeries('A');
		
		boolean verification = true;
		
		while(verification) {			
			System.out.println("\n*=*=* SERIES SELECTION *=*=*\nEnter appropriate number of series to edit\nor 'list' to display list of series\nor press enter on an empty line to return to main menu: ");
			String input = in.nextLine();
			if(Utilities.checkForInt(input)) {				
				int intInput = Integer.parseInt(input);				
				if(intInput <= this.getShowArray().size() && intInput > 0)
				{
					System.out.println("\n*=*=* '" + this.getShow(intInput - 1).getTitle().toUpperCase() + "' SELECTED *=*=*");
					editTVSeries(this.getShow(intInput - 1));
				}
			}
			else if(Utilities.checkForNull(input))
				break;
			else if(input.toLowerCase().equals("list")) {
				seriesID = 1;
				
				listTVSeries('A');
			}
			else {
				Utilities.printInvalid();
			}
		}
	}
	
	/*
	 * Method for editing a TV series
	 */
	public void editTVSeries(TVSeries series) {		
		boolean verification = true;			
		while(verification) {
			boolean propertyChooseVerification = true;
			int propertyIndex = -1;
			String input;
			
			/*
			 * Selecting property to edit
			 */
			while(propertyChooseVerification) {
				String[] properties = {"Title", "Genres", "Parental Rating", "Reviews", "Actors", "Launch Date", "End Date", "Seasons", "Episode List"};
				int counter = 1;
				
				
				System.out.println("\n*=*=* PROPERTY SELECTION *=*=*\nEnter appropriate number from the following list of properties which you wish to edit\nor press enter on an empty line return to series selection menu: ");
				for(String property : properties) {
					System.out.println(counter + ") " + property);
					counter++;
				}
				input = in.nextLine();
				if(Utilities.checkForNull(input)) {
					verification = false;
					break;
				}
				else if(Utilities.checkForInt(input)) {
					propertyIndex = Integer.parseInt(input);
					if(propertyIndex > 0 && propertyIndex < 10) {
						propertyIndex -= 1;
						propertyChooseVerification = false;
					}
					else
						Utilities.printInvalid();
						
				}
				else {
					Utilities.printInvalid();
				}
					
			}
			
			boolean propertyVerification = true;
			// Edit title
			if(propertyIndex == 0) {
				System.out.println("\n*=*=* EDIT TITLE *=*=*");
				while(propertyVerification) {
					System.out.println("Enter new title\nor press enter on an empty line to return to edit menu: ");
					input = in.nextLine();
					if(Utilities.checkForNull(input)) {
						break;			
					}					
					else {
						System.out.println("~ TITLE SET");
						series.setTitle(input);
						propertyVerification = false;
					}
				}
				propertyVerification = true;
			}													
			else if(propertyIndex == 1) { // Edit genres
				System.out.println("\n*=*=* EDIT GENRES *=*=*");
				while(propertyVerification) {
					System.out.println("Do you wish to add or remove a genre?\nEnter 'add' to add a genre \nor 'remove' to remove a genre\nor press enter on an empty line to return to edit menu:");
					input = in.nextLine();
					if(Utilities.checkForNull(input)) 
						break;
					else if(input.toLowerCase().equals("add")) {
						series.editTVSeriesGenres(false, in);
						System.out.println("\n*=*=* EDIT GENRES *=*=*");
					}
					else if(input.toLowerCase().equals("remove")) {
						ArrayList<String> originalGenres = new ArrayList<String>();
						
						for(String genre : series.getGenres())
							originalGenres.add(genre);
						
						series.editTVSeriesGenres(true, in);
						if(!originalGenres.equals(series.getGenres()))
							System.out.println("~ GENRE REMOVED\n");
						System.out.println("\n*=*=* EDIT GENRES *=*=*");
					}
					else
						Utilities.printInvalid(true);
						
				}
				propertyVerification = true;
			}
			else if(propertyIndex == 2) { // Edit parental rating
				System.out.println("\n*=*=* EDIT PARENTAL RATING *=*=*");
				series.editParentalRating(true, in);
			}
			else if(propertyIndex == 3) { // Edit reviews
				System.out.println("\n*=*=* EDIT REVIEWS *=*=*");
				series.editTVSeriesReviews(in);
				series.calculateAverageReview();
			}
			else if(propertyIndex == 4) { // Edit actors
				System.out.println("\n*=*=* EDIT ACTORS *=*=*");
				while(propertyVerification) {
					System.out.println("Do you wish to add/edit or remove an actor?\nEnter 'add' to add/edit an actor \nor 'remove' to remove an actor\nor 'list' to display all actors in the series\nor press enter on an empty line to return to edit menu:");
					input = in.nextLine();
					if(Utilities.checkForNull(input)) 
						break;
					else if(input.toLowerCase().equals("add")) {
						series.editTVSeriesActors(true, false, in);
						System.out.println("\n*=*=* EDIT ACTORS *=*=*");
					}
					else if(input.toLowerCase().equals("remove")) {
						series.editTVSeriesActors(true, true, in);
						System.out.println("\n*=*=* EDIT ACTORS *=*=*");
					}
					else if(input.toLowerCase().equals("list")) {
						System.out.println("\n*=* ACTORS IN " + series.getTitle().toUpperCase() + " *=*");
						int counter = 1;
						for(String actorString : series.getActors()) {
							System.out.println(counter + ") " + actorString);
							counter++;
						}	
						System.out.println("");
					}
					else
						Utilities.printInvalid();
				}
				propertyVerification = true;	
			}
			else if(propertyIndex == 5) { // Edit launch date								
				System.out.println("\n*=*=* EDIT LAUNCH DATE *=*=*");
				series.editTVSeriesDate(false, in);
				propertyVerification = false;																											
			}
			else if(propertyIndex == 6) { // Edit end date
				System.out.println("\n*=*=* EDIT END DATE *=*=*");
				series.editTVSeriesDate(true, in);
				propertyVerification = false;
			}
			else if(propertyIndex == 7) { // Edit seasons		
				System.out.println("\n*=*=* EDIT SEASONS *=*=*");										
				series.editTVSeriesSeasons(in);
															
			}
			else if(propertyIndex == 8) { // Edit episode list
				System.out.println("\n*=*=* EDIT EPISODE LIST *=*=*");
				series.editTVSeriesEpisodeList(true, in);
			}
		}					
	}
	
	/*
	 * Method for reviewing a TV series
	 */
	public void reviewTVSeries() {
		System.out.println("\n*=*=*=* RATE TV SERIES *=*=*=*");
		
		listTVSeries('A');
		
		boolean verification = true;
		
		while(verification) {			
			System.out.println("\nEnter appropriate number of selected series to rate\nor 'list' to display list of series\nor press enter on an empty line to return to main menu: ");
			String input = in.nextLine();
			if(Utilities.checkForNull(input))
				break;
			else if(Utilities.checkForInt(input)) {				
				int intInput = Integer.parseInt(input);				
				if(intInput <= this.getShowArray().size() && intInput > 0)
				{
					TVSeries selectedShow = this.getShow(intInput - 1);
					System.out.println("~ " + selectedShow.getTitle().toUpperCase() + " SELECTED\n");
					
					boolean reviewVerification = true;
					while(reviewVerification) {
						System.out.println("Enter your review (value from 0.0 to 10.0)\nor press enter to return to main menu");
						double review = -1; 
						input = in.nextLine();
						if(!Utilities.checkForNull(input)) {
							if(Utilities.checkForDouble(input))
								review = Double.parseDouble(input);
							else {
								Utilities.printInvalid();						
							}								
						}
						else {
							break;			
						}
						
						if(review != -1) {
							String reviewString = String.valueOf(review);
							int decimalIndex = reviewString.indexOf('.');
							int decimalNumbers = reviewString.substring(decimalIndex + 1, reviewString.length()).length();
							
							if(review < 0 || review > 10)
								Utilities.printInvalid();
							else if(decimalNumbers > 1)
								Utilities.printInvalid();
							else {
								ArrayList<Double> reviews = new ArrayList<Double>();
								
								if(selectedShow.getReviews() != null)
									reviews = selectedShow.getReviews();
								
								reviews.add(Math.round(review*10.0)/10.0);
								selectedShow.setReviews(reviews);
								selectedShow.calculateAverageReview();
								System.out.println("~ REVIEW ADDED");
								reviewVerification = false;
							}	
						}																												
					}
					
				}
				else 
					Utilities.printInvalid();
			}
			else if(input.toLowerCase().equals("list")) {
				seriesID = 1;
				
				listTVSeries('A');
			}
			else {
				Utilities.printInvalid();
			}
		}
	}
	
	/*
	 * Method for removing a TV series
	 */
	public void removeTVSeries() {
		System.out.println("\n*=*=*=* REMOVE TV SERIES *=*=*=*");
		
		listTVSeries('A');
		
		boolean verification = true;
		
		while(verification) {			
			System.out.println("\nEnter appropriate number to remove series \nor 'list' to display series \nor press enter on an empty line to return to main menu: ");
			String input = in.nextLine();
			if(Utilities.checkForNull(input))
				break;
			else if(Utilities.checkForInt(input)) {				
				int intInput = Integer.parseInt(input);				
				if(intInput <= this.getShowArray().size() && intInput > 0)
				{
					System.out.println("\nAre you sure you wish to remove '" + this.getShow(intInput - 1).getTitle() + "'?\nOnce executed the data is not recoverable.\nEnter 'y' for yes or 'n' for no");
					boolean confirmVerification = true;
					while(confirmVerification) {
						input = in.nextLine();
						if(Utilities.checkForNull(input))
							Utilities.printInvalid();
						else if(input.toLowerCase().equals("y")) {
							this.showArray.remove(intInput - 1);
							System.out.println("~ TV SERIES REMOVED");
							return;
						}
						else if(input.toLowerCase().equals("n")) {
							return;
						}
						else
							Utilities.printInvalid();
						
					}
				}
				else
					Utilities.printInvalid();
			}
			else if(input.toLowerCase().equals("list")) {
				seriesID = 1;
				System.out.println("");
				
				listTVSeries('A');
			}
			else 
				Utilities.printInvalid();
			
		}	
	}
}
