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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "solo_member_most_played",
               joinColumns = @JoinColumn(name = "solo_members_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "most_playeds_id", referencedColumnName = "id"))
    private Set<Champion> mostPlayeds = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "solo_member_member",
               joinColumns = @JoinColumn(name = "solo_members_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "members_id", referencedColumnName = "id"))
    private Set<SoloMember> members = new HashSet<>();

    @ManyToMany(mappedBy = "members")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SoloMember> partners = new HashSet<>();

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

    public Set<Champion> getMostPlayeds() {
        return mostPlayeds;
    }

    public SoloMember mostPlayeds(Set<Champion> champions) {
        this.mostPlayeds = champions;
        return this;
    }

    public SoloMember addMostPlayed(Champion champion) {
        this.mostPlayeds.add(champion);
        champion.getMembers().add(this);
        return this;
    }

    public SoloMember removeMostPlayed(Champion champion) {
        this.mostPlayeds.remove(champion);
        champion.getMembers().remove(this);
        return this;
    }

    public void setMostPlayeds(Set<Champion> champions) {
        this.mostPlayeds = champions;
    }

    public Set<SoloMember> getMembers() {
        return members;
    }

    public SoloMember members(Set<SoloMember> soloMembers) {
        this.members = soloMembers;
        return this;
    }

    public SoloMember addMember(SoloMember soloMember) {
        this.members.add(soloMember);
        soloMember.getPartners().add(this);
        return this;
    }

    public SoloMember removeMember(SoloMember soloMember) {
        this.members.remove(soloMember);
        soloMember.getPartners().remove(this);
        return this;
    }

    public void setMembers(Set<SoloMember> soloMembers) {
        this.members = soloMembers;
    }

    public Set<SoloMember> getPartners() {
        return partners;
    }

    public SoloMember partners(Set<SoloMember> soloMembers) {
        this.partners = soloMembers;
        return this;
    }

    public SoloMember addPartner(SoloMember soloMember) {
        this.partners.add(soloMember);
        soloMember.getMembers().add(this);
        return this;
    }

    public SoloMember removePartner(SoloMember soloMember) {
        this.partners.remove(soloMember);
        soloMember.getMembers().remove(this);
        return this;
    }

    public void setPartners(Set<SoloMember> soloMembers) {
        this.partners = soloMembers;
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
