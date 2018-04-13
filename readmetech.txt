These Eclipse projects used to test pom.xml deletion in Spring Hibernate project MJS 3.25.18
Although denoted hib33 hibernate4.2.1 AND hibernate 3.3.2 both used.

---------------------------------------------------------
10. sp3hib33CoalLegacy10 - 3.31.18

Tried setting up 2 Spring Legacy projects, using a simple template and a persistence template.  
Our prior projects pom matched the simple template.  Furthermore only this template included 
a hibernate dependency, so it was used.  Also note that, as expected, the maven src/main/java, 
etc., directory structure was setup. 

Quickly loaded all files and changed DBname.  Missing pom issues included 
missing dbcp, spring.framework orm, and javax.transaction.TransactionManager. 

Big problem changing hibernate from 4.2.1.Final to 3.3.2.GA so that ChacheProvider could 
be found. org.hibernate.cache.CacheProvider Works, but be sure to change hibernate-entityManager 
to hibernate-core or many problems with javax.transaction.api and javax.transaction:jta:jar.1.0.1B. 
Tried adding 1.0.1B dependency but no help. Tried changing spring to pre-3.1 version, but no help. 
Finally solved by changing hibernate-entitymanger to hibernate-core.  
Then needed to add javassist and it finally worked.  

---------------------------------------------------------------
9. sp3hib33CoalJava9 - 3.31.18

This went well.  Put all files in the same directory.  Had to add 
many libs, but this was not too hard.  Used UserLibrary for both 
spring and SL4J.  This helped organize the jars and also made the 
file structure much more similar to the sample project.

Got rid of no SSL warnings by appending to dbFileName in appContext.xml.

-------------------------------------------------
8. sp3hib33CoalMaven8 - 3.30.18 

Due to non-standard directory structure and problems manipulating directories the 
full maven project was re-created.  There was an load/parse xml problem at the 
beginning.  Corrected tis by switching from spring 3.1.0 to 3.0.7.  Aside from 
this the project went well.  Still ended up with "src/main/java", "src/main/resources" 
(which had package symbols) and a "src" directory (with no package symbol).  Under
src/main/java was a single coalcamps.models package, as desired. 

Tried re-doing this from scratch one more time and discovered the above directory 
structure is what you get when you start a pure maven project.

Had to use logback-classic to get debugging msgs to show. This, in turn, lead to
deleting sl4j-logger since these had the same routines.

------------------------------------------------
7. sp3hib33CoalMaven7 3.29.18

Main goal is to use just a maven file, not a spring project.

First trouble getting package symbols.  Must set folder as source folder.  Delete old 
src folders.  Use BuildPath->set as source folder.  The package boxes-symbol will then 
show up as part of the src-folder icon and when you create a new java class the package you type 
in will be represented with the boxes-icon. 

If multiple versions of the same dependency (such as hibernate-core) are in pom.xml, only 
the last one will show up in the maven dependencies tab (under build path). When building you 
will get a warning.

Trouble with import org.springframework.orm.hibernate3.HibernateTemplate.  
Neither hibernate4.2.1.Final nor hibernate3.3.2.GA would enable this class to be recognized. 
hibernate3.3.2 would not load from pom.xml.  Tried bumping spring from 3.0.7 to 3.1.0, 
but this did not help. Finally realized that problem was that org.springframework.orm was 
not loaded. Loaded this with either version 3.1.0 or 3.0.7 and problem gone.

Next tried to run Maven.  Was able to find goals (clean install, build, package, etc.) on web.
Had to move files from src to src/main/java to get it to run.  VERY difficult managing files 
inside of eclipse.  Was able to create directory (but not files with hoped for package symbols)
in eclipse.  Files then compiled, although 3.1.0 gave warnings that 3.0.7 did not. 

Had a son of a ***** time getting the compiled project to run.  Could not find a maven option, and 
could not run as a Java app.  "Run as" selection does not work in my eclipse.  (No submenu).  Tried
creating new Java run configuration, but HiberTest.java would never show up.  Tried lots and lots of 
web pages.  Finally changed package from mySQL.coalcamp to mySQL.coalcamp4, which displayed as a package 
and not just a folder in eclipse.  Then tried creating a Java run configuration and HiberTest.java showed 
up!!   Hours of frustration for very little gain.

Program next missing dependencies. Added commons.dbpc easily. Had to change appContext.xml to 
new coalcamp4 package.  Next missing org.hibernate.cache.CacheProvider. 
Per internet, had to downgrade from hib4 to hib3.  (4.2.1 to 3.3.2.GA in particular.) This worked. 

Had to applicationContext.xml and *.hbm.xml files in src/main/resources. 

