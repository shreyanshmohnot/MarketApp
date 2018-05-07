JFLAGS = -g
JC = javac -cp ".:mysql-connector-java.jar"
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
AbstractClass/AbstractFactory.java\
AbstractClass/AdminFactory.java\
AbstractClass/ClientFactory.java\
AbstractClass/FactoryCreator.java\
Client/Client.java\
Client/ClientRMI.java\
Handler/AuthorizationException.java\
Handler/AuthorizationInvocationHandler.java\
Handler/RequiresRole.java\
Handler/Session.java\
Handler/Products.java\
Interface/AdminInterface.java\
Interface/LoginInterface.java\
Interface/ClientInterface.java\
Interface/BrowseInterface.java\
Model/AdminModel.java\
Model/ClientModel.java\
Model/LoginModel.java\
Model/BrowseModel.java\
Model/ConnectionFactory.java\
ModelController/AdminController.java\
ModelController/BrowseController.java\
ModelController/ClientController.java\
ModelController/LoginController.java\
Server/Server.java\
Server/ServerRMI.java\
View/AdminPanel.java\
View/CustomerPanel.java\
View/SignIn.java\
View/SignUpClass.java\
View/Views.java\
View/CustomerBrowse.java\
View/AdminBrowse.java\
View/ShoppingCart.java\
View/Commands.java\
ViewController/FrontController.java\
ViewController/FrontDispatcher.java\
ViewController/ApplicationControl.java\

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) */*.class