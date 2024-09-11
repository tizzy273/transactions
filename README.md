# **Transaction Service**

The **Transactions Service** is a microservice for managing transactions. It provides endpoints for creating and retrieving transactions. The service uses an in-memory **H2** database and is fully tested with **JUnit** and **Cucumber**. API documentation is available through [Swagger](https://app-transactions-665026f5a398.herokuapp.com/swagger-ui/index.html#/).

To run locally, you need to clone the repository to your machine and run the project using a Maven-compatible compiler.


## **Continuous Integration and Deployment**
The project follows the Gitflow workflow with two main branches:

- **develop**: Active development happens here.
- **main**: Production-ready code.

**GitHub Actions** automates the CI/CD pipeline in the **.github/workflows**  directory

Every push to the main branch triggers a deploy to Heroku.

---

## **Features**
- **Create Transaction**
- **Retrieve Transaction**

If you want to test the APIs, you can use this [Postman Collection](https://drive.google.com/file/d/1m55ledumGUJvMTIFD7_pcI1KVbNMQQOq/view?usp=sharing) for both Local and Development environment


---

## **Technologies**
- **Java 21** and **Spring Boot**
- **H2 Database** (In-memory)
- **JUnit** and **Cucumber** (Testing)
- **Swagger** (API Documentation)
- **GitHub Actions** (CI/CD)
- **Heroku** (Deployment)

---

## **Database**
The service uses an in-memory **H2** database
