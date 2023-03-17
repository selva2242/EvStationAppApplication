package com.example.EVStationApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stations")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Station {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long stationId;

  private String stationName;

  private String stationImage;

  private float stationPricing;

  private String stationAddress;

}
