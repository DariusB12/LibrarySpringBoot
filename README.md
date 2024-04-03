# LibrarySpringBoot
# Requirement:
  A library offers its subscribers a list of books that can be borrowed. 
  For a subscriber, the system retains (at least) information related to the cnp, name, address, telephone and a unique identification code within the library. 
  Each book can exist in one or more copies, identified by unique codes. The library has several terminals where subscribers can borrow books. 
  To be able to use a terminal, a subscriber must log in. 
  After logging in, he sees the list of copies available at that moment and can borrow one or more. For the return of books, there is only one working point, served by a librarian. 
  After each loan/return, all library terminal users see the updated list of available books.

# Model UML Diagram
<img width="911" alt="ModelDiagram" src="https://github.com/DariusB12/LibrarySpringBoot/assets/131203165/974b3100-7fea-4940-8026-e60742ac971a">

# UseCases UML Diagram
<img width="1105" alt="UseCasesDiagram" src="https://github.com/DariusB12/LibrarySpringBoot/assets/131203165/b9618608-2cc6-405c-8d7e-7d29559326d2">

# I divided the problem into 3 iterations
1. Sign IN/UP/OUT/Delete Account/Forgot Password + JWT Authentication + Initialize Graphical Interface + Data Base connection + Show Books\
  UseCases - 1,2,3,4,5
2. BorrowBooks + Penalizations + Cancel Appointment + Reactivate Account + confirm Borrow Appointment\
  UseCases - 6,7,9
3. ReturnBooks + Penalizations + Cancel Appointment + confirm Return Appointment + confirm Other Appointments\
  UseCases - 8,10
