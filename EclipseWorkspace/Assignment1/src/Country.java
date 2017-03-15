import java.io.File;

/**
* Country serves as the primary representation of data for this application. 
* This class implements several attributes found in real world countries.
*
* @author  Olivier Perrault (250890527)
* @version 1.0
* @since   2017-01-31 
*/


public class Country 
{
	/** Constant:
     * LINE_SEPARATOR*/
	private final String LINE_SEPARATOR = System.getProperty("line.separator");
	/** Attribute:
     * name*/
	private String name; 
	/** Attribute:
     * population*/
	private int population; 
	/** Attribute:
     * area*/
	private double area; 
	/** Attribute:
     * Continent on which it exists*/
	private String continent; 
	
	/**
	* Class' constructor
	* @param String _name
	* @param int _population
	* @param double _area
	* @param String _continent
	*/
	public Country(String _name, int _population, double _area, String _continent)
	{
		name = _name;
		population = _population;
		area = _area;
		continent = _continent;
			
	}
	
	//region ACCESSORS
	/** accessor method for the name
     * @return String name
     */
	public String getName(){return name;}
	
	/** accessor method for the population
     * @return int population 
     */
	public int getPopulation(){	return population;	}
	
	/** accessor method for the population density
     * @return double population_density (population/area). 
     */
	public double getPopDensity(){return (population/area);}
  	
	/** accessor method for the area
     * @return double area
     */
	
	public double getArea(){return area;}
		/** accessor method for the continent
     * @return String continent
     */
	public String getContinent(){return continent;}
	
	///endregion ACCESSORS
	
	//region MUTATORS
	/** mutator method for the population
	 * @param int _population
    */
	public void setPopulation(int _population){	 population = _population;}
	/** mutator method for the continent
	 * @param int _continent
     */
	public void setContinent(String _continent)	{continent = _continent;}
		
	//endregion MUTATORS
	
	//region METHODS
	/** write the name, continent, population, and population density of the Country object (separated by commas) to the provided ThingToWriteFile object.
    * @param ThingToWriteFile out
    */
	public void writeToFile(ThingToWriteFile out) 
	{
		String s = name +", "+continent+", "+String.valueOf(population)+", "+String.valueOf(getPopDensity());
		out.writeLine(s);
		out.writeLine(LINE_SEPARATOR);//skip line
	}
	
	 /** prints on the screen details of the Country object
	 */
	public void printCountryDetails()
	{
		String s = name +" is located in "+continent+", has a population of "
		+String.valueOf(population)+", an area of "+String.valueOf(area)+", and has a population density of "
		+String.valueOf(getPopDensity()+".");
		
		//print the string defined above
		System.out.println(s);
	}
	
	 /** prints on the screen details of the Country object
	  * @return String toString
     */
	public String toString() //overrides the parents to string
	{
		String s = name +" in "+ continent;
		return s;
	}
	
	//endregion METHODS


}
