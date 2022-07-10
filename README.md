# Library Management System
A mini console based menu library management system, using PostgreSQL as the database. Check out the set-up information in the ReadMe.

## How to set-up project
1.	Install IntelliJ IDEA if you have not installed one and the Java JDK version 16.0
2.	Extract the system file from the zip
3.	Open IntelliJ  File  Open  Locate the system files folder and click Okay
4.	Go to Database setups file  Postgres setup windows If using a windows OS Run as administrator to install the database
5.	Follow the guide below during installation:

> #### IF USING A MAC OS GO TO Postgres MAC folder then run the setup, use the following instruction on this	[link](https://www.enterprisedb.com/postgres-tutorials/installation-postgresql-mac-os) to install the package. 

6.	Go to Intellij IDEA to add a driver that enables connection between PostgreSQL Server and Java.
7.	Hover your pointer on the project name or main file --> Right click --> Open Module Settings
8.	Click Libraries --> Click on the existing library and delete it (-) --> Click on add new (+) --> Locate the **JavaDriver** folder on the Database setup folder  Open It then click on the file “postgresql-42.3.5”   Click Okay  Apply  Okay
9.	Search for **pgAdmin 4** on the taskbar, open it --> Create database **LibraryManagementSysDB**
10.	Open cmd change directory to `C:\Program Files\PostgreSQL\14\bin` or enter this command: `cd C:\Program Files\PostgreSQL\14\bin`, if it does not work locate the same path in the Program Files (x86) folder
12.	Go to Intellij and change password on the connection object to your postgres password
13.	Run the code.
14.	A console based menu is shown.
15.	Enter the number of selection on the menu.


