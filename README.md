This Eclipse project demonstrates a spring-hibernate mySQL database using 
Spring version 3.2.3 and hibernate version 3.3.2.  Created by Michael Sheliga 3.15.18

This project uses older non-class-annotated hibernate mapping files (hbm.xml's), 
along with older hibernate 3.3.2.GA and spring 3.2.3.RELEASE.  While these are 
both older tech, they demonstrate how to implement a legacy spring-hibernate 
database. 

Implementation Highlights:
3 POJO Classes/Tables: CoalCompany, CoalCamp (1 foreign key) and CampLease (2 foreign keys).
3 DAO Classes.  One for each POJO.
Hibernate automatically creates tables in the coalsp3hib33SpringHiber11 database. 
The database is implemented using MySQL.  
There is no need to create the table structures, although the database schema should be created by the user. 
Uses applicationContext.xml beans and HibernateTemplates. (old but for demonstration purposes).
Lazy data initialization normally with eager fetching of foreign keys using JOIN FETCH. 
Test file creates data records and prints them out using eager fetching as necessary.

-------------------------------------------------------------------------------------
Technical Notes: Setup from Eclipse Spring Legacy projects, using a simple template.

Problem changing hibernate pom.xml dependency from 4.2.1.Final to 3.3.2.GA so that ChacheProvider could 
be found. While org.hibernate.cache.CacheProvider works, be sure to change hibernate-entityManager 
to hibernate-core or many problems with javax.transaction.api and javax.transaction:jta:jar.1.0.1B. 
Tried adding 1.0.1B dependency, and changing spring to pre-3.1 version, but no help. 
Finally solved by changing hibernate-entitymanger to hibernate-core.  
