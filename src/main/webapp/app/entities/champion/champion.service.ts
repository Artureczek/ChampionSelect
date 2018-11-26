import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
        return this.http.post<IChampion>(this.resourceUrl, champion, { observe: 'response' });
    }

    update(champion: IChampion): Observable<EntityResponseType> {
        return this.http.put<IChampion>(this.resourceUrl, champion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IChampion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IChampion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
