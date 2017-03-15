
public class TestCountry {

	public static void main(String[] args) {
		final int FRANCE_POPULATION = 100000;
		final double FRANCE_AREA = 20000.00;
		
		System.out.println("Tests for class Country");
		System.out.println("=======================");
		Country cntry = new Country ("France",FRANCE_POPULATION,FRANCE_AREA,"Asia");
		
		String name = cntry.getName();
		Boolean test = true;
		if (!name.equals("France")) test = false;
		if (cntry.getPopulation() != FRANCE_POPULATION) test = false;
		if (cntry.getArea() != FRANCE_AREA) test = false;
		if (!cntry.getContinent().equals("Asia")) test = false;
		if (cntry.getPopDensity() != FRANCE_POPULATION/FRANCE_AREA) test = false;
		cntry.setPopulation(50000);
		if (cntry.getPopulation() != 50000) test = false;
		cntry.setContinent("Europe");
		if (!cntry.getContinent().equals("Europe")) test = false;

		if (test) System.out.println ("Test 1 passed");
		else System.out.println("Test 1 failed");

		test = true;
		ThingToWriteFile ofile = new ThingToWriteFile("file1");
		cntry.writeToFile(ofile);
		ofile.close();
		
		ThingToReadFile ifile = new ThingToReadFile("file1");
		if (ifile.endOfFile()) test = false;
		String line = ifile.readLine();
		if (!line.contains("France") || !line.contains("Europe") || !line.contains("50000") ||
				!line.contains("2.5")) test = false;
		
		if (test) System.out.println ("Test 2 passed");
		else System.out.println("Test 2 failed");	
		
		test = true;
		line = cntry.toString();
		if (!line.contains("France") || !line.contains("Europe")) test = false;
		if (test == false) System.out.println("Test 3 failed");
		else {
			System.out.println("For Test 3 check that the following two lines are the same:");
			System.out.println("France is located in Europe has a population of 50000, an area of 20000.0, and has a population density of 2.5");
			cntry.printCountryDetails();
		}
		
	}

}
