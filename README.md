# customers-addresses-api

# Getting Started

## Prerequisites
* JDK 11 installed
* Maven
* Preference IDE

### How to run the app

* The mainclass is com.castoldi.custaddr.CustomersAddressesApiApplication

* Run the app using below maven command.

```sh
mvn clean spring-boot:run
```
* The available endpoints will be as default in below URLs.

### 1) Save new customer

[POST] http://localhost:8080/customer

Need to add Content-Type: application/json in the header.

Payload example:

```sh
{
	"documentId": "123648951-6",
	"name": "Name of the customer-6",
	"age": 40,
	"registrationDate": "2002-02-02",
	"addresses": [
		{
			"zipCode": "81540-371",
			"number": 105
		},
		{
			"zipCode": "81540-370",
			"number": 205
		}
	]
}
```

### 2) Get Customer List

[GET] http://localhost:8080/customer

Need to add Content-Type: application/json in the header.

### 3) Get Customer by id

[GET] http://localhost:8080/customer/{id}

### 4) Update customer

[PUT] http://localhost:8080/customer/{id}

Need to add Content-Type: application/json in the header.

```sh
{
	"documentId": "New document 111",
	"name": "New name 111",
	"age": 41,
	"registrationDate": "2010-10-10"
}
```

### 5) Delete customer

[DELETE] http://localhost:8080/customer/{id}

### 6) Find customer by Zip Code

[GET] http://localhost:8080/customer/zipCode/{zipCode}

* Run tests using below command.

```sh
mvn clean test
```

### Generate javadoc
* Run maven command below to generate the javadocs.

```sh
mvn javadoc:javadoc
```

* Javadoc is generated in folder \customers-addresses-api\target\site\apidocs

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)