# Psyche-Mobile-App--Whirlpool
Capstone Mobile App development project

## Introduction
Psyche: A Journey to a Metal World is an ASU mission chosen by NASA to launch a spacecraft to an asteroid between Mars and Jupiter. Scientists believe that the asteroid is the core of a protoplanet, so studying it may provide information about the Earth’s own core. In order to spread information about the mission and reach a large audience, ASU aims to develop a mobile application.

The Psyche mobile application must include five main features:
1. A multimedia gallery that can accommodate still images and videos
2. Aggregation of Psyche social media
   - https://twitter.com/NASAPsyche
   - https://www.facebook.com/NASAPsyche/ 
3. A mission countdown clock
4. An interesting/engaging way to present Psyche mission facts (such as size of the spacecraft, distance of the asteroid from Earth, etc.) 
5. An interesting/engaging way to present the Psyche mission timeline.

Additionally, the mobile app should include a “wild card” feature left to the creativity of the development team. This wild-card feature will be a small game.

Furthermore, the mobile app should target either Android or iOS, match the design guidelines set by the Psyche Mission, and be compliant with government 508 regulations. The mobile app must be fully built (capable of being launched on the Google Play Store) by April 18th, 2018. Once this deadline is reached, the app will be judged alongside four other apps developed by other groups.

Since the Psyche orbiter will not launch till 2022 and continue until 2027, the ASU Psyche team will be gathering multiple mobile app prototypes from other capstone groups. Due to this, the scope of our app is more short-term. However, this also means that our app should be modular and easy to update in case the Psyche team wishes to retain/update any of the features it includes in future versions of the app.

## Overview
The Psyche mobile application will be developed in Android Studio using Java. The five components and wild card game will have their own user interfaces that the user can switch between using a tab system. The components will be modular and thus not be coupled to one another with the exception of the Timeline and Mission Facts. All aspects of the mobile app should be Section 508 compliant, allowing disabled people to still access and use our app.

The mobile app should be capable of being launched in the Google Play store by the deadline April 18th, 2018. After this deadline, a group of judges from the ASU Psyche mission and NASA will decide on the final outcome of our app as well as the other four groups competing. Since this app will go through multiple iterations in the coming years, the components of the app should be extendable and maintainable. Furthermore, the ASU Psyche group intends to reach a general public audience, so the mobile app should appeal to as many users as possible.

## Intro Screens
The intro for the app only appears on the first run of the app or when the app data is cleared. It is a ViewPager with three screens. The java file for the pager is `FirstRunIntroActivity.java`, and the three classes for the page fragments are in `FirstIntroFragment.java`, `SecondIntroFragment.java`, and `ThirdIntroFragment.java`. The main layout with the buttons to navigate and indicators of which page the user is currently on are stored in `activity_first_run_intro.xml`, and the layouts for the three pages are in `fragment_first_intro.xml`, `fragment_second_intro.xml`, and `fragment_third_intro.xml`.

## Home Screen
The behavior for the home screen is defined in MainActivity.java and this file also contains the method which checks the last version run to determine whether the intro should be displayed or--in future implementations--whether update details should be shown.

The layout for the home screen is in activity_main.xml and the home screen contains six buttons including the NASA meatball logo. The buttons on the home screen toggle night/dark mode and link to the countdown clock, Psyche news, mission facts, and navigation help. Credits for the app are also accessible via the navigation help screen.

## Countdown Clock
The countdown clock is accessible from the home screen and its behavior is defined in the `CountdownActivity.java` file. A library from github is used to make the DonutProgress clock circles around the text. In order to support API 16, no libraries or more advanced java library functions were used to calculate the time remaining until the given dates. As the dates in the countdown get closer, a more precise method of counting may need to be transitioned to as this method may be off by couple of days due to leap years and the times and dates currently programmed are best estimates or goals. 

