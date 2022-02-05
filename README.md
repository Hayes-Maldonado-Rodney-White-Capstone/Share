<div align="center">
<img src="https://i.postimg.cc/6qJ25kTs/logo-4-1.jpg" width="700">
</div>

<div align="center">
<h3>https://share-project.site/</h3>
</div>

## About The Idea

Do you have things laying around your house that you don’t use anymore? Do these things still have use? Would you like to make money off of your items without selling them? You can repurpose and lend out your things using Share!

Share is a social networking and online marketplace that allows members to share and borrow items with fellow neighbors. Share users can list their items, freely adjust daily borrow fees and time limits, and engage in lending with other users. Users can also leave reviews and message others directly, ensuring open and honest communication with the Share community as a whole, so you’ll never feel unsure of your lendings or borrows.

Our mission here at Share is to help people become more connected with their neighborhoods and keep the world a little greener. In past generations, not everybody owned a tent, a specialty tool or a bicycle. These things were commonly lent to trusted neighbors as a regular practice. Share is hoping to revive this tradition in a modern and digital way, making things more accessible to others. Using Share will also negate the need for throwing away unwanted items somewhere to rot and encourage recycling, community engagement, and of course, sharing.

## Technologies/Project Setup

This full-stack application was made using HTML, CSS, Bootstrap, JavaScript, MySQL, Java, SpringBoot, Thymeleaf and multiple APIs. This application is also completely mobile-responsive with all original assets created using Clip Studio Paint. In a team of four,  we were involved in the full software development cycle including planning, standups, coding, testing, and git workflow. This project demonstrates all the skills learned during the 6 month bootcamp experience at Codeup.

#### Setup

1. Clone the repository to your local machine using new project from version control
2. Make a copy of the example.properties and name it application.properties
3. Update the username and password in application.properties to the database you will be using on your local machine
4. Be sure you are running MYSQL before running the application
5. Run the seeder file (ends in .sql)

<hr>

## Feature List

<details>
<summary>Our application includes the following features:</summary>
    
    
- Registering users and allowing them to login
- Allowing users to logout
- Dynamic navbar for logged in guests and users
- Intended redirects (register to login, clicking post to individual post page, etc)
- Sticky forms
- Custom error messages
    
- CRUD functionality for items (Create, Edit, Delete)
- CRUD functionality for profile (Create, Edit, Delete)
- A collective catalog which contain links to each individual items page
- An individual item show page that shows additional information
- Search functionality that allows users to search through the items by name, description, category, condition, or zipcode
- Dictating if an item is available for a lend/borrow or if it’s off the market
    
- Users can send a request for a an item, which can then be viewed + accepted or denied by the lender
- User's items appear on their profile page
- Users can see another person's profile
- A user can message another user
- A user can leave a review on another person’s profile
- A user can see reviews left on another person’s profile
  
</details>





##  Stories

<details>

<summary>Visitor</summary>

- As a visitor, when I complete the registration form I will be redirected to a login screen. 
- As a visitor, I cannot use the platform or access its features until I make an account.

</details>

<details>
<summary>Registered User </summary>

- As a user, I can list items and they will be displayed in the main list as well as on my personal profile page. 
- As a user, I can edit the items I created. 
- As a user, I cannot edit/delete another user’s items.
- As a user, I can delete my own items.
- As a user interested in borrowing, I can click on items I’m interested in and put in a borrow offer.
- As a user interested in lending, if I receive a borrow offer, I will receive a notification stating as such and can view the information regarding the offer.
- As a user interested in lending, I can decide who can borrow my items from me (accept/deny functionality).
- As a user, I can view other people's public profiles.  
- As a user, I can leave reviews and send messages to other users.
- As a user, I can view my own reviews and messages.

    
</details>

## Citations

<details>
    <summary>See citations here</summary>
    
   * Nam Ha Minh, for ideas on file upload, https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial and search functionality     https://www.codejava.net/frameworks/spring-boot/spring-data-jpa-filter-search-examples

   * https://github.com/jcrane613/YUWorkflow for ideas on form validation and a request/approval process
    
   * https://github.com/Tomasz-Makuch/library-springsecuirty-h2-thymeleaf/blob/master/src/main/resources/templates/borrowBook.html for general ideas on borrowing items 
    
   * Zipcode API http://www.zippopotam.us/
    </details>
