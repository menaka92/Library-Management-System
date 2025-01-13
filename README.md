# Library Management System

This is a Library Management System developed using JavaFX and MySQL. The system allows two types of users: **Members** and **Librarians**, each with different functionalities.

## Features

### Member Tasks:
1. **Login:** Members can log in to their accounts.
2. **Register:** Members can create a new account.
3. **Get Books by Category:** Members can view books filtered by category.
4. **Add to Cart:** Members can add books to their cart (one-time book checkout).
5. **Review Cart:** Members can review the books in their cart and remove unnecessary books.
6. **Borrow Books:** Members can borrow books from the library.
7. **Logout:** Members can log out from the system.
8. **Back to Previous Pages:** Members can navigate to the previous pages.

### Librarian Tasks:
1. **Login:** Librarians can log in to their accounts.
2. **Register:** Librarians can create new accounts.
3. **Show Members & Search:** Librarians can view a list of members and search by their names.
4. **Delete Members:** Librarians can delete members from the system.
5. **Show Books by Category:** Librarians can view books in storage filtered by category.
6. **Logout:** Librarians can log out from the system.
7. **Back to Previous Pages:** Librarians can navigate to the previous pages.

## Technologies Used
- **JavaFX:** For the user interface.
- **MySQL:** For database management.
- **JDBC (Java Database Connectivity):** For connecting the JavaFX application to the MySQL database.
-  **singleton design pattern:** For connecting database.

## Database Setup

1. Clone or download the project files.
2. Set up your MySQL database using the provided `query.sql` file located in the **database** folder.
3. Modify the database connection settings in the project if needed (e.g., database URL, username, and password).

## How to Run the Project

1. Import the project into your IDE (e.g., IntelliJ IDEA).
2. Ensure the MySQL database is set up and running.
3. Run the `AppInitializer.java` file to start the Library Management System.

## File Structure

- **src:** Contains the source code for the project.
- **database:** Contains the `query.sql` file with the database schema.
- **README.md:** This file.


