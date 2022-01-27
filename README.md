
# Share

<hr>

## Project Setup (IntelliJ)

<hr>

1. Clone the repository to your local machine using new project from version control
2. Make a copy of the example.properties and name it application.properties
3. Update the username and password in application.properties to the database you will be using on your local machine
4. Be sure you are running MYSQL before running the application

## Description

<hr>


##  Stories

<hr>

<details>

<summary>Visitor</summary>


    - As a visitor, when I complete the registration form I will be redirected to a welcome/profile screen. 


    - As a visitor, I cannot use the platform or access its features until I make an account.

</details>

<details>
<summary>Registered User </summary>

    - As a user, I can create posts and they will be displayed in the main list as well as on my personal profile page. 

    - As a user, I can edit the posts I created. 

    - As a user, I cannot edit/delete another user’s posts.
    - As a user, I can delete my own posts.
    - As a user interested in borrowing, I can click on posts I’m interested in and put in a borrow offer.
    - As a user interested in lending, if I receive a borrow offer, I will receive a notification stating as such and can view the information regarding the offer.
    - As a user interested in borrowing, if I do not wish to borrow directly with somebody I can put in a trade offer instead.
    - As a user interested in borrowing, if I put in a trade offer, I can choose which item from my current listed ads that I wish to trade.
    - As a user interested in lending, if I receive a trade offer, I will receive a notification stating as such and can view the information regarding the offer.
    - As a user interested in lending, I can decide who can borrow my products/trade with me (accept/deny functionality.
    - As a user, I can see how much time is left on an item I borrowed or the time left on an item someone is borrowing from me.
    - As a user, I can leave reviews and send messages to other users.
    - As a user, I can view my own reviews and messages.
</details>

## Technologies

<hr>

<details>
<summary>Languages and Framework:</summary>

    - HTML
    - Javascript
    - Java
    - CSS
    - Boostrap
    - Springboot
    - Thymeleaf
    - MYSQL
</details>

## Feature List

<hr>

<details>
<summary>This application includes the following features:</summary>

    - Implement CRUD for Posts (Create, Edit, Delete)
    - Implement CRUD for Profile
    - Register users and allow them to login
    - Individual Post show page
    - Allow a user to send a request for a borrow on Individual Post show page (send request to seller so they can confirm or deny request)
    - Create a collective Ads page which should contain links to each individual Post page
    - Allow users to logout
    - Search functionality that allows users to search through the post in your database by title (ZIP code)
    - Show the user's posts on their profile page
    - Ensure usernames are unique 
    - Dynamic navbar for logged in users, guests and admins
    - Error messages
    - Sticky Forms
    - Allow a post to a category
    - Allow a post to have an image
    - Intended Redirects (Register to Login, Clicking post to Individual post page, etc)
    - Allow a user to leave a review on another person’s profile
    - Allow a user to message another user
    - Allow a user to see favorited/bookmarked items 
    - Allow users to see notifications (inquiries about items, trade offers, etc)
    - Allow a user to view their reputation and see who posted them (the reviews)
    - Dictate if an item is available for a Take and Trade/trade or if it’s off the market
    - Allow a seller to see how much time is left for a user to borrow an item (ex: You’re sharing a bike! Time remaining: 2 days). Implement counter/timer as needed and have it update
    - Allow a user to see which time they’re borrowing and how much time is left (ex: you’re borrowing a bike! Time remaining: 2 days)
    - Allow a user to impose their own terms and conditions on listed items


</details>
