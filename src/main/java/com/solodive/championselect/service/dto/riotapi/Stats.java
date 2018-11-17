package com.solodive.championselect.service.dto.riotapi;

public class Stats {
    private Long participantId;
    private Boolean win;
    private Long kills;
    private Long deaths;
    private Long assists;
    private Long largestKillingSpree;
    private Long largestMultiKill;
    private Long killingSprees;
    private Long longestTimeSpentLiving;
    private Long doubleKills;
    private Long tripleKills;
    private Long quadraKills;
    private Long pentaKills;
    private Long totalDamageDealtToChampions;
    private Long totalHeal;
    private Long visionScore;
    private Long timeCCingOthers;
    private Long totalDamageTaken;
    private Long goldEarned;
    private Long goldSpent;
    private Long turretKills;
    private Long inhibitorKills;
    private Long totalMinionsKilled;
    private Long visionWardsBoughtInGame;
    private Long sightWardsBoughtInGame;
    private Long wardsPlaced;
    private Long wardsKilled;


    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public Long getKills() {
        return kills;
    }

    public void setKills(Long kills) {
        this.kills = kills;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }

    public Long getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public void setLargestKillingSpree(Long largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    public Long getLargestMultiKill() {
        return largestMultiKill;
    }

    public void setLargestMultiKill(Long largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public Long getKillingSprees() {
        return killingSprees;
    }

    public void setKillingSprees(Long killingSprees) {
        this.killingSprees = killingSprees;
    }

    public Long getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    public void setLongestTimeSpentLiving(Long LongestTimeSpentLiving) {
        this.longestTimeSpentLiving = LongestTimeSpentLiving;
    }

    public Long getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(Long doubleKills) {
        this.doubleKills = doubleKills;
    }

    public Long getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(Long tripleKills) {
        this.tripleKills = tripleKills;
    }

    public Long getQuadraKills() {
        return quadraKills;
    }

    public void setQuadraKills(Long quadraKills) {
        this.quadraKills = quadraKills;
    }

    public Long getPentaKills() {
        return pentaKills;
    }

    public void setPentaKills(Long pentaKills) {
        this.pentaKills = pentaKills;
    }

    public Long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public void setTotalDamageDealtToChampions(Long totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    public Long getTotalHeal() {
        return totalHeal;
    }

    public void setTotalHeal(Long totalHeal) {
        this.totalHeal = totalHeal;
    }

    public Long getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(Long visionScore) {
        this.visionScore = visionScore;
    }

    public Long getTimeCCingOthers() {
        return timeCCingOthers;
    }

    public void setTimeCCingOthers(Long timeCCingOthers) {
        this.timeCCingOthers = timeCCingOthers;
    }

    public Long getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(Long totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public Long getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(Long goldEarned) {
        this.goldEarned = goldEarned;
    }

    public Long getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(Long goldSpent) {
        this.goldSpent = goldSpent;
    }

    public Long getTurretKills() {
        return turretKills;
    }

    public void setTurretKills(Long turretKills) {
        this.turretKills = turretKills;
    }

    public Long getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(Long inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public Long getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(Long totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public Long getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public void setVisionWardsBoughtInGame(Long visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public Long getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public void setSightWardsBoughtInGame(Long sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    public Long getWardsPlaced() {
        return wardsPlaced;
    }

    public void setWardsPlaced(Long wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    public Long getWardsKilled() {
        return wardsKilled;
    }

    public void setWardsKilled(Long wardsKilled) {
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
