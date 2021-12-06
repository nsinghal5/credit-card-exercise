Credit Card Exercise
--------------------

Prerequisites
	1. npm
	2. node
	3. maven
	
Install
	npm install
	maven - https://maven.apache.org/download.cgi
	
Start
-----
    1. Build UI:
        cd react-credit-card-exercise
        npm run build
    2. Build Service
        cd credit-card-exercise
        mvn package
        mvn spring-boot:run

    Access the UI at: http://localhost:8080

End Points
----------
    POST: http://localhost:8080/api/v1/credit-card @ResponseBody (#ref CreditCard)
    GET:  http://localhost:8080/api/v1/credit-card
   

References
----------
    CreditCard.json
    {
        "name": "Neeraj Singhal",
        "cardNumber": "6011008785309789331",
    }

Postman Collection:
------------------
Refer File:  Credit Card.postman_collection.json
    
Database Login:
--------------
    Link: http://localhost:8080/h2-console
    UserName: sa
    Password: password
