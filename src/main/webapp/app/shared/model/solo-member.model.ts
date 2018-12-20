import { ILeagueAccount } from 'app/shared/model//league-account.model';
import { IQuote } from 'app/shared/model//quote.model';
import { IDuos } from 'app/shared/model//duos.model';
import { IMostPlayed } from 'app/shared/model//most-played.model';

export interface ISoloMember {
    id?: number;
    name?: string;
    surname?: string;
    description?: string;
    hometown?: string;
    account?: ILeagueAccount;
    quotes?: IQuote[];
    members?: IDuos[];
    duos?: IDuos[];
    players?: IMostPlayed[];
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
        public members?: IDuos[],
        public duos?: IDuos[],
        public players?: IMostPlayed[]
    ) {}
}
