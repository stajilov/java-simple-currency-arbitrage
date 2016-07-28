# java-simple-currency-arbitrage
This is a simple currency arbitrage software in Java

#Description
The workflow is the following:
1. Load data from the source supplied as an argument from the command line: web, csv, localhtml. The default is web, but for this purpose you’ll have to rewrite your own WebServiceDataLoader.
2.Data Processor will search and order the best rates and therefore you’ll be able to effect operations with currency and gain margin.

The code is self explanatory.

This project has 2 dependencies: jsoup and lambdaj.