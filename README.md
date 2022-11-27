# Excess Gone

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Contact](#Contact)
1. [Acknowledgements](#Acknowledgements)

## Overview

### Description

This app will allow to decrease food wastage, by connecting resaurants with homeless shelters to donate excess food to the homeless people, resulting in a good idea to solve the issue of hunger and food insecurity among the homeless. 

### App Evaluation


  - **Category:** Food Donating System
   - **Mobile:** This app will use location to find near by homeless shelters and it will use pictures of food to be donated using camera. 
   - **Story:** This app will reduce waste and helping with lowering chance of the food being wasted, and instead being used to help homeless people, in which the food is being put to good use. 
   - **Market:** This app targets unique market, which is the resturants and owners of resturants as well as homeless shelters.
   - **Habit:**  This app will be used everyday at the end of the working hours of the resturant to let the shelters know what is available to be donated the next day. 
   - **Scope:** The scope of this app is to use google maps API, use SQLite to save the information about resturants donating.

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

- [X] Create layouts - activities & fragment: 
* activity home has two fragments: map and history with navigation bar included. 
* three activities: history activity, form activity, detail activity.
* history activity: have a list of past shelters that the restaurant has donated to.
* form activity: allow user to fill out the information about the shelter that's being donated to plus the donated food. 
* detail activity: allow user to click on the recyclerview items in history activity to see more detailed version of the past forms. 

- [ ] Use GoogleMap API to find homeles shelter near the Restaurant
* setup the map on the map fragment.
* get user's current location.
* find the nearest homeless shelters using Places API.
* add shelter information on the map marker of each shelter near the user.

- [ ] Add database for restaurant's profile
* Set up firebase on Android studio
* Allow the form information to upload to firebase, including food image.
* Allow the firebase data to get back to recyclerview, including food image. 
* Create data to show past food given in the fragment of history.
* Organize data in card view models showing only the shelter restaurant and the food donated, then allow user to press on each card to get into the detail of what they submitted to the shelter.


**Stretch Features**

- [ ] Chat between shelter and restaurant to allow for any problems or challenges to arise and be solved.
* "Hi [restaurant name], my shelter, [shelter name], did not get the delivery of [food being donated]."
- [ ] Bar graph analysing types of foods being donated from different restaurants
* For pure statistics and data gathering/collection.
- [ ] Turn navigation bar to hamburger menu
* Hamburger menu tends to be more aesthetic and easier to use than bottom navigation view.
- [ ] Food API to add to list 
* Instead of having user write out the meal type, they can select it from a drop down menu and add their own food type if needed.
- [ ] Use common aesthetic practices such as upgrading navigation bar look.
* Either turn navigation bar to hamburger menu, or give it an upgraded look, or add more tabs. 
- [ ] Update the form and make it more convenient to choose a location instead of typing one.
* Allow the form to have features such as drop down food menu or drop down location of nearest shelters.


### 2. Screen Archetypes

- Home Creation
    - User can search "homeless shelters near me", and then, go to another activity, and that activity shows the map with the results
- Stream
    - User can navigate to the database list of past given food, and scroll through that list
- New Food Creation
    - User can send request to send food donation data to the shelter
- Camera Creation
    - User presses button the form, leads them to take pics of the food they are giving.
- Detail
    - Sending request to see detail about the food


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Maps
* History

**Flow Navigation** (Screen to Screen)

- Home Creation
    - If search 
        - Maps
    - If send request
        - New Food creation screen
- New Food Creation
    - Camera Creation
- Camera Creation
    -  New Food Creation
- History
    -  Stream
        -  Detail 


## Wireframes
<img src="wireframe.jpg" width=600>

### GIFs of Milestones & Progress
* Gifs created with [ScreenToGif](https://www.screentogif.com/)

---------- Part 1 ------------------------------ Part 2 ------------------------------ Part 3 ----------

<img src='Part1.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' width="200" height="450"/><img src='Part2.gif' title='Video Walkthrough' width='' alt='Video Walkthrough'  width="200" height="450" /><img src='Part3.gif' title='Video Walkthrough' width='' alt='Video Walkthrough'  width="200" height="450" />

## Contact

- Email: nour.siwar@outlook.com
- LinkedIn: https://www.linkedin.com/in/nour-siwar/

## Acknowledgements

- [Neko Code - Form Design](https://www.youtube.com/watch?v=eKVWsrNVRJA)
- [CodePrim - Form design](https://www.youtube.com/watch?v=g_ZxWOUCbHg)
- [Bersyte - Implementing Camera API](https://www.youtube.com/watch?v=HjXJh_vHXFs&t=2s)
- [Hardki Parsania - Setting up Maps in Fragment](https://demonuts.com/google-map-in-fragment-kotlin/)
- [EDMT Dev - Getting nearest places video series](https://www.youtube.com/watch?v=NfF2_tr35SU)
- [The Android Enigma - Setting up Firebase](https://www.youtube.com/watch?v=YGgauhOiF1c&lc=UgzKkeLNtipZHl7Rc5Z4AaABAg.9i6g-Kx4EXI9iV3yDfMJyJ)
- [Foxandroid - Uploading Firebase data to Recyclerview](https://www.youtube.com/watch?v=M8sKwoVjqU0&t=45s)
- [Foxandroid - Uploading images to Firebase](https://www.youtube.com/watch?v=g2Iibnnqga0&t=620s)
- [Codepath - Creating and using Fragments](https://guides.codepath.com/android/Creating-and-Using-Fragments)
- [Philipp Lackner - Retrieving image from Firebase Storage](https://www.youtube.com/watch?v=g04l2nH5M80&t=132s)
- [CodingZest - Adding a ClickListener on RecyclerView Item with Firebase Realtime DB](https://www.youtube.com/watch?v=0oOC9cdN2I0)
- [Zeeshan Academy - Solving black screen emulator error](https://www.youtube.com/watch?v=0fk47QksSeA&t=145s)
- [Technical Skillz - Adding zoom controls to Maps](https://www.youtube.com/watch?v=Cz8MVEV8rZ4)
- [Convert JSON to C# - Retrieving nearby places from Places Api and converting to C# code](https://json2csharp.com/)
