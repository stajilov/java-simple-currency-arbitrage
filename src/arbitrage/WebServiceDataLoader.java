package arbitrage;


import java.io.File;
import java.io.IOException;
  
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
  

public class WebServiceDataLoader extends DataLoader {

	String source = null;
	public WebServiceDataLoader(String source) {
		this.source = source;
	}
	
	public void loadDataFromSource() {
		// TODO Auto-generated method stub
		this.loadPageContents();
		
	}
	
	private void loadPageContents() {
		
		Document htmlDoc = null;		
        try {
        	if(this.source.equalsIgnoreCase("localhtml")) {
        		htmlDoc = Jsoup.parse(new File("sample.html"), "UTF-8");
        	} else {
        		htmlDoc = Jsoup.connect("http://www.curs.md/ru/curs_valutar_banci").get();
        	}
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.parseHtmlDocument(htmlDoc);

	}
	
	
	private void parseHtmlDocument(Document htmlDoc) {
		
		Element table = htmlDoc.getElementById("tabelBankValute");
        Elements trs = table.getElementsByTag("tr");

        for (Element tr : trs) {
          
            Elements tdTags = tr.getElementsByTag("td");           
            String bankName = null;
            int iteration = 0;
            for (Element td : tdTags) {
                String currency = null;
            	String operation = null;

            	//Avoiding header
            	if(!td.hasAttr("colspan")) {
            		
            		if(td.className().equalsIgnoreCase("bank_name")) {
            			bankName = td.text();
            			
            			if(bankName.equalsIgnoreCase("Banca Nationala")) {

            				break;
            			}
            		}
            		
            		if(td.className().startsWith("col-")) {
            			
            			currency = td.className().split("\\-")[1];
            			
            			//Extract operation name based on order
            			operation = ((iteration & 1) == 0) ? "B" : "S";
            			iteration++;            			
            			
            			//if value present, create an item
            			if(!td.text().equalsIgnoreCase("-")) {
            				Item i = this.createItem(bankName, currency, 
            						operation, td.text());

            				this.loadedItems.add(i);
            			}
            			
            		}	
            	}
            }  	
        }
		
	}			
	
	
	private Item createItem(String bankName, String currencyName, String operationName, String value) {
		
		return Item.newBuilder()
                .setCurrency(currencyName)
                .setBank(bankName)
                .setOperation(operationName)
                .setAmount(Double.parseDouble(value.replace(',','.')))
                .build();
		
	}
	

}
