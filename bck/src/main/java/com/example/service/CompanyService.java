package com.example.service;

import java.util.List;

import com.example.model.Company;
import com.example.repository.CompanyRepository;

import org.springframework.stereotype.Service;


@Service
public class CompanyService {
  private CompanyRepository repo;
  public CompanyService(CompanyRepository repo) {
    this.repo = repo;
  }

  public void putCompany(Company company){
    repo.save(company);
  }
  
  public List<Company> getCompany(){
    return repo.findAll();
  }
}
