package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.Supplier;

import java.util.List;

public interface SupplierRepository {
    List<Supplier> getSuppliers();
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean removeSupplier(Supplier supplier);
}
