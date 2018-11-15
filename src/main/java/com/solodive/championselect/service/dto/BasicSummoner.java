package com.solodive.championselect.service.dto;

public class BasicSummoner {

    private long id;
    private String name;
    private long profileIconId;
    private long summonerLevel;
    private long revisionDate;
    private long accountId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(long profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }


    @Override
    public String toString() {
        return "BasicSummoner{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", profileIconId=" + profileIconId +
            ", summonerLevel=" + summonerLevel +
            ", revisionDate=" + revisionDate +
            ", accountId=" + accountId +
            '}';
    }

}
