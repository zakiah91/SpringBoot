# MyJournal-spring
<i>Summary:</i> A simple Java springboot app where user can write their journal/note.

## Components
A) <i>Login</i> - Contains login component. A user needs to login in order to access his/her journal content. When the user login, accessId will be given. This accessId is needed to access the Journal table. <br/>
B) <i>Journal</i> - Contains the journal component. AccessId from Login is needed in order to access this part. A user is allows to add/modify 2 items from here:1)date,2) content <br/> 

## ER Diagram
![image](https://github.com/user-attachments/assets/6cfab974-5087-44cb-98c3-6b781ab9cb6e)

## Developement Environment
IDE : Eclipse <br/>
Database : MySQL <br/>
Java JDK version : 23 <br />

## How to run
1) create a new database called <i>myJournalDB</i> <br/>
2) From Eclipse, import this springboot project as "Existing maven project" <br/>
3) Modify the components in <i>myJournal-spring\src\main\resources</i> according to your own requirement <br/>
4) Go to pom.xml and run it as "Maven install" . After that, a .war file will be generated under <i>myJournal-spring\target</i> <br/>
5) Copy this .war file to your server or you can directly run it using this command <i>java -jar <YOUR_FILE_NAME>.war </i>
