# Social-Media-System-CS151-Spring-2025
# Overview
This is a program that emulates a social media system i.e. Facebook and MySpace. It contains basic functionalities such as creating an account, joining a group, adding friends, messaging, and creating posts.
# Design
Classes:
* Forum - main, holds most of the data such as users in the database and their groups, functions as ui holding together all the systems and allowing for the user to manipulate it.
* Group - holds consumers, basically a sub-forum where assigned moderators can delete posts and remove other users from the group
* Messages -  message functionality, holds the information that lets the user send and receive messages from one another.
* Moderator - user but with the ability to delete posts, groups, and other users
* Consumer - basic user, holds posts, messages, friends
* Post - post functionality, holds information regarding the description of the post and the amount of likes it has. nested comment class lets it hold comments
Our social media system is designed where the Forum class functions as the main database, holding the registered Consumers and created Groups. Posts, Friends, and Messages are held within Consumer. When a user logs into our system, they're the designated current Consumer and is given a menu that lets them view posts, view groups, view or add friends, and message other users. The view posts and view groups submenus allow the user to create new posts or new groups. When the user is finished and ready to logout, they are able to logout and either sign up for a new account or login to a new one.
# Installation Instructions
* Clone this repository: https://github.com/Xrainer101/Social-Media-System-CS151-Spring-2025
# Usage
* Open the project in an IDE, such as IntelliJ, Eclipse
* Run 'Forum.java'
* Sign up for an account and log in
* Input EXIT at anytime to completely close out of the program, data is not saved.
* A main menu gives multiple prompts:
* viewPosts shows all the posts present in the forum and allows the user to select the index of which one they would like to view
* viewFriend lets the user input the name of another user
# Contributions
* Steven: created Moderator and Group classes, Forum functionality for Group, wrote the README and made the UML diagram
* Saivardan Mamidi: 
* Hetav Vyas:
* Isaac Kim:
