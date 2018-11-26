import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISoloMember } from 'app/shared/model/solo-member.model';

type EntityResponseType = HttpResponse<ISoloMember>;
type EntityArrayResponseType = HttpResponse<ISoloMember[]>;

@Injectable({ providedIn: 'root' })
export class SoloMemberService {
    private resourceUrl = SERVER_API_URL + 'api/solo-members';

    constructor(private http: HttpClient) {}

    create(soloMember: ISoloMember): Observable<EntityResponseType> {
        return this.http.post<ISoloMember>(this.resourceUrl, soloMember, { observe: 'response' });
    }

    update(soloMember: ISoloMember): Observable<EntityResponseType> {
        return this.http.put<ISoloMember>(this.resourceUrl, soloMember, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISoloMember>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISoloMember[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
