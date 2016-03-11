# Vision Document

**Author**: 6300Spring16Team54

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |


## 1 Introduction

The "TCCart" project aims to develope a payment and rewards system for coffee and tea carts. The project will be developed for the Android platform.

## 2 Business Needs/Requirements

We are very glad to provide the mobile software TCCart required by Brad and Janet in a timely manner to help their entrepreneurship and bussiness grow. In general, they are requesting us to develop TCCart so that they can (1) allow their customers to pay on credit card and (2) reward their most loyal customers. A detailed list of requirements for TCCart are listed below. 

 - The cart manager is able to use the TCCart to (1) add new customers, (2) print and scan customer's ID card, (3) edit customer info, (4) scan credit card and process purchases, and (5) track purchases and rewards. 

 - The cart manager is also able to keep track of a complete list of transactions for any customer in the system.

 - The TCCart will send an email receipt to a customer every time he/she completes a purchase.

 - For each valid transaction, if a customer spends $30 or more, he/she gets a $3 credit towards his/her next purchase as a reward. The following rules also apply:

    - The reward credit expires in a month.

    - The credit is applied towards the very next purchase.

    - The purchase amount is computed after any existing discount/credit is applied.

    - The TCCart will send an email to a customer when he/she gets a credit.

 - Customers can be qualified for a VIP status, if he/she spend $300 or more in a single calendar year. The TCCart will allow them to get 10% discount on every purchase for the following year, effective from January 1. The system will send an email to a customer whenever he/she achieves VIP status.


## 3 Product / Solution Overview

We will develop an Android app (TCCart) as **product** with appropriate interface to the managers, allow their customers to pay on credit card and reward the purchase. Logically, our product should contains three essential parts, 1) GUI interface, 2) customer info (& history), 3) transaction processing. In addition to normal transaction/purchase, the customer need to be award with credit and may pay at a 10% discount. It worths mention that the Android utility softwares that provide necessary (1) customer-card printing, (2) QR code scanning, (3) credit card scanning, (4) payment processing, and (5) email sending capabilities are courteously provide by our contractor, Brad and Janet, and used as-is.


TCCart app will have three major classes TCCart (referred as TC), Transaction, and Customer. TC will handle creating customer and initializing transaction (instances) and maintains lists of Customer and Transaction. Customer handles the customer information and reward/vip status. The important transaction/purhcase processing is handled by Transaction, which also handle the discount/credit on purhcase. To assist the purhcase and other functinality, we will make additional classes including Item (for tea and coffee), CreditCard (for credit-card), and RewardsAndDiscount (for query/review).

(The details of solution can be found on DesignDocument.md.)


## 4 Major Features

We expect the major features of our product, TCCart, will be user-friendly, simple, very reliable, and resposive. Some noteworthy features are:

- Create customers info and print cusotmer card with QR code, etc.

- The purchase price can be calcualted with credit & discount for each transaction. 

- Send email notification to customer when any item purchased, credit rewarded, or VIP status changed. 

- View customer info and their transaction history at any time.

## 5 Scope and Limitations

- All payments are considered to be proceeded via credit card. No cash payments are allowed.

- Customers are entitled to a 10% discount on every purchase for the following year not the time they achieved (i.e, the change of status is effective from January 1 of the following year) . 

