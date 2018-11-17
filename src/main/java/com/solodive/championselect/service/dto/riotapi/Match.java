package com.solodive.championselect.service.dto.riotapi;

public class Match {
    private String lane;
    private Long gameId;
    private Long champion;
    private String platformId;
    private Long season;
    private Long queue;
    private String role;
    private Long timestamp;

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getChampion() {
        return champion;
    }

    public void setChampion(Long champion) {
        this.champion = champion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Long getSeason() {
        return season;
    }

    public void setSeason(Long season) {
        this.season = season;
    }

    public Long getQueue() {
        return queue;
    }

    public void setQueue(Long queue) {
        this.queue = queue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Match{" +
            "lane='" + lane + '\'' +
            ", gameId=" + gameId +
            ", champion=" + champion +
            ", platformId='" + platformId + '\'' +
            ", season=" + season +
            ", queue=" + queue +
            ", role='" + role + '\'' +
            ", timestamp=" + timestamp +
            '}';
    }
}


