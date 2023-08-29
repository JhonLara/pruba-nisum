Aplicación Java realizada con SpringBoot, Java, Hibernate entre otras herramientas. 
Como solución al ejercicio tecnico planteado.

mvnw spring-boot:run

Enviar peticiones al backend

Peticiones 

EndPoint para registrar usuario

POST
localhost:8080/nisum/login-user 

Body json

{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
{
"number": "1234567",
"cityCode": "1",
"contryCode": "57"
}
]
}

Respuesta 

{
"idUserLogin": 6248011028518910822,
"created": "2023-08-29",
"modified": null,
"last_login": "2023-08-29",
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE2OTMzMzQ0MDgsImV4cCI6MTY5MzMzNjIwOH0.xBdWB-xP-PllT7IgTh50Gw_ws3z1s1UR7MMmxzroYwY",
"isActive": true,
"message": null
}

Se habilitaron otros 4 endpoints para verificar y eliminar datos de usuarios y de telefonos

DELETE localhost:8080/nisum/{ID_USER}
DELETE localhost:8080/nisum/phone/{ID_PHONE}

Get all
GET localhost:8080/nisum/phone todos los telefonos
GET localhost:8080/nisum todos los usuarios
