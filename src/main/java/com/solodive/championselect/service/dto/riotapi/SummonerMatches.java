package com.solodive.championselect.service.dto.riotapi;

import java.util.ArrayList;
import java.util.List;

public class SummonerMatches {
    private Long totalGames;
    private Long startIndex;
    private Long endIndex;
    private List<Match> matches = new ArrayList<>();

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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


    @Override
    public String toString() {
        return "SummonerMatches{" +
            "totalGames=" + totalGames +
            ", startIndex=" + startIndex +
            ", endIndex=" + endIndex +
            ", matches=" + matches +
            '}';
    }
}
