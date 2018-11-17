package com.solodive.championselect.service.dto.riotapi;

public class BasicSummoner {

    private Long id;
    private String name;
    private Long profileIconId;
    private Long summonerLevel;
    private Long revisionDate;
    private Long accountId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Long profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
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
