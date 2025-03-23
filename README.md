# Social-Media-System-CS151-Spring-2025
# Overview
This is a program that emulates a social media system i.e. Facebook and MySpace. It contains basic functionalities such as creating an account, joining a group, adding friends, messaging with friends, and creating posts.
# Design
Classes:
* Forum - main, holds most of the data such as users in the database and their groups, functions as ui holding together all the systems and allowing for the user to interact with everything.
* Group - holds consumers, basically a sub-forum where assigned moderators can delete posts and remove other users from the group
* Messages -  message functionality, holds the information and ui to let users send and receive messages from friends.
* Moderator - user but with the ability to delete posts from groups, groups, and other users
* Consumer - basic user, holds username/password, posts, messages, friends
* Post - post functionality, holds information regarding the description of the post and the amount of likes it has. nested comment class lets it hold comments.
* Sharer - abstract class that shares some functionality with messages and posts.
* Maneagable interface - allows us to change properties like usernames, passwords, group data. Allows deleting Users or group data and gives you some general information about the group or user.
  
Our social media system is designed where the Forum class functions as the main database, holding the registered Consumers and created Groups. Posts, Friends, and Messages are held within Consumer. When a user logs into our system, they're the designated current Consumer and is given a menu that lets them view posts, view groups, view or add friends, message other users, change settings, or start any other feature. Each feature has their own submenus where the user is guided through them and has to answer questions about what they want to do in that feature. When the user is finished and ready to logout, they are able to logout and either sign up for a new account or login to a new one. For designing the ui, we wanted to make it straightforward and easy to navigate.
# Installation Instructions
* Clone this repository: https://github.com/Xrainer101/Social-Media-System-CS151-Spring-2025
# Usage
* Open the project and run Forum.java
# Preloaded data -> Username: "Sza", password: 1234; Username Matt password:1234
# These accounts allow you to test all of the features since they are friends.
* You can sign up for new accounts
* Login with the new acccount or test accounts
* Input EXIT at anytime in Forum and other applicable places to completely close out of the program, data is not saved.
* If you need to stop the program in the middle (hit control c).
* A main menu gives multiple prompts: (vf: view friend means to view friends posts)
* Enter the two character string input you want to use
* A lot of inputs in the features have (y/n) inputs: Enter y to do what the program says and n not to.
* In certain situations (like in viewing posts, viewing friend, etc) you might have to enter "n" in (y/n) multiple times to back to the main menu
# Contributions
* Steven: created Moderator and Group classes, Forum functionality for Group, wrote the README and made the UML diagram, Design
* Saivardan Mamidi: Forum (linking stuff from other classes), general functionality (adding friends, posts), abstract class for posts and messages.
* Hetav Vyas: Private messaging(UI, sending, receiving), Design
* Isaac Kim: Created Interfaces, worked on implementing interfaces to different classes, experimented on an abstract class for User, Design
