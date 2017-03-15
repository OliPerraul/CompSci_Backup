import java.io.File;

/**
* The CountryCatalogue implements an application that reads
*  information from a textfile, organises it in a catalogue, 
*  and provides functionalities to manipulate the data
*
* @author  Olivier Perrault (250890527)
* @version 1.0
* @since   2017-01-31 
*/


public class CountryCatalogue 
{

	/** Constant:
     * DEFAULT_SIZE (5)*/
	private final int DEFAULT_SIZE = 5;
	/** Constant:
     * NOT_FOUND*/
	private final int NOT_FOUND = -1;
	/** Attribute:
     * catalogue*/
	private Country[] catalogue; 
	/** Attribute:
     * numCountries stores the number of countries currently in the catalogue (initially 0)*/
	private int numCountries; 

	/**
	* First overload of CountryCatalogue's constructor
	*/
	public CountryCatalogue()
	{
		catalogue =  new Country[DEFAULT_SIZE]; 
				
		numCountries = 0; 

	}
	
	/**
	* Second overload of CountryCatalogue's constructor
	* @param String file_country
	* @param String file_continent
	*/
	public CountryCatalogue(String file_country, String file_continent)
	{
			
		catalogue =  new Country[DEFAULT_SIZE]; 
		numCountries = 0; 
			
		ThingToReadFile ttrf = new ThingToReadFile(file_country); //reader class
		ttrf.readLine(); //reads and discards header line 
		do 
		{
		String complete_line = ttrf.readLine(); //reads a complete line
		String[] line_content = complete_line.split(","); //Splits a string into substrings that are based on the characters in an array. 
		
		//create new country(name, pop, area , "" <--continent in blank initially
		Country c = new Country(line_content[0], Integer.parseInt(line_content[1]), Double.parseDouble(line_content[2]),"");
		addCountry(c);//stores country in catalogue
		
		
					
		} while(!ttrf.endOfFile()); //while has something to read
		
		ttrf.close();//close reader
		
		ttrf = new ThingToReadFile(file_continent); //reader class
		ttrf.readLine(); //reads and discards header line 
		do 
		{
		String complete_line = ttrf.readLine();
		String[] line_content = complete_line.split(","); //Splits a string into substrings that are based on the characters in an array. 
		//name, pop, area , "" <--continent in blank initially
		String country = line_content[0];
		String continent = line_content[1];
		
			
		
			for (int i= 0; i <numCountries; i++) //iterate through catalogue to distribute continent (if two country have the same name, then same continent)
			{
				if(catalogue[i].getName().equals(country)) //if country matches
				{
					catalogue[i].setContinent(continent); //set correct continent					
				}
			
			}
				
					
		} while(!ttrf.endOfFile());
		
		
		
		ttrf.close();//close buffer
		//ThingToReadFile ttwf2 = new ThingToReadFile(file_continent);
		
		
	}
	

		/** Accessor method for numCountries (number of countries in the catalogue)
		* @return int NumCountries
		*/
		public int getNumCountries(){return numCountries;}
	
		/** adds the provided country to the catalogue array. 
	    * @param Country c
	    */
		public void addCountry(Country c) 
		{
			if(numCountries>catalogue.length-1)
			{
				expandCapacity();
			}
			
			catalogue[numCountries] = c;//stores country in catalogue
			numCountries++;//increment country
			
		}
		
		/** double the size of the catalogue array.
	    */
		private void expandCapacity() 
		{
			int size = catalogue.length*2; //checks for curr size and doubles it
			
			Country[] newCatalogue = new Country[size];
			
			for(int i = 0; i< numCountries; i++) //populate new array
			{
				newCatalogue[i] = catalogue[i];
				
			}
			
			//changes old catalogue for new one
			catalogue = newCatalogue.clone();
			newCatalogue = null; //frees memory
		}
		
		/** takes an index as a parameter and returns a Country object from the indexed location in the catalogue
		* @param int index
		* @return Country c
		*/
		public Country getCountry(int index)
		{
			if(index != NOT_FOUND)
			return catalogue[index];//retrieves
			else
			return null;
		}
		
		/** which simply calls the toString() method for each Country currently in the catalogue instance variable and prints these strings. 
		*/
		public void printCountryCatalogue()
		{
			for(int i = 0; i< numCountries; i++)
			{
				System.out.println(catalogue[i].toString());
				
			}
		}

		/** print out all the countries from a specified continent.
		* @param String name
		*/
		public void filterCountriesByContinent(String continent)
		{
		    boolean exists = false; //flags if countries continent is in catalogue
		    
				for(int i = 0; i< numCountries; i++) 
				{
				    	if(catalogue[i].getContinent().equals(continent)) //if continents matches
					{
					System.out.println(catalogue[i].toString());	
					exists = true;
					}
				
				}
				
			if(!exists)//notifies user if continent is not in the catalogue
			System.out.println("No country in the catalogue is located in this continent.");
		}
		
