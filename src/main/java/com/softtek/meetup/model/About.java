package com.softtek.meetup.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@Document
@ToString
public class About {

  @Id
  private String uuid;
  private String biography;
  private String degree;
  private Date birthDate;

}
