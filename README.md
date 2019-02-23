# currency-service
Prerequisite – Maven should be installed in the operating system.

	1. Run run-currency-service.sh from bash terminal in this folder.
	2. This service will start checking latest exchange rate for BTC in every 15 Seconds.
	3. Check log file currency-service.log
	
Currency Service API can be seen using swagger interface

	1. Link to Swagger http://localhost:1010/swagger-ui.html
	2. Link to H2 Database Console http://localhost:1010/h2/login.do
			Driver Class org.h2.Driver
			JDBC URL jdbc:h2:file:~/test
			Username sa
			Password
			There’s no password to the H2 Database console.