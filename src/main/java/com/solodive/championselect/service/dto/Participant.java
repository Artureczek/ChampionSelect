package com.solodive.championselect.service.dto;

public class Participant {
    private long participantId;
    private long teamId;
    private long championId;
    private long spell1Id;
    private long spell2Id;
    private String highestAchievedSeasonTier;
    private Stats stats;


    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public long getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(long spell1Id) {
        this.spell1Id = spell1Id;
    }

    public long getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(long spell2Id) {
        this.spell2Id = spell2Id;
    }

    public String getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }


    @Override
    public String toString() {
        return "Participant{" +
            "participantId=" + participantId +
            ", teamId=" + teamId +
            ", championId=" + championId +
            ", spell1Id=" + spell1Id +
            ", spell2Id=" + spell2Id +
            ", highestAchievedSeasonTier='" + highestAchievedSeasonTier + '\'' +
            ", stats=" + stats +
            '}';
    }
}
