#Imported from svn into git on Feb/18/2014
git svn clone --authors-file users.txt --no-metadata --ignore-paths="(ejb3-project|java-web-services-up-running|jms-examples|jpa-ejb3-jsf-project|servlets-jsp|spring-in-action)" svn://blake/ restful-services


#NOTES

*This project is the result of combining 3 projects, both REST related projects had a dependency on the database project.
I created a pom.xml for the parent, arranged the build order, extracted common configurations and fixed some maven
dependency issues. Also changed default jetty port for jaxrs-service because now oracle installs at 8080!! Jetty runs in
integration tests phase.

*JBoss resteasy seems crazy and once in a while has problems recognizing "Accepts" header giving error in this assert:
assertEquals("application/json", get.getResponseHeader("Content-Type").getValue());
Sometimes it'll pass, sometimes it won't. You can try to change the expected to application/xml and the service will
return application/json and so on, it's nuts.

*Right now you need Oracle 11g driver and Oracle Express 11g to run these projects, which is a pain. In the future I plan
to migrate this to postgres DB, so I can create the tables for each integration tests run.

*oracle-hibernate-mappings was created with an schema first approach, no hbm2dll was used to create the DB DDL scripts

*jpa-mappings project also uses the same database schema and DDL scripts as oracle-hibernate-mappings


#Instructions

*Download Oracle Database 11g Release 2 (11.2.0.4) JDBC Drivers
http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-112010-090769.html

*Install ojdbc6.jar using this command line.
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc5 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=ojdbc5.jar
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=ojdbc6.jar

*Download and install Oracle Express 11g
http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html

*Activate default HR user or do it with any user you want(change JDBC url in pom.xml)
http://docs.oracle.com/cd/E17781_01/admin.112/e18585/toc.htm#BJFDGBHJ

*Run the sql scripts from oracle-hibernate-mappings/src/main/sql
SQLDDL.sql
SQLinsert.sql
SQLselect.sql

*Run the project
