# Ecommerce
Required Softwares
------------------
Spring Boot 2.3.1.RELEASE
JDK 1.8 
Maven - 3.2+
IDE - Eclipse or Spring Tool Suite (STS)
MYSQL
PostMan


----------------------------------------------------------------------------------------------------------------------
Download the Source from github repository
Change the properties under below file

File Name : ecommerce\src\main\resources\application.properties

Changes :
----------
server.port=8080
spring.datasource.url: jdbc:mysql://<mysqlipaddress>:<mysqlport>/authentication
spring.datasource.username: <Username>
spring.datasource.password: <Password>


Create below Schema under with above user access privilage in the my sql server
------------------------------------------------------------------------------
ecommerce

------------------------------------------------------------------------------------------------------------------------------
Run the Application
-------------------

EcommerceApplication



Test Case
----------
To mock the product,discount,tax details Run the below automated test file
com.org.altimetrik.ecommerce.repo.DiscountDetailsRepoTest.java
com.org.altimetrik.ecommerce.repo.ProductRepoTest.java
com.org.altimetrik.ecommerce.repo.TaxDetailsRepoTest.java

To run Junit test case run the below Java file
com.org.altimetrik.ecommerce.service.ProductServiceTest.java
com.org.altimetrik.ecommerce.service.CartServiceTest.java
com.org.altimetrik.ecommerce.service.PurchaseDetailsServiceTest.java
com.org.altimetrik.ecommerce.service.PrintReceiptServiceTest.java 

-----------------------------------------------------------------------------------------------------------------------------
To test in Swagger
------------------
url = http://localhost:8080/swagger-ui.html#

API Methods
1. Method Name - addToCart, description - add the prodcut in user cart post validating stock availablity for this product.
2. Method Name - checkout, description - checkout the items which was added in the cart post validating the stock availablity for this product.
3. Method Name - printReceipt, description - to print and showing the items purchased and toal amount and discount amount,etc.
