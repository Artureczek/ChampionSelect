import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChampion } from 'app/shared/model/champion.model';

type EntityResponseType = HttpResponse<IChampion>;
type EntityArrayResponseType = HttpResponse<IChampion[]>;

@Injectable({ providedIn: 'root' })
export class ChampionService {
    private resourceUrl = SERVER_API_URL + 'api/champions';

    constructor(private http: HttpClient) {}

    create(champion: IChampion): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(champion);
        return this.http
            .post<IChampion>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(champion: IChampion): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(champion);
        return this.http
            .put<IChampion>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IChampion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IChampion[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(champion: IChampion): IChampion {
        const copy: IChampion = Object.assign({}, champion, {
            lastModified: champion.lastModified != null && champion.lastModified.isValid() ? champion.lastModified.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.lastModified = res.body.lastModified != null ? moment(res.body.lastModified) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((champion: IChampion) => {
            champion.lastModified = champion.lastModified != null ? moment(champion.lastModified) : null;
        });
        return res;
    }
}
