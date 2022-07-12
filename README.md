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

notification-svc
url --> http://localhost:8991

api-gateway --> Load balancer "Entry pont url"
url --> http://localhost:8992

rabbitmq
ports: 5672
admin url --> http://localhost:15672
default credentials are:
    username: guest
    password: guest