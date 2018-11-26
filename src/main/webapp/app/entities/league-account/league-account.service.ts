import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILeagueAccount } from 'app/shared/model/league-account.model';

type EntityResponseType = HttpResponse<ILeagueAccount>;
type EntityArrayResponseType = HttpResponse<ILeagueAccount[]>;

@Injectable({ providedIn: 'root' })
export class LeagueAccountService {
    private resourceUrl = SERVER_API_URL + 'api/league-accounts';

    constructor(private http: HttpClient) {}

    create(leagueAccount: ILeagueAccount): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(leagueAccount);
        return this.http
            .post<ILeagueAccount>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(leagueAccount: ILeagueAccount): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(leagueAccount);
        return this.http
            .put<ILeagueAccount>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILeagueAccount>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILeagueAccount[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(leagueAccount: ILeagueAccount): ILeagueAccount {
        const copy: ILeagueAccount = Object.assign({}, leagueAccount, {
            lastUpdate:
                leagueAccount.lastUpdate != null && leagueAccount.lastUpdate.isValid() ? leagueAccount.lastUpdate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.lastUpdate = res.body.lastUpdate != null ? moment(res.body.lastUpdate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((leagueAccount: ILeagueAccount) => {
            leagueAccount.lastUpdate = leagueAccount.lastUpdate != null ? moment(leagueAccount.lastUpdate) : null;
        });
        return res;
    }
}
