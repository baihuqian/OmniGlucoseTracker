Instruction to execute the project source code:

1. Download Google Play Services and Google APIs Level 19 in Android SDK Manager;

2. Import google-play-services-lib project at <path-to-sdk>\sdk\extras\google\google_play_services\libproject into Eclipse workspace;

3. Right click on OmniGlucoseTracker project, select Properties, in Android panel, select Google APIs Level 19 as the Project Build Target, remove all existing libraries, and add google-play-services-lib project to library.

4. Change meta-data "com.google.android.maps.v2.API_KEY" to your api key;

5. Run project from Eclipse.