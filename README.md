# JIT Pay User Api
## Description
This is an API for mobile and web applications to store and retrieve location and
user data.

## Tech Stack
* Java11
* SpringBoot 2.6.9
* H2 Database
## Getting Started
You can run the application by maven repository 
    
    maven clean install
## Api Description
This api includes next endpoints:

#### Get User By Identifier
    
    GET Http://{host}:{port}/user/{id}
    
returned JSON:

    {
        "userId": "2",
        "email": "1@1.tv",
        "firstName": "Ivan",
        "secondName": "Ivanov"
    }
    
#### Create or update user


    POST Http://{host}:{port}/user  
Body
    
    {
        "userId" : "3",
        "email" : "1@1.tv",
        "firstName" : "Ivan",
        "secondName" : "Ivanov"
    }
    
Return User Id
#### Create current location 


    POST Http://{host}:{port}/location
Json

    {
        "userId":"1",
        "createdOn":"2022-02-08T11:44:00.524",
        "location":{
            "latitude": 52.25742342295784,
            "longitude": 10.540583401747602
        }
    }

#### Get Last User Location


    GET Http://{host}:{port}/location/{userId}
Returned Json

    {
        "userId": "1",
        "email": "@@",
        "firstName": "Ivan",
        "secondName": "Ivanov",
        "location": {
            "latitude": 52.25742342295784,
            "longitude": 10.540583401747602
        }
    }

#### Get User Location from date Range


    GET Http://{host}:{port}/location/range/{userId}?from={from}&?to={to}
Returned Json
    
    {
        "userId": "1",
        "locations": [
            {
                "createdOn": "2022-11-29T18:12:56.805768",
                "location": {
                    "latitude": 52.25742342295784,
                    "longitude": 10.540583401747602
                }
            },
            {
                "createdOn": "2022-11-29T18:13:07.658506",
                "location": {
                    "latitude": 52.25742342295784,
                    "longitude": 10.540583401747602
                }
            },
            {
                "createdOn": "2022-11-29T18:13:18.182842",
                "location": {
                    "latitude": 52.25742342295784,
                    "longitude": 10.540583401747602
                }
            }
        ]
    }
    
By default, host is a localhost, port is 8085
## Testing
For testing this API you can use [Postman Collection](JITPay API.postman_collection.json)

By default, there is one User with identifier 1

## Author
Bardyzh Arseniy

arseniy270994@gmail.com
