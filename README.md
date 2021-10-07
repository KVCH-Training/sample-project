# sample-project

## Steps to run

    sbt run

## Prerequisite

 - Install MySql Database
 - Run the following queries in mysql console

       CREATE DATABASE StudentDB;
       USE StudentDB;
       CREATE TABLE Student(
    	  name VARCHAR(20),
          rollNumber INT PRIMARY KEY,
          mobile VARCHAR(20),
          section VARCHAR(5),
          branch VARCHAR(10));

 - Update the username and password in the **application.conf** file ().
 - User Name @ https://github.com/KVCH-Training/sample-project/blob/main/src/main/resources/application.conf#L11
 - Password @ https://github.com/KVCH-Training/sample-project/blob/main/src/main/resources/application.conf#L12

## Supported Operation

**Sample Data**

    {
      "branch": "CSE",
      "name": "Test",
      "rollNumber": 1,
      "section": "A"
    }

**Input - 1**

    add----{"branch":"CSE","name":"Test","rollNumber":1,"section":"A"}

**Output**

    Successful User Inserted Successfully.

**Input - 2**

    get----1

**Output**

    Successful {"branch":"CSE","name":"Test","rollNumber":1,"section":"A"}

**Input - 3**

    updateName----1----NewName

**Output**

    Successful Name:- Updated, Mobile: Nothing to Update

**Input - 4**

    updateMobile----1----+911234567890

**Output**

    Successful Name:- Nothing to Update, Mobile: Updated

**Input - 5**

    delete----1

**Output**

    Successful Deleted

## Application Logs

    sbt run
    [info] welcome to sbt 1.5.5 (AdoptOpenJDK Java 16)
    [info] loading global plugins from C:\Users\Test\.sbt\1.0\plugins
    [info] loading project definition from C:\Users\Test\Documents\personal_idea\sample-project\project
    [info] loading settings for project sample-project from build.sbt ...
    [info] set current project to sample-project (in build file:/C:/Users/Test/Documents/personal_idea/sample-project/)
    [info] compiling 12 Scala sources to C:\Users\Test\Documents\personal_idea\sample-project\target\scala-2.13\classes ...
    [info] running com.example.Application
    add----{"branch":"CSE","name":"Test","rollNumber":1,"section":"A"}
    [INFO] [2021-10-07 13:03:14.193] [scala-execution-context-global-177] [com.zaxxer.hikari.HikariDataSource] slick.db - Starting...
    [INFO] [2021-10-07 13:03:14.226] [scala-execution-context-global-177] [com.zaxxer.hikari.HikariDataSource] slick.db - Start completed.
    [INFO] [2021-10-07 13:03:15.283] [scala-execution-context-global-177] [com.example.Application] Successful User Inserted Successfully.
    get----1
    [INFO] [2021-10-07 13:05:56.167] [scala-execution-context-global-180] [com.example.Application] Successful {"branch":"CSE","name":"Test","rollNumber":1,"section":"A"}
    updateName----1----NewName
    [INFO] [2021-10-07 13:07:09.652] [scala-execution-context-global-227] [com.example.Application] Successful Name:- Updated, Mobile: Nothing to Update
    updateMobile----1----+911234567890
    [INFO] [2021-10-07 13:08:23.802] [scala-execution-context-global-199] [com.example.Application] Successful Name:- Nothing to Update, Mobile: Updated
    get----1
    [INFO] [2021-10-07 13:09:25.751] [scala-execution-context-global-273] [com.example.Application] Successful {"branch":"CSE","mobile":"+911234567890","name":"NewName","rollNumber":1,"section":"A"}
    delete---1
    [INFO] [2021-10-07 13:09:33.600] [run-main-0] [com.example.Application] Not a valid option
    remove---1
    [INFO] [2021-10-07 13:09:42.178] [run-main-0] [com.example.Application] Not a valid option
    delete----1
    [INFO] [2021-10-07 13:09:59.719] [scala-execution-context-global-273] [com.example.Application] Successful Deleted

