#Design Document

*Payment and Rewards Management System for Tea and Coffee Carts*

##Assumptions

- The design assumes that the system can obtain the correct date and time information
- The design assumes that there is an mechanism to compare dates that are represented by a string in a certain format
- The design assumes that there is enough memory space to save all the customer info and transaction history

##Design Rationales

- A seperate Manager class is designed, even though it is likely that there will be only one instance
- A seperate Reward class is designed insteading of representing rewards as attribute of Customer, since rewards have expireation date and thus need to treated differently
- The CustomerCard class is seperated from the Customer class to allow the flexibility that in future there may be need to re-use customer card information, multiple customer cards (and multiple QRcodes) assigned to a single customer
- A seperate Transaction class is designed to track all the details regarding a purchase and to be conviniently archived
- A seperate CreditCard class is designed to save credit card information read by the credit card scanner and then passed to the payment-processing service provider


