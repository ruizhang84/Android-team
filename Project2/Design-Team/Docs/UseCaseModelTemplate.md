# Use Case Model

**Author**: 6300Spring16Team54 

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |

## 1 Use Case Diagram
![enter image description here](https://lh3.googleusercontent.com/RGePiUB6uQuF_ICIGJcEfUMnJLbeZomxXwVrJ_IKoIaN9HueznFAl5bxd2xG1k2Q664=s0 "TCCart Use Case Diagram - Dark.png")

![enter image description here](https://lh3.googleusercontent.com/2rhlhLCWNVeUMQ9qI_esIZLFEgpEz8hTN-C-X-Q0tGhU0NbtmQ4W6VD4M3PYtVWb_1E=s0 "External Library - Sandstorm.png")
![enter image description here](https://lh3.googleusercontent.com/QlamoCcggad6xMFo6ov3BvgICVDkFVI429svhMD82LR3-OskDyBRTf1FqgIfWPSQr28=s0 "Transaction - Standard.png")

## 2 Use Case Descriptions

 'TC Cart Manager' actor uses the app TCCart, to manage a Payment and Rewards System which sells Tea and Coffee. Top level use cases are Hardware devices and Transaction. Transaction use case could be used as a part of 'Make Purchase' use case. It allows the Customer to pay for the purchase he/she has made through Payment Processing system.Hardware devices use case allows the system to connect to various hardware devices such as Card Printer, Video Camera, Credit Card Scanner, enable the system to connect-to Payment Processing System and send email whenever necessary, for example sending mail to the Customer after every Purchase is made. 'Apply Discount& Rewards' use case is extended use case not available by itself as it's part of making purchase.

Except for the 'TC Cart Manager' actor there are several other actors which will be described below with detailed use cases. 

**Hardware Devices**

*Requirements:*

All the hardware devices attached to the system such as card printer, videocam, and credit card scanner can be accessed through existing external libraries. It enables the system to connect-to Payment Processing System and send emails.

  *Pre-conditions:*
 

 - New Customer should be added by 'TCCartManager',
 -  Customer Card should contain a QR Code, 
 - Customer should swipe Credit Card, 
 - Transaction must have been initiated

*Post-conditions*

-Unique id should be generated and Customer card should be printed using a special card printer as soon as a new Customer has been added by the 'TCCartManager'.
-customer card contains a QR code which should be read using a videocam attached to the system and encodes the customer’s unique ID.
-As soon as the Customer swipes the Credit Card it should be read by a Credit Card Scanner which ensures Authentication.
-The external libraries connected to the system should enable it to connect to the Payment Processing system once transaction has been initiated and send emails.

**Transaction**

*Requiremens*

Once the Customer makes Purchase and swipes his Credit Card, the transaction has been initiated which allows the system to authenticate the credit card used by the Customer, Calculate Credit, apply Discounts & Rewards for the Purchase being made, enables Payment through Credit Card service provider and send email after every purchase.

  *Pre-conditions:*
-Customer should have swiped Credit Card for the transaction to be initiated

  *Post-conditions:*

-Credit Card Authentication is done after which Credit and Discounts are applied for the current purchase and enables the Customer to Pay through Credit card service provider and send email after the purchase is complete.

