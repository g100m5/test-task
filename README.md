Use api to import customers
POST http://localhost:8085/api/customers/import
with file parameter uploadFile

Use api to get customers
GET http://localhost:8085/api/customers/top?latitude=100.0&longitude=100.0
(The latitude and longitude values are optional, if not specified, is 0.)

use api to delete customer file
DELETE http://localhost:8085/api/customers/clear 