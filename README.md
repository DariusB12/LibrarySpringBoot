# LibrarySpringBoot
# Requirement:
  A library offers its subscribers a list of books that can be borrowed. 
  For a subscriber, the system retains (at least) information related to the cnp, name, address, telephone and a unique identification code within the library. 
  Each book can exist in one or more copies, identified by unique codes. The library has several terminals where subscribers can borrow books. 
  To be able to use a terminal, a subscriber must log in. 
  After logging in, he sees the list of copies available at that moment and can borrow one or more. For the return of books, there is only one working point, served by a librarian. 
  After each loan/return, all library terminal users see the updated list of available books.
# The project stage
For the time being I implemented the SignIn and SignUp functionalities using JWT authentication.
# Model UML Diagram
<img width="915" alt="ModelDiagram" src="https://github.com/DariusB12/LibrarySpringBoot/assets/131203165/a859ca65-736d-4014-9f71-aa1c442c68e3">

# UseCases UML Diagram
<img width="1105" alt="UseCasesDiagram" src="https://github.com/DariusB12/LibrarySpringBoot/assets/131203165/b9618608-2cc6-405c-8d7e-7d29559326d2">

# I divided the problem into 3 iterations
1. Sign IN/UP/OUT/Delete Account/Forgot Password + JWT Authentication + Initialize Graphical Interface + Data Base connection + Show Books\
  UseCases - 1,2,3,4,5
2. BorrowBooks + Penalizations + Cancel Appointment + Reactivate Account + confirm Borrow Appointment\
  UseCases - 6,7,9
3. ReturnBooks + Penalizations + Cancel Appointment + confirm Return Appointment + confirm Other Appointments\
  UseCases - 8,10

## UseCases Scenarios with Alternative flows and Exceptions:
[Use Cases 2.pdf](https://github.com/DariusB12/LibrarySpringBoot/files/14855176/Use.Cases.2.pdf)

# Technologies Used
1. PostgreSQL for the data base connection
2. SpringBoot framework
3. Spring security
4. Jwt security for authentication
5. Lombok dependency to make it easier to manipulate the entities
6. Postman app in order to send requests to my app
