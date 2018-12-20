package com.solodive.championselect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SoloMember.
 */
@Entity
@Table(name = "solo_member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SoloMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "description")
    private String description;

    @Column(name = "hometown")
    private String hometown;

    @OneToOne
    @JoinColumn(unique = true)
    private LeagueAccount account;

    @OneToMany(mappedBy = "soloMember")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Quote> quotes = new HashSet<>();

    @OneToMany(mappedBy = "member")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Duos> members = new HashSet<>();

    @OneToMany(mappedBy = "duo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Duos> duos = new HashSet<>();

    @OneToMany(mappedBy = "member")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MostPlayed> players = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SoloMember name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public SoloMember surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public SoloMember description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHometown() {
        return hometown;
    }

    public SoloMember hometown(String hometown) {
        this.hometown = hometown;
        return this;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public LeagueAccount getAccount() {
        return account;
    }

    public SoloMember account(LeagueAccount leagueAccount) {
        this.account = leagueAccount;
        return this;
    }

    public void setAccount(LeagueAccount leagueAccount) {
        this.account = leagueAccount;
    }

    public Set<Quote> getQuotes() {
        return quotes;
    }

    public SoloMember quotes(Set<Quote> quotes) {
        this.quotes = quotes;
        return this;
    }

    public SoloMember addQuote(Quote quote) {
        this.quotes.add(quote);
        quote.setSoloMember(this);
        return this;
    }

    public SoloMember removeQuote(Quote quote) {
        this.quotes.remove(quote);
        quote.setSoloMember(null);
        return this;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }

    public Set<Duos> getMembers() {
        return members;
    }

    public SoloMember members(Set<Duos> duos) {
        this.members = duos;
        return this;
    }

    public SoloMember addMember(Duos duos) {
        this.members.add(duos);
        duos.setMember(this);
        return this;
    }

    public SoloMember removeMember(Duos duos) {
        this.members.remove(duos);
        duos.setMember(null);
        return this;
    }

    public void setMembers(Set<Duos> duos) {
        this.members = duos;
    }

    public Set<Duos> getDuos() {
        return duos;
    }

    public SoloMember duos(Set<Duos> duos) {
        this.duos = duos;
        return this;
    }

    public SoloMember addDuo(Duos duos) {
        this.duos.add(duos);
        duos.setDuo(this);
        return this;
    }

    public SoloMember removeDuo(Duos duos) {
        this.duos.remove(duos);
        duos.setDuo(null);
        return this;
    }

    public void setDuos(Set<Duos> duos) {
        this.duos = duos;
    }

    public Set<MostPlayed> getPlayers() {
        return players;
    }

    public SoloMember players(Set<MostPlayed> mostPlayeds) {
        this.players = mostPlayeds;
        return this;
    }

    public SoloMember addPlayer(MostPlayed mostPlayed) {
        this.players.add(mostPlayed);
        mostPlayed.setMember(this);
        return this;
    }

    public SoloMember removePlayer(MostPlayed mostPlayed) {
        this.players.remove(mostPlayed);
        mostPlayed.setMember(null);
        return this;
    }

    public void setPlayers(Set<MostPlayed> mostPlayeds) {
        this.players = mostPlayeds;
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
        SoloMember soloMember = (SoloMember) o;
        if (soloMember.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), soloMember.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SoloMember{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", description='" + getDescription() + "'" +
            ", hometown='" + getHometown() + "'" +
            "}";
    }
}
