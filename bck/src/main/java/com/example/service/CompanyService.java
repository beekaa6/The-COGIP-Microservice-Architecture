package com.example.service;

import java.util.List;
import java.util.Optional;
import com.example.model.Company;
import com.example.repository.CompanyRepository;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {
  private CompanyRepository repo;
  public CompanyService(CompanyRepository repo) {
    this.repo = repo;
  }
  
  public List<Company> getCompany(){
    return repo.findAll();
  }
  
  public Optional<Company> getCompanyById(int id){
    return repo.findById(id);
  }

  public void putCompany(Company company){
    repo.save(company);
  }
  
  public void updateCompany(Company company){
    repo.save(company);
  }

  public void deleteCompanyById(int id){
    repo.deleteById(id);
  }
}
