package edu.icet.ecom.service.impl;

import edu.icet.ecom.model.dto.ItemDTO;
import edu.icet.ecom.repository.PosRepository;
import edu.icet.ecom.repository.impl.PosRepositoryImpl;
import edu.icet.ecom.service.PosService;

import java.util.List;

public class PosServiceImpl implements PosService {

    PosRepository repository = new PosRepositoryImpl();

    @Override
    public List<ItemDTO> getALlItems() {
        return null;
    }
}
