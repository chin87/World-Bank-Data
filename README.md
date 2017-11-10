## Following branch follows MVC architecture pattern 
### It uses following components: 
 1. Retrofit: for communication
 2. Eventbus: for event handling
 3. Databinding: android databinding for binding views 
 
activity_main.xml is view
MainActivity is Controller: 
 -- It has a button to fetch latest data from worldbank.org api's
 -- calls WebCommunicator class for communication to fetch data
 -- contains business logic for parsing and communicating data to View
 
Model class: 
WebCommunicator can be considered as model class

###To see problem statement please check master branch.
