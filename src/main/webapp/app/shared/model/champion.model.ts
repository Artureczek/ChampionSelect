import { Moment } from 'moment';
import { IChampionTag } from 'app/shared/model//champion-tag.model';

export const enum ChampionResourceType {
    HEALTH = 'HEALTH',
    MANA = 'MANA',
    ENERGY = 'ENERGY',
    BATTLEFURY = 'BATTLEFURY'
}

export interface IChampion {
    id?: number;
    internalVersion?: number;
    lastModified?: Moment;
    riotId?: string;
    riotKey?: string;
    version?: string;
    name?: string;
    title?: string;
    blurb?: string;
    attack?: number;
    defence?: number;
    magic?: number;
    difficulty?: number;
    movementSpeed?: number;
    attackRange?: number;
    armorFlat?: number;
    armorPerLevel?: number;
    spellBlockFlat?: number;
    spellBlockPerLevel?: number;
    criticalFlat?: number;
    criticalPerLevel?: number;
    attackDamageFlat?: number;
    attackDamagePerLevel?: number;
    attackSpeedFlat?: number;
    attackSpeedPerLevel?: number;
    resourceFirstType?: ChampionResourceType;
    resourceSecondType?: ChampionResourceType;
    resourceFirstPoolFlat?: number;
    resourceFirstPoolPerLevel?: number;
    resourceFirstRegenerationFlat?: number;
    resourceFirstRegenerationPerLevel?: number;
    resourceSecondPoolFlat?: number;
    resourceSecondPoolPerLevel?: number;
    resourceSecondRegenerationFlat?: number;
    resourceSecondRegenerationPerLevel?: number;
    tags?: IChampionTag[];
}

export class Champion implements IChampion {
    constructor(
        public id?: number,
        public internalVersion?: number,
        public lastModified?: Moment,
        public riotId?: string,
        public riotKey?: string,
        public version?: string,
        public name?: string,
        public title?: string,
        public blurb?: string,
        public attack?: number,
        public defence?: number,
        public magic?: number,
        public difficulty?: number,
        public movementSpeed?: number,
        public attackRange?: number,
        public armorFlat?: number,
        public armorPerLevel?: number,
        public spellBlockFlat?: number,
        public spellBlockPerLevel?: number,
        public criticalFlat?: number,
        public criticalPerLevel?: number,
        public attackDamageFlat?: number,
        public attackDamagePerLevel?: number,
        public attackSpeedFlat?: number,
        public attackSpeedPerLevel?: number,
        public resourceFirstType?: ChampionResourceType,
        public resourceSecondType?: ChampionResourceType,
        public resourceFirstPoolFlat?: number,
        public resourceFirstPoolPerLevel?: number,
        public resourceFirstRegenerationFlat?: number,
        public resourceFirstRegenerationPerLevel?: number,
        public resourceSecondPoolFlat?: number,
        public resourceSecondPoolPerLevel?: number,
        public resourceSecondRegenerationFlat?: number,
        public resourceSecondRegenerationPerLevel?: number,
        public tags?: IChampionTag[]
    ) {}
}
