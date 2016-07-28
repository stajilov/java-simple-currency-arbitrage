package arbitrage;


public class Main {

	public static void main(String[] args) {
		String loadFrom = null;
		try {
	        loadFrom = args[0];
	        
	    }
	    catch (ArrayIndexOutOfBoundsException e){
	        System.out.println("Loading from web by default");
	    }

		if (loadFrom == null) {		
			loadFrom = "web";
		}

		DataLoaderFactory dataLoaderFactory = new DataLoaderFactory();		
		DataLoader dataLoader = dataLoaderFactory.getDataLoader(loadFrom);
		dataLoader.loadDataFromSource();
		DataProcessor dataProcessor = new DataProcessor();
		dataProcessor.processList(dataLoader.getItems());
		System.out.println("***Session start***");
		dataProcessor.showProcessedData();
		System.out.println("***Session end***");
		System.exit(1);
	}

}
