package com.solodive.championselect.service.dto.riotapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RiotSummonerMatchesDTO {
    private Long totalGames;
    private Long startIndex;
    private Long endIndex;
    @JsonProperty("matches")
    private List<RiotMatchDTO> riotMatchDTOS = new ArrayList<>();

    public Long getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Long totalGames) {
        this.totalGames = totalGames;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Long endIndex) {
        this.endIndex = endIndex;
    }

    public List<RiotMatchDTO> getRiotMatchDTOS() {
        return riotMatchDTOS;
    }

    public void setRiotMatchDTOS(List<RiotMatchDTO> riotMatchDTOS) {
        this.riotMatchDTOS = riotMatchDTOS;
    }


    @Override
    public String toString() {
        return "RiotSummonerMatchesDTO{" +
            "totalGames=" + totalGames +
            ", startIndex=" + startIndex +
            ", endIndex=" + endIndex +
            ", riotMatchDTOS=" + riotMatchDTOS +
            '}';
    }
}
