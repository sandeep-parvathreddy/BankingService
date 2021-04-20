## Introduction

BankingService is a simple microservice, which does banking related operations. This is built using the following technologies

 * Java 1.8 
 * Spring Boot
 * Maven
 * H2 Database


 
## How to start the app
1. Goto $PROJECT_DIRECTORY, and execute the following command
		` ./mvnw spring-boot:run`
2. This starts a webserver on port 8080(configurable). Application can be accessed through `http://localhost:8080/`
3. Alternatively, import the project in any IDE(Eclipse or IntelliJ) and run the class `BankingServiceApplication.java`


## API's

### Create bank account

- Method : POST 
- Endpoint : `/accounts` 
- Headers : 
 
 ```
    Content-Type : application/json
    Accept : application/json
 
 ```
- Request Body
```
 {
	 "balance" : 100.0,
	 "type" : "CONSUMER",
	 "firstName" : "sandeep", 
	 "lastName" : "reddy"
 }


```

- Response Body

 ```
	{
		"id": 1618942046519,
		"balance": 100.0,
	}
 
 ```
		
	

### Get account details
- Method : GET 
- Endpoint : `/accounts/{id}` 
- Headers

```
	Content-Type : application/json
    	Accept : application/json
    
```    
- Response Body

 ```
	{
		"id": 1618942046519,
		"balance": 100.0,
	}
 
 ```



### Transfer Money between two accounts

- Method : POST 
- Endpoint : `/transfer` 
- Headers

```
	Content-Type : application/json
	Accept : application/json
```    

- Request Body

```
{
    "fromAccountNum" : 1618948949636,
    "toAccountNum" : 1618948959477,
    "amount" : 99,
    "type" : "TRANSFER"
}
``` 

- Response Body

```
{
    "transactionId": "22a1dd04-476a-4dbb-981d-d779ce90c89a",
    "accounts": [
        {
            "id": 1618948949636,
            "balance": 1.0,
            "accountHolder": null,
            "type": null
        },
        {
            "id": 1618948959477,
            "balance": 199.0,
            "accountHolder": null,
            "type": null
        }
    ]
}
```


## Future Tasks/Improvements

1. Currently implemented using H2 database an in-memory storage. We can use any RDBMS storage in future and its easy to plug in using Spring JPA
2. During Account create, Account Id is set as timestamp. This has security issues as account number is easily guessable. To minimise the risk, need to provide an implementation for Account Id generator. May be use timestamp along with a random Id
3. Account model currently contains only minimal data, this has to be extended based on requirements
4. Incase model has some user specific data(email/photo/documents), then it can be moved to NoSQL storage. During Account Create/Get data can be processed from both RDBMS and NoSQL
5.Transaction processing can be extended by providing a factory of implementations like scheduled transfer/recurring transfer/direct transfer.
6. Use Docker to package the entire application


