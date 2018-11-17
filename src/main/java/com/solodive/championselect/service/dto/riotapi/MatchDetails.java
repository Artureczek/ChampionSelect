package com.solodive.championselect.service.dto.riotapi;

import java.util.List;

public class MatchDetails {
    private long gameId;
    private String platformId;
    private long gameCreation;
    private long gameDuration;
    private long queueId;
    private long mapId;
    private long seasonId;
    private String gameVersion;
    private String gameMode;
    private String gameType;
    //private List<Team> teams = null;
    private List<Participant> participants = null;
    private List<ParticipantIdentity> participantIdentities = null;


    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getQueueId() {
        return queueId;
    }

    public void setQueueId(long queueId) {
        this.queueId = queueId;
    }

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(long seasonId) {
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

    public List<ParticipantIdentity> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(List<ParticipantIdentity> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }


    @Override
    public String toString() {
        return "MatchDetails{" +
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
            ", participants=" + participants +
            ", participantIdentities=" + participantIdentities +
            '}';
    }
}
