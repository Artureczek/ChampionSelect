package com.solodive.championselect.web.rest.riotapi;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.service.dto.riotapi.*;
import com.solodive.championselect.service.riotapi.SummonerService;
import com.solodive.championselect.web.rest.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SummonerController {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private SummonerService summonerService;
    private RiotBasicSummonerDTO riotBasicSummonerDTO;
    private RiotExtendedSummonerDTO riotExtendedSummonerDTO;
    private List<RiotSummonerRankDTO>  riotSummonerRankDTO;
    private RiotSummonerMatchesDTO riotSummonerMatchesDTO;
    private RiotMatchDetailsDTO riotMatchDetailsDTO;


    @GetMapping("/summoner/by-name/{name}") //consumes= MediaType.APPLICATION_JSON_UTF8_VALUE,value=
    @Timed
    public ResponseEntity<RiotBasicSummonerDTO> getSummonerByName(@PathVariable String name) {
        riotBasicSummonerDTO = summonerService.getBasicSummonerByName(name);
        return new ResponseEntity<>(riotBasicSummonerDTO, null, HttpStatus.OK);
    }

    @GetMapping("/summoner/by-id/{id}")
    @Timed
    public ResponseEntity<RiotBasicSummonerDTO> getSummonerById(@PathVariable String id) {
        riotBasicSummonerDTO = summonerService.getBasicSummonerById(id);
        return new ResponseEntity<>(riotBasicSummonerDTO, null, HttpStatus.OK);
    }


    @GetMapping("/summoner-rank/{id}")
    @Timed
    public ResponseEntity<List<RiotSummonerRankDTO>> getSummonerRankById(@PathVariable String id) {
        riotSummonerRankDTO = summonerService.getSummonerRankById(id);
        return new ResponseEntity<>(riotSummonerRankDTO, null, HttpStatus.OK);
    }

    @GetMapping("/summoner-matches/{id}")
    @Timed
    public ResponseEntity<RiotSummonerMatchesDTO> getSummonerMatchesById(@PathVariable String id) {
        riotSummonerMatchesDTO = summonerService.getSummonerMatchList(id);
        return new ResponseEntity<>(riotSummonerMatchesDTO, null, HttpStatus.OK);
    }


    @GetMapping("/extended-summoner/{id}")
    @Timed
    public ResponseEntity<RiotExtendedSummonerDTO> getExtendedSummonerById(@PathVariable String id) {
        riotExtendedSummonerDTO = summonerService.getExtendedSummonerById(id);
        return new ResponseEntity<>(riotExtendedSummonerDTO, null, HttpStatus.OK);
    }

    @GetMapping("/match/{id}")
    @Timed
    public ResponseEntity<RiotMatchDetailsDTO> getMatchDetailsById(@PathVariable String id) {
        riotMatchDetailsDTO = summonerService.getMatchDetails(id);
        return new ResponseEntity<>(riotMatchDetailsDTO, null, HttpStatus.OK);
    }




}
