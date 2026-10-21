package com.homefix.backend_api.repository;

import com.homefix.backend_api.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}