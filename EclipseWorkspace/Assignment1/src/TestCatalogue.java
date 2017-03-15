
public class TestCatalogue {

	public static void main(String[] args) {
		final int NUM_COUNTRIES = 162;
		final int NEW_POPULATION = 300;
		final int LARGEST_POPULATION = 322;
		final int NOT_FOUND = -1;

		boolean test;
		String testCountry1 = "Country50";
		String testCountry2 = "Country100";
		String testCountry3 = "Country60";
		int indexCountry1 = 50;
		int indexCountry3 = 60;

		CountryCatalogue catalogue = new CountryCatalogue();

		System.out.println("Tests for class CountryCatalogue");
		System.out.println("================================");
		test = true;
		for (int i = 0; i < NUM_COUNTRIES; ++i)
			catalogue.addCountry(new Country("Country" + i, 2 * i, (double) i, "Continent" + (i / 40)));

		if (catalogue.searchCatalogue(testCountry1) != indexCountry1)
			test = false;
		Country cntry = catalogue.getCountry(indexCountry1);
		if (!cntry.getName().equals(testCountry1))
			test = false;

		if (test)
			System.out.println("Test 1 passed");
		else
			System.out.println("Test 1 failed");

		if (testSaveCatalogue(catalogue, NUM_COUNTRIES))
			System.out.println("\nTest 2 passed");
		else
			System.out.println("\nTest 2 failed");

		test = true;
		catalogue.removeCountry(testCountry2);
		if (catalogue.searchCatalogue(testCountry2) != NOT_FOUND)
			test = false;
		catalogue.setPopulationOfACountry(testCountry3, NEW_POPULATION);
		cntry = catalogue.getCountry(indexCountry3);
		if (cntry.getPopulation() != NEW_POPULATION)
			test = false;
		int index = catalogue.findCountryWithLargestPop();
		if (index < 0)
			test = false;
		else {
			cntry = catalogue.getCountry(index);
			if (cntry.getPopulation() != LARGEST_POPULATION)
				test = false;
		}
		index = catalogue.findCountryWithSmallestArea();
		if (index != 0)
			test = false;

		if (test)
			System.out.println("\nTest 3 passed");
		else
			System.out.println("\nTest 3 failed");

		System.out.println("\nTest 4: to pass check that the following two lines are the same");
		catalogue.findMostPopulousContinent();
		System.out.println("Continent with the largest population: Continent3, with population 11160");

		System.out.println("\nTest 5: to pass check that below countries \"Country" + (NUM_COUNTRIES - 2)
				+ "\" and \"Country" + (NUM_COUNTRIES - 1) + "\" are printed:");
		catalogue.filterCountriesByContinent("Continent4");

		catalogue.addCountry(new Country("Country162", 1000, 1000, "Continent5"));
		catalogue.addCountry(new Country("Country163", 500, 1000, "Continent5"));
		System.out.println("\nTest 6: to pass check that below countries \"Country" + NUM_COUNTRIES + "\" and \"Country"
				+ (NUM_COUNTRIES + 1) + "\" are printed");
		catalogue.printCountriesFilterDensity(0, 1);

		catalogue = new CountryCatalogue();
		catalogue.addCountry(new Country("Japan", 127380000, 377835.00, "Asia"));
		catalogue.addCountry(new Country("Canada", 34207000, 9976140.00, "America"));
		System.out.println("\nTest 7: to pass check that below countries \"Canada\" and \"Japan\" are printed");
		catalogue.printCountryCatalogue();
	}

	/*
	 * Save the catalogue to a file and verify that all the countries in the
	 * catalogue were stored in the file.
	 */
	private static boolean testSaveCatalogue(CountryCatalogue catalogue, int NUM_COUNTRIES) {
		catalogue.saveCountryCatalogue("file2");
		ThingToReadFile ifile = new ThingToReadFile("file2");
		String line;
		for (int i = 0; i < NUM_COUNTRIES; ++i) {
			if (!ifile.endOfFile()) {
				line = ifile.readLine();
				if (!line.contains("Country" + i))
					return false;
			} else
				return false;
		}
		return true;
	}

}
