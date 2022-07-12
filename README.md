This is used for learning purposes only.

Customer Service is connected to a postgresDB and pgAdmin.

All the services are run through Docker: docker-compose up

pgAdmin:
url: http://localhost:5050/browser/

customer-svc:
url --> http://localhost:8989

fraud-svc:
url --> http://localhost:8990

eureka-svc
url --> http://localhost:8761

zipkin
url --> http://localhost:9411

api-gateway
port: 8991