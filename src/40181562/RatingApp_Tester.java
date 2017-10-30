import java.util.Scanner;

/*
 * Tester class used to invoke the main menu
 */
public class RatingApp_Tester {
	
	/*
	 * Instance variable declarations
	 */
	public static Scanner in = new Scanner(System.in);
	public static SeriesLibrary seriesLibrary = new SeriesLibrary();
	
	/*
	 * Main method 
	 */
	public static void main(String[] args) {
		/*
		 * Reading in TV series from txt file and assigning to instance of SeriesLibrary
		 */
		seriesLibrary.readInTVSeries();

		/*
		 * Displaying main menu to user and accepting input		    
		 */
		mainMenu();
	}

	/*
	 * Method for displaying main menu
	 */
	public static void mainMenu() {
		boolean adminMode = false;
		boolean bool = true;
		while(bool)
		{
			String menuMessage;
			if(adminMode)
				menuMessage = "\n*=*=*=*=*=* T V  D A T A B A S E  M A N A G E R *=*=*=*=*=*\n	      *=*=* ADMINISTRATOR  MODE *=*=*\n1) Browse TV series\n2) Add TV series\n3) Rate TV series\n4) Edit TV series\n5) Remove TV series\n6) Exit application\n\nEnter appropriate number to select an option: ";
			else
				menuMessage = "\n*=*=*=*=*=* T V  D A T A B A S E  M A N A G E R *=*=*=*=*=*\n1) Browse TV series\n2) Add TV series\n3) Rate TV series\n4) Activate administrator mode\n5) Exit application\n\nEnter appropriate number to select an option: ";
			
			System.out.print(menuMessage);				
			boolean mainMenuBool = true;
			while(mainMenuBool) {
				String input = in.nextLine();
				switch(input) {
				case "1":
					if(emptyLibrary()) {
						System.out.println("\nNo TV series exist in library");
					}
					else
						seriesLibrary.browseTVSeries();
					
					mainMenuBool = false;
					break;
				case "2":
					seriesLibrary.addTVSeries();
					mainMenuBool = false;
					break;
				case "3":
					if(emptyLibrary()) {
						System.out.println("\nNo TV series exist in library");
					}
					else
						seriesLibrary.reviewTVSeries();
					
					mainMenuBool = false;
					break;
				case "4":
					if(adminMode) {
						if(emptyLibrary()) {
							System.out.println("\nNo TV series exist in library");
						}
						else
							seriesLibrary.editTVSeriesMain();
					}
					else {
						if(activateAdminMode())
							adminMode = true;
					}										
					mainMenuBool = false;
					break;
				case "5":
					if(adminMode) {
						seriesLibrary.removeTVSeries();
					}
					else {
						if(!emptyLibrary()) {
							System.out.println("\n\n*=*=* SAVING ANY CHANGES *=*=*\n\n*=*=* APPLICATION TERMINATED *=*=*");					
							seriesLibrary.writeTVSeriesToFile();
						}			
						else
							System.out.println("\n\n*=*=* APPLICATION TERMINATED *=*=*");
						System.exit(0);
					}
					mainMenuBool = false;
					break;
				case "6":
					if(adminMode) {
						if(!emptyLibrary()) {
							System.out.println("\n\n*=*=* SAVING ANY CHANGES *=*=*\n\n*=*=* APPLICATION TERMINATED *=*=*");					
							seriesLibrary.writeTVSeriesToFile();
						}			
						else
							System.out.println("\n\n*=*=* APPLICATION TERMINATED *=*=*");
						System.exit(0);
					}
					else
						System.out.println("\nInvalid input. Please try again: ");					
					break;
				default:
					System.out.println("\nInvalid input. Please try again: ");
					break;
				}
			}						
		}
	}
	
	/*
	 * Method for activating administrator mode
	 */
	public static boolean activateAdminMode() {
		System.out.println("\n*=*=* ACTIVATE ADMINISTRATOR MODE *=*=*\nEnter admin password\nor press enter on an empty line to return to main menu: ");
		String adminPass = "Admin16";
		boolean output = false;
		
		boolean verification = true;
		while(verification) {
			String input = in.nextLine();
			if(Utilities.checkForNull(input))
				return false;
			else if(input.equals(adminPass)) {
				System.out.println("\n~ PASSWORD ACCEPTED");
				output =  true;
				break;
			}
			else if(input.equals("back")) {
				output = false;
				break;
			}	
			else
				System.out.println("\nIncorrect password. Try again:");
		}
		
		return output;
	}
	
	/*
	 * Method for checking for empty library
	 */
	public static boolean emptyLibrary() {
		if(seriesLibrary.getShowArray().size() == 0)
			return true;
		else
			return false;
	}

}
