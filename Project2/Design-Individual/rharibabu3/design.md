


**Design Document Template**

**1. Introduction**

Purpose of this document is to create a System for Tea and Coffee Cart Payment & Rewards Management. The Manager will use this System to 1) add customers, (2) print customer cards, (3) edit customer information, (4) process purchases, and (5) keep track of purchases and rewards.

**2. Design Assumptions**

1.	The System has been designed in a way such that it’s managed entirely by the “Cart Manager” with the help of a “Utility Manager”.
2.	*Cart and Utility Manager:*

•	Create Transaction () – method will check if the transaction is done via Credit Card
•	Process Transaction () – method will use the Utility Manager to call external library (Credit card reader and Payment System). i.e., It will take care of getting the initial Card Details via Credit card reader ((i)the cardholder’s name, (ii) the card’s account number, (iii) the card's expiration date, and (iv) the card's security code and enables the Payment Processing system and to send emails.
•	DiscountEvaluationAndCreditCheck() – method will apply the business logic to perform discount evaluation and existing credit check(mentioned in point no 11 in the Requirements Doc)
•	ApplyRewards() –method will apply discount(mentioned in point no 13 in the Requirements Doc) based on the VIP Status
•	RewardsAndCreditHistory()  – method will maintain the Credit/Reward history of the Customer
•	DisplayTransactionDetails() – method will
3.	*Customer Class*: Customer class includes basic details such as Name, Email id and Unique Hexadecimal id (Customer id). It also includes specific details needed for calculating discount/reward points at any point of time, such as Yearly Expenditure, VIP Status, Card Expiry Date, Existing Credit, and Last Transaction Date.

**Appendix:**

1.Item Id – Id for items ordered (Tea/Coffee)

2.Purchase Id –Id for every purchase made by the Customer

3.Transaction Id-Id for every Credit Card transaction made by the Customer

