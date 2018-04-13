package coalcamps.dao;

import coalcamps.models.CoalCamp;
import java.util.*;  

public interface CoalCampDao {
	
	public void saveCoalCamp(CoalCamp camp);
	
	public void updateCoalCamp(CoalCamp camp);
	
	public CoalCamp coalCampGetById(int ID);
	
	//method to return all coalCamps (using default fetch strategy) 
	public List<CoalCamp> getCoalCamps();
	

	/*** 
	* Return all coalCamps either with or without foreign key company 
	* already fetched.
	*/
	public List<CoalCamp> getCoalCamps(boolean eager);

	public void deleteCoalCamp(CoalCamp camp);
	
} // end interface CoalCampDao
