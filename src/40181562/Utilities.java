/*
 * Class used to store static methods used by multiple classes
 */
public class Utilities {

	/*
	 * Method for checking if a given String can be converted to an int
	 */
	public static boolean checkForInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/*
	 * Method for checking if a given String is null/empty
	 */
	public static boolean checkForNull(String input) {
		if(input.length() == 0 || input.charAt(0) == ' ') {
			return true;
		}
		else
			return false;
	}
	
	/*
	 * Method for checking if a given String can be converted to a Double
	 */
	public static boolean checkForDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/*
	 * Method for checking if a percentage is present in a given String
	 */
	public static boolean checkForPercentage(String input) {
		boolean result = false;
		for(char i : input.toCharArray()) {
			if(i == '%') {
				result = true;
				System.out.println("\nInvalid input. Illegal character '%' found");
				break;
			}				
		}
		
		return result;
	}
	
	/*
	 * Method for printing 'Invalid input'
	 */
	public static void printInvalid() {
		System.out.println("\nInvalid input. Please try again:");
	}
	
	/*
	 * Method for printing 'Invalid input' with a line break afterwards 
	 */
	public static void printInvalid(boolean lineBreak) {
		System.out.println("\nInvalid input. Please try again:\n");
	}
	
	
}
