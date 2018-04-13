package coalcamps.dao.hibernate;

// Use old template class for demonstration purposes.  A newer 
// implementation would use SessionFactory.
import org.springframework.orm.hibernate3.HibernateTemplate;

import coalcamps.dao.CoalCompanyDao;
import coalcamps.models.CoalCompany;

import java.util.*; 

public class CoalCompanyDaoImpl implements CoalCompanyDao { 
	
	// This is the bean from the applicationContext.xml file
	HibernateTemplate template;  

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void saveCoalCompany(CoalCompany co) {		
		try {
			// hibernate save (could throw duplicate entry for key primary exception 
			// but only if key is set by user.)
			// if (coalCompanyGetById(co.getId()) == null) {
			template.save(co);
		// this catch works MJS 3.26.18
		} catch (org.springframework.dao.DataIntegrityViolationException ex) {
			// org.hibernate.exception.ConstraintViolationException wont be caught and 
			// BatchUpdateException, MySQLIntegrityConstraintViolationException not thrown
			System.out.println(co.toString() + " could not be saved. It likely already exists? " + ex.getMessage());
		} catch (Exception ex) {
			// even though exception is caught, lots of exception trace is printed to the console.
			System.out.println(co.toString() + " could not be saved. Maybe it already exists? " + ex.getMessage()
					+ " ---- Name ---- " + ex.toString());
		}
	}
	
	public void updateCoalCompany(CoalCompany co) {
		template.update(co);
	}
	
	public CoalCompany coalCompanyGetById(int ID) {
		CoalCompany e = (CoalCompany) template.get(CoalCompany.class, ID);
		return e;
	}
	
	/*** 
	 * Return a list of all coalCompanies
	 */
	// (no need to worry about lazy vs eager since no foreign keys).  
	public List<CoalCompany> getCoalCompanies(){  
	    List<CoalCompany> list=new ArrayList<CoalCompany>();  
	    list=template.loadAll(CoalCompany.class);  
	    return list;  
	} 

	public void deleteCoalCompany(CoalCompany co) {
		template.delete(co);
	}
	
	public int getCoalCompanyCount( ) {
		String hql = "SELECT count(*) FROM CoalCompany";
		// template.getSession();
		List<Object> list = (List<Object>) template.find(hql);

		if ((list == null) || (list.size() != 1)) {
			System.out.println("Error getting count of Coal Companies. Bad number "
					+ " of records returned from SELECT.");
			return 0;
		}
		// Object -> Long -> long -> int (prefer to have function return int).
		return (int) (long) ((Long) list.get(0));
	}
	
} // end class CoalCompanyDao
