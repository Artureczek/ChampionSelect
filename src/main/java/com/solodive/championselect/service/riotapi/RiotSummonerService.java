package com.solodive.championselect.service.riotapi;

import com.solodive.championselect.service.dto.riotapi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SummonerService {
    private final Logger log = LoggerFactory.getLogger(SummonerService.class);


    @Autowired
    private RestTemplate restTemplate;

    @Value("${riotapi.base-url}")
    private String baseUrl;

    @Value("${riotapi.summoner-by-name-url}")
    private String summonerByNameUrl;

    @Value("${riotapi.summoner-by-id-url}")
    private String summonerByIdUrl;

    @Value("${riotapi.api-key}")
    private String apiKey;

    @Value("${riotapi.rank-by-id-url}")
    private String rankByIdUrl;

    @Value("${riotapi.matches-list-by-id-url}")
    private String matchesListByIdUrl;

    @Value("${riotapi.match-by-id-url}")
    private String matchByIdUrl;


    private URI getUrl(String url, String parameter){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + url);
        Map<String, String> params = new HashMap<>();
        params.put("parameter", parameter);
        params.put("api-key", apiKey);
        return builder.buildAndExpand(params).toUri();
    }

    public RiotBasicSummonerDTO getBasicSummonerByName(String name) {
        ResponseEntity<RiotBasicSummonerDTO> response = restTemplate.getForEntity(getUrl(summonerByNameUrl, name), RiotBasicSummonerDTO.class);
        return response.getBody();
    }
    public RiotBasicSummonerDTO getBasicSummonerById(String id) {
        ResponseEntity<RiotBasicSummonerDTO> response = restTemplate.getForEntity(getUrl(summonerByIdUrl, id), RiotBasicSummonerDTO.class);
        return response.getBody();
    }

    public List<RiotSummonerRankDTO> getSummonerRankById(String id){
        ResponseEntity<RiotSummonerRankDTO[]> response = restTemplate.getForEntity(getUrl(rankByIdUrl, id), RiotSummonerRankDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public RiotExtendedSummonerDTO getExtendedSummonerById(String id){
        RiotExtendedSummonerDTO riotExtendedSummonerDTO = new RiotExtendedSummonerDTO();
        riotExtendedSummonerDTO.setRiotBasicSummonerDTO(getBasicSummonerById(id));
        riotExtendedSummonerDTO.setRiotSummonerMatchesDTO(getSummonerMatchList(id));
        riotExtendedSummonerDTO.setRiotSummonerRankDTO(getSummonerRankById(id));
        return riotExtendedSummonerDTO;
    }

    public RiotSummonerMatchesDTO getSummonerMatchList(String id){
        ResponseEntity<RiotSummonerMatchesDTO> response = restTemplate.getForEntity(getUrl(matchesListByIdUrl, ""+getBasicSummonerById(id).getAccountId()), RiotSummonerMatchesDTO.class);
        return response.getBody();
    }

    public RiotMatchDetailsDTO getMatchDetails(String id){
        ResponseEntity<RiotMatchDetailsDTO> response = restTemplate.getForEntity(getUrl(matchByIdUrl, id), RiotMatchDetailsDTO.class);
        return response.getBody();
    }


}
