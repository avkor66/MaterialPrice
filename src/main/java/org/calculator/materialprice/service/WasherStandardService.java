package org.calculator.materialprice.service;

import org.calculator.materialprice.domain.CatalogWasherStandards;
import org.calculator.materialprice.repository.WasherStandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WasherStandardService {
    private final WasherStandardRepository repository;

    @Autowired
    public WasherStandardService(WasherStandardRepository washerStandardRepository) {
        this.repository = washerStandardRepository;
    }

    public CatalogWasherStandards createWasherStandard(CatalogWasherStandards washerStandard) {
        return repository.save(washerStandard);
    }

    public CatalogWasherStandards findWasherStandardById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public CatalogWasherStandards getWasherStandard(String washerStandard) {
        return repository.findByStandard(washerStandard);
    }
    public List<CatalogWasherStandards> getWasherStandards() {
        return repository.findAll();
    }

}
