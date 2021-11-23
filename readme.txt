User Guide:

1. Open NetBeans. Save the contact_app_manager folder in the NetBeansProjects folder on the desktop. The project is built in NetBeans and the building tool used is Maven.
2. Open the contact_app_manager in NetBeans. There will be six java files in the source package => contact_app_manager folder 
3. Click on dependencies to check if the mysql-connector-java-8.0.27 is present. If not, right click on Dependencies => Add Dependency. 
Use Group Id-mysql, Artifact Id- mysql-connector-java and Version 8.0.27
4. Open MySQL workbench. Open contact_app_manager.sql. Use Create database/schema to create database. 
5. Use the contact_app_manager.sql for creating the necessary tables and inserting/loading data into the database.
6. Use LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Contacts.csv' to load the contacts.csv file into database. This can be done using command prompt as well.
7. On the left side of NetBeans, click on services tab=>connect to mySQL database with username and password if not already connected.
8. Copy the URL and use the mySQL username, password and URL in the ContactConnectDatabase.java 
9. In case if you want to load the database file using JAVA use the URL where the file is saved locally in the LoadDatabase.java
10. Run the ContactAppManager.java. The Contact Display Window will pop up.
11. Search Contact: Search the necessary field in the Text Box. Click on Search to search a contact with that field. 
12. View Contact: Click on the contact in the table and then click on the View Button to View the contact 
13. Add Contact: Click on the Add Contact Button to add a new contact
14. Modify Contact: After searching and selecting a contact from the table, Click on Modify Button to modify the contact
15. Delete Contact: After searching and selecting a contact from the table, Click on Delete Button to delete the contact from the database.
16. It will either throw an error message or success message based on selections made.


* The java classes are built in Apache NetBeans IDE 12.5 and the building tool used is Maven
* The operating system used is Windows
* The MySQL Connector jar file used is of version 8.0.27 (latest version)
* MySQl Workbench 8.0 CE is used for backend database

