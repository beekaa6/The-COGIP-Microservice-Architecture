package com.example.Repository;

import com.example.Model.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
  
}
