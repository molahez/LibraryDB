Online bookstore Project using Java & Mysql

The project aims to make system to record every thing happens in bookstore . It supports many functionalities as:-
1 - Add new books
2 - Modify existing books
3 - Place orders on books
4 - Confirm orders
5 - Search for books
Also we have two types of users :- customer & manager each one of them has functionalities in system which are:-
Customer:- 
1. Edit his personal information including his password
2. Search for books by any of the book’s attributes. 
3. Add books to a shopping cart
4. Manage his shopping cart. This includes the following.
• View the items in the cart
• View the individual and total prices of the books in the cart
• Remove items from the cart
5. Checkout a shopping cart
• The customer is then required to provide a credit card number and its expiry date.
This transaction is completed successfully if the credit card information is appropriate.
• The book’s quantities in the store are updated according to this transaction.
6. Logout of the system
• Doing this will remove all the items in the current cart

Manger:-
All operations of customer in addition to :-
1. Add new books
2. Modify existing books
3. Place orders for books
4. Confirm orders
5. Promote registered customers to have managers credentials
6. View the following reports on sales
a. The total sales for books in the previous month
b. The top 5 customers who purchase the most purchase amount in descending order for the
last three months
c. The top 10 selling books for the last three months

In project we first create database using Mysql to apply required functions
then using java we create Gui to call queries from database created and to implement what users can do.


Userguide :-
In order to use project you have to download repo first then to make database connection (in our case it's local database ) you have to create it first using
MYsql workbench or any program provides making databases. Schema is also provided in project as this we worked on you just have to implement it.
Also there is data provided to test project
• Number of books in the database: 1,000,000
• Average Number of authors: 500,000
• Number of inquiries to the database (using the book ISBN, title or category) per day: 150,000
• Number of customer purchases per day: 50,000
• Average number of book orders generated per day :10

After that run from Java and project is ready .
Feel free for any questions

