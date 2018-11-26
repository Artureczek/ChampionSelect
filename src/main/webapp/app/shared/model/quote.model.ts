import { ISoloMember } from 'app/shared/model//solo-member.model';

export interface IQuote {
    id?: number;
    text?: string;
    soloMember?: ISoloMember;
}

export class Quote implements IQuote {
    constructor(public id?: number, public text?: string, public soloMember?: ISoloMember) {}
}
