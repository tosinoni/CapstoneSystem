# CapstoneSystem
A role based system for managing 4th year projects for the Departement of Engineering at Carleton University. The system maybe used by profs and a project coordinator to enter projects, communicate with students and scedule presentations. Students may select projects, view project details, submit deliverables and update presentation availabilities.

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

## Hot reloading in IntelliJ

Open intellij and perform the below actions:

      Open Settings --> Build-Execution-Deployment --> Compiler  
      enable Build Project Automatically.
      Apply and close.
      press ctrl+shift+A and search for the registry
      enable compiler.automake.allow.when.app.running
      
      
 ## Milestones

| Milestone       | Description                                                                                                                                                                      | Due        |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| Early Prototype | Preliminary views included, handle authentication and create models. Password encrypted in database. Created models and tests. Deployed to Heroku and Using Travis for integration.  Domain currently pointing to kapi.ca | March 8th  |
| Alpha Release   | Ability to add projects for profs. Students can apply to projects. All users have a customized dashboard. Profs and coordinators can send emails to students. Projects catalogue is dynamic and can view individual project information by ID in route. Added static announcements page. Updated registration page.  | March 22nd |
| Final Demo      | We need to find a good library for a sceduling tool to handle student availabilites. We will also implement the ability to upload a final report. Finally, we will implement all project actions such as edit, delete and add users. | April 5th  |

 ## Diagrams
 
UML for Models:
![uml](https://user-images.githubusercontent.com/14824913/37755639-b10ff1aa-2d7c-11e8-9d1b-cb3c65d0462e.png)

Please refer to phpMyAdmin for the database tables. 
