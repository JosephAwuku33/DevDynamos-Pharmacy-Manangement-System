package com.pharmacy_management.DataAccessObject.Purchase;
import java.util.Date;
import java.util.List;

import com.pharmacy_management.Data.Purchase;

public interface PurchaseDAOInterface {
    void addPurchase(Purchase purchase);
    Purchase getPurchaseById(String purchaseId);
    List<Purchase> getAllPurchases();
    List<Purchase> getPurchasesByDate(Date date);
    List<Purchase> getPurchasesByCustomer(String customerId);
    void updatePurchase(Purchase purchase);
    void deletePurchase(String purchaseId);
}