		/** receives as parameter the name of a country and returns an int representing the index of the country in catalogue. Notifies user if not found
		* @param String name
		* @return Country c
		*/
		public int searchCatalogue(String countryName) //
		{
			for(int i = 0; i< numCountries; i++) 
			{
				if(catalogue[i].getName().equals(countryName)) //if country matches
				{
					System.out.println("Country Found!");	
					return i;				
				}
				
			}
			
		System.out.println("Country could not be found");
		return NOT_FOUND;
		}
		
		
		/** which receives as parameter the name of a country and removes it from the catalogue. Notifies user if not found
		* This method can use searchCatalogue. 
		* @param String CountryName
		*/
		public void removeCountry(String countryName)
		{
			for(int i = 0; i< numCountries; i++) 
			{
				if(catalogue[i].getName().equals(countryName)) //if country matches
				{
					catalogue[i] = catalogue[numCountries-1]; //replace item to be removed with one at the end
					catalogue[numCountries-1] = null;// remove endlist item
					numCountries--; //deccrease numCountries
					System.out.println("Country successfully removed");	
								
				}
				
			}
			System.out.println("Country could not be found");
			
		}
		
		/** which receives as parameter the name of a country and an integer value and changes the population of that country to the specified value. This method can call searchCatalogue. Notify user if item was successfully altered.
		* @param String name
		* @param int value
		* @return Country c
		*/
		public void setPopulationOfACountry(String countryName, int pop) 
		{
		    if(searchCatalogue(countryName)!=-1)
		    {
			for(int i = 0; i< numCountries; i++) 
			{
				if(catalogue[i].getName().equals(countryName)) //if country matches
				{
					catalogue[i].setPopulation(pop); //replace population with new one
				}
				
			}
		    }
		}
		
		/** Which will write the catalogue’s content  to a file. This method calls the Country’s writeToFile() method for each country in the catalogue.
		* Carefully closes the file. 
		* @param String file (name of the file)
		*/
		public void saveCountryCatalogue(String filename) //TODO: Fix wrong parameter type specified
		{
			ThingToWriteFile out = new ThingToWriteFile(filename);
			
			for(int i = 0; i< numCountries; i++) 
			{
				
				catalogue[i].writeToFile(out);  //•	Write a method called writeToFile(ThingToWriteFile out)  which takes a ThingToWriteFile object as a parameter
				
			}
			
			out.close(); //carefullt closes file to prevent access from writer
		}
		
		/** Returns the index location of the country with the largest population currently in the catalogue.
		* @return int index
		*/
		public int findCountryWithLargestPop()
		{
			int index_largest = 0;
			int largest_pop = 0;
			
			for(int i = 0; i< numCountries; i++) 
			{
				if(catalogue[i].getPopulation()>largest_pop)
				{
					largest_pop = catalogue[i].getPopulation();
					index_largest = i;
				}
			}
			
			return index_largest;
		}
		
		/** Return the index location of the country with the smallest area currently in the catalogue. 
		* @return int index
		*/
		public int findCountryWithSmallestArea()
		{
			int index_smallest = 0;
			int smallest_pop = 0;
			
			for(int i = 0; i< numCountries; i++) 
			{
				if(catalogue[i].getPopulation()<smallest_pop)
				{
					smallest_pop = catalogue[i].getPopulation();
					index_smallest = i;
				}
			}
			
			return index_smallest;
		}
		
		/** Prints out details of all the countries whose population densities lie within a specified population density range. This method takes two integers specifying the population density range inclusively (low and high). 
		* @param int l_b (lower bound)
		* @param int u_b (upper bound)
		*/
		public void printCountriesFilterDensity(int l_b, int u_b)
		{
			for(int i = 0; i< numCountries; i++) 
			{
				if((catalogue[i].getPopDensity()>=l_b) && (catalogue[i].getPopDensity()<=u_b)) //if lies between range
				{
					System.out.println(catalogue[i].toString());	
				}
			}
		}
		
			
		/**  Prints out the continent, and the total population of the continent with the largest population (based only on the countries in the catalogue)
		*/
		public void findMostPopulousContinent()
		{
		   if(numCountries > 0)//if we have countries in catalogye
		   {
		    	
			String largest_continent = ""; //name of largest continent
			int largest_pop = 0; //size of largest population
			
			String curr_continent = "";
			int curr_pop = 0; //curr pop
			
			for(int i = 0; i< numCountries; i++) 
			{
				curr_continent = catalogue[i].getContinent(); //curr continent
				//iterate from the begining to check for countries of same continent (includes current country)
				for(int j = 0; j< numCountries; j++) 
				{
					if(catalogue[j].getContinent().equals(curr_continent)) //if continents matches
					{
						curr_pop += catalogue[j].getPopulation(); //increment continent pop by country's pop		
					}
				
				}
				
				//compares curr continent with curr largest continent
				if(curr_pop > largest_pop)
				{
					largest_pop = curr_pop; //if larger: replace it
					largest_continent = curr_continent;
				}
				
				//reset curr_pop
				curr_pop = 0;
				
			}
			
			//print results
			System.out.println("Most populous continent in the catalogue is "+ largest_continent+ ". It has a population of "+ largest_pop);
			}
		   else
		  System.out.println("No countries in the list");	
		}
		
		
		
		
		
		
}//end of CountryCatalogue
