# JAVA-MANGA-PROJECT
Manga management project where there is 3 classes with details
Manga.java which has class with details, constructors, getters and setters
Database.java has crud operations( connection and functions for creating, updating and deleting the table and can be further updated to add more )
Main.java has details of mangas added to it using create, the data is also updated, created again and one record with id "4" is deleted
After running main, MangaaDb.db is updated accordingly with a table with fields id, title, status, author, volume
the database contains 13 id numbers with 4 missing as it is deleted
the title represents the manga title, status shows if it is completed,ongoing or not started, author gives the mangaka's name and volume give the number of volumes(tankōbons)

Admin.java and User.java is there but is currently useless due to the code not being added to support as I was having some troubles in it

the idea behind it is Admin can delete,update, or add manga and the user can only access the details which can be done by authorization through username and password and connecting through database.java and main

For now, the project can display the database
