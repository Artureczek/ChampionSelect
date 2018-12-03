package com.solodive.championselect.web.rest.riotapi;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.service.dto.riotapi.RiotChampionDTO;
import com.solodive.championselect.service.riotapi.ChampionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChampionController {


    @Autowired
    private ChampionService championService;
    private RiotChampionDTO riotChampionDTO;
    private List<RiotChampionDTO> riotChampionDTOS;

    @GetMapping("/champion/{name}")
    @Timed
    public ResponseEntity<RiotChampionDTO> getChampionByName(@PathVariable String name) {
        riotChampionDTO = championService.getChampionByName(name);
        return new ResponseEntity<>(riotChampionDTO, null, HttpStatus.OK);
    }
    @GetMapping("/champions")
    @Timed
    public ResponseEntity<List<RiotChampionDTO>> getRiotChampionDTOS() {
        riotChampionDTOS = championService.getAllChampions();
        return new ResponseEntity<>(riotChampionDTOS, null, HttpStatus.OK);
    }

}
