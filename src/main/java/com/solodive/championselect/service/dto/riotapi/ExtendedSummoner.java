package com.solodive.championselect.service.dto.riotapi;

import java.util.Arrays;

public class ExtendedSummoner {

    private BasicSummoner basicSummoner;
    private SummonerRank[] summonerRank;
    private SummonerMatches summonerMatches;


    public BasicSummoner getBasicSummoner() {
        return basicSummoner;
    }

    public void setBasicSummoner(BasicSummoner basicSummoner) {
        this.basicSummoner = basicSummoner;
    }

    public SummonerRank[] getSummonerRank() {
        return summonerRank;
    }

    public void setSummonerRank(SummonerRank[] summonerRank) {
        this.summonerRank = summonerRank;
    }

    public SummonerMatches getSummonerMatches() {
        return summonerMatches;
    }

    public void setSummonerMatches(SummonerMatches summonerMatches) {
        this.summonerMatches = summonerMatches;
    }

    @Override
    public String toString() {
        return "ExtendedSummoner{" +
            "basicSummoner=" + basicSummoner +
            ", summonerRank=" + Arrays.toString(summonerRank) +
            ", summonerMatches=" + summonerMatches +
            '}';
    }
}
