package com.solodive.championselect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.solodive.championselect.domain.enumeration.ChampionTagValue;

/**
 * A ChampionTag.
 */
@Entity
@Table(name = "champion_tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChampionTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = false)
    private ChampionTagValue tag;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Champion> champions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChampionTagValue getTag() {
        return tag;
    }

    public ChampionTag tag(ChampionTagValue tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(ChampionTagValue tag) {
        this.tag = tag;
    }

    public Set<Champion> getChampions() {
        return champions;
    }

    public ChampionTag champions(Set<Champion> champions) {
        this.champions = champions;
        return this;
    }

    public ChampionTag addChampions(Champion champion) {
        this.champions.add(champion);
        champion.getTags().add(this);
        return this;
    }

    public ChampionTag removeChampions(Champion champion) {
        this.champions.remove(champion);
        champion.getTags().remove(this);
        return this;
    }

    public void setChampions(Set<Champion> champions) {
        this.champions = champions;
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
        ChampionTag championTag = (ChampionTag) o;
        if (championTag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), championTag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChampionTag{" +
            "id=" + getId() +
            ", tag='" + getTag() + "'" +
            "}";
    }
}
