package coalcamps.models;


import java.util.logging.Level;
 
//all org.springframework imports work once spring-framework.context in pom.
import org.springframework.beans.factory.BeanFactory;   
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;

import coalcamps.dao.CampLeaseDao;
import coalcamps.dao.CoalCampDao;
import coalcamps.dao.CoalCompanyDao;   

// class to test a Legacy Spring Hibernate example using hibernate mapping hbm.xml files.
// Note that old classes such as XmlBeanFactory and HibernateTemplatge are allowed since 
// this is a demonstration of older tech.  MJS 3.31.18
public class HiberTestCoalSpring11 {

	public static void main(String[] args) {
		System.out.println("Starting HiberTestCoalSpring11 main.");
		// show fewer log msgs - way too many to read.
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Resource r = new ClassPathResource("applicationContext.xml");
	 	System.out.println("Loaded resource, creating XmlBeanFacotry");		
		BeanFactory factory = new XmlBeanFactory(r);
		System.out.println("Newed XmlBeanFactory ... getting Dao beans.");
		
		CoalCompanyDao compDao = (CoalCompanyDao) factory.getBean("compDaoBean");  		
		CoalCampDao campDao = (CoalCampDao) factory.getBean("campDaoBean");
		CampLeaseDao leaseDao = (CampLeaseDao) factory.getBean("leaseDaoBean");
		
		if (compDao.getCoalCompanyCount() == 0) {
			addCoalCampData(compDao, campDao, leaseDao);
		}
			
		for (CoalCompany cc: compDao.getCoalCompanies()) {
			System.out.println(cc.toString());
		}
		// Cant use lambdas as java version is 1.6 even though Version changed to 1.8 in pom.xml
		// Even stuck 1.8JDK in build path.  Did not help.
		// compDao.getCoalCompanies().stream().forEach((cc)->System.out.println());
				
		boolean eager = true;  // LazyInitializationError if non-eager fetch
		for (CoalCamp cc: campDao.getCoalCamps(eager)) {
		 	System.out.println(cc.toString());
		}
		System.out.println(" ------ getCoalCamps(eager=true above) -------- ");
		
		for (CampLease cl: leaseDao.getCampLeases(eager)) {
			System.out.println(cl.toString());
		}
		System.out.println(" ------ getCampLeases(eager=true above) -------- ");
		
		System.out.println("Ending HiberTest sp3hib332 HiberTestCoalSpring11 main.");
	} // end main
	
	
	// addCoalCampData (will replicate data if it already exists).
	public static void addCoalCampData(CoalCompanyDao compDao, CoalCampDao campDao, CampLeaseDao leaseDao) { 
		System.out.println("Inserting records into database.");

		CoalCompany co = null;		
		CoalCamp camp = null;
		CampLease lease = null;
	
		co = new CoalCompany("Rochester and Pittsburgh", 1885);
		CoalCompany randp = co;
		System.out.println("RandP id unset, value is " + randp.getId());
		co.setId(8);  // this has no effect upon save since id is generated as identity
		System.out.println("RandP id set to 8, value is " + randp.getId() + " ... saving .... ");
		compDao.saveCoalCompany(co);
		System.out.println("RandP saved, id is " + randp.getId());
		// R&P coal camps
		campDao.saveCoalCamp(new CoalCamp("Iselin", 1905, randp));
		CoalCamp harttown = new CoalCamp("Hart Town", 1906, randp);
		// Demonstrate a deletion.
		campDao.saveCoalCamp(harttown);
		campDao.deleteCoalCamp(harttown);
		// campDao.saveCoalCamp(new CoalCamp("Whiskey Run", 1906, randp));	
		// campDao.saveCoalCamp(new CoalCamp("Nesbitt Run", 1906, randp));
		// campDao.saveCoalCamp(new CoalCamp("Earnest", 1910, randp));
		// campDao.saveCoalCamp(new CoalCamp("Lucerne", 1912, randp));
		
		co = new CoalCompany("Cambria Steel", 1852);
		compDao.saveCoalCompany(co);
		CoalCamp slickville = new CoalCamp("Slickville", 1917, co);
		campDao.saveCoalCamp(slickville);
		// campDao.saveCoalCamp(new CoalCamp("Wherum", 1890, co));
		// campDao.saveCoalCamp(new CoalCamp("Cokeville", 1910, co));
		
		co = new CoalCompany("Edwards", 1912);
		compDao.saveCoalCompany(co);
		// campDao.saveCoalCamp(new CoalCamp("Edwards", 1920, co));
		
		lease = new CampLease(slickville, randp, 1922, 1924);
		leaseDao.saveCampLease(lease);
	} // end addCoalCampData 
	
} // end class HiberTestCoalSpring11
