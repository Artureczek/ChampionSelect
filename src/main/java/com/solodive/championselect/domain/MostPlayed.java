package com.solodive.championselect.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MostPlayed.
 */
@Entity
@Table(name = "most_played")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MostPlayed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "times_played")
    private Long timesPlayed;

    @ManyToOne
    @JsonIgnoreProperties("players")
    private SoloMember member;

    @ManyToOne
    @JsonIgnoreProperties("champions")
    private Champion champion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimesPlayed() {
        return timesPlayed;
    }

    public MostPlayed timesPlayed(Long timesPlayed) {
        this.timesPlayed = timesPlayed;
        return this;
    }

    public void setTimesPlayed(Long timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public SoloMember getMember() {
        return member;
    }

    public MostPlayed member(SoloMember soloMember) {
        this.member = soloMember;
        return this;
    }

    public void setMember(SoloMember soloMember) {
        this.member = soloMember;
    }

    public Champion getChampion() {
        return champion;
    }

    public MostPlayed champion(Champion champion) {
        this.champion = champion;
        return this;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
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
        MostPlayed mostPlayed = (MostPlayed) o;
        if (mostPlayed.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mostPlayed.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MostPlayed{" +
            "id=" + getId() +
            ", timesPlayed=" + getTimesPlayed() +
            "}";
    }
}
