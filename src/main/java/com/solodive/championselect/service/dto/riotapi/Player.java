package com.solodive.championselect.service.dto.riotapi;

public class Player {
    private String platformId;
    private Long accountId;
    private String summonerName;
    private Long summonerId;
    private String currentPlatformId;
    private Long currentAccountId;
    private String matchHistoryUri;
    private Long profileIcon;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(Long summonerId) {
        this.summonerId = summonerId;
    }

    public String getCurrentPlatformId() {
        return currentPlatformId;
    }

    public void setCurrentPlatformId(String currentPlatformId) {
        this.currentPlatformId = currentPlatformId;
    }

    public Long getCurrentAccountId() {
        return currentAccountId;
    }

    public void setCurrentAccountId(Long currentAccountId) {
        this.currentAccountId = currentAccountId;
    }

    public String getMatchHistoryUri() {
        return matchHistoryUri;
    }

    public void setMatchHistoryUri(String matchHistoryUri) {
        this.matchHistoryUri = matchHistoryUri;
    }

    public Long getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(Long profileIcon) {
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
