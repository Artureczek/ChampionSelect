import { ISoloMember } from 'app/shared/model//solo-member.model';
import { IChampion } from 'app/shared/model//champion.model';

export interface IMostPlayed {
    id?: number;
    timesPlayed?: number;
    member?: ISoloMember;
    champion?: IChampion;
}

export class MostPlayed implements IMostPlayed {
    constructor(public id?: number, public timesPlayed?: number, public member?: ISoloMember, public champion?: IChampion) {}
}
