package com.solodive.championselect.service.dto.riotapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RiotParticipantIdentityDTO {

    private Long participantId;
    @JsonProperty("player")
    private RiotPlayerDTO riotPlayerDTO;

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public RiotPlayerDTO getRiotPlayerDTO() {
        return riotPlayerDTO;
    }

    public void setRiotPlayerDTO(RiotPlayerDTO riotPlayerDTO) {
        this.riotPlayerDTO = riotPlayerDTO;
    }

    @Override
    public String toString() {
        return "RiotParticipantIdentityDTO{" +
            "participantId=" + participantId +
            ", riotPlayerDTO=" + riotPlayerDTO +
            '}';
    }
}
