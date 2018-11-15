package com.solodive.championselect.service.dto;

public class SummonerRank {

    private String leagueId;
    private String leagueName;
    private String tier;
    private String queueType;
    private String rank;
    private String playerOrTeamId;
    private String playerOrTeamName;
    private long leaguePoints;
    private long wins;
    private long losses;
    private Boolean veteran;
    private Boolean inactive;
    private Boolean freshBlood;
    private Boolean hotStreak;

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    public void setPlayerOrTeamId(String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    public void setPlayerOrTeamName(String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
    }

    public long getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public long getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Boolean getVeteran() {
        return veteran;
    }

    public void setVeteran(Boolean veteran) {
        this.veteran = veteran;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Boolean getFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(Boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public Boolean getHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(Boolean hotStreak) {
        this.hotStreak = hotStreak;
    }


    @Override
    public String toString() {
        return "SummonerRank{" +
            "leagueId='" + leagueId + '\'' +
            ", leagueName='" + leagueName + '\'' +
            ", tier='" + tier + '\'' +
            ", queueType='" + queueType + '\'' +
            ", rank='" + rank + '\'' +
            ", playerOrTeamId='" + playerOrTeamId + '\'' +
            ", playerOrTeamName='" + playerOrTeamName + '\'' +
            ", leaguePoints=" + leaguePoints +
            ", wins=" + wins +
            ", losses=" + losses +
            ", veteran=" + veteran +
            ", inactive=" + inactive +
            ", freshBlood=" + freshBlood +
            ", hotStreak=" + hotStreak +
            '}';
    }
}
