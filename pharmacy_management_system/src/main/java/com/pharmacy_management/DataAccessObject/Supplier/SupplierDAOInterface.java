package com.pharmacy_management.DataAccessObject.Supplier;
import java.util.List;

import com.pharmacy_management.Data.Supplier;


public interface SupplierDAOInterface {
    void addSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(String supplierId);
    Supplier getSupplierById(String supplierId);
    List<Supplier> getAllSuppliers();
}
