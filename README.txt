= = = = = = = = = = = = = 
 CSC1020 Java Assignment
  TV Review Application
      Eimhin Laverty
= = = = = = = = = = = = = 

To edit or remove a TV series, administrator mode must be activated. 
To do this go to the main menu, then the 'Activate administrator mode' option (option 4). 
The password is: Admin16

For the purposes of the project, only a small sample of data has been used.

To save any changes made in the application, ensure that you exit by using the 
'Exit application' option in the main menu.

= = = = = = = = = = = =
 About the application
= = = = = = = = = = = =
This solo Level 1 project involved utilising Java to create a console based TV series rating application as well as producing a video demonstrating the end product. As part of the requirements, the application had to contain the functionality to allow a user to add, edit and rate a tv series.

This project was my first foray into using Java to build a complex application made up of multiple classes. It required applying all the knowledge I had gained from my Programming module. One of my primary requirements was to provide the user with a console based menu to carry out all the previously mentioned requirements. In order to achieve a high mark, it was necessary to implement additional features.

Initially, time was spent on building the basic functionality by focusing on the core aims of the project. I built a preliminary TVSeries class which is used to store tv series data as an object. I also added additional classes such as Episode containing the episode season, number and title. In doing so, this provided an easier means of reading and manipulating data related to episodes. The main class, creates an instance of SeriesLibrary, which is the class containing the methods used to retrieve and export tv series data from a CSV style text file, and then parses it into TVSeries objects. The adding/editing functions proved to be particularly difficult as they required the implementation of extensive validation to ensure that valid data was entered. One of the things I did in order to achieve this was to use an enum for genres to encourage consistency in the data.

The project required a considerable investment of time and effort to ensure I achieved a high 2:1 grade. However, looking back at the code, I realise that the approach I had taken in the architecture of the solution may not have been the most effective route. In future projects I will now appreciate the value of properly planning out not just the general features and front end of a project but more importantly, the internal design of the application you are building.
