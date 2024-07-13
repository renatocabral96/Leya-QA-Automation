# Leya Website automation challenge

## Prerequisites
Before running the tests, ensure you have the following software installed:

1. **Java Development Kit (JDK)** - Ensure you have JDK 8 or higher installed.
2. **Apache Maven** - This is used to manage project dependencies and build the project.
3. **Google Chrome** - The tests are designed to run on the Chrome browser.
4. **ChromeDriver** - This is required to run the tests on the Chrome browser. Ensure the ChromeDriver version matches your installed version of Google Chrome.

## Project Setup

1. **Clone the Repository**

   git clone https://github.com/renatocabral96/Leya-QA-Automation.git

2. **Navigate to the Repository**

   cd leya-automatic-tests

3. **Execute all tests**
   
   mvn clean install
   mvn test

5. **Execute individual tests**
   
   mvn -Dtest=<name_of_the_test> test

## Extra Requirements
  - Sixth Requirement:
    Tests a user login on the website and confirms its success

  - Seventh Requiremtment:
    Tests if the filters applied to the books using the filter button is done successfully

