package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CompanyDTO {
  private Integer id;
  @NonNull private String name;
  @NonNull private Integer vatNumber;

  @Override
  public String toString() {
    return "Company [ID=" + id + ", Name=" + name + ", VAT=" + vatNumber + "]";
  }
}
