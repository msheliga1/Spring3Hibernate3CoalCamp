package coalcamps.dao.hibernate;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import coalcamps.dao.CampLeaseDao;
import coalcamps.models.CampLease;

import java.util.*;  

public class CampLeaseDaoImpl implements CampLeaseDao {
	
	// This is the bean from the applicationContext.xml file
	HibernateTemplate template;  

	public HibernateTemplate getTemplate() {
		return template; 
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void saveCampLease(CampLease lease) {
		try {
			template.save(lease);
		} catch (org.hibernate.PropertyValueException ex) {
			System.out.println(lease.toString() + 
				" could not be saved. Maybe it references a camp or company that is not in the database? " + 
					" ---- Exception Name PVE ---- " + ex.toString() + ex.getMessage() );
		} catch (org.springframework.dao.DataIntegrityViolationException ex) {
			System.out.println(lease.toString() + 
				" could not be saved. Maybe it references a camp or company that is not in the database? " + 
					" ---- Exception Name DIVE---- " + ex.toString() + ex.getMessage() );
		} catch (Exception ex) {
			// even though exception is caught, lots of exception trace is printed to the console.
			System.out.println(lease.toString() + " could not be saved. Maybe it already exists? " + 
						" ---- Exception Name ---- " + ex.toString() + ex.getMessage() );
		} // end try-catch
	}
	
	public void updateCampLease(CampLease e) {
		template.update(e);
	}
	
	public CampLease campLeaseGetById(int ID) {
		CampLease e = (CampLease) template.get(CampLease.class, ID);
		return e;
	}
	
	// method to return all campLeases  
	public List<CampLease> getCampLeases(){  
	    List<CampLease> list=new ArrayList<CampLease>();  
	    list=template.loadAll(CampLease.class);  
	    return list;  
	} 
	
	/*** 
	 * Return all campLeases with either lazy or eager fetching  
	 */
	@SuppressWarnings("unchecked")
	public List<CampLease> getCampLeases(boolean eager){  
	    List<CampLease> list=new ArrayList<CampLease>();
	    if (eager) {
	    	String hql = "SELECT lease FROM CampLease lease LEFT JOIN FETCH lease.campLeased camp " + 
	    				 " LEFT JOIN FETCH lease.companyLeasing co";
	    	list = template.find(hql);
	    } else { // lazy (cant be forced if default fetch is eager)
	    	SessionFactory sFactory = template.getSessionFactory();
	    	Session session = null;  // imported non-classic org.hibernate.Session
	    	try {
	    		session = sFactory.openSession();
	    		Criteria crit = session.createCriteria(CampLease.class);
	    		crit.createCriteria("campLeased", "campLeased"); // companyLeasing
	    		crit.setFetchMode("campLeased", FetchMode.LAZY);
	    		list = crit.list();
	    	} catch (Exception ex) {
	    		System.out.println("Error getting Camp Leases (Lazyily) " + ex.getMessage());
	    	} finally {
	    		if (session != null) {session.close();}
	    	}
	    }  
	    return list;  
	} 

	public void deleteCampLease(CampLease e) {
		template.delete(e);
	}
	
} // end class CampLeaseDao
