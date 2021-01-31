# String Reply Generator Task

### Server Running: Production (AWS) - Demo

#### API Routes:

| Request Method | Version | URL Endpoint | Description |
| --- |--- | --- | --- |
| GET | v1 | URL: [http://string-reply-service.com/reply](http://stringreplyservice-env.eba-ifbyppbv.us-east-2.elasticbeanstalk.com/reply) | Returns the default empty message |
| GET | v1 | URL: [http://string-reply-service.com/reply/kbzw9ru](http://stringreplyservice-env.eba-ifbyppbv.us-east-2.elasticbeanstalk.com/reply/kbzw9ru) | Returns the passed message |
| GET | v2 | URL: [http://string-reply-service.com/v2/reply/1-kbzw9ru](http://stringreplyservice-env.eba-ifbyppbv.us-east-2.elasticbeanstalk.com/v2/reply/1-kbzw9ru) | Returns the reversed message |
| GET | v2 | URL: [http://string-reply-service.com/v2/reply/2-kbzw9ru](http://stringreplyservice-env.eba-ifbyppbv.us-east-2.elasticbeanstalk.com/v2/reply/2-kbzw9ru) | Returns the hashed message |
| GET | v2 | URL: [http://string-reply-service.com/v2/reply/12-kbzw9ru](http://stringreplyservice-env.eba-ifbyppbv.us-east-2.elasticbeanstalk.com/v2/reply/12-kbzw9ru) | Returns the combined reserved with hashed message |
| GET | v2 | URL: [http://string-reply-service.com/v2/reply/22-kbzw9ru](http://stringreplyservice-env.eba-ifbyppbv.us-east-2.elasticbeanstalk.com/v2/reply/22-kbzw9ru) | Returns the double hashed message |

Our company has released a beta version of **String Reply Service** and it has been a huge success.
In the current implementation, the **String Reply Service** takes in an input string (in the format of `[a-z0-9]*`)
and returns the input in a JSON object.

For example,

```
GET /reply/kbzw9ru
{
    "data": "kbzw9ru"
}
```

As the service is widely adopted, there have been increasing feature requests.
Our project manager has come back with the following requirement:

The input string will now be comprised of two components, a rule and a string, separated by a dash (-).
Rules always contain two numbers. Each number represents a string operation.

The supported numbers are:

- `1`: reverse the string

   E.g. `kbzw9ru` becomes `ur9wzbk`

- `2`: encode the string via MD5 hash algorithm

   E.g. `kbzw9ru` becomes `0fafeaae780954464c1b29f765861fad`

The numbers are applied in sequence, i.e. the output of the first rule will
serve as the input of the second rule. The numbers can also be repeated,
i.e. a rule of 11 would mean reversing the string twice, resulting in no change to the string.

Giving a few examples,

```
GET /v2/reply/11-kbzw9ru
{
    "data": "kbzw9ru"
}
```
```
GET /v2/reply/12-kbzw9ru
{
    "data": "5a8973b3b1fafaeaadf10e195c6e1dd4"
}
```
```
GET /v2/reply/22-kbzw9ru
{
    "data": "e8501e64cf0a9fa45e3c25aa9e77ffd5"
}
```

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
### Prerequisites
* **Java 8+**

### Libraries Used
* **Lombok** to include boilerplate code & reduce redundant lines of code
* **Junit 5** for Unit test
* **Spring Boot Actuator** allows us to monitor and interact with the application

### Build project

To build the project, simply run
```
./gradlew build
```

### Test project

The Unit Tests are written using [JUnit 5](https://junit.org/junit5/).


    # On Linux/MacOS:
    ./gradlew clean test

    # On Windows:
    gradlew.bat clean test

### Start project

To start the project, simply run
```
./gradlew bootRun
```

Once the service started, the endpoint will be available at `localhost:8080`, so you can make request to the service endpoint

```json
GET localhost:8080/reply/helloworld

{
    message: "helloword"
}
```