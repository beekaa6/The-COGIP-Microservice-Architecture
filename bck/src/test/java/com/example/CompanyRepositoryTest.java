package com.example;

import com.example.Model.Company;
import com.example.Repository.CompanyRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyRepositoryTest {
  
  @Autowired
  private CompanyRepository companyRepository;

  @Test
  public void testCompanyRepository(){
    Company company = new Company(null, "Test", "le test");
    companyRepository.save(company);
    
    

  }
}
