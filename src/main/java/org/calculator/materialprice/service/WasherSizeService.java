package org.calculator.materialprice.service;

import org.calculator.materialprice.domain.CatalogWasherSizes;
import org.calculator.materialprice.repository.WasherSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WasherSizeService {
    private final WasherSizeRepository repository;

    public WasherSizeService(WasherSizeRepository repository) {
        this.repository = repository;
    }

    public CatalogWasherSizes createWasherSize(CatalogWasherSizes catalogWasherSizes) {
        return repository.save(catalogWasherSizes);
    }

    public CatalogWasherSizes findWasherSizeById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public CatalogWasherSizes getWasherSize(String size) {
        return repository.findBySize(size);
    }

    public List<CatalogWasherSizes> getAllWasherSizes() {
        return repository.findAll();
    }
}
