# marvel-heroes-ff
Fun mini project to test my skills

The project is organized into 3 main packages (comms, datastorage and ui).

comms contains everything related to communications.

datastorage contains everything related to persistance.

ui contains everything related to UI (activities, customviews, adapter and view holders).


Communications make use of Retrofit. Only one endpoint is used in this app (v1/public/characters) and can be found in MarvelApi interface in comms package.

Realm is used for data storage. The app makes use of RealmChangeListener to add a reactive efect to the app. 
The data fetched with Retrofit goes to Realm. This trigers RealmChangeListener onChange() method which updated the UI (adapter in this case) In the LandingPageActivity.

The "favorites" action updates the element in Realm and this again trigers RealmChangeListener onChange(), which again updates the UI.

Both activities make use of a CustomToolBar, which is a subclass of ToolBar. This element is responsible for displaying the Tool bar title and actions (search and search field).

The app UI is not prepared for rotation


