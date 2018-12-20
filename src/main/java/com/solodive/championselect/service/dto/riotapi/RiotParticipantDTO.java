package com.solodive.championselect.service.dto.riotapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RiotParticipantDTO {
    private Long participantId;
    private Long teamId;
    private Long championId;
    private Long spell1Id;
    private Long spell2Id;
    private String highestAchievedSeasonTier;
    @JsonProperty("stats")
    private RiotParticipantStatsDTO riotParticipantStatsDTO;


    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getChampionId() {
        return championId;
    }

    public void setChampionId(Long championId) {
        this.championId = championId;
    }

    public Long getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(Long spell1Id) {
        this.spell1Id = spell1Id;
    }

    public Long getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(Long spell2Id) {
        this.spell2Id = spell2Id;
    }

    public String getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public RiotParticipantStatsDTO getRiotParticipantStatsDTO() {
        return riotParticipantStatsDTO;
    }

    public void setRiotParticipantStatsDTO(RiotParticipantStatsDTO riotParticipantStatsDTO) {
        this.riotParticipantStatsDTO = riotParticipantStatsDTO;
    }


    @Override
    public String toString() {
        return "RiotParticipantDTO{" +
            "participantId=" + participantId +
            ", teamId=" + teamId +
            ", championId=" + championId +
            ", spell1Id=" + spell1Id +
            ", spell2Id=" + spell2Id +
            ", highestAchievedSeasonTier='" + highestAchievedSeasonTier + '\'' +
            ", riotParticipantStatsDTO=" + riotParticipantStatsDTO +
            '}';
    }
}
