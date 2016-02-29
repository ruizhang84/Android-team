#Desgin Document

##Retionale
I first created three classes **Store**, **Customer**, and **Transaction**, since a purchase was occured between customers and the store (i.e. the store sell coffee and tea to customers).
- The **store** keep a list of customer info and transaction history. 
- The **customer** has name, email, and id info. 
- The **transaction** has info about purchase price and credit/discount.

In addition, we need two additional classes **CreditCard** and **Item** to store credit card and selling item information.

##Note
- The store has a *decodeQR* operation to get custom ID. Then custom ID can be used in *sell* operation to complete a transaction. (In case that a transaction is not sucessed, I can return a special transaction that will not be added in transactionHistory).
- The customer has a *sendEmail* operation to send email to customer to notice their credit or vip status. 
- The *sell* operation first create a transaction instance, which get the vipStatus and credit remain from Customer class with known id. The transaction instance has *checkItems* method that check item price and *processCredit* method that check if credit card can be processed. Once *processCredit* return a True value (which also means a payment been made), we will update Customer's sepending, credit and vip status.
