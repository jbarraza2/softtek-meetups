package com.softtek.meetup.model

import static javax.persistence.GenerationType.AUTO

import java.io.Serializable
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue

@Entity
class Technology implements Serializable {

  @Id
  @GeneratedValue(strategy=AUTO)
  Long id

  @Column(unique = true, nullable = false)
  String name

}
