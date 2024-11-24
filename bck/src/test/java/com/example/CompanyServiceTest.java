package com.example;

import java.util.Optional;
import com.example.model.Company;
import com.example.model.CompanyType;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class CompanyServiceTest {

  @Mock
  private CompanyRepository companyRepository;

  @InjectMocks
  private CompanyService companyService;

  @Before
  public void before(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCompany(){
    Company mockCompany = new Company();
    mockCompany.setId(1);
    mockCompany.setName("alex");
    mockCompany.setCountry("nowhere");
    mockCompany.setVat("123");
    mockCompany.setType(CompanyType.CLIENT);

    when(companyRepository.findById(1)).thenReturn(Optional.of(mockCompany));

    Optional<Company> result = companyService.getCompanyById(1);

    assertTrue(result.isPresent());
    assertEquals("alex", result.get().getName());
    assertEquals("nowhere", result.get().getCountry());
    assertEquals("123", result.get().getVat());
    assertEquals(CompanyType.CLIENT, result.get().getType());
  }
}
