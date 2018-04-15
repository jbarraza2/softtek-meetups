package com.softtek.meetup.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

import com.softtek.meetup.enums.RegistrationCodeStatus;

@Data
@Document
@ToString
public class RegistrationCode {

  @Id
  private String uuid;
  private String email;
	private Date dateCreated = new Date();
  private String token = UUID.randomUUID().toString();
  private RegistrationCodeStatus status = RegistrationCodeStatus.VALID;

  public Boolean isValid(){
    return status == RegistrationCodeStatus.VALID;
  }

}