A countdown is created by the startCountdown method, and the manner in which this is written could support the display of multiple countdown clocks on the same page, but there is currently only one clock displayed at a time and a spinner is used to determine which clock that is. The default for the spinner is set to countdown to the next phase. Any and all active timers must be destroyed using the cancelTimers whenever the class is destroyed to avoid a memory leak. The layout for the countdown is in the `activity_countdown.xml` resource file.

## Mission Facts
The Mission Facts section of the app displays information pertaining to the Psyche mission. The section is divided into twelve titled items in a ListView format. When pressed, each item switches into a different activity filled with information in a question and answer format that details facts about the Psyche mission related to the title. The question “What does the Psyche asteroid look like?” is supposed to display an image at the end of the answer and it does not.

## Timeline
The Timeline section of the Psyche app displays events relating to the Psyche mission in a linear format separated into the respective phases. The phases are separated by using a tab system with a fragment for every system. In each fragment, a RecyclerViewer and custom adapter is used for programmatically instantiating each node in the timeline. By using a RecyclerViewer and custom adapter, the same fragment can be used for all phases of the Timeline.

The information displayed in each Timeline node is stored in the timeline.xml document. All of the information included in the XML document comes from the official Psyche timeline (https://psyche.asu.edu/timeline/). The information in the XML document is organized into two main nodes: an array of titles summarizing the events of a phase and an array containing the full details of the events of a phase. These nodes are then accessed in the Timeline java classes.

## Gallery
The Gallery section of the Psyche app allows the user to view various images and videos about the Psyche mission.

### Images
Due to the lack of an image API, the images are obtained using the image loading framework Glide (https://github.com/bumptech/glide). Thumbnails displayed in the image section of the Gallery are stored in-app, and when the user selects a thumbnail to view a larger image with its description, Glide is used to obtain the larger image. Image URLs and descriptions are stored in the `image_descriptions.xml` document. The XML document for the images contains three arrays:

- References to the thumbnail images
- URLs to the larger images
- Descriptions for every image in-app

Images and descriptions were obtained from the image section of the Psyche Gallery (https://psyche.asu.edu/galleries/images/). Future versions of the mobile app Gallery should implement an API that allows the app to fetch all images and descriptions from a server. This would allow the app’s Gallery to update at the same time as the Psyche website.

### Videos
The video section of the Gallery implements the YouTube Data API for video streaming. Additionally, the thumbnails displayed in the video section are obtained from API calls to the Psyche channel on YouTube (https://www.youtube.com/channel/UC2BGcbPW8mxryXnjQcBqk6A). The YouTube Data API was chosen instead of the Vimeo API due to the larger amount of videos found in the YouTube Psyche channel and due to the larger popularity of YouTube players for video streaming in apps.

Currently, the video IDs for obtaining the thumbnails are stored in the `VideoRecycleAdapter.java` class and the API key is in the `YouTubePlayer.java` class, but these should be switched out as needed for future versions of the mobile app.

## Social Media
### Instagram
~ Access Token: Access Token is currently stored in app. Token will need to be created in MainInstagramActivity.Java

~ API call: Once Facebook has reviewed and approved instagram_basic permissions, Facebook graph API will be available for integration. Line 63-251 in MainInstagramActivity explain in detail how to obtain access token, what code to remove, what web service call to edit, and what JSON’s will need to be modified. For instructions on what to pass into GraphRequest see Facebook notes below.

~ Model Class and Adapters:  Model class will not need to be modified at it is the foundation for obtaining the JSON objects from the web service call. Contents contain variables to store amount of likes, amount of comments, profile image, user name, caption, and main image. Adapter class uses the model to set Recycleviewer items. 

~ Respective Class and XML:

MainInstagramActivity.Java  -> instagramactivity_main.xml
Adapter.Java -> list.xml

### Twitter
~Access Token: Only thing needed is to create a Twitter Development Account or use previously created account. No access token is required, Consumer key and Consumer Secret can be used for addition implementations such as allowing user to log in.

~ API call: Twitter API use is very simple. With Twitter API already implemented in project, only thing needed is to instantiate the Twitter API instance and use its adapter to set the Listview. Examples in line 60-69 in TwitterActivity.Java iis where the Twitter instance is created and where the Twitter adapter gets used an set

~ Model Class and Adapters:  No Model class or custom adapters is needed. Twitter API provides both of these

~ Respective Class and XML:

TwitterActivity.Java  -> activity_socialmedia.xml

### Facebook
~ Access Token: Access Token is currently stored in app. Token will need to be created in FacebookActivity.Java

~ API call: To obtain the page’s feed, facebook API is used along its graphrequest call. Graphrequest takes in the following parameters: Access token, graph explorer get parameters, null, HttpMethod.Get(Get webservice request), and a Graphrequest callback. 
Ex:
new GraphRequest(
accessToken, "/userid/feed?fields=id,message,full_picture,story,created_time", null,
HttpMethod.GET,new GraphRequest.Callback(){})

~ Model Class and Adapters:  Model class will not need to be modified at it is the foundation for obtaining the JSON objects from the web service call. Contents contain variables to store id, message, picture, story, and time. Adapter class uses the model to set Recycleviewer items.
ListAdapter class uses the model to set Recycleviewer items. 

~ Respective Class and XML:

FacebookActivity.Java  ->activity_facebook.xml
ListAdapter.Java -> fbframe.xml

## Wild Card: Psyche the Game
The game was built using the Unity3D game engine, and uses a mix of 3D and 2D features to create mostly 2D gameplay. During gameplay, the player controls the Psyche probe in a abstract journey around the Psyche asteroid surface, maneuvering the probe up and down to avoid spaceborn rocky debris and to align with photography points (indicated by reticles). At each of these points, an image of the below geography is generated, scored for points, and (if newly a high score) stored on the local hard disk for looking at later. The randomized nature of these points and of the players ability to hit these points means these photos will be different each time.

The controls are limited to a single press on the screen, which allows the player to thrust the probe upwards. The probe moves down overtime under the force of the gravity of the asteroid. The physics of the situation are not particularly accurate to reality but rather towards good gameplay.

No existing Psyche 3D model was suitable for the purposes of providing the base of the play space, so the game uses instead a textured sphere with metallic shading/lighting (the game taking place in one spot, while the sphere rotates underneath). Because of this lack of detail, the intended play experience of going up and over parts of the terrain is not existent . In addition, the generated photographs are much less interesting than they should otherwise be. In the future more assets can be created to make a detailed asteroid surface with varying features.

### Game Integration
~Steps for integrating Unity Game into Existing Android Studio Project:

Step 1: .File->Build Settings...

Step 2:  Select Android on the left then tick Google Android Project checkbox.

Step 3: Check in the boxes titled Export Project and Development Build

Step 4: You can simply select the folder where your existing project is located. I exported it under the app folder and also outside of the app folder to make sure . Unity should add a sub folder with an Android Studio format project and all dependencies and everything it needs to run.

Step 5: Now we need to convert this "Application" project to a "Library" project. This is simply done by changing the build.gradle file. Replace apply plugin: 'com.android.application' with apply plugin: 'com.android.library'. You may also need to change classpath to 'com.android.tools.build:gradle:3.0.1' if you get a lint error on build

Step 6: You also need to remove the applicationId and change the compile versions to match your project's versions.

Step 7: Make sure to modify the Unity module AndroidManifest.xml file. You will need to remove everything inside  application in the manifest. You may also need to add <activity android:name="com.YourCompanyName.YourProductName.UnityPlayerActivity"> </activity>
Note: com.YourCompany is the name of the folder the UnityActivity class is in.

Step 8: Finally in your settings.gradle add the module and then add the Unity project dependency on your "app" module: compile project(":PsycheWhirlpoolGame-debug") and add ':PsycheWhirlpoolGame-debug’ project(':PsycheWhirlpoolGame-debug').projectDir = new File('PsycheWhirlpoolGame') in the gradle settings

Step 9: sync gradle, clean, and build and the unity project is ready to go
