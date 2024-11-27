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

## What happen after run
1) After you run, it will automatically create tables under <i>myJournalDB</i>, as below : <br/>
![image](https://github.com/user-attachments/assets/800773a4-ffb6-4229-9712-c74525700772)

2) The table later, will be automatically filled with the following values : <br/>
![image](https://github.com/user-attachments/assets/20888663-5771-4e73-b7c5-3c6b1097a49f)
3) After that you can start test it

## REST APIs
There are 2 controllers. They are: <br/>
<b>A.login-rest-controller </b><br/>

  &emsp;&emsp;i.POST /login/update -update the password in db for the associated accessId. If OK, it will return TRUE. If NG, it will return FALSE<br/>
    &emsp;&emsp;&emsp;Example of OK case : <b>{"password":"827ccb0eea8a706c4c34a16891f84e7b","accessId":"1" }</b><br/>
    &emsp;&emsp;&emsp;Example of NG case : <b>{ "accessId":"1" }</b><br/>
  
  &emsp;&emsp;ii.POST /login/isValid - check whether the given username and password is correct. If OK, it will return the login accessId. If NG, it will return ERR<br/>
    &emsp;&emsp;&emsp;Example of OK case : <b>{ "usrName": "userA","pwd": "827ccb0eea8a706c4c34a16891f84e7b" }</b><br/>
    &emsp;&emsp;&emsp;Example of NG case : <b>{ "usrName": "userA","pwd": "827ccb0eea8a706c4c34a16891f84e7c" }</b><br/>
  
  &emsp;&emsp;iii.POST /login/create - create a new account. If OK, it will return TRUE. If NG, it will return FALSE.<br/>
    &emsp;&emsp;&emsp;Example of OK case : <b>{"pwd" : "827ccb0eea8a706c4c34a16891f84e7b","usrName" : "userB"}</b><br/> 
    &emsp;&emsp;&emsp;Example of NG case : <b>{"usrName" : "userB"}</b><br/>
  
  &emsp;&emsp;iv.GET /login - as a test to mark server is running. If OK, it will response "test"<br/>

<b>B.journal-rest-controller </b><br/>
&emsp;&emsp;i.POST /journal/post <br/>
&emsp;&emsp;ii.POST /journal/delete <br/>
&emsp;&emsp;iii.GET /journal <br/>
