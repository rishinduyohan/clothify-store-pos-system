package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.dto.ProductDTO;
import edu.icet.clothify.model.entity.Product;
import edu.icet.clothify.repository.PosRepository;
import edu.icet.clothify.repository.impl.PosRepositoryImpl;
import edu.icet.clothify.service.PosService;

import java.util.ArrayList;
import java.util.List;

public class PosServiceImpl implements PosService {

    PosRepository posRepository = new PosRepositoryImpl();
    List<ProductDTO> list = new ArrayList<>();

    @Override
    public List<ProductDTO> getALlItems() {
        list.clear();
        if (posRepository.getAllItems()!=null) {
            for (Product product : posRepository.getAllItems()) {
                list.add(new ProductDTO(
                        product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImagePath(),
                        product.getPrice(),
                        product.getStockQuantity(),
                        product.getCategory(),
                        product.getSupplier()
                ));
            }
        }
        return list;
    }
}
