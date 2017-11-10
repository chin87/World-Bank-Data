## Following branch follows MVP architecture pattern 
### It uses following components: 
 1. Retrofit: for communication
 2. Eventbus: for event handling
 3. Databinding: android databinding for binding views 
 
MainActivity is View: 
 -- It has a button to fetch latest data from worldbank.org api's
 -- It will show a indefinite progress until data/error is received
DataCatalogPresenter is presenter for MainActivity
 -- calls WebCommunicator class for communication to fetch data
 -- contains business logic for parsing and communicating data to View
 

###To see problem statement please check master branch.
