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
 * A Champion.
 */
@Entity
@Table(name = "champion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Champion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "riot_id")
    private String riotId;

    @Column(name = "riot_key")
    private Long riotKey;

    @Column(name = "version")
    private String version;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "blurb")
    private String blurb;

    @OneToMany(mappedBy = "champion")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MostPlayed> champions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiotId() {
        return riotId;
    }

    public Champion riotId(String riotId) {
        this.riotId = riotId;
        return this;
    }

    public void setRiotId(String riotId) {
        this.riotId = riotId;
    }

    public Long getRiotKey() {
        return riotKey;
    }

    public Champion riotKey(Long riotKey) {
        this.riotKey = riotKey;
        return this;
    }

    public void setRiotKey(Long riotKey) {
        this.riotKey = riotKey;
    }

    public String getVersion() {
        return version;
    }

    public Champion version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public Champion name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public Champion title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public Champion blurb(String blurb) {
        this.blurb = blurb;
        return this;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public Set<MostPlayed> getChampions() {
        return champions;
    }

    public Champion champions(Set<MostPlayed> mostPlayeds) {
        this.champions = mostPlayeds;
        return this;
    }

    public Champion addChampion(MostPlayed mostPlayed) {
        this.champions.add(mostPlayed);
        mostPlayed.setChampion(this);
        return this;
    }

    public Champion removeChampion(MostPlayed mostPlayed) {
        this.champions.remove(mostPlayed);
        mostPlayed.setChampion(null);
        return this;
    }

    public void setChampions(Set<MostPlayed> mostPlayeds) {
        this.champions = mostPlayeds;
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
        Champion champion = (Champion) o;
        if (champion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), champion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Champion{" +
            "id=" + getId() +
            ", riotId='" + getRiotId() + "'" +
            ", riotKey=" + getRiotKey() +
            ", version='" + getVersion() + "'" +
            ", name='" + getName() + "'" +
            ", title='" + getTitle() + "'" +
            ", blurb='" + getBlurb() + "'" +
            "}";
    }
}
