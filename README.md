Coverage: 34%
IMS - Starter

A program that can add, update and delete information for a commerce business that handles customers, items and orders.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

 - Java
 - SQL
 - Eclipse
 - Git
 - Maven

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

- Fork repository
- Clone repository
- Open project in eclipse
- Run Runner.java as a Java application

- Type CUSTOMER
- Type CREATE
- Type a first name
- Type a last name
- The customer is then added
- Type RETURN
- Type Stop
- You have now added a customer to the system.

## Running the tests

Using JUnit we run tests for each of the 3 sections of the program, Customers, Items and Orders.
In eclipse when you look in src/test/java there are a large group of test files that when run as a JUnit Test.

These tests are also run is you use Maven to package the program into an executable java file.

## Deployment

To run this program on the command line you will need to package it using Maven. In a Git Bash terminal if you type mvn clean any unused classes will be discarded from the executable. Once that has finished if you type mvn package in it will create the java executable which can be run from either the git bash terminal or the regular command line.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
Harry Brown

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

Pawel for the help.
