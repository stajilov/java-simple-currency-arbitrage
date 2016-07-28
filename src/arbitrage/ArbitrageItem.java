package arbitrage;


public class ArbitrageItem {
	
	
	private String currencyName;
	private float ratio;
	private String buyFromBank;
	private double buyFromBankPrice;
	private String sellToBank;
	private double sellToBankPrice;
	private double winOnUnit;
	

	public String getCurrencyName()
	{
		return currencyName;
	}
	
	public float getRatio() {
		return ratio;
	}
	
	public String getBuyFromBank() {
		return buyFromBank;
	}

	public double getBuyFromBankPrice() {
		return buyFromBankPrice;
	}
	
	public String getSellToBank() {
		return sellToBank;
	}
	
	public double getSellToBankPrice() {
		return sellToBankPrice;
	}
	
	public double getWinOnUnit() {
		return winOnUnit;
	}

	
	//method to call this elegant builder
	public static Builder newBuilder() {
	    return new ArbitrageItem().new Builder();
	}
		
	public void displayContents()
	{
		System.out.printf("%s %.5f %s %.2f %s %.2f %.3f\n", this.currencyName, this.ratio, 
				this.buyFromBank,this.buyFromBankPrice, this.sellToBank, this.sellToBankPrice, this.getWinOnUnit());
	}
	
	//Elegant builder for arbitrage item
		public class Builder {
			
			private Builder() {
	           
	        }
			
			public Builder setCurrencyName(String currencyName) {
				ArbitrageItem.this.currencyName = currencyName;
				return this;
			}
			
			public Builder setRatio(float ratio) {
				ArbitrageItem.this.ratio = ratio;
				return this;
			}
			
			public Builder setBuyFromBank(String buyFromBank) {
				ArbitrageItem.this.buyFromBank = buyFromBank;
				return this;
			}
			
			public Builder setBuyFromBankPrice(double buyFromBankPrice) {
				ArbitrageItem.this.buyFromBankPrice = buyFromBankPrice;
				return this;
			}
			
			public Builder setSellToBank(String sellToBank) {
				ArbitrageItem.this.sellToBank = sellToBank;
				return this;
			}
			
			public Builder setSellToBankPrice(double sellToBankPrice) {
				ArbitrageItem.this.sellToBankPrice = sellToBankPrice;
				return this;
			}
			
			public Builder setWinOnUnit(double winOnUnit) {
				ArbitrageItem.this.winOnUnit = winOnUnit;
				return this;
			}
			
			public ArbitrageItem build() {
	            return ArbitrageItem.this;
	        }
		}

		
	

}
