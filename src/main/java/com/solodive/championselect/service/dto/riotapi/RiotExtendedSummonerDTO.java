package com.solodive.championselect.service.dto.riotapi;

import java.util.Arrays;

public class RiotExtendedSummonerDTO {

    private RiotBasicSummonerDTO riotBasicSummonerDTO;
    private RiotSummonerRankDTO[] riotSummonerRankDTO;
    private RiotSummonerMatchesDTO riotSummonerMatchesDTO;


    public RiotBasicSummonerDTO getRiotBasicSummonerDTO() {
        return riotBasicSummonerDTO;
    }

    public void setRiotBasicSummonerDTO(RiotBasicSummonerDTO riotBasicSummonerDTO) {
        this.riotBasicSummonerDTO = riotBasicSummonerDTO;
    }

    public RiotSummonerRankDTO[] getRiotSummonerRankDTO() {
        return riotSummonerRankDTO;
    }

    public void setRiotSummonerRankDTO(RiotSummonerRankDTO[] riotSummonerRankDTO) {
        this.riotSummonerRankDTO = riotSummonerRankDTO;
    }

    public RiotSummonerMatchesDTO getRiotSummonerMatchesDTO() {
        return riotSummonerMatchesDTO;
    }

    public void setRiotSummonerMatchesDTO(RiotSummonerMatchesDTO riotSummonerMatchesDTO) {
        this.riotSummonerMatchesDTO = riotSummonerMatchesDTO;
    }

    @Override
    public String toString() {
        return "RiotExtendedSummonerDTO{" +
            "riotBasicSummonerDTO=" + riotBasicSummonerDTO +
            ", riotSummonerRankDTO=" + Arrays.toString(riotSummonerRankDTO) +
            ", riotSummonerMatchesDTO=" + riotSummonerMatchesDTO +
            '}';
    }
}
