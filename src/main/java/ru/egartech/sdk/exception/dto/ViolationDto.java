package ru.egartech.sdk.exception.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeName("Violation")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ViolationDto {
  
  @JsonProperty("field_name")
  private String fieldName;
  
  @JsonProperty("message")
  private String message;
}

