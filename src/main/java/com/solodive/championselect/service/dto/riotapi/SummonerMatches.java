package com.solodive.championselect.service.dto.riotapi;

import java.util.List;

public class SummonerMatches {
    private int totalGames;
    private int startIndex;
    private int endIndex;
    private List<Match> matches;

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
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
