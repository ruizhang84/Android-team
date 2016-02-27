# Design Document for TCCart

Main Classes

  - **TCCart.**  Holds multiple customers and multiple transactions.  doPurchase() does everything required for a single purchase including creating a new Transaction.  Data from CCScanner is temporarily saved in CreditCard and feed into PaymentProcessing.
  - **Transaction.**  Data for single transaction, including information to process before and after discounts.  Holds a reference to the customer involved in the transaction.
  - **Customer.**  Data for single customer, including credit left if any and VIP date obtained in order to calculate any discount.  These rewards automatically updated with a new transaction.
  - **CreditCard.**  Temporary data structure used as output of CCScanner and input of PaymentProcessing.
  - **External Libraries.**  Every class starting with a "*" denotes an external library.  All of these are accessed by TCCart.

### Version
1.0

### Created Using

http://yuml.me/diagram/nofunky/class/draw

```
[note: * denotes an external library{bg:cornsilk}]
[note: Getter and setter actions not listed{bg:cornsilk}]

[CardPrinter*||+printCustomerCard()]
[VideoCamera*||+readQRCode()]
[CCScanner*||+readCC()]
[PaymentProcessing*||+processCCTransaction()]
[SMTP*||+sendMail()]

[CreditCard|-name: String;-accountNum: Long;-expDate: Date;-ccv: Int|]
[Transaction|-id: Long;-Items: ArrayOf Item;-custID: Long;-date: Date;-totalAmount: Money;-vipDiscount: Double;-creditApplied: Money|]
[Item|-name: String;-price: Money;-description: String|]
[Customer|-name: String;-email: String;-id: Long;-credit: Money;-vipStatusDate: Date|+editInfo();]
[TCCart|-Customers: ArrayOf Customer;-Transactions: ArrayOf Transaction|+addCustomer();+doPurchase();+showTransactions()]

[TCCart]<>-*[Customer]
[TCCart]<>-*[Transaction]
[Transaction]->1[Customer]
[Transaction]<>-*[Item]
[TCCart]-.->[CreditCard]
[TCCart]-.->[CardPrinter*]
[TCCart]-.->[VideoCamera*]
[TCCart]-.->[CCScanner*]
[TCCart]-.->[PaymentProcessing*]
[TCCart]-.->[SMTP*]
```

