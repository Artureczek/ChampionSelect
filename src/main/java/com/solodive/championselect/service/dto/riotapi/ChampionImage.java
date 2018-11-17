package com.solodive.championselect.service.dto.riotapi;

public class ChampionImage {

    private String full;
    private String sprite;
    private String group;
    private Long x;
    private Long y;
    private Long w;
    private Long h;


    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getW() {
        return w;
    }

    public void setW(Long w) {
        this.w = w;
    }

    public Long getH() {
        return h;
    }

    public void setH(Long h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "ChampionImage{" +
            "full='" + full + '\'' +
            ", sprite='" + sprite + '\'' +
            ", group='" + group + '\'' +
            ", x=" + x +
            ", y=" + y +
            ", w=" + w +
            ", h=" + h +
            '}';
    }
}
