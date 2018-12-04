package com.solodive.championselect.domain;

import com.solodive.championselect.service.dto.riotapi.RiotChampionDTO;
import com.solodive.championselect.service.dto.riotapi.RiotChampionInfoDTO;
import com.solodive.championselect.service.dto.riotapi.RiotChampionStatsDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.solodive.championselect.domain.enumeration.ChampionResourceType;

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

    @NotNull
    @Column(name = "internal_version", nullable = false)
    private Long internalVersion;

    @NotNull
    @Column(name = "last_modified", nullable = false)
    private Instant lastModified;

    @Column(name = "riot_id")
    private String riotId;

    @Column(name = "riot_key")
    private String riotKey;

    @Column(name = "version")
    private String version;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "blurb")
    private String blurb;

    @Column(name = "attack")
    private Long attack;

    @Column(name = "defence")
    private Long defence;

    @Column(name = "magic")
    private Long magic;

    @Column(name = "difficulty")
    private Long difficulty;

    @Column(name = "movement_speed")
    private Double movementSpeed;

    @Column(name = "attack_range")
    private Double attackRange;

    @Column(name = "armor_flat")
    private Double armorFlat;

    @Column(name = "armor_per_level")
    private Double armorPerLevel;

    @Column(name = "spell_block_flat")
    private Double spellBlockFlat;

    @Column(name = "spell_block_per_level")
    private Double spellBlockPerLevel;

    @Column(name = "critical_flat")
    private Double criticalFlat;

    @Column(name = "critical_per_level")
    private Double criticalPerLevel;

    @Column(name = "attack_damage_flat")
    private Double attackDamageFlat;

    @Column(name = "attack_damage_per_level")
    private Double attackDamagePerLevel;

    @Column(name = "attack_speed_flat")
    private Double attackSpeedFlat;

    @Column(name = "attack_speed_per_level")
    private Double attackSpeedPerLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_first_type")
    private ChampionResourceType resourceFirstType;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_second_type")
    private ChampionResourceType resourceSecondType;

    @Column(name = "resource_first_pool_flat")
    private Double resourceFirstPoolFlat;

    @Column(name = "resource_first_pool_per_level")
    private Double resourceFirstPoolPerLevel;

    @Column(name = "resource_first_regeneration_flat")
    private Double resourceFirstRegenerationFlat;

    @Column(name = "resource_first_regeneration_per_level")
    private Double resourceFirstRegenerationPerLevel;

    @Column(name = "resource_second_pool_flat")
    private Double resourceSecondPoolFlat;

    @Column(name = "resource_second_pool_per_level")
    private Double resourceSecondPoolPerLevel;

    @Column(name = "resource_second_regeneration_flat")
    private Double resourceSecondRegenerationFlat;

    @Column(name = "resource_second_regeneration_per_level")
    private Double resourceSecondRegenerationPerLevel;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "champion_tags",
               joinColumns = @JoinColumn(name = "champions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id"))
    private Set<ChampionTag> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Champion() {}

    public Champion(RiotChampionDTO riotChampionDTO) {

        this
            .riotId(riotChampionDTO.getId())
            .riotKey(riotChampionDTO.getKey())
            .version(riotChampionDTO.getVersion())
            .name(riotChampionDTO.getName())
            .title(riotChampionDTO.getTitle())
            .blurb(riotChampionDTO.getBlurb())
            .resourceFirstType(ChampionResourceType.HEALTH)
            .resourceSecondType(ChampionResourceType.getType(riotChampionDTO.getPartype()));

        RiotChampionInfoDTO riotChampionInfoDTO = riotChampionDTO.getInfo();
        if (riotChampionInfoDTO != null) {
            this
                .attack(riotChampionInfoDTO.getAttack())
                .defence(riotChampionInfoDTO.getDefense())
                .magic(riotChampionInfoDTO.getMagic())
                .difficulty(riotChampionInfoDTO.getDifficulty());
        }

        RiotChampionStatsDTO riotChampionStatsDTO = riotChampionDTO.getStats();
        if (riotChampionStatsDTO != null) {
            this
                .movementSpeed(riotChampionStatsDTO.getMovespeed())
                .attackRange(riotChampionStatsDTO.getAttackrange())
                .armorFlat(riotChampionStatsDTO.getArmor())
                .armorPerLevel(riotChampionStatsDTO.getArmorperlevel())
                .spellBlockFlat(riotChampionStatsDTO.getSpellblock())
                .spellBlockPerLevel(riotChampionStatsDTO.getSpellblockperlevel())
                .criticalFlat(riotChampionStatsDTO.getCrit())
                .criticalPerLevel(riotChampionStatsDTO.getCritperlevel())
                .attackDamageFlat(riotChampionStatsDTO.getAttackdamage())
                .attackDamagePerLevel(riotChampionStatsDTO.getAttackdamageperlevel())
                .attackSpeedFlat(riotChampionStatsDTO.getAttackspeedoffset())
                .attackSpeedPerLevel(riotChampionStatsDTO.getAttackspeedperlevel())
                .resourceFirstPoolFlat(riotChampionStatsDTO.getHp())
                .resourceFirstPoolPerLevel(riotChampionStatsDTO.getHpperlevel())
                .resourceFirstRegenerationFlat(riotChampionStatsDTO.getHpregen())
                .resourceFirstRegenerationPerLevel(riotChampionStatsDTO.getHpregenperlevel())
                .resourceSecondPoolFlat(riotChampionStatsDTO.getMp())
                .resourceSecondPoolPerLevel(riotChampionStatsDTO.getMpperlevel())
                .resourceSecondRegenerationFlat(riotChampionStatsDTO.getMpregen())
                .resourceSecondRegenerationPerLevel(riotChampionStatsDTO.getMpregenperlevel());
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInternalVersion() {
        return internalVersion;
    }

    public Champion internalVersion(Long internalVersion) {
        this.internalVersion = internalVersion;
        return this;
    }

    public void setInternalVersion(Long internalVersion) {
        this.internalVersion = internalVersion;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public Champion lastModified(Instant lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
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

    public String getRiotKey() {
        return riotKey;
    }

    public Champion riotKey(String riotKey) {
        this.riotKey = riotKey;
        return this;
    }

    public void setRiotKey(String riotKey) {
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

    public Long getAttack() {
        return attack;
    }

    public Champion attack(Long attack) {
        this.attack = attack;
        return this;
    }

    public void setAttack(Long attack) {
        this.attack = attack;
    }

    public Long getDefence() {
        return defence;
    }

    public Champion defence(Long defence) {
        this.defence = defence;
        return this;
    }

    public void setDefence(Long defence) {
        this.defence = defence;
    }

    public Long getMagic() {
        return magic;
    }

    public Champion magic(Long magic) {
        this.magic = magic;
        return this;
    }

    public void setMagic(Long magic) {
        this.magic = magic;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public Champion difficulty(Long difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public Double getMovementSpeed() {
        return movementSpeed;
    }

    public Champion movementSpeed(Double movementSpeed) {
        this.movementSpeed = movementSpeed;
        return this;
    }

    public void setMovementSpeed(Double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public Double getAttackRange() {
        return attackRange;
    }

    public Champion attackRange(Double attackRange) {
        this.attackRange = attackRange;
        return this;
    }

    public void setAttackRange(Double attackRange) {
        this.attackRange = attackRange;
    }

    public Double getArmorFlat() {
        return armorFlat;
    }

    public Champion armorFlat(Double armorFlat) {
        this.armorFlat = armorFlat;
        return this;
    }

    public void setArmorFlat(Double armorFlat) {
        this.armorFlat = armorFlat;
    }

    public Double getArmorPerLevel() {
        return armorPerLevel;
    }

    public Champion armorPerLevel(Double armorPerLevel) {
        this.armorPerLevel = armorPerLevel;
        return this;
    }

    public void setArmorPerLevel(Double armorPerLevel) {
        this.armorPerLevel = armorPerLevel;
    }

    public Double getSpellBlockFlat() {
        return spellBlockFlat;
    }

    public Champion spellBlockFlat(Double spellBlockFlat) {
        this.spellBlockFlat = spellBlockFlat;
        return this;
    }

    public void setSpellBlockFlat(Double spellBlockFlat) {
        this.spellBlockFlat = spellBlockFlat;
    }

    public Double getSpellBlockPerLevel() {
        return spellBlockPerLevel;
    }

    public Champion spellBlockPerLevel(Double spellBlockPerLevel) {
        this.spellBlockPerLevel = spellBlockPerLevel;
        return this;
    }

    public void setSpellBlockPerLevel(Double spellBlockPerLevel) {
        this.spellBlockPerLevel = spellBlockPerLevel;
    }

    public Double getCriticalFlat() {
        return criticalFlat;
    }

    public Champion criticalFlat(Double criticalFlat) {
        this.criticalFlat = criticalFlat;
        return this;
    }

    public void setCriticalFlat(Double criticalFlat) {
        this.criticalFlat = criticalFlat;
    }

    public Double getCriticalPerLevel() {
        return criticalPerLevel;
    }

    public Champion criticalPerLevel(Double criticalPerLevel) {
        this.criticalPerLevel = criticalPerLevel;
        return this;
    }

    public void setCriticalPerLevel(Double criticalPerLevel) {
        this.criticalPerLevel = criticalPerLevel;
    }

    public Double getAttackDamageFlat() {
        return attackDamageFlat;
    }

    public Champion attackDamageFlat(Double attackDamageFlat) {
        this.attackDamageFlat = attackDamageFlat;
        return this;
    }

    public void setAttackDamageFlat(Double attackDamageFlat) {
        this.attackDamageFlat = attackDamageFlat;
    }

    public Double getAttackDamagePerLevel() {
        return attackDamagePerLevel;
    }

    public Champion attackDamagePerLevel(Double attackDamagePerLevel) {
        this.attackDamagePerLevel = attackDamagePerLevel;
        return this;
    }

    public void setAttackDamagePerLevel(Double attackDamagePerLevel) {
        this.attackDamagePerLevel = attackDamagePerLevel;
    }

    public Double getAttackSpeedFlat() {
        return attackSpeedFlat;
    }

    public Champion attackSpeedFlat(Double attackSpeedFlat) {
        this.attackSpeedFlat = attackSpeedFlat;
        return this;
    }

    public void setAttackSpeedFlat(Double attackSpeedFlat) {
        this.attackSpeedFlat = attackSpeedFlat;
    }

    public Double getAttackSpeedPerLevel() {
        return attackSpeedPerLevel;
    }

    public Champion attackSpeedPerLevel(Double attackSpeedPerLevel) {
        this.attackSpeedPerLevel = attackSpeedPerLevel;
        return this;
    }

    public void setAttackSpeedPerLevel(Double attackSpeedPerLevel) {
        this.attackSpeedPerLevel = attackSpeedPerLevel;
    }

    public ChampionResourceType getResourceFirstType() {
        return resourceFirstType;
    }

    public Champion resourceFirstType(ChampionResourceType resourceFirstType) {
        this.resourceFirstType = resourceFirstType;
        return this;
    }

    public void setResourceFirstType(ChampionResourceType resourceFirstType) {
        this.resourceFirstType = resourceFirstType;
    }

    public ChampionResourceType getResourceSecondType() {
        return resourceSecondType;
    }

    public Champion resourceSecondType(ChampionResourceType resourceSecondType) {
        this.resourceSecondType = resourceSecondType;
        return this;
    }

    public void setResourceSecondType(ChampionResourceType resourceSecondType) {
        this.resourceSecondType = resourceSecondType;
    }

    public Double getResourceFirstPoolFlat() {
        return resourceFirstPoolFlat;
    }

    public Champion resourceFirstPoolFlat(Double resourceFirstPoolFlat) {
        this.resourceFirstPoolFlat = resourceFirstPoolFlat;
        return this;
    }

    public void setResourceFirstPoolFlat(Double resourceFirstPoolFlat) {
        this.resourceFirstPoolFlat = resourceFirstPoolFlat;
    }

    public Double getResourceFirstPoolPerLevel() {
        return resourceFirstPoolPerLevel;
    }

    public Champion resourceFirstPoolPerLevel(Double resourceFirstPoolPerLevel) {
        this.resourceFirstPoolPerLevel = resourceFirstPoolPerLevel;
        return this;
    }

    public void setResourceFirstPoolPerLevel(Double resourceFirstPoolPerLevel) {
        this.resourceFirstPoolPerLevel = resourceFirstPoolPerLevel;
    }

    public Double getResourceFirstRegenerationFlat() {
        return resourceFirstRegenerationFlat;
    }

    public Champion resourceFirstRegenerationFlat(Double resourceFirstRegenerationFlat) {
        this.resourceFirstRegenerationFlat = resourceFirstRegenerationFlat;
        return this;
    }

    public void setResourceFirstRegenerationFlat(Double resourceFirstRegenerationFlat) {
        this.resourceFirstRegenerationFlat = resourceFirstRegenerationFlat;
    }

    public Double getResourceFirstRegenerationPerLevel() {
        return resourceFirstRegenerationPerLevel;
    }

    public Champion resourceFirstRegenerationPerLevel(Double resourceFirstRegenerationPerLevel) {
        this.resourceFirstRegenerationPerLevel = resourceFirstRegenerationPerLevel;
        return this;
    }

    public void setResourceFirstRegenerationPerLevel(Double resourceFirstRegenerationPerLevel) {
        this.resourceFirstRegenerationPerLevel = resourceFirstRegenerationPerLevel;
    }

    public Double getResourceSecondPoolFlat() {
        return resourceSecondPoolFlat;
    }

    public Champion resourceSecondPoolFlat(Double resourceSecondPoolFlat) {
        this.resourceSecondPoolFlat = resourceSecondPoolFlat;
        return this;
    }

    public void setResourceSecondPoolFlat(Double resourceSecondPoolFlat) {
        this.resourceSecondPoolFlat = resourceSecondPoolFlat;
    }

    public Double getResourceSecondPoolPerLevel() {
        return resourceSecondPoolPerLevel;
    }

    public Champion resourceSecondPoolPerLevel(Double resourceSecondPoolPerLevel) {
        this.resourceSecondPoolPerLevel = resourceSecondPoolPerLevel;
        return this;
    }

    public void setResourceSecondPoolPerLevel(Double resourceSecondPoolPerLevel) {
        this.resourceSecondPoolPerLevel = resourceSecondPoolPerLevel;
    }

    public Double getResourceSecondRegenerationFlat() {
        return resourceSecondRegenerationFlat;
    }

    public Champion resourceSecondRegenerationFlat(Double resourceSecondRegenerationFlat) {
        this.resourceSecondRegenerationFlat = resourceSecondRegenerationFlat;
        return this;
    }

    public void setResourceSecondRegenerationFlat(Double resourceSecondRegenerationFlat) {
        this.resourceSecondRegenerationFlat = resourceSecondRegenerationFlat;
    }

    public Double getResourceSecondRegenerationPerLevel() {
        return resourceSecondRegenerationPerLevel;
    }

    public Champion resourceSecondRegenerationPerLevel(Double resourceSecondRegenerationPerLevel) {
        this.resourceSecondRegenerationPerLevel = resourceSecondRegenerationPerLevel;
        return this;
    }

    public void setResourceSecondRegenerationPerLevel(Double resourceSecondRegenerationPerLevel) {
        this.resourceSecondRegenerationPerLevel = resourceSecondRegenerationPerLevel;
    }

    public Set<ChampionTag> getTags() {
        return tags;
    }

    public Champion tags(Set<ChampionTag> championTags) {
        this.tags = championTags;
        return this;
    }

    public Champion addTags(ChampionTag championTag) {
        this.tags.add(championTag);
        championTag.getChampions().add(this);
        return this;
    }

    public Champion removeTags(ChampionTag championTag) {
        this.tags.remove(championTag);
        championTag.getChampions().remove(this);
        return this;
    }

    public void setTags(Set<ChampionTag> championTags) {
        this.tags = championTags;
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
            ", internalVersion=" + getInternalVersion() +
            ", lastModified='" + getLastModified() + "'" +
            ", riotId='" + getRiotId() + "'" +
            ", riotKey='" + getRiotKey() + "'" +
            ", version='" + getVersion() + "'" +
            ", name='" + getName() + "'" +
            ", title='" + getTitle() + "'" +
            ", blurb='" + getBlurb() + "'" +
            ", attack=" + getAttack() +
            ", defence=" + getDefence() +
            ", magic=" + getMagic() +
            ", difficulty=" + getDifficulty() +
            ", movementSpeed=" + getMovementSpeed() +
            ", attackRange=" + getAttackRange() +
            ", armorFlat=" + getArmorFlat() +
            ", armorPerLevel=" + getArmorPerLevel() +
            ", spellBlockFlat=" + getSpellBlockFlat() +
            ", spellBlockPerLevel=" + getSpellBlockPerLevel() +
            ", criticalFlat=" + getCriticalFlat() +
            ", criticalPerLevel=" + getCriticalPerLevel() +
            ", attackDamageFlat=" + getAttackDamageFlat() +
            ", attackDamagePerLevel=" + getAttackDamagePerLevel() +
            ", attackSpeedFlat=" + getAttackSpeedFlat() +
            ", attackSpeedPerLevel=" + getAttackSpeedPerLevel() +
            ", resourceFirstType='" + getResourceFirstType() + "'" +
            ", resourceSecondType='" + getResourceSecondType() + "'" +
            ", resourceFirstPoolFlat=" + getResourceFirstPoolFlat() +
            ", resourceFirstPoolPerLevel=" + getResourceFirstPoolPerLevel() +
            ", resourceFirstRegenerationFlat=" + getResourceFirstRegenerationFlat() +
            ", resourceFirstRegenerationPerLevel=" + getResourceFirstRegenerationPerLevel() +
            ", resourceSecondPoolFlat=" + getResourceSecondPoolFlat() +
            ", resourceSecondPoolPerLevel=" + getResourceSecondPoolPerLevel() +
            ", resourceSecondRegenerationFlat=" + getResourceSecondRegenerationFlat() +
            ", resourceSecondRegenerationPerLevel=" + getResourceSecondRegenerationPerLevel() +
            "}";
    }
}
