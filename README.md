# Development Books Kata

### About the project
This is an implementation of [stephane-genicot's Development Boooks kata](https://github.com/stephane-genicot/katas/blob/master/DevelopmentBooks.md) using TDD approach.


### How to compile and run

Clone the repository via git:

        git clone https://github.com/2022-DEV1-033/DevelopmentBooks.git

then chose one of these methods:

#### Using Docker

        docker build --tag=development-books:latest .
        docker run -p8080:8080 development-books:latest
        
#### Using Maven (JDK 8 required)

        mvn clean install
        mvn spring-boot:run

### API guide

