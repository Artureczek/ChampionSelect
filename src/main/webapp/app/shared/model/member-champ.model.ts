import { ISoloMember } from 'app/shared/model//solo-member.model';
import { IChampion } from 'app/shared/model//champion.model';

export interface IMemberChamp {
    id?: number;
    timesPlayed?: number;
    member?: ISoloMember;
    champion?: IChampion;
}

export class MemberChamp implements IMemberChamp {
    constructor(public id?: number, public timesPlayed?: number, public member?: ISoloMember, public champion?: IChampion) {}
}