Finally was able to change coalcamps4 package back to coalcamps package.  Strange Project Explorer directory 
structure with both "src/main/java" folder, src folder, etc.  Under "src/main/java" is mySQL package and coalcamps 
folder instead of mySQL/coalcamps package.  However program continued to run so this has been left as is. 
----------------------------------------------------------
6. sp3hib33CoalAllPom6 3.29.18

Main goal is to use only the pom.xml file without any external libraries.
Deleted almost all external libraries.  As expected had error msgs.  
Typing in maven dependency path.name takes one to a common Maven dependency site. 
This site has almost every possible dependency, but the search feature does not 
always seem to work, and it is often hard to tell which sub-file to download.
And, of course, it is not possbile to tell which version to download.  

The pom.xml used hibernate 4.2.1.Final, but the external library hibernate 3.3.2
was also needed for a localBean type class. (or maybe it was cacheProvider).

There was a version mismatch, which showed up with a msg in the output, for some of 
the SL4J files. Tried downloading all stuff from near 2012, which fixed the problem.

The application ran but not LOGGER output was produced.  Tracked this down to 
a missing ch.qos.logback <artifactId>logback-classic, but wouldnt have known 
this without examining the original pom.xml file.

Had multiple SLF4J bindings - StaticLoggerBinder.class.  Eliminated 		
org.apache.logging.log4j/log4j-slf4j-impl while keeping ch.qos.logback/logback-classic.

Also eliminated unused org.slf4j\slf4j-simple without changing program output.

As a whole this took quite a few less files than actually downloading the jars.

Also changed artifactID name and java version from 1.6 to 1.8 in pom.xml.  This did not 
allow the use of lambdas. Neither did changing build path to include JDK1.8

-------------------------------------------
5. sp3hib33CoalFunc 3.29.18

Began by cleaning up many comments and old routine for lazy-eager fetch join 
setFetchMode criteria getCoalCamp foreign key issue. Added double foreign key
fetch for CampLeases.  Improved name to consistently be noun1Name.  Improved 
lease toString to account for foreign keys of camp and company, including 
possible missing data.

Added record count routine for Coal Companies and used it to avoid adding 
duplicate records. Implemented separate addCoalCampData routine. 

-----------------------------------------------------------------------


