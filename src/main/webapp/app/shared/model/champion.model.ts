import { ISoloMember } from 'app/shared/model//solo-member.model';

export interface IChampion {
    id?: number;
    riotId?: string;
    riotKey?: number;
    version?: string;
    name?: string;
    title?: string;
    blurb?: string;
    members?: ISoloMember[];
}

export class Champion implements IChampion {
    constructor(
        public id?: number,
        public riotId?: string,
        public riotKey?: number,
        public version?: string,
        public name?: string,
        public title?: string,
        public blurb?: string,
        public members?: ISoloMember[]
    ) {}
}
