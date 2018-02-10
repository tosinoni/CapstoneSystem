# CapstoneSystem
A system for managing final year projects

The system uses [AngularJS](http://angularjs.org) for client side and [Spring boot](https://projects.spring.io/spring-boot/) for server side.


##	Steps for setting up development environment
Install [Git](http://git-scm.com), [node.js](http://nodejs.org), [JDK 8](https://www.java.com), and [Maven 3](http://maven.apache.org/).


Install Bower:

    npm install -g bower

##	Setting up the database
Install [XAMPP](https://www.apachefriends.org/download.html)

      Open xampp control
      Start Apache and MySQL
      Click Admin to view the database

## Starting the application

In the CapstoneSystem directory, execute the following:

      bower install
      mvn clean package
      java -jar target/CapstoneSystem-0.0.1-SNAPSHOT.jar
      visit http://localhost:8080