package com.example.EVStationApp.repo;

import com.example.EVStationApp.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepo extends JpaRepository<Station, Long> {
}
