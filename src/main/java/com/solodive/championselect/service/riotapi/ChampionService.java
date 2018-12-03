package com.solodive.championselect.service.riotapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solodive.championselect.service.dto.riotapi.RiotChampionDTO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChampionService {
    private final Logger log = LoggerFactory.getLogger(SummonerService.class);

    @Value("${riot-api.data-dragon-url}")
    private String dataDragonUrl;
    @Value("${riot-api.champion-data-url}")
    private String championDataUrl;

    //try with resources
    // sprobowac resttemplate na stringa
    private String getChampionJsonString() {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(dataDragonUrl + championDataUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private JSONObject getChampionDataJsonObject(){
        JSONObject jsonObject = new JSONObject(getChampionJsonString());
        return jsonObject.getJSONObject("data");
    }


    public RiotChampionDTO getChampionByName(String name){
        JSONObject jsonChampionData = getChampionDataJsonObject();
        JSONObject jsonChampion = jsonChampionData.getJSONObject(name);
        ObjectMapper objectMapper = new ObjectMapper();
        RiotChampionDTO riotChampionDTO = null;
        try {
            riotChampionDTO = objectMapper.readValue(jsonChampion.toString(), RiotChampionDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return riotChampionDTO;
    }

    public List<RiotChampionDTO> getAllChampions(){
        List<RiotChampionDTO> riotChampionDTOS = new ArrayList<RiotChampionDTO>();
        JSONObject jsonChampionData = getChampionDataJsonObject();
        String[] names = jsonChampionData.getNames(jsonChampionData);
        for (int i = 1; i < names.length; i++) {
            JSONObject jsonChampion = jsonChampionData.getJSONObject(names[i]);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                riotChampionDTOS.add(objectMapper.readValue(jsonChampion.toString(), RiotChampionDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return riotChampionDTOS;
    }







}
