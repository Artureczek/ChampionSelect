package com.solodive.championselect.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Image.
 */
@Entity
@Table(name = "image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "width_start")
    private Long widthStart;

    @Column(name = "height_start")
    private Long heightStart;

    @Column(name = "width_end")
    private Long widthEnd;

    @Column(name = "height_end")
    private Long heightEnd;

    @Column(name = "jhi_group")
    private String group;

    @Column(name = "sprite")
    private String sprite;

    @Column(name = "jhi_full")
    private String full;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWidthStart() {
        return widthStart;
    }

    public Image widthStart(Long widthStart) {
        this.widthStart = widthStart;
        return this;
    }

    public void setWidthStart(Long widthStart) {
        this.widthStart = widthStart;
    }

    public Long getHeightStart() {
        return heightStart;
    }

    public Image heightStart(Long heightStart) {
        this.heightStart = heightStart;
        return this;
    }

    public void setHeightStart(Long heightStart) {
        this.heightStart = heightStart;
    }

    public Long getWidthEnd() {
        return widthEnd;
    }

    public Image widthEnd(Long widthEnd) {
        this.widthEnd = widthEnd;
        return this;
    }

    public void setWidthEnd(Long widthEnd) {
        this.widthEnd = widthEnd;
    }

    public Long getHeightEnd() {
        return heightEnd;
    }

    public Image heightEnd(Long heightEnd) {
        this.heightEnd = heightEnd;
        return this;
    }

    public void setHeightEnd(Long heightEnd) {
        this.heightEnd = heightEnd;
    }

    public String getGroup() {
        return group;
    }

    public Image group(String group) {
        this.group = group;
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSprite() {
        return sprite;
    }

    public Image sprite(String sprite) {
        this.sprite = sprite;
        return this;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getFull() {
        return full;
    }

    public Image full(String full) {
        this.full = full;
        return this;
    }

    public void setFull(String full) {
        this.full = full;
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
        Image image = (Image) o;
        if (image.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), image.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Image{" +
            "id=" + getId() +
            ", widthStart=" + getWidthStart() +
            ", heightStart=" + getHeightStart() +
            ", widthEnd=" + getWidthEnd() +
            ", heightEnd=" + getHeightEnd() +
            ", group='" + getGroup() + "'" +
            ", sprite='" + getSprite() + "'" +
            ", full='" + getFull() + "'" +
            "}";
    }
}
