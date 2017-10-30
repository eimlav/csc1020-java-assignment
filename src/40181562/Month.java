import java.time.YearMonth;

/*
 *  Class used to convert a numerical value to a month
 *  and return both the name and days of said month 
 */
public class Month {
	
	/*
	 * Instance variables
	 */
	private String name;
	private int days;
	
	/*
	 * Constructor
	 */
	public Month(int monthNumber, int yearNumber)
	{	
		int year = yearNumber;
		
		switch(monthNumber)
		{
			case 1:
				this.name = "January";
				this.days = YearMonth.of(year, 1).lengthOfMonth();
				break;
			case 2:
				this.name = "February";
				this.days = YearMonth.of(year, 2).lengthOfMonth();
				break;
			case 3:
				this.name = "March";
				this.days = YearMonth.of(year, 3).lengthOfMonth();
				break;
			case 4:
				this.name = "April";
				this.days = YearMonth.of(year, 4).lengthOfMonth();
				break;
			case 5:
				this.name = "May";
				this.days = YearMonth.of(year, 5).lengthOfMonth();
				break;
			case 6:
				this.name = "June";
				this.days = YearMonth.of(year, 6).lengthOfMonth();
				break;
			case 7:
				this.name = "July";
				this.days = YearMonth.of(year, 7).lengthOfMonth();
				break;
			case 8:
				this.name = "August";
				this.days = YearMonth.of(year, 8).lengthOfMonth();
				break;
			case 9:
				this.name = "September";
				this.days = YearMonth.of(year, 9).lengthOfMonth();
				break;
			case 10:
				this.name = "October";
				this.days = YearMonth.of(year, 10).lengthOfMonth();
				break;
			case 11:
				this.name = "November";
				this.days = YearMonth.of(year, 11).lengthOfMonth();
				break;
			case 12:
				this.name = "December";
				this.days = YearMonth.of(year, 12).lengthOfMonth();
				break;								
		}
	}

	/*
	 * Method to get the name of the month
	 */
	public String getName() {
		return name;
	}

	/*
	 * Method to set the name of the month
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Method to get the days of the month
	 */
	public int getDays() {
		return days;
	}

	/*
	 * Method to set the days of the month
	 */
	public void setDays(int days) {
		this.days = days;
	}
	
}
