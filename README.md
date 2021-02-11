Coverage: 88.1% (81.4% main)
# Inventory Mangement system (IMS)

This project is developed as a simple inventory mangemenet system with the ability to manage orders,products and customers.

## Getting Started

Fork this repository and git pull the files to open with Eclipse or to run the -jar file.

### Prerequisites

What things you need to install the software and how to install them
To use these files you will need Java 14.0 and mysql version 5.7.33
To develop this project further you should use the IDE version the files were developed on Eclipse 4.18.0.
It is also useful to use MYSQL Workbench  8.0.23

### Installing
To run the application ensure you have java installed.
Open a command prompt in the location of the IMS files.
Ensure you navigate to the ims-0.0.1-jar-with-dependencies.jar files (located in the Target folder inside IMS)
Enter the command "java -jar ims-0.0.1-jar-with-dependencies.jar"
A step by step series of examples that tell you how to get a development env running

## Running the tests

The testing code was developed using JUNIT and MOCKITO. This was all constructed within Eclipse and should be ran through Eclipse also.

### Unit Tests 

Explain what these tests test, why and how to run them
The units tests are used to test the database functionality of the system. There is a seperate test class for each major table of the database. These tests will cover the CRUD operations.

The below image shows the DAO tests. Running any test ending DAO.java will test the CRUD functionalities of the application.
![unittets](https://i.ibb.co/fnP2jxt/unittests.png)

### Integration Tests 
Explain what these tests test, why and how to run them
Integration tests are used to test the controller classes
The below image shows the controller tests. Running any of these tests will test the controller classes.

![Integrationtests](https://i.ibb.co/McdjBrs/intergrationtest.png)

## Deployment

Add additional notes about how to deploy this on a live system
To deploy this application officially it need to have an accompanying MYSQL database. The ERD for the database used is below.

![ERD](https://i.ibb.co/sVQ5rjg/Project-Ending-ERD.png)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Dan Bennett** - Main Developer - [danielbennett](https://github.com/dbennett98)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
