# Market Place

###### An application to design and implements software pattern in an online marketplace application using JAVA RMI.

## *Files:* 
1. AbstractClass/AbstractFactory.java
2. AbstractClass/AdminFactory.java
3. AbstractClass/ClientFactory.java
4. AbstractClass/FactoryCreator.java
5. Client/Client.java
6. Client/ClientRMI.java
7. Handler/AuthorizationException.java
8. Handler/AuthorizationInvocationHandler.java
9. Handler/RequiresRole.java
10. Handler/Session.java
11. Handler/Products.java
12.	Interface/AdminInterface.java
13.	Interface/LoginInterface.java
14.	Interface/ClientInterface.java
15.	Interface/BrowseInterface.java
16.	Model/AdminModel.java
17.	Model/ClientModel.java
18.	Model/LoginModel.java
19.	Model/BrowseModel.java
20.	Model/ConnectionFactory.java
21.	ModelController/AdminController.java
22.	ModelController/BrowseController.java
23.	ModelController/ClientController.java
24.	ModelController/LoginController.java
25.	Server/Server.java
26.	Server/ServerRMI.java
27.	View/AdminPanel.java
28.	View/CustomerPanel.java
29.	View/SignIn.java
30.	View/SignUpClass.java
31.	View/Views.java
32.	View/CustomerBrowse.java
33.	View/AdminBrowse.java
34.	View/Commands.java
35.	ViewController/FrontController.java
36.	ViewController/FrontDispatcher.java
37.	ViewController/ApplicationControl.java

## *Steps to run Server -* 

1. ***Pull*** the repository from *github.iu.edu*
2. Open UNIX ***terminal*** which supports ***rmiregistry*** and *MYSQL 5.5 & above*.
3. **ssh into any UNIX machine** - ***Server** runs here with MYSQL database*
4. Run ***make***
5. To clean - Run ***make clean***
6. Start RMI registery - ***rmiregistery 3232&***
7. Run - ***java -cp ".:mysql-connector-java.jar" -Djava.security.policy=policy Server.Server***

## *Steps to run Client -* 

1. ***Pull*** the repository from *github.iu.edu*
2. Open UNIX ***terminal***
3. **ssh into any UNIX machine** - ***Clients** should run here*
4. Run ***make***
5. To clean - Run ***make clean***
6. Make sure you run the **Server steps** before next step execution.
7. Run - ***java -Djava.security.policy=policy Client.Client***

## Access for logins - 
###### for admin functions
* **username - admin**
* **password - admin**
 
###### for customer functions
* **username - shreyansh**
* **password - shreyansh**

###### for mysql login
* **Note -** Database is preconfigured with tables and values for testing.* 
