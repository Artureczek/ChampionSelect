package com.solodive.championselect.service.dto.riotapi;

public class Player {
    private String platformId;
    private long accountId;
    private String summonerName;
    private long summonerId;
    private String currentPlatformId;
    private long currentAccountId;
    private String matchHistoryUri;
    private long profileIcon;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public String getCurrentPlatformId() {
        return currentPlatformId;
    }

    public void setCurrentPlatformId(String currentPlatformId) {
        this.currentPlatformId = currentPlatformId;
    }

    public long getCurrentAccountId() {
        return currentAccountId;
    }

    public void setCurrentAccountId(long currentAccountId) {
        this.currentAccountId = currentAccountId;
    }

    public String getMatchHistoryUri() {
        return matchHistoryUri;
    }

    public void setMatchHistoryUri(String matchHistoryUri) {
        this.matchHistoryUri = matchHistoryUri;
    }

    public long getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(long profileIcon) {
        this.profileIcon = profileIcon;
    }

    @Override
    public String toString() {
        return "Player{" +
            "platformId='" + platformId + '\'' +
            ", accountId=" + accountId +
            ", summonerName='" + summonerName + '\'' +
            ", summonerId=" + summonerId +
            ", currentPlatformId='" + currentPlatformId + '\'' +
            ", currentAccountId=" + currentAccountId +
            ", matchHistoryUri='" + matchHistoryUri + '\'' +
            ", profileIcon=" + profileIcon +
            '}';
    }
}
