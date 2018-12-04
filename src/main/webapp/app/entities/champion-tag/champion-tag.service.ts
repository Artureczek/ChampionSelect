import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChampionTag } from 'app/shared/model/champion-tag.model';

type EntityResponseType = HttpResponse<IChampionTag>;
type EntityArrayResponseType = HttpResponse<IChampionTag[]>;

@Injectable({ providedIn: 'root' })
export class ChampionTagService {
    private resourceUrl = SERVER_API_URL + 'api/champion-tags';

    constructor(private http: HttpClient) {}

    create(championTag: IChampionTag): Observable<EntityResponseType> {
        return this.http.post<IChampionTag>(this.resourceUrl, championTag, { observe: 'response' });
    }

    update(championTag: IChampionTag): Observable<EntityResponseType> {
        return this.http.put<IChampionTag>(this.resourceUrl, championTag, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IChampionTag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IChampionTag[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
