package com.solodive.championselect.domain;

import com.solodive.championselect.service.dto.riotapi.ExtendedSummoner;
import com.solodive.championselect.service.dto.riotapi.SummonerRank;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.solodive.championselect.domain.enumeration.Server;

import com.solodive.championselect.domain.enumeration.Division;

import com.solodive.championselect.domain.enumeration.Tier;

/**
 * A LeagueAccount.
 */
@Entity
@Table(name = "league_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LeagueAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "summoners_id")
    private Long summonersId;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_level")
    private Integer level;

    @Enumerated(EnumType.STRING)
    @Column(name = "server")
    private Server server;

    @Enumerated(EnumType.STRING)
    @Column(name = "division")
    private Division division;

    @Enumerated(EnumType.STRING)
    @Column(name = "tier")
    private Tier tier;

    @Column(name = "lp")
    private Long lp;

    @Column(name = "latest")
    private Boolean latest;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    public LeagueAccount() {
    }

    public LeagueAccount(ExtendedSummoner extendedSummoner) {
        SummonerRank soloQ = new SummonerRank();
        for (SummonerRank summonerRank : extendedSummoner.getSummonerRank()) {
            if (summonerRank.getQueueType().equals("RANKED_SOLO_5x5")) {
                soloQ = summonerRank;
            }
        }
        this.summonersId = extendedSummoner.getBasicSummoner().getAccountId();
        this.division = Division.valueOf(soloQ.getRank());
        this.tier = Tier.valueOf(soloQ.getTier());
        //not present in API??
        this.level = null;
        this.lastUpdate = LocalDate.now();
        this.latest = true;
        this.name = extendedSummoner.getBasicSummoner().getName();
        this.lp = soloQ.getLeaguePoints();
        this.server = Server.EUNE;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSummonersId() {
        return summonersId;
    }

    public LeagueAccount summonersId(Long summonersId) {
        this.summonersId = summonersId;
        return this;
    }

    public void setSummonersId(Long summonersId) {
        this.summonersId = summonersId;
    }

    public String getName() {
        return name;
    }

    public LeagueAccount name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public LeagueAccount level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Server getServer() {
        return server;
    }

    public LeagueAccount server(Server server) {
        this.server = server;
        return this;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Division getDivision() {
        return division;
    }

    public LeagueAccount division(Division division) {
        this.division = division;
        return this;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Tier getTier() {
        return tier;
    }

    public LeagueAccount tier(Tier tier) {
        this.tier = tier;
        return this;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Long getLp() {
        return lp;
    }

    public LeagueAccount lp(Long lp) {
        this.lp = lp;
        return this;
    }

    public void setLp(Long lp) {
        this.lp = lp;
    }

    public Boolean isLatest() {
        return latest;
    }

    public LeagueAccount latest(Boolean latest) {
        this.latest = latest;
        return this;
    }

    public void setLatest(Boolean latest) {
        this.latest = latest;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public LeagueAccount lastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeagueAccount leagueAccount = (LeagueAccount) o;
        if (leagueAccount.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leagueAccount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeagueAccount{" +
            "id=" + getId() +
            ", summonersId=" + getSummonersId() +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", server='" + getServer() + "'" +
            ", division='" + getDivision() + "'" +
            ", tier='" + getTier() + "'" +
            ", lp=" + getLp() +
            ", latest='" + isLatest() + "'" +
            ", lastUpdate='" + getLastUpdate() + "'" +
            "}";
    }
}
