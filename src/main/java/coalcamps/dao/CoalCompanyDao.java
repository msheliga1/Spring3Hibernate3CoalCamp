package coalcamps.dao;

import coalcamps.models.CoalCompany;
import java.util.*; 

public interface CoalCompanyDao { 
	
	public void saveCoalCompany(CoalCompany co);
	
	public void updateCoalCompany(CoalCompany company);
	
	public CoalCompany coalCompanyGetById(int ID);
	
	/*** 
	 * Return a list of all coalCompanies
	 */ 
	public List<CoalCompany> getCoalCompanies();

	public void deleteCoalCompany(CoalCompany company);
	
	public int getCoalCompanyCount( );
	
} // end interface CoalCompanyDao
