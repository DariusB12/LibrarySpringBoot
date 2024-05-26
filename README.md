# LibrarySpringBoot
# Requirement:
  A library offers its subscribers a list of books that can be borrowed.\
  Each book can exist in one or more copies, identified by unique codes (id). \
  The library has several terminals where subscribers(REGULAR users) can borrow books. \
  To be able to use a terminal, a subscriber must sign in. \
  After signing in, the user can see the list of books available at that moment and can borrow/return one or more.  (in order to borrow or return a book the user should make an appointment)\
  For the return of books, there is only one terminal. \
  After each loan/return, all the users see the updated list of available books.

# I divided the problem into 3 iterations 
1. Sign IN/UP/Delete Account+ JWT Authentication + Initialize Graphical Interface + Data Base connection + Show Books
UC- 1,2,3  #DONE
2. BorrowBooks + Cancel Appointments + confirm BorrowAppointment
UC- 4,5,6  #DONE
3. ReturnBooks + Cancel Appointments + confirm Return Appointment
UC- 4,7,8  #DONE

# Technologies Used 
1. PostgreSQL for the data base connection
2. SpringBoot framework
3. Hibernate
4. Spring security
5. JWT security for authentication
6. Lombok dependency to make it easier to manipulate the entities
7. JavaFX
8. SSE (Server-Sent Events) - in order to send updates to clients

# Client JavaFX project repository link
[Link Client JavaFX App](https://github.com/DariusB12/LibraryJavaFX)

# Problems I faced:
  1. I had to add, in the dependency section of gradle.build, the lombok annotationProcessor because some lombok annotations were not handled during compilation time and this is the main reason why I could not compile my app.
  2. I had to disable the csrf for http requests, because when I wanted to make a request that changed the state (ex: POST), the application expected a csrf Token from this request, but i did not need this feature because I use JWT authentication.
  3. Exceptions thrown at the level of internal filter weren't catch in my CustomExceptionHandler class, so i had to use a handlerExceptionResolver and annotate my ControllerAdvice to be @Order(1) so that the exceptions were first handled by my CustomExceptionHandler.
   
# Model UML Diagram
<img width="994" alt="ModelDiagram" src="https://github.com/DariusB12/LibrarySpringBoot/assets/131203165/15e6aeec-f74a-4620-9335-6934f88460d0">

# UseCases UML Diagram
<img width="981" alt="UseCasesDiagram" src="https://github.com/DariusB12/LibrarySpringBoot/assets/131203165/d381ee5d-a745-43c3-91fa-dd01b012dcd2">
