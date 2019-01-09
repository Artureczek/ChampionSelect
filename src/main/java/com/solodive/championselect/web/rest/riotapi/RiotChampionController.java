package com.solodive.championselect.web.rest.riotapi;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.service.SoloMemberService;
import com.solodive.championselect.service.dto.riotapi.RiotChampionDTO;
import com.solodive.championselect.service.riotapi.RiotChampionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/riotapi")
public class RiotChampionController {


    @Autowired
    private RiotChampionService riotChampionService;

    private RiotChampionDTO riotChampionDTO;
    private List<RiotChampionDTO> riotChampionDTOS;

    @GetMapping("/champion/{name}")
    @Timed
    public ResponseEntity<RiotChampionDTO> getChampionByName(@PathVariable String name) {
        riotChampionDTO = riotChampionService.getChampionByName(name);
        return new ResponseEntity<>(riotChampionDTO, null, HttpStatus.OK);
    }
    @GetMapping("/champions")
    @Timed
    public ResponseEntity<List<RiotChampionDTO>> getRiotChampionDTOS() {
        riotChampionDTOS = riotChampionService.getAllChampions();
        return new ResponseEntity<>(riotChampionDTOS, null, HttpStatus.OK);
    }

}
