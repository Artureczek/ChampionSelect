import { IChampion } from 'app/shared/model//champion.model';

export const enum ChampionTagValue {
    FIGHTER = 'FIGHTER',
    TANK = 'TANK',
    MAGE = 'MAGE',
    ASSASSIN = 'ASSASSIN'
}

export interface IChampionTag {
    id?: number;
    tag?: ChampionTagValue;
    champions?: IChampion[];
}

export class ChampionTag implements IChampionTag {
    constructor(public id?: number, public tag?: ChampionTagValue, public champions?: IChampion[]) {}
}
