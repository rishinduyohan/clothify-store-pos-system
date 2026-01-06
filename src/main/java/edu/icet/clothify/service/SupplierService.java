package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.SupplierDTO;
import edu.icet.clothify.model.entity.Supplier;
import javafx.collections.ObservableList;

public interface SupplierService {
    ObservableList<SupplierDTO> getAllSuppliers();
    boolean addSupplier(SupplierDTO supplier);
    boolean updateSupplier(Long id,SupplierDTO supplier);
    boolean removeSupplier(Long id,SupplierDTO supplier);
}
