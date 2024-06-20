package com.pharmacy_management.DataAccessObject.Drug;
import java.util.List;

import com.pharmacy_management.Data.Drug;

public interface DrugDAOInterface {
    void addDrug(Drug drug);
    Drug getDrugByCode(String drugCode);
    List<Drug> getAllDrugs();
    void updateDrug(Drug drug);
    void deleteDrug(String drugCode);
}
