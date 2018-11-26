export interface IImage {
    id?: number;
    widthStart?: number;
    heightStart?: number;
    widthEnd?: number;
    heightEnd?: number;
    group?: string;
    sprite?: string;
    full?: string;
}

export class Image implements IImage {
    constructor(
        public id?: number,
        public widthStart?: number,
        public heightStart?: number,
        public widthEnd?: number,
        public heightEnd?: number,
        public group?: string,
        public sprite?: string,
        public full?: string
    ) {}
}
