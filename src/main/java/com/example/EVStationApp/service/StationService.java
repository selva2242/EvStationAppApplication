package com.example.EVStationApp.service;

import com.example.EVStationApp.model.Station;
import com.example.EVStationApp.repo.StationRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StationService {

  @Autowired
  private StationRepo stationRepo;

  private static final Logger log = Logger.getLogger(StationService.class.getName());

  public ResponseEntity<List<Station>> getAllStations(int limit, String param, String sort) {

    try {
      List<Station> allStations = new ArrayList<>();
      

      Pageable page = PageRequest.of(0, limit,
          sort.equals("desc") ? Sort.by(param).descending() : Sort.by(param).ascending());

      stationRepo.findAll(page).forEach(allStations::add);

      if (allStations.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(allStations, HttpStatus.OK);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in get all stations :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Station> getStation(long stationId) {
    try {
      Optional<Station> station = stationRepo.findById(stationId);

      if (!station.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(station.get(), HttpStatus.OK);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in get station :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Station> addStation(Station station) {
    try {
      Station newStation = stationRepo.save(station);
      return new ResponseEntity<>(newStation, HttpStatus.OK);
    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in add stations :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  public ResponseEntity<Station> updateStation(long stationId, Station station) {
    try {
      Optional<Station> existingStation = stationRepo.findById(stationId);

      if (!existingStation.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      Station updatedStation = existingStation.get();
      updatedStation.setStationName(station.getStationName());
      updatedStation.setStationAddress(station.getStationAddress());
      updatedStation.setStationPricing(station.getStationPricing());
      updatedStation.setStationImage(station.getStationImage());

      Station updatedStationObj = stationRepo.save(updatedStation);

      return new ResponseEntity<>(updatedStationObj, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      log.log(Level.INFO, "Exception occurred in update station :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<HttpStatus> deleteStation(long stationId) {
    try {
      stationRepo.deleteById(stationId);
      return new ResponseEntity<>(HttpStatus.OK);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in delete stations :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
