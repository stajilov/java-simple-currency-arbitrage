package arbitrage;

public class DataLoaderFactory {
	
	public DataLoader getDataLoader(String loderType){
	      if(loderType == null){
	         return null;
	      }		
	      if(loderType.equalsIgnoreCase("web")){
	         return new WebServiceDataLoader(loderType);
	         
	      } else if(loderType.equalsIgnoreCase("csv")){
	         return new CsvDataLoader();
	         
	      } else if(loderType.equalsIgnoreCase("localhtml")){
	         return new WebServiceDataLoader(loderType);
	      }
	      
	      return null;
	   }

}
