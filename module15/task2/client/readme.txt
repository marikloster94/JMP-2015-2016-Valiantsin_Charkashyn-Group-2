For generate client classes deploy web service and  use command:
wsimport -keep -verbose http://localhost:8080/RandomServ?wsdl

where http://localhost:8080/RandomServ?wsdl - path to wsdl

then copy this files to package com.epam.service in project Client