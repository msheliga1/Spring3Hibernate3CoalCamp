package coalcamps.dao.hibernate;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
// import org.hibernate.criterion.DetachedCriteria;
// import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import coalcamps.dao.CoalCampDao;
import coalcamps.models.CoalCamp;

import java.util.*;  

public class CoalCampDaoImpl implements CoalCampDao {
	
	// This is the bean from the applicationContext.xml file
	HibernateTemplate template;  

	public HibernateTemplate getTemplate() {
		return template; 
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void saveCoalCamp(CoalCamp camp) {
			try {
				template.save(camp);
			} catch (org.hibernate.PropertyValueException ex) {
				System.out.println(camp.toString() + 
					" could not be saved. Maybe it references a coalCompany that is not in the database? " + 
						" ---- Exception Name PVE ---- " + ex.toString() + ex.getMessage() );
			} catch (org.springframework.dao.DataIntegrityViolationException ex) {
				System.out.println(camp.toString() + 
					" could not be saved. Maybe it references a coalCompany that is not in the database? " + 
						" ---- Exception Name DIVE---- " + ex.toString() + ex.getMessage() );
			} catch (Exception ex) {
				// even though exception is caught, lots of exception trace is printed to the console.
				System.out.println(camp.toString() + " could not be saved. Maybe it already exists? " + 
							" ---- Exception Name ---- " + ex.toString() + ex.getMessage() );
			} // end try-catch
	}
	
	public void updateCoalCamp(CoalCamp camp) {
		template.update(camp);
	}
	
	public CoalCamp coalCampGetById(int ID) {
		CoalCamp e = (CoalCamp) template.get(CoalCamp.class, ID);
		return e;
	}
	
	//method to return all coalCamps (using default fetch strategy) 
	public List<CoalCamp> getCoalCamps() {  	    
		return template.loadAll(CoalCamp.class);  
	} 
	

	/*** 
	* Return all coalCamps either with or without foreign key company 
	* already fetched.
	 */
	@SuppressWarnings("unchecked")
	public List<CoalCamp> getCoalCamps(boolean eager) { 
		String hql = null;
		List<CoalCamp> ccList = null;
	    if (eager) {
			// We CAN force eager with either crit.createAlias(fkField, alias, LEFT_JOIN)
			// or with setFetchMode(fkField, FetchMode.EAGER). Works for crit or detachedCrit MJS 3.28.18
	    	hql = "SELECT camp FROM CoalCamp camp LEFT JOIN FETCH camp.companyBuilding co";
		    ccList = template.find(hql);
	    } else { // Want to enforce lazy retrieval for foreign keys here.
	    	// Could NOT do so ... just live with eager fetching if thats what xml file wants.
	    	SessionFactory factory = template.getSessionFactory();
	    	Session session = null;
	    	try {  // Could not force lazy retrieval. MJS 3.28.18
	    		// Tried crit.createAlias with setFecthMode(lazy), and both alone.
	    		// Tried same 3 combos with DetachedCrit both with and without LEFT_JOIN.
	    		// Also tried setResultTransformer and .setFetchMode.list. 
	    		// 	.createAlias("companyBuilding", "companyBuilding", Criteria.LEFT_JOIN) 
	    		// CriteriaBuilder/Query not recognized by Eclipse for old Hibernate3
	    		session = factory.openSession();  // not sure if to use classic subtype or not
	    		// Criteria crit = session.createCriteria(CoalCamp.class);
	    		ccList = session.createCriteria(CoalCamp.class)
	    						.setFetchMode("companyBuilding", FetchMode.LAZY) // LAZY replaced by SELECT
	    						.list();
	    	} catch (Exception ex ) {
	    		System.out.println("Exception in getCoalCamps Lazy Retrieval." + ex.getMessage());
	    	} finally {
	    		if (session != null) session.close();
	    	}	    	
	    }  // if eager retrieval or not
	    // return template.loadAll(CoalCamp.class);  // can lead to lazy init error
	    return ccList; // return ccList;  
	} // end getCoalCamps

	public void deleteCoalCamp(CoalCamp camp) {
		template.delete(camp);
	}
	
} // end class CoalCampDao
