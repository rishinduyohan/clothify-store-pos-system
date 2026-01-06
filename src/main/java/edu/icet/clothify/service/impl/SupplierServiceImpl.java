package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.dto.SupplierDTO;
import edu.icet.clothify.model.entity.Supplier;
import edu.icet.clothify.repository.SupplierRepository;
import edu.icet.clothify.repository.impl.SupplierRepositoryImpl;
import edu.icet.clothify.service.SupplierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    SupplierRepository supplierRepository = new SupplierRepositoryImpl();
    ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList();

    @Override
    public ObservableList<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.getSuppliers();
        for (Supplier supplier : suppliers) {
            supplierDTOS.add(
                    new SupplierDTO(
                            supplier.getSupplierId(),
                            supplier.getCompanyName(),
                            supplier.getContactPerson(),
                            supplier.getContactNumber(),
                            supplier.getItemCategory(),
                            supplier.getEmail()
                    )
            );
        }
        return supplierDTOS;
    }

    @Override
    public boolean addSupplier(SupplierDTO supplier) {
        Supplier newSupplier = new Supplier();
        newSupplier.setCompanyName(supplier.getCompanyName());
        newSupplier.setContactPerson(supplier.getContactPerson());
        newSupplier.setContactNumber(supplier.getContactNumber());
        newSupplier.setItemCategory(supplier.getItemCategory());
        newSupplier.setEmail(supplier.getEmail());
        return supplierRepository.addSupplier(newSupplier);
    }

    @Override
    public boolean updateSupplier(Long id,SupplierDTO supplier) {
        Supplier newSupplier = new Supplier();
        newSupplier.setSupplierId(id);
        newSupplier.setCompanyName(supplier.getCompanyName());
        newSupplier.setContactPerson(supplier.getContactPerson());
        newSupplier.setContactNumber(supplier.getContactNumber());
        newSupplier.setItemCategory(supplier.getItemCategory());
        newSupplier.setEmail(supplier.getEmail());
        return supplierRepository.updateSupplier(newSupplier);
    }

    @Override
    public boolean removeSupplier(Long id,SupplierDTO supplier) {
        Supplier newSupplier = new Supplier();
        newSupplier.setSupplierId(id);
        newSupplier.setCompanyName(supplier.getCompanyName());
        newSupplier.setContactPerson(supplier.getContactPerson());
        newSupplier.setContactNumber(supplier.getContactNumber());
        newSupplier.setItemCategory(supplier.getItemCategory());
        newSupplier.setEmail(supplier.getEmail());
        return supplierRepository.removeSupplier(newSupplier);
    }
}
