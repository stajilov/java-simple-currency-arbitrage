package arbitrage;

public class Item {
	
	
	private String bank;
	private String currency;
	private String operation;
	private double amount;
	

	public double getAmount() {
		return amount;
	}

	
	public String getOperation() {
		
		return operation;
	}
	

	public String getCurrency() {
		return currency;
	}


	public String getBank() {
		return bank;
	}
	//method to call this elegant builder
	public static Builder newBuilder() {
        return new Item().new Builder();
    }
	
	public void displayContents()
	{
		System.out.printf("%s %s %s %.2f\n", this.currency, this.bank, 
				this.operation,this.amount);
	}

	
	//Elegant builder for item
	public class Builder
	{
		private Builder() {
           
        }
		
		public Builder setAmount(double amount) {
			Item.this.amount = amount;
			return this;
		}
		
		public Builder setCurrency(String currency) {
			Item.this.currency = currency;
			return this;
		}
		public Builder setBank(String bank) {
			Item.this.bank = bank;
			return this;
		}
		
		public Builder setOperation(String operation) {
			Item.this.operation = operation;
			return this;
		}
		
		public Item build() {
            return Item.this;
        }
	}
	
}
