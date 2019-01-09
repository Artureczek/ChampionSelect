import { ISoloMember } from 'app/shared/model//solo-member.model';

export interface IMemberMember {
    id?: number;
    timesPlayed?: number;
    member?: ISoloMember;
    duo?: ISoloMember;
}

export class MemberMember implements IMemberMember {
    constructor(public id?: number, public timesPlayed?: number, public member?: ISoloMember, public duo?: ISoloMember) {}
}