4. sp3hib33CoalDelPomEnt4  3.28.18 (Mainly convert to coal and lazy/eager fetch issue.

Created new CoalCompany class, dao and hbm.xml files. Changed db to coalsp3hib33delpoment4.
Created tables sucessfully.  Tried adding a coalCompany record. Worked like a charm. 

Tried changing auto in applicationContext.xml to update. This correctly keeps table data 
between invocations.  Hence adding a record that already exists will throw an exception. 
Note that console shows exception trace even if exception is caught. Also tried renaming 
Book to CoalCamp at same time ... got this to run, but need to change table name
from book558. Had to change table value in coalcamp.hbm.xml file.  This all worked.  Note that 
with auto=update the new coalcamp table is added but the book table is NOT deleted.  
Similarly when a column name is changed for a table the new column is added but the old is NOT deleted.

Changed com.javapoint to mySQL.coalamp. Kept getting 
org.hibernate.MappingException: class com.javapoint.Employee not found ... Needed to 
change package (com.javapoint to coalcamps.models) in all xml files.

Added a foreign key companyID in the camp table.  Made it uni-directional (aka dont maintain a list 
of towns built in the company table. Got rid of unique=true in many-to-one mapping as this made mapping actually 1-to-1.  
Changed cascade to none.
Tested dbase delete in mySQL. As hoped a company could not be deleted while camps built by it 
were in the database, but could be after all camps built by it were deleted.  Added lease table.

Got a LazyInitializationError when trying to print coalCamp foreigh key (coal company) value. 
Best method to handle this is to do a fetch join which worked okay. 
"SELECT camp FROM CoalCamp camp LEFT JOIN FETCH camp.companyBuilding co";
Was able to change to eager fetch in xml file, but this is not recommended as it causes slow performance. 

Wanted to enable user to select wheter to do a Lazy or Eager fetch. This proved to be NOT possible. 
// Seems we can force eager with either crit.createAlias(fkField, alias, LEFT_JOIN)
// or with setFetchMode(fkField, FetchMode.EAGER). Works for crit or detachedCrit MJS 3.28.18
// Also note criteria and defaultCriteria appear to ALWAYS return the same result in debugger, although it 
// is unclear if they actually do.  It is confirmed that if either is EAGER, correct result prints.
// Try to enforce lazy retrieval for foreign key companyBuilding here.
// Could NOT do so ... just live with eager fetching if thats what xml file wants.
	    		// Could not force lazy retrieval. MJS 3.28.18
	    		// Tried crit.createAlias with setFecthMode(lazy), and both alone.
	    		// Tried same 3 combos with DetachedCrit both with and without LEFT_JOIN.
	    		// Also tried setResultTransformer and .setFetchMode.list. 
	    		Criteria crit = session.createCriteria(CoalCamp.class);
	    		// ccList = session.createCriteria(CoalCamp.class)
	    		// 	.createCriteria("companyBuilding", "companyBuilding", Criteria.LEFT_JOIN)
	    		//	.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
	    		// 	.list();
	    		ccList = session.createCriteria(CoalCamp.class)
	    						.setFetchMode("companyBuilding", FetchMode.LAZY)
	    						.list();
	    		// crit.setFetchMode("companyBuilding", FetchMode.SELECT);  // LAZY replaced by SELECT
	    		// ccList = crit.list();
Ultimately it is fairly easy to change from default lazy fetch to eager, either using a join fetch, or 
setting the fetch mode, or even just using createAlias.  These work for both criteria and detachedCriteria.
However, despite many, many tries it does not seem possible to change a default EAGER fetch strategy 
to lazy.  Spent entire day on 3.28.18 trying and eventually gave up. 

		// Seems we can force eager with either crit.createAlias(fkField, alias, LEFT_JOIN)
		// or with setFetchMode(fkField, FetchMode.EAGER). Works for crit or detachedCrit MJS 3.28.18
		// Also note criteria and defaultCriteria appear to ALWAYS return the same result in debugger, although it 
		// is unclear if they actually do.  It is confirmed that if either is EAGER, correct result prints.
		
-----------------------------------------------------------------------------

1. sp3hib33javaptDelPom1

First tried deleting entire pom.xml file. 
This has not worked out too well.  As soon as the pom.xml is deleted the 
Maven Dependencies folder disappears and when the project is run, a 
"Could not find or load main class com.javapoint.HibTest" error is thrown. 

Tried many suggestions on the web, notably deleting .project and .classpath 
and removing all refs to project inside .metaData, but none of these helped.

Trying to re-add pom.xml.  This worked to the extent that 
the main file was now found, but it could not be updated. 
Tried deleting HibernateTest.class under target/classes, but could not get it to 
rebuild. 

-------------------------------------------------------------------
2. sp3hib33javaptDelPomEnt2

As soon as pom.xml was deleted run gives a could not find main class error. 
If pom deleted,  Maven Dependencies folder also goes away. 
If pom.xml re-added it starts working again.

Deleted spring and hibernate files from pom.  Found main but many libraries 
need to be reloadded including a javax.transaction.TransactionManager

These libraries were added to the project 
by right clicking on the project -> Build Path ->Configure Build Path, then selecting the 
Libraries tab and clicking the Add External JAR button.  They show up in the "Referenced 
Libraries" tag directly under the project directory.  Note that the Maven depencies tag 
(which was auto-created) also has many spring and some hibernate libaries under it.  It 
is suspected these are linked to the auto-generated POM file.  This POM was  
used in the build process, because when entries are deleted from it the compiler complains. 

-------------------------------------------------------------------
After much fiddling, it appears that after values are deleted in the pom properties, they 
also need to be deleted in the pom.xml (the last tab) as well.  If not, the *.class 
files will NOT be recreated and the dreaded "Could not find or load main class ..." 
error will occur when trying to run the program.

After downloading many jar files got the program working again with a pom.xml file with 
no dependencies.
---------------------------------------------------------------------

3. sp3hib33javaptDelPom3 

Tried removing reference to Java 1.6 from pom and output encodings.  Program ran fine.
Tried removing project info at top of pom (modelVersion, groupId, artifactId, version). 
Immediately got "Could not find or load main class ..." error.  class file NOT in target subdir.
Also noticed EffectivePom tab shows "Unable to load effective POM ... " msg. Added 
project info at top back in, made change to main and it recompiled and ran.  Took out project 
info and removed xxxTest.class from target subdir. Would not run and xxxTest.class would not 
auto-create.  Tried re-inserting project info in pom file. Program still would not run as 
xxxTest.class did not re-create.  Tried Project->Clean.  This recreated the project and it 
ran fine. 

Tried deleting entire pom.xml file.  As expected this lead to "Could not find or load main 
class..." error.  Recreated pom.xml file from a backup and it worked fine.  

OVERALL LESSON: YOU CANT DELETE THE POM.XML FILE FROM A LEGACY SPRING PROJECT OR THE PROJECT 
ENTRIES, BUT YOU CAN DELETE THE OTHER CONTENTS OF POM.XML.  3.26.18

----------------------------------------------------------------------------------






