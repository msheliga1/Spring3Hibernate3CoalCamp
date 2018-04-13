package coalcamps.models;


import java.io.Serializable;

public class CampLease implements Serializable {
	
	static final Long serialVersionUID = 1L;
	private int id;  
	private CoalCamp campLeased;  // foreign key in DB
	private CoalCompany companyLeasing;  // foreign key in DB
	private int beginYear;
	private int endYear;
	
	// ------ Constructors -------
	public CampLease( ) {};
	
	public CampLease(CoalCamp campLeased, CoalCompany companyLeasing, int beginYear, int endYear) {
		this.campLeased = campLeased;
		this.companyLeasing = companyLeasing;
		this.beginYear = beginYear;
		this.endYear = endYear;
	}
	
	// ------ standard methods --------------------
	@Override
	public String toString() {
		String camp = "";
		try {
			if (campLeased != null) camp = " Camp " + campLeased.getCampName() + " Leased";
		} catch (Exception ex) {
			// swallow exceptions such as LazyInitialization Exception
		}
		String company = "";
		try {
			if (companyLeasing != null) company = " by " + companyLeasing.getCompanyName();
		} catch (Exception ex) {
			// swallow exceptions such as LazyInitialization Exception
		}
		String id = "Camp Lease: ID=" + this.id;
		String years = " (" + beginYear + " - " + endYear + ")";
		return id + camp + company + years;
	}
	
	// ------ standard getters and setters --------
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
		
	public CoalCamp getCampLeased() {
		return campLeased;
	}
	public void setCampLeased(CoalCamp campLeased) {
		this.campLeased = campLeased;
	}
	public CoalCompany getCompanyLeasing() {
		return companyLeasing;
	}
	public void setCompanyLeasing(CoalCompany companyLeasing) {
		this.companyLeasing = companyLeasing;
	}
	public int getBeginYear() {
		return beginYear;
	}
	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
} // end class CampLease
