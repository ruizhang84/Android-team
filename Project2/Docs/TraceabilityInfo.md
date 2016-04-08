# Traceability Info

**Author**: 6300Spring16Team54 

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |
| V2      | Updated traceability information table |


**Use Case Tracking Table:**

|  User Case | Design Element | Corresponding Code | Test Cases |
| :---------------|:---------------:|:---------------:|:---------------:|
| Add Customer | Customer | AddCustomerActivity.java: onCreate()| TC001_Add_Customer |
| Add Customer | Customer | AddCustomerActivity.java: handleAdd()| TC002_Add_Customer to TC004_Add_Customer |
| Add Customer | Customer | AddCustomerActivity.java: handleClear(), handleCancel() | TC005_Add_Customer |
| Add Customer | Customer_info | CustomerDBHandler.java | TC006_Add_Customer |
| Scan Customer Card | Customer | MainActivity.java: handleScanCard()| TC007_Scan_Customer_Card to TC008_Scan_Customer_Card |
| Edit Customer info | Customer | EditCustomerInfoActivity.java: onCreate() | TC009_Edit_Customer_Info to TC010_Edit_Customer_Info|
| Edit Customer info | Customer | EditCustomerInfoActivity.java: handleConfirm() | TC010_Edit_Customer_Info to TC012_Edit_Customer_Info|
| Edit Customer info | Customer | EditCustomerInfoActivity.java: handleCancel(), handleClearSelected(), handleClearField() | TC013_Edit_Customer_Info|
| Edit Customer info | Customer_info | CustomerDBHandler.java | TC014_Edit_Customer_Info |
| Edit Customer info | Customer | EditCustomerInfoActivity.java: handleDelete() | TC015_Edit_Customer_Info |
| Make Purchase | Transaction | MakePurchaseActivity.java:onCreate() | TC018_Make_Purchase , TC035_Make_Purchase|
| Make Purchase | Transaction | MakePurchaseActivity.java: handleScanCreditCard() | TC019_Make_Purchase to TC024_Make_Purchase |
| Make Purchase | Transaction | MakePurchaseActivity.java: handleApplyRewardDiscount() | TC025_Make_Purchase to TC030_Make_Purchase | 
| Make Purchase | Transaction | MakePurchaseActivity.java: handleConfirm() | TC031_Make_Purchase to TC033_Make_Purchase |
| Make Purchase | Transaction | MakePurchaseActivity.java: handleClear(), handleCancel() | TC034_Make_Purchase |
| Make Purchase | Transaction | CustomerDBHandler.java, MakePurchaseActivity.java: handleConfirm() | TC037_Make_Purchase to TC039_Make_Purchase |
| View Transaction History | Transaction_info | ViewTransactionActivity.java,  onCreate() | TC040_View_Transaction_History | 

