import { Moment } from 'moment';

export const enum Server {
    EUNE = 'EUNE',
    EUW = 'EUW',
    NA = 'NA'
}

export const enum Division {
    IRON = 'IRON',
    BRONZE = 'BRONZE',
    SILVER = 'SILVER',
    GOLD = 'GOLD',
    PLATINUM = 'PLATINUM',
    DIAMOND = 'DIAMOND',
    MASTER = 'MASTER',
    GRANDMASTER = 'GRANDMASTER',
    CHALLENGER = 'CHALLENGER'
}

export const enum Tier {
    I = 'I',
    II = 'II',
    III = 'III',
    IV = 'IV',
    V = 'V',
    NA = 'NA'
}

export interface ILeagueAccount {
    id?: number;
    summonersId?: number;
    name?: string;
    level?: number;
    server?: Server;
    division?: Division;
    tier?: Tier;
    lp?: number;
    latest?: boolean;
    lastUpdate?: Moment;
}

export class LeagueAccount implements ILeagueAccount {
    constructor(
        public id?: number,
        public summonersId?: number,
        public name?: string,
        public level?: number,
        public server?: Server,
        public division?: Division,
        public tier?: Tier,
        public lp?: number,
        public latest?: boolean,
        public lastUpdate?: Moment
    ) {
        this.latest = this.latest || false;
    }
}
