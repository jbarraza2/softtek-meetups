package com.softtek.meetup.model

import static javax.persistence.GenerationType.AUTO

import java.io.Serializable
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue

@Entity
class Profile {

  @Id
  @GeneratedValue(strategy=AUTO)
  Long id

  @Column(nullable = true)
  String biography
}
