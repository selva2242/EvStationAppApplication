package com.example.EVStationApp.controller;


import com.example.EVStationApp.model.Station;
import com.example.EVStationApp.service.StationService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {

  @Autowired
  private StationService stationService;

  private static final Logger log = Logger.getLogger(StationController.class.getName());

  @GetMapping("/")
  public ResponseEntity<List<Station>> getAllStations(
      @RequestParam(name = "limit", defaultValue = "10") Integer limit,
      @RequestParam(name = "sort", defaultValue = "asc") String sort,
      @RequestParam(name = "param", defaultValue = "stationId") String param
  ) {
    try {
      return stationService.getAllStations(limit, param, sort);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in get all station :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/show/{stationId}")
  public ResponseEntity<Station> getStation(@PathVariable long stationId) {
    try {

      return stationService.getStation(stationId);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in get station :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/")
  public ResponseEntity<Station> addStation(@RequestBody Station station) {
    try {

      return stationService.addStation(station);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in add stations :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PutMapping("/{stationId}/edit")
  public ResponseEntity<Station> updateStation(@PathVariable long stationId,
      @RequestBody Station station) {
    try {

      return stationService.updateStation(stationId, station);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in update station :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/delete/{stationId}")
  public ResponseEntity<HttpStatus> deleteStation(@PathVariable long stationId) {
    try {

      return stationService.deleteStation(stationId);

    } catch (Exception e) {
      log.log(Level.INFO, "Exception occurred in delete stations :: {0}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
