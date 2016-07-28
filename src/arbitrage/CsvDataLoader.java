package arbitrage;

import java.io.BufferedReader;
import java.io.FileReader;


public class CsvDataLoader extends DataLoader {

	
	//Front method
	public void loadDataFromSource() {
		this.readFromSource(",", "rt.csv");
	}
	
	//Reads from the file itself
	private void readFromSource(String splitBy, String fileName) {
		
		try {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
             String[] b = line.split(splitBy);
             
             Item item = Item.newBuilder()
                     .setCurrency(b[1])
                     .setBank(b[2])
                     .setOperation(b[3])
                     .setAmount(Double.parseDouble(b[4]))
                     .build();
             this.loadedItems.add(item);
       
        }
        br.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
