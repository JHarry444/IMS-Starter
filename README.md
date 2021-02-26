Coverage: IMS-Starter (79.3%) , src/main/java (73.6%)
# Project Title
Inventory Management System (IMS)

One Paragraph of project description goes here
--------------------------------------------------------------
Creating a functional application, which customers can purchase items in their orders and view the total cost for their order.
The customers can add, update, delete items and orders.


## Getting Started

All dependecies should be included.
To compile from source: `mvn package`
Had to bypass tests cause of some errors : `mvn package -D maven.test.skip=true`

### Prerequisites

What things you need to install the software and how to install them

Java 1.8
MySQL Server 
MySQL Workbench

### Installing

Compile the program from main source using `mvn package`, then using the .jar file
with its dependecies to run the program.
Another way is to download the latest release from GitHub.
Database should be set up correctly and the queries are included in `src/main/resources/sql-schema.sql`


## Running the tests
We use JUnit5 for running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 
Every file needs to have it's own respective test file and test the methods* within. We make sure we have the correct imports on top.

Explain what these tests test, why and how to run them

These tests, test the constructors and the methods in the main file. This happends with dummy values
and with assertions. They are several types of assetions, but the main ones are assertEquals, assertNotNull, assertSame, assertTrue.

Give an example

We have the constructor below

	public Item(String title, float value) {
		this.setTitle(title);
		this.setValue(value);
}

To test the above constructor we need to create a new Item and pass in some values. We can also check if the item is an instance of the Item class.
	private static Item testItem;
	@Test
	public void ItemConstructorTest() {
		testItem = new Item("eggs", 1.20f);
		assertNotNull(testItem);
		assertTrue(testItem instanceof Item);	
}


### Integration Tests 
Explain what these tests test, why and how to run them

These tests, have an approach to target the fundamental buidling blocks of an application.
We can use Mockito for Integration Testing, using mock values to see if it works.
We always make sure we have imported the correct imports on top.

Give an example

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1l, "pepsi", 1.30f));
		Mockito.when(dao.readAll()).thenReturn(items);
		assertEquals(items, controller.readAll());
		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

The above example tests the ReadAll method which reads all the items.
We add a new Mock Item, in this case a pepsi with a value of 1.30.
We assertEqual that the item that passed is verified in the Arraylist of items.


## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Nikolaos Papadopoulos** - *Other work* - [Moodhunter34](https://github.com/Moodhunter34)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* **Jordan Harrison** - *Other Contributors* - [JHarry444](https://github.com/JHarry444)
* **Ed Reynolds** - *Other Contributors* - [Erdz-96](https://github.com/Edrz-96)

* Hat tip to anyone whose code was used
* Inspiration
* etc
