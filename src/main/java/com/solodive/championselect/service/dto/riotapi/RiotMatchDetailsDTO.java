package com.solodive.championselect.service.dto.riotapi;

import java.util.ArrayList;
import java.util.List;

public class RiotMatchDetailsDTO {
    private Long gameId;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;
    private Long queueId;
    private Long mapId;
    private Long seasonId;
    private String gameVersion;
    private String gameMode;
    private String gameType;
    private List<RiotParticipantDTO> riotParticipantDTOS = new ArrayList<>();
    private List<RiotParticipantIdentityDTO> participantIdentities = new ArrayList<>();


    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(Long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public Long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(Long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public List<RiotParticipantIdentityDTO> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(List<RiotParticipantIdentityDTO> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public List<RiotParticipantDTO> getRiotParticipantDTOS() {
        return riotParticipantDTOS;
    }

    public void setRiotParticipantDTOS(List<RiotParticipantDTO> riotParticipantDTOS) {
        this.riotParticipantDTOS = riotParticipantDTOS;
    }


    @Override
    public String toString() {
        return "RiotMatchDetailsDTO{" +
            "gameId=" + gameId +
            ", platformId='" + platformId + '\'' +
            ", gameCreation=" + gameCreation +
            ", gameDuration=" + gameDuration +
            ", queueId=" + queueId +
            ", mapId=" + mapId +
            ", seasonId=" + seasonId +
            ", gameVersion='" + gameVersion + '\'' +
            ", gameMode='" + gameMode + '\'' +
            ", gameType='" + gameType + '\'' +
            ", riotParticipantDTOS=" + riotParticipantDTOS +
            ", participantIdentities=" + participantIdentities +
            '}';
    }
}
