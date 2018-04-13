package coalcamps.dao;

import coalcamps.models.CampLease;

import java.util.*;  

public interface CampLeaseDao {
	
	public void saveCampLease(CampLease lease);
	
	public void updateCampLease(CampLease e);
	
	public CampLease campLeaseGetById(int ID);
	
	// method to return all campLeases  
	public List<CampLease> getCampLeases(); 
	
	// Return all campLeases with either lazy or eager fetching  
	public List<CampLease> getCampLeases(boolean eager);

	public void deleteCampLease(CampLease e);
	
} // end interface CampLeaseDao
