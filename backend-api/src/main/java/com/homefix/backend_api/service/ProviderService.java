package com.homefix.backend_api.service;

import com.homefix.backend_api.entity.Provider;
import com.homefix.backend_api.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public Provider updateProvider(Long id, Provider updatedProvider) {
        return providerRepository.findById(id).map(provider -> {
            provider.setName(updatedProvider.getName());
            provider.setEmail(updatedProvider.getEmail());
            provider.setServiceType(updatedProvider.getServiceType());
            provider.setHourlyRate(updatedProvider.getHourlyRate());
            provider.setBio(updatedProvider.getBio());
            return providerRepository.save(provider);
        }).orElseThrow(() -> new RuntimeException("Provider not found with id: " + id));
    }

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}