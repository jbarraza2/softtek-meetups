package com.softtek.meetup.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@Document
@ToString
public class RegistrationCode {

  @Id
  private String uuid;
  private String email;
	private Date dateCreated = new Date();
  private String token = UUID.randomUUID().toString().replaceAll('-','');
  private RegistrationCodeStatus status = RegistrationCodeStatus.VALID;

  public Boolean isValid(){
    return status == RegistrationCodeStatus.VALID;
  }

}
