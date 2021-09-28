# Rate API
Luxoft Rate Api Test

The Rates API is a free service for current and historical foreign exchange rates for different
currencies against Euro.

The purpose of this project is to storing and viewing currency exchange rates.


### Application info:
1. Developed with Java -version 17
2. Spring Boot -version 2.5.5
3. Spring Data JPA -version 2.5.5

### To run the application:

1. Run the as a Spring Boot Application, the way you prefer.
3. The server is configured to **localhost** by defaul at **8080** (**http://localhost:8080**)

### To use the endpoints of the application:

1. **http://localhost:8080/api/v1/retrieve** responsible to update the database with the new rates from the last x days defined on properties.
2. **http://localhost:8080/api/rate/2021-09-17** responsible to find an specific rate taking as a parameter the **date**
3. **http://localhost:8080/api/v1/rate?start_date=2021-09-17&end_date=2021-09-19** responsible to find the data between range date informed take two **dates** as a parameters
