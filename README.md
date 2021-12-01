Product Catalogue
-----------------

Start
-----
    dos
    cd credit-card-exercise
    mvn spring-boot:run

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
    
Database Login:
--------------
    Link: http://localhost:8080/h2-console
    UserName: sa
    Password: password