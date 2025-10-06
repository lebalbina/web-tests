# SauceDemo Selenium Tests

Automated Selenium-based tests in Java for the SauceDemo demo web app.

## Features

- Tests written in Java with Selenium WebDriver

- Page Object Model design pattern used

- Basic e2e and functional test scenarios

- Allure integration for rich test reports

- CI/CD integration with Jenkins (Jenkinsfile included)

## Current State

- Covers key user flows: login, adding/removing products, checkout, sorting products

## Structure

```
WebTests/
├── app/
│   ├── src/test/
│   │   ├── java/
│   │   └── resources/
│   │       ├── testng.xml
            └── allure.properties
│   └── build.gradle
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── .gitignore
├── gradlew
├── gradlew.bat
├── Jenkinsfile
├── README.md
└── settings.gradle
```

## Notes

This project uses the public demo site [saucedemo.com](saucedemo.com) for educational and demonstration purposes only.