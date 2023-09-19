[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/U8kcykWd)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=11499447)

# Group 7: EesyEets
### Purpose of App
The purpose of this app is to assist people of all culinary experiences in finding new meals to cook. 
Version 1.0.3 brings many new improvements, including the ability to save a meal to be able to look at it later using ViewModel to get Data persistence. 
This new version implements a ViewModel with LiveData to hold the user’s name, and a brand new page that allows for the user’s location to be used in order to find the closest supermarkets.
Lastly there is also the feature of a view that allows the user to take pictures of the meals they create in our app!

### How to find new features:
You can find the new additions to the app by going to the bottom navigation of the app. 
The "Camera" tab will take you to the camera function where you can take a picture in-app and store it to your phone.
The "Browse" tab will allow you to pick a meal from the displayed list and once clicked on, the meal will give the option to save it. 
The "Featured" tab will take you to your saved meal, saved from the "Browse" tab. 
This data will persist if the app is killed, due to the DataStore usage of LiveData and ViewModel.
If you are looking for the map fragment, you will go through the fragments, pressing the buttons on the fragments themselves.
Click "Let's get started", then on the new fragment click any button ("Breakfast", "Lunch", or "Dinner"). 
Then pick a dish offered by the next fragment. It will then take you to a fragment with a button that says "Find Store." 
Click on that button to get to the map fragment. 
The LiveData and ViewModel is used to display the user's name, defaulting to "Friend." The 





# Resources:
* [Stack Overflow](https://stackoverflow.com/questions/54484585/googles-new-places-library-implementation-com-google-android-libraries-place)- This site was used in understanding why places was not working as expected. 
* [Stack Overflow](https://stackoverflow.com/questions/23546303/android-app-application-cannot-be-cast-to-android-app-activity)- This resource we used in figuring out class casting error
* [Google Documentation](https://developers.google.com/maps/documentation/places/android-sdk/client-migration#install_the_client_library)- We used this site to understand the places version needed for what we were trying to accomplish.
* [StreetMap](https://wiki.openstreetmap.org/wiki/Tag:shop%3Dsupermarket)- We used this site to get the desired supermarket tags.
* [Stack Overflow](https://stackoverflow.com/questions/38068786/places-class-is-removed-from-android-play-services-9-2-0/38075730#38075730)- We used this site to find missing a dependency.
* [Kingsoopers.com](https://www.kingsoopers.com/stores/search)- This is the site we use in our app to help us find a store near the customer.
* [ChatGPT](https://chat.openai.com/c/e14213d9-37bd-4896-be1f-c4a0a95fb024) - We used this to understand why the map fragment wasn't loading a second time in the app.
* [ChatGPT](https://chat.openai.com/c/622ec95a-b5b8-4b55-a767-c99b7ea18474) - We used this to understand how to register for the activity result in the camera functionality.

# Part Two Sources
### API sources:
[API call](https://www.themealdb.com/api/json/v1/1/random.php)- Here is an example of a JSON response from our API.
[API Documentation](https://www.themealdb.com/api.php)- TheMealDB API site and documentation
### Third party sources
* [MSU Denver](https://msudenver.instructure.com/courses/82419/discussion_topics/1010646)
* [Stack Overflow link 1](https://stackoverflow.com/questions/56639529/duplicate-class-com-google-common-util-concurrent-listenablefuture-found-in-modu)- We used this link to fix an error of unknown package.
* [Stack Overflow link 2](https://stackoverflow.com/questions/62837201/retrofit-error-expected-begin-array-but-was-begin-object-at-path)- We used this site to help fix an error of beginning item of API response being an object, not an array.
* [Kotlin documentation](https://kotlinlang.org/docs/collection-write.html)- This site we used to help understand Kotlin syntax.
* [Data into RecyclerView](https://appdevassist.com/android/android-load-data-in-recyclerview-from-api)- This helped us get the api call into recycler view.
* [Stack Overflow link 3](https://stackoverflow.com/questions/35093884/retrofit-illegalstateexception-already-executed)- This helped us learn how to call multiple times.
* [ChatGPT link](https://chat.openai.com/share/ea9cc22b-a9ea-4ccc-9582-7c6d9037679c)- This links to the conversation with ChatGPT that helped with getting multiple api calls into multiple view holders.
* [Stack Overflow link 4](https://stackoverflow.com/questions/53036425/how-to-set-two-columns-recyclerview)- This link we used to help format the view holders into a grid.
* [Kingsoopers.com](https://www.kingsoopers.com/stores/search)- This is the site we use in our app to help us find a store near the customer.
* [YouTube](https://www.youtube.com/watch?v=4lEnLTqsnaw&pp=ygUbYW5pdG1hdGUgYW5kcmlvZCBiYWNrZ3JvdW5k)- We used this video to get our animated background working.

# Part One Third Party Resources
* [Stackoverflow-Runtimeexeception](https://stackoverflow.com/questions/43342068/java-lang-runtimeexception-unable-to-start-activity-componentinfo-mainactivity)- We used this site when we needed to figure out a specific error because our navigation file wasn't named the same
* [Stackoverflow-LogcatIssue](https://stackoverflow.com/questions/48005090/android-studio-logcat-no-connected-devices)- Used when not able to use the emulator due to an error message saying it was already running when it was not. The site said to delete the device and start again.
* [Blog-JetpackNav](https://blog.devgenius.io/bottom-navigation-made-simple-using-jetpack-navigation-component-efcdbfc913be)- We had a problem with Jetpack Nav not opening to the main page. We had to change the starting fragment.
* [Stackoverflow-Labeling](https://stackoverflow.com/questions/37112967/android-cannot-resolve-symbol-id-mylabel)- This site we used when getting an error about labeling. We had to make sure the id tag was correct.
* [Stackoverflow-JetpackNav](https://stackoverflow.com/questions/37112967/android-cannot-resolve-symbol-id-mylabel)- We used this site to help us understand the reason that we couldn't have multiple activities on Jetpack Navigation.
* [Google-ElvisOperator](https://www.google.com/search?q=elvis+operator+kotlin&rlz=1C1SQJL_enUS1048US1048&oq=elvis+operator&aqs=chrome.1.69i57j0i512l9.4451j0j7&sourceid=chrome&ie=UTF-8)- We made use of the Elvis operator and used the Kotlin site to help us with that.
* [Blog-JetpackNav2](https://blog.devgenius.io/bottom-navigation-made-simple-using-jetpack-navigation-component-efcdbfc913be)- This was a good site to use as an example of what we wanted to do.
* [Materialpalette](https://www.materialpalette.com/)- for a Color Palette for our Material Design
* [Drawables](https://pictogrammers.com/library/mdi/)- Drawable XML files which we used so that we could have unique buttons.
* [Atif Pervaiz](https://www.youtube.com/watch?v=6ms2TFwA8nU)- allowed us to implement a Webview in Fragments. 
