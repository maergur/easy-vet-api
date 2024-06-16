# :open_book:	EasyVet API UML
![EasyVet UML](https://github.com/maergur/project-3/assets/153439629/487ec3f2-c149-4a51-997a-d86553ceb716)

## :technologist:	Technologies Used

- Java
- SprintBoot
- PostgreSQL

### Postman Link: https://documenter.getpostman.com/view/33339031/2sA2xfXD6w

#### API Basic Features

Management of Animals and Owners

- Registering, updating, viewing, and deleting animal information.
- Registering, updating, viewing, and deleting owner information.
- Creating an endpoint to filter owners by name.
- Creating an endpoint to filter animals by name.
- Creating an API endpoint to view all animals registered by a specific owner, with the ability to filter animals based on the owner.

Vaccination Management

- Registering, updating, viewing, and deleting vaccinations for animals.
- Restricting the addition of a new vaccination if the same type of vaccine (with the same name and code) for the same animal has not reached its expiration date. This should be checked 
  based on vaccine codes and expiration dates.
- Creating an API endpoint to list all vaccination records for a specific animal based on its ID.
- Creating an API endpoint to return a list of animals whose vaccination protection end date falls within a user-entered start and end date range.

Appointment Management

-> Please use the following date format "yyyy-MM-dd HH:mm" while creating new appointment. <-

- Creating, updating, viewing, and deleting appointments for vaccinations and examinations for animals.
- Recording appointments with date and time using LocalDateTime.
- Scheduling appointments with doctors for various examinations on available dates and times. Only hourly appointments can be scheduled for each doctor, assuming each examination takes a 
  fixed one-hour duration.
- Checking the doctor's availability on the entered date and, if available, verifying if the doctor has another appointment at the entered time. If both conditions are met, the appointment 
  can be created; otherwise, throw a RuntimeException with the appropriate error message.
- Creating an API endpoint to filter appointments based on the user-entered date range and doctor.
- Creating an API endpoint to filter appointments based on the user-entered date range and animal. You can explore JPA's findBy between usage for this purpose.

Veterinary Doctor Management

- Registering, updating, viewing, and deleting veterinary doctor information.

Doctor's Available Days Management

- Adding, updating, viewing, and deleting the available days for veterinary doctors.
- Storing the working days of doctors in the system as LocalDate. Only date information will be stored; there will be no hour, minute, or second information.


