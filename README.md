# Library Management System
A mini console based menu library management system, using PostgreSQL as the database. Check out the set-up information in the ReadMe.

---
## How to set-up project
1.	Install IntelliJ IDEA if you have not installed one and the Java JDK version 16.0
2.	Navigate in the project folder --> Go to links_to_downloadPostgre.txt file then download the tools.
3.	Open IntelliJ --> File --> Open --> Locate the system files folder and click Okay
4.	Follow the guide below to install postgres:
---
> #### Click on your operating systems	
>   - [Mac OS](https://www.enterprisedb.com/postgres-tutorials/installation-postgresql-mac-os)
>   - [Windows](https://www.enterprisedb.com/docs/supported-open-source/postgresql/installer/02_installing_postgresql_with_the_graphical_installation_wizard/01_invoking_the_graphical_installer/)
---
5.	Go to Intellij IDEA to add a the driver that enables connection between PostgreSQL Server and Java.
6.	Hover your pointer on the project name or main file --> Right click --> Open Module Settings
7.	Click Libraries --> Click on the existing library and delete it (:heavy_minus_sign:) --> Click on add new (:heavy_plus_sign:) --> Locate the **JavaDriver** folder on the Database setup folder --> Open It then click on the file **postgresql-42.3.5**  --> Click Okay --> Apply --> Okay
8.	Search for **pgAdmin 4** on the taskbar, open it --> Create database **LibraryManagementSysDB**
9.	Open cmd change directory to `C:\Program Files\PostgreSQL\14\bin` or enter this command: `cd C:\Program Files\PostgreSQL\14\bin`, if it does not work locate the same path in the **Program Files (x86) folder**
10.	Go to Intellij and change password on the connection object to your postgres password
11.	Run the code.
12.	A console based menu is shown.
13.	Enter the number of selection on the menu.


