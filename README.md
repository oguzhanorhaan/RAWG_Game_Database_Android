# RAWG Game Database

## Requirements
- Kotlin 1.3x
- Android API 26+
- JDK at least 1.8

## Installation

#### Dependencies
For Gradle dependencies, `Android Studio` will take care of them. So, simply open project in Android Studio then wait
for Gradle Sync. You are ready to go! Used libraries in the project is :  

- Retrofit2  
- Moshi  
- Glide  
- Coroutines, Flow, LiveData
- SafeArgs Plugin : is a Gradle Plugin that generates code to help guarantee that the arguments on both side matches up while also simplifying argument passing between fragments.
- Koin for dependency Injection
- Mockito
- Firebase Crashlytics, Analytics
- Room
- KtLint

## Structure  
The used architectural pattern in the project is MVVM with Clean Architecture. Project consist of 4 layers   
- Presentation : Includes activities, fragments, view-models and UI related things.   
- Domain : Includes usecases, domain object models, repository interfaces.   
- Data : Includes repository implementations, data-level model objects, data-source interfaces.  
- DataSource : Includes data-source implementations, API interfaces, response handlers and data-source models.   
![Screenshot](Clean_arc.png)   

Commit Message Convention 

 - Init: Initialize a library or feature or file
 - Add: A new feature or file  
 - Update: Update a file or structure
 - Fix: Fix changes  
 - Remove: Remove a file or code  
 - Test: Add new tests to project 
 - Format: Linter format

Commit messages and branches are below:
![Screenshot](commits.png)

Project divided into 7 tasks, ITS(RAWG):   
RAWG-01 - Add splash screen for the project  
RAWG-02 - Add base structure for the project
RAWG-03 - Create List screen and details screen
RAWG-04 - Add room db save to locale feature
RAWG-05 - Add favourites page
RAWG-06 - Add Crashlytics and Analytics  
RAWG-07 - Refactor code and add tests.  

