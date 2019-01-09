import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMostPlayed } from 'app/shared/model/most-played.model';

type EntityResponseType = HttpResponse<IMostPlayed>;
type EntityArrayResponseType = HttpResponse<IMostPlayed[]>;

@Injectable({ providedIn: 'root' })
export class MostPlayedService {
    private resourceUrl = SERVER_API_URL + 'api/most-playeds';

    constructor(private http: HttpClient) {}

    create(mostPlayed: IMostPlayed): Observable<EntityResponseType> {
        return this.http.post<IMostPlayed>(this.resourceUrl, mostPlayed, { observe: 'response' });
    }

    update(mostPlayed: IMostPlayed): Observable<EntityResponseType> {
        return this.http.put<IMostPlayed>(this.resourceUrl, mostPlayed, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMostPlayed>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMostPlayed[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
