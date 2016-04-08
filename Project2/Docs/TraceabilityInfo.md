# Traceability Info

**Author**: 6300Spring16Team54 

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |
| V2      | Updated traceability information table |


**Use Case Tracking Table:**

|  User Case | Design Element | Corresponding Code | Test Cases | Test Case Description |
| :---------------|:---------------:|:---------------:|:---------------:|:---------------:|
| Add Customer | Customer | AddCustomerActivity.java, handleAdd()| TC001_Add_Customer to TC006_Add_Customer | To verify whether the TC Cart Manager is able to add new Customers and 8-digit unique ID gets generated automatically after the Customer is added and a card is printed. |
| Edit Customer info | Customer |EditCustomerInfoActivity.java,  handleConfirm() | TC009_Edit_Customer_Info to TC015_Edit_Customer_Info| To verify whether the TC Cart Manager is able to Edit the Customer's info which already exists and newly modified details get updated to the System. |
| Make Purchase | Transaction | MakePurchaseActivity.java, handleScanCreditCard() | TC_Trasaction | To verify whether once the Customer makes Purchase a transaction has been initiated,Customer's credit card has been scanned for Authentication purpose. |
| Make Purchase | Transaction | MakePurchaseActivity.java, handleApplyRewardDiscount | TC_Trasaction | To verify whether once the Customer makes Purchase a transaction has been initiated, Credit is calculated and Discounts/Rewards have been applied. |
| View Transaction History | Transaction_info | ViewTransactionActivity.java,  onCreate() | To verify whether at any particular point in time, the TC Cart manager should be able to view, a complete list of Customer transactions. |

