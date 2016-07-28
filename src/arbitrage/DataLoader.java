package arbitrage;

import java.util.ArrayList;

public abstract class DataLoader {

	ArrayList<Item> loadedItems = new ArrayList<Item>();
	abstract public void loadDataFromSource();
	
	public void showLoadedData() {
		
		if(this.loadedItems.size() == 0) {
			System.out.println("Service in unavailable or the source is empty");
			return;
		}
		
		System.out.println("Currency\t Bank\t Operation\t Amount");
		for(Item i: this.loadedItems)
		{
			i.displayContents();
		}
	}
	
	public ArrayList<Item> getItems() {
		return loadedItems;
	}
}
