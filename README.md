#Consumption Meter REST API
/**
 * 
 *  lezginaksoy
 *
 */

# Spring Data JPA with Hibernate using h2 Example
This project depicts the Spring Boot  with Spring Data JPA with Hibernate using h2 .



## Description

NOTE.!
Because of missunderstanding in usecase, i need to re-develope appi.
so i dont have time to develope Unit test sufficiently and i dont implement UI interface for consuming(i offer Postman)
 

This project Using the following endpoints, different operations can be achieved:
- `/meterreading/all` - This returns the list of meterreading in the  
- `/meterreading/addsingle` - save and update  meterreading(post,put)
- `/meterreading/add` - save  collection of meterreading(post)
- `/meterreading/delete/{id}` -delete meter reading


- `/fractions/all`    - This returns the list of fractions in the  
- `/fractions/addsingle`    - save and update  fractions(post,put)
- `/fractions/add`    - save collection
- `/fractions/delete` -delete fractions 


- `/consume/get` - This returns consume for given meterid and month  


## Libraries used
- Spring Boot
- test
- Spring Data JPA with Hibernate
- h2

## Tools used
- sts 3.9


## Compilation Command
- `mvn clean install` - Plain maven clean and install
