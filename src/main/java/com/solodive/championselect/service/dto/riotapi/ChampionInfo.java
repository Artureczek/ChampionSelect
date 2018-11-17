package com.solodive.championselect.service.dto.riotapi;

public class ChampionInfo {

    private Integer attack;
    private Integer defense;
    private Integer magic;
    private Integer difficulty;


    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getMagic() {
        return magic;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "ChampionInfo{" +
            "attack=" + attack +
            ", defense=" + defense +
            ", magic=" + magic +
            ", difficulty=" + difficulty +
            '}';
    }
}