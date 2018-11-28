package com.solodive.championselect.service.dto.riotapi;

public class RiotChampionInfoDTO {

    private Long attack;
    private Long defense;
    private Long magic;
    private Long difficulty;


    public Long getAttack() {
        return attack;
    }

    public void setAttack(Long attack) {
        this.attack = attack;
    }

    public Long getDefense() {
        return defense;
    }

    public void setDefense(Long defense) {
        this.defense = defense;
    }

    public Long getMagic() {
        return magic;
    }

    public void setMagic(Long magic) {
        this.magic = magic;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "RiotChampionInfoDTO{" +
            "attack=" + attack +
            ", defense=" + defense +
            ", magic=" + magic +
            ", difficulty=" + difficulty +
            '}';
    }
}
