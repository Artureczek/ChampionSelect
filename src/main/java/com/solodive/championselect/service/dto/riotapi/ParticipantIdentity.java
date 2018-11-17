package com.solodive.championselect.service.dto.riotapi;

public class ParticipantIdentity {

    private long participantId;
    private Player player;

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "ParticipantIdentity{" +
            "participantId=" + participantId +
            ", player=" + player +
            '}';
    }
}
