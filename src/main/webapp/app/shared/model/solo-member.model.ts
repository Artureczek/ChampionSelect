import { ILeagueAccount } from 'app/shared/model//league-account.model';
import { IQuote } from 'app/shared/model//quote.model';
import { IChampion } from 'app/shared/model//champion.model';
import { ISoloMember } from 'app/shared/model//solo-member.model';

export interface ISoloMember {
    id?: number;
    name?: string;
    surname?: string;
    description?: string;
    hometown?: string;
    account?: ILeagueAccount;
    quotes?: IQuote[];
    mostPlayeds?: IChampion[];
    members?: ISoloMember[];
    partners?: ISoloMember[];
}

export class SoloMember implements ISoloMember {
    constructor(
        public id?: number,
        public name?: string,
        public surname?: string,
        public description?: string,
        public hometown?: string,
        public account?: ILeagueAccount,
        public quotes?: IQuote[],
        public mostPlayeds?: IChampion[],
        public members?: ISoloMember[],
        public partners?: ISoloMember[]
    ) {}
}
