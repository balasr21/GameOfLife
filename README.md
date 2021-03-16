#GameOfLife API

**********************************************************************

## 1.Task

This project is an REST API developed in Java using Spring boot.

This project provides the complete set of generation details given the matrix size(row,column) and initial position of the first generation of live cells

### 1.1 Create Generations

 POST / Submits the request for evaluation of next generation based on given input
 
 ```
 {
     "row":5,
     "column":8,
     "position":[10,19,25,26,27]
 }
 ```
 
 If given Row/Column/Initial Positions are invalid then appropriate error will be responded
**********************************************************************

## 2. Technical Details:

### 2.1 Tools&Framework:

   The below are the list of tools and framework used in the project!

* [SpringBoot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - for Dependency Management
* [Java](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Java 11 as Programming language
 
### 2.2 Key Features to highlight:

  1.Handled invalid requests with appropriate error message
  2.100% unit test coverage  

### 2.3 Solution & Assumptions

  1. If the validations are successful, in order to generate complete set of generations below steps are performed
    
        a. For each of the initial generation, identify its nearest neighbours considering border conditions
        
        b. For each of the identified cells , check whether they are going to be alive in the next generation by applying rules
        
            i.      Any live cell with fewer than two live neighbours dies, as if caused by under­population.
            ii.     Any live cell with two or three live neighbours lives on to the next generation.
            iii.    Any live cell with more than three live neighbours dies, as if by overcrowding.
            iv.     Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        
        c. Once the next generation status is identified, collect them and repeat the process until the number of live cells count becomes less
           than the initial population(its assumed end of the grid is reached)
    
  2.Currently only default profile is used in application.properties but if required this can be extended to use environment specific ones
  
**********************************************************************
 
## 3.Swagger
 
 If this application is being accessed locally,then swagger UI can be accessed at
 
http://localhost:8080/swagger-ui.html#/ 

**********************************************************************

## 4.Run Application

Below command can be used to invoke the application

```
mvn spring-boot:run
```

**********************************************************************

## 4.1 Access Application

This App is now available in AWS and can be accessed with endpoint as

```
https://gameoflifeapi-env.eba-xvhqsdpp.eu-west-2.elasticbeanstalk.com/start/
```
**********************************************************************
