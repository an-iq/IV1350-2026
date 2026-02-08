# IV1350-2024
# Seminar 4 - Point-of-Sale (POS) System

## Introduction

This project is part of the Object-Oriented Design course (IV1350) and focuses on developing a point-of-sale (POS) system for a retail store. The goal is to apply object-oriented programming principles to create a robust and maintainable software solution based on a given design model.

## Project Structure

The project is structured according to the Model-View-Controller (MVC) and Layer patterns, ensuring a clear separation of concerns and maintainability.


## Tasks

### Task 1: Exception Handling

- **Objective**: Implement exception handling for the `InventorySystem` class to handle item not found and database failure scenarios.
- **Implementation**: Custom exceptions are created and used in the `InventorySystem` class. The `Controller` and `View` classes handle these exceptions and log errors appropriately.
- **Sample Run**:
    ```
    Starting a new sale...
    Item added: Milk
    Item added: Bread
    Item added: Bread
    Total price: 56.0
    Payment registered
    Change: 44.0
    Logging sale...
    Sale logged successfully.
    ---------------------
    Receipt:
    Date: 2026-02-08T22:14:07.331581
    Milk x1: 11,20
    Bread x2: 44,80
    Total: 56,00
    Paid: 100,00
    Change: 44,00
    ---------------------
    ```

### Task 2 Part a: Observer Pattern

- **Objective**: Use the Observer pattern to implement functionality that shows the total amount paid for purchases since the program started.
- **Implementation**: 
  - `SaleObserver` interface is defined.
  - `TotalRevenueView` and `TotalRevenueFileOutput` classes are implemented as observers.
  - `Sale` class is updated to notify observers of total revenue.
  - `Controller` registers observers.
- **Sample Run**:
    ```
   Starting a new sale...
    Item added: Milk x1 | Item total: 11,20 | Running total: 11,20
    Item added: Bread x1 | Item total: 22,40 | Running total: 33,60
    Item added: Bread x2 | Item total: 44,80 | Running total: 56,00
    Error: Database connection failure for item: 999
    Error: Item with identifier 003 not found.
    Total price: 56.0
    Total Revenue: 56.0
    Payment registered
    Change: 44.0
    Logging sale...
    Sale logged successfully.
    ---------------------
    Receipt:
    Date: 2026-02-09T00:12:11.059379
    Milk x1: 11,20
    Bread x2: 44,80
    Total: 56,00
    Paid: 100,00
    Change: 44,00
    ---------------------
    ```

## How to Run

### Prerequisites

- Java Development Kit (JDK)
- Apache Maven

### Steps

1. **Clone the repository**:
    ```sh
    git clone [https://github.com/an-iq/IV1350-2024.git]
    cd example
    ```

2. **Compile the project**:
    ```sh
    mvn compile
    ```

3. **Run the sample program**:
    ```sh
    mvn exec:java -Dexec.mainClass="com.example.POS_System.view.View"
    ```

4. **Run the tests**:
    ```sh
    mvn test
    ```

## Conclusion

This project demonstrates the application of object-oriented principles to develop a maintainable and robust POS system. The implementation and testing ensure that the system functions correctly, handling various scenarios effectively.



