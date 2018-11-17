package com.solodive.championselect.service.dto.riotapi;

public class Stats {
    private long participantId;
    private Boolean win;
    private long kills;
    private long deaths;
    private long assists;
    private long largestKillingSpree;
    private long largestMultiKill;
    private long killingSprees;
    private long longestTimeSpentLiving;
    private long doubleKills;
    private long tripleKills;
    private long quadraKills;
    private long pentaKills;
    private long totalDamageDealtToChampions;
    private long totalHeal;
    private long visionScore;
    private long timeCCingOthers;
    private long totalDamageTaken;
    private long goldEarned;
    private long goldSpent;
    private long turretKills;
    private long inhibitorKills;
    private long totalMinionsKilled;
    private long visionWardsBoughtInGame;
    private long sightWardsBoughtInGame;
    private long wardsPlaced;
    private long wardsKilled;


    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getAssists() {
        return assists;
    }

    public void setAssists(long assists) {
        this.assists = assists;
    }

    public long getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public void setLargestKillingSpree(long largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    public long getLargestMultiKill() {
        return largestMultiKill;
    }

    public void setLargestMultiKill(long largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public long getKillingSprees() {
        return killingSprees;
    }

    public void setKillingSprees(long killingSprees) {
        this.killingSprees = killingSprees;
    }

    public long getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    public void setLongestTimeSpentLiving(long longestTimeSpentLiving) {
        this.longestTimeSpentLiving = longestTimeSpentLiving;
    }

    public long getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(long doubleKills) {
        this.doubleKills = doubleKills;
    }

    public long getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(long tripleKills) {
        this.tripleKills = tripleKills;
    }

    public long getQuadraKills() {
        return quadraKills;
    }

    public void setQuadraKills(long quadraKills) {
        this.quadraKills = quadraKills;
    }

    public long getPentaKills() {
        return pentaKills;
    }

    public void setPentaKills(long pentaKills) {
        this.pentaKills = pentaKills;
    }

    public long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public void setTotalDamageDealtToChampions(long totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    public long getTotalHeal() {
        return totalHeal;
    }

    public void setTotalHeal(long totalHeal) {
        this.totalHeal = totalHeal;
    }

    public long getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(long visionScore) {
        this.visionScore = visionScore;
    }

    public long getTimeCCingOthers() {
        return timeCCingOthers;
    }

    public void setTimeCCingOthers(long timeCCingOthers) {
        this.timeCCingOthers = timeCCingOthers;
    }

    public long getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(long totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public long getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(long goldEarned) {
        this.goldEarned = goldEarned;
    }

    public long getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(long goldSpent) {
        this.goldSpent = goldSpent;
    }

    public long getTurretKills() {
        return turretKills;
    }

    public void setTurretKills(long turretKills) {
        this.turretKills = turretKills;
    }

    public long getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(long inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public long getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(long totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public long getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public void setVisionWardsBoughtInGame(long visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public long getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public void setSightWardsBoughtInGame(long sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    public long getWardsPlaced() {
        return wardsPlaced;
    }

    public void setWardsPlaced(long wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    public long getWardsKilled() {
        return wardsKilled;
    }

    public void setWardsKilled(long wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    @Override
    public String toString() {
        return "Stats{" +
            "participantId=" + participantId +
            ", win=" + win +
            ", kills=" + kills +
            ", deaths=" + deaths +
            ", assists=" + assists +
            ", largestKillingSpree=" + largestKillingSpree +
            ", largestMultiKill=" + largestMultiKill +
            ", killingSprees=" + killingSprees +
            ", longestTimeSpentLiving=" + longestTimeSpentLiving +
            ", doubleKills=" + doubleKills +
            ", tripleKills=" + tripleKills +
            ", quadraKills=" + quadraKills +
            ", pentaKills=" + pentaKills +
            ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
            ", totalHeal=" + totalHeal +
            ", visionScore=" + visionScore +
            ", timeCCingOthers=" + timeCCingOthers +
            ", totalDamageTaken=" + totalDamageTaken +
            ", goldEarned=" + goldEarned +
            ", goldSpent=" + goldSpent +
            ", turretKills=" + turretKills +
            ", inhibitorKills=" + inhibitorKills +
            ", totalMinionsKilled=" + totalMinionsKilled +
            ", visionWardsBoughtInGame=" + visionWardsBoughtInGame +
            ", sightWardsBoughtInGame=" + sightWardsBoughtInGame +
            ", wardsPlaced=" + wardsPlaced +
            ", wardsKilled=" + wardsKilled +
            '}';
    }
}
