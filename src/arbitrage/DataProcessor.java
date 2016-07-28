package arbitrage;
import static ch.lambdaj.Lambda.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matchers;

public class DataProcessor {
	
	ArrayList<ArbitrageItem> arbitrageItems = new ArrayList<ArbitrageItem>();
	
	public void processList(ArrayList<Item> items) {
		
		Collection<String> currencies = this.extractCurrencies(items);				
				//(LinkedList<Item>) filter(having(on(Item.class).getOperation(), Matchers.equalTo("B")), items);
		for(String c: currencies) {
			List<Item> buyItems = this.filterItemsByCurrencyAndOperation(c, "B", items);
			List<Item> sellItems = this.filterItemsByCurrencyAndOperation(c, "S", items);
			this.createArbitrageChart(buyItems, sellItems, c);
			
		}
		
		this.sortArbitrageItems();
	}
	
	private Collection<String> extractCurrencies(ArrayList<Item> items) {
		return selectDistinct(collect(items, on(Item.class).getCurrency()));
	}
	
	private List<Item> filterItemsByCurrencyAndOperation(String currencyName, 
			String operationName, ArrayList<Item> items)
	{
		return select(select(items, having(on(Item.class).getCurrency(), Matchers.equalTo(currencyName))), 
				having(on(Item.class).getOperation(), Matchers.equalTo(operationName)));
	}
	
	private void createArbitrageChart(List<Item> buyItems, List<Item> sellItems, String currencyName) {
		
		for(Item bItem: buyItems) {
			for (Item sItem: sellItems) {
				
				float ratio = (float) ((bItem.getAmount()/(sItem.getAmount())) - 0.001);
				double gainOnUnit = bItem.getAmount() - sItem.getAmount();
				
				ArbitrageItem arbitrageItem = ArbitrageItem.newBuilder()
						.setRatio(ratio)
						.setCurrencyName(currencyName)
						.setBuyFromBank(sItem.getBank())
						.setBuyFromBankPrice(sItem.getAmount())
						.setSellToBank(bItem.getBank())
						.setSellToBankPrice(bItem.getAmount())
						.setWinOnUnit(gainOnUnit)
						.build();
				
				arbitrageItems.add(arbitrageItem);
				
			}
		}
	}
	
	private void sortArbitrageItems() {
		List<ArbitrageItem> sorted = sort(arbitrageItems, on(ArbitrageItem.class).getRatio(), DESCENDING);
		arbitrageItems = null;
		arbitrageItems = new ArrayList<ArbitrageItem>(sorted);
	}
	
	public void showProcessedData() {
		
		if(this.arbitrageItems.size() == 0) {
			System.out.println("Service in unavailable or the source is empty");
			return;
		}
		
		System.out.printf("Number of objects: %s\n", 
				Integer.toString(this.arbitrageItems.size()));
		
		System.out.println("Currency\tRatioBuy From bank and price\t Sell To bank and price\t Gain on unit");
		for(ArbitrageItem a: this.arbitrageItems)
		{
			a.displayContents();
		}
	}
	

}
