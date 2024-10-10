package cz.crowire.be_tests.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadDto {
  @JsonIgnore
  private int id;
  private String name;
  private String email;

  public LeadDto(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
