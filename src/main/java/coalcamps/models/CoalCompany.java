package coalcamps.models;


import java.io.Serializable;

/**
 *
 * @author Mike Sheliga 3.26.18
 */
public class CoalCompany implements Serializable {
	    
		static final long serialVersionUID = 1L;
		private int id;  
	    private String companyName; 
	    private int yearFounded; 
	    
	    // ----------------- Constructors --------------------------------------
	    public CoalCompany() { }
	    
	    public CoalCompany(String companyName, int yearFounded) {
	    	this.companyName = companyName;
	    	this.yearFounded = yearFounded;
	    }
	    
	    // --------------------- Standard Methods ----------------------
	    @Override public String toString() {
	    	return "Coal Company: ID: " + id + " Name: " + companyName + " Year Founded: " + yearFounded;
	    }
	    
	    // --------------------- Standard Getters and Setters --------------
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	         this.id = id;
	    }

	    public String getCompanyName() {
	        return companyName;
	    }

	    public void setCompanyName(String companyName) {
	        this.companyName = companyName;
	    }

	    public int getYearFounded() {
	        return yearFounded;
	    }

	    public void setYearFounded(int yearFounded) {
	        this.yearFounded = yearFounded;
	    }

} // end class CoalCompany
