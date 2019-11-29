### Demonstrating issue when it's not possible to access permitted endpoint when access token is present

in project root:

* start keycloak with `docker-compose up -d`
* start application with `./mvnw spring-boot:run` 
* execute `curl -I http://localhost:8080/permitted.html` - this should display content of `perrmitted.html` file
* execute `curl -I -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c" http://localhost:8080/permitted.html` - this should display content of
 `perrmitted.html` but instead it complains about security
 
 If you want to test it with correct token you can get one with `curl  -k --data "client_id=admin-cli&grant_type=password&username=admin&password=Password1."  http://localhost:9999/auth/realms/master/protocol/openid-connect/token`