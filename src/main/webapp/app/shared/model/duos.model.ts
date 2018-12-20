import { ISoloMember } from 'app/shared/model//solo-member.model';

export interface IDuos {
    id?: number;
    timesPlayed?: number;
    member?: ISoloMember;
    duo?: ISoloMember;
}

export class Duos implements IDuos {
    constructor(public id?: number, public timesPlayed?: number, public member?: ISoloMember, public duo?: ISoloMember) {}
}
