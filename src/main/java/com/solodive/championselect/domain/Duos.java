package com.solodive.championselect.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Duos.
 */
@Entity
@Table(name = "duos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Duos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "times_played")
    private Long timesPlayed;

    @ManyToOne
    @JsonIgnoreProperties("members")
    private SoloMember member;

    @ManyToOne
    @JsonIgnoreProperties("duos")
    private SoloMember duo;

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

    public Duos timesPlayed(Long timesPlayed) {
        this.timesPlayed = timesPlayed;
        return this;
    }

    public void setTimesPlayed(Long timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public SoloMember getMember() {
        return member;
    }

    public Duos member(SoloMember soloMember) {
        this.member = soloMember;
        return this;
    }

    public void setMember(SoloMember soloMember) {
        this.member = soloMember;
    }

    public SoloMember getDuo() {
        return duo;
    }

    public Duos duo(SoloMember soloMember) {
        this.duo = soloMember;
        return this;
    }

    public void setDuo(SoloMember soloMember) {
        this.duo = soloMember;
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
        Duos duos = (Duos) o;
        if (duos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), duos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Duos{" +
            "id=" + getId() +
            ", timesPlayed=" + getTimesPlayed() +
            "}";
    }
}
