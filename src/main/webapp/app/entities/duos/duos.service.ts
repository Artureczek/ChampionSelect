import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDuos } from 'app/shared/model/duos.model';

type EntityResponseType = HttpResponse<IDuos>;
type EntityArrayResponseType = HttpResponse<IDuos[]>;

@Injectable({ providedIn: 'root' })
export class DuosService {
    private resourceUrl = SERVER_API_URL + 'api/duos';

    constructor(private http: HttpClient) {}

    create(duos: IDuos): Observable<EntityResponseType> {
        return this.http.post<IDuos>(this.resourceUrl, duos, { observe: 'response' });
    }

    update(duos: IDuos): Observable<EntityResponseType> {
        return this.http.put<IDuos>(this.resourceUrl, duos, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDuos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDuos[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
