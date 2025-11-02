
Step 1: Create a spring boot project using https://start.spring.io/

add lombook compatable version

Step 2: Setup mysql data base  "billing_App"
// Don't create table manually ask jpa to create it

add mysql and jpa dependency in pom.xml

Step 3: Go to application.properties file and configure the properties

Stp 4: Folder structure

in.project.billingSoftware
|---> io
|     |--> Category Request
|     |--> Category Response
|
|
|--->entity
|    |-->Category Entity
|   
|--->controller
|    |-->Category Controller
|
|--->repository
|    |--> Category Repository (Interface)
|
|--->Service
|    |-->impl
|    |   |--> CategoryServiceImpl
|    |
|    |-->categoryServiceInterface


