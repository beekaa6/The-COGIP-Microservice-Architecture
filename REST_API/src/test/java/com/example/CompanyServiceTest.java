package com.example;

import java.util.List;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


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
  public void testCompanyIfEmpty(){
    when(companyRepository.findById(999)).thenReturn(Optional.empty());

    Optional<Company> emptyCompany = companyService.getCompanyById(999);

    assertFalse(emptyCompany.isPresent());
  }

  @Test
  public void testGetCompany(){
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
  
  @Test
  public void testGetAllCompany(){
    List<Company> companyList = List.of(
      new Company("company uno", "here", "9000", CompanyType.CLIENT),
      new Company("company dos", "there", "1000", CompanyType.PROVIDER)
    );

    when(companyRepository.findAll()).thenReturn(companyList);

    List<Company> result = companyService.getCompany();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("company uno", result.get(0).getName());
    assertEquals("here", result.get(0).getCountry());
    assertEquals("9000", result.get(0).getVat());
    assertEquals(CompanyType.CLIENT, result.get(0).getType());
  
    assertEquals("company dos", result.get(1).getName());
    assertEquals("there", result.get(1).getCountry());
    assertEquals("1000", result.get(1).getVat());
    assertEquals(CompanyType.PROVIDER, result.get(1).getType());
  }

  @Test
  public void testSaveCompany(){
    Company testingCompany = new Company();
    testingCompany.setName("heh");
    testingCompany.setCountry("hello");
    testingCompany.setVat("0000");

    when(companyRepository.save(testingCompany)).thenReturn(testingCompany);

    companyService.saveCompany(testingCompany);

    verify(companyRepository, times(1)).save(testingCompany);

  }
  
  @Test
  public void testDeleteContacts(){
    companyService.deleteCompanyById(1);

    verify(companyRepository, times(1)).deleteById(1);
  }
}