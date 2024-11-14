package com.example.controller;

import java.util.List;
import java.util.Optional;
import com.example.model.Company;
import com.example.service.CompanyService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/companies")
public class CompanyController {
  private CompanyService companyService;
  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping
  public List<Company> getAllCompanies(){
    return companyService.getCompany();
  }

  @GetMapping("/{id}")
  public Optional<Company> getCompaniesById(@PathVariable int id){
    return companyService.getCompanyById(id);
  }

  @PostMapping
  public void addToCompanies(@RequestBody Company company){
    companyService.putCompany(company);
  }

  @PutMapping
  public void updateCompanies(@RequestBody Company company){
    companyService.updateCompany(company);
  }

  @DeleteMapping("/{id}")
  public void deleteCompaniesById(@PathVariable int id){
    companyService.deleteCompanyById(id);
  }
}
