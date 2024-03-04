import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { TvShow } from '../models/tv-show';

@Injectable({
  providedIn: 'root',
})
export class TvShowService {
  private url = environment.baseUrl + 'api/shows';

  constructor(private http: HttpClient) {}

  getHttpOptions() {
    const token = localStorage.getItem('authToken');
    let options = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return options;
  }

  index(): Observable<TvShow[]> {
    return this.http.get<TvShow[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TvShowService.index(): error retrieving shows: ' + err));
      })
    );
  }

  show(showId: number): Observable<TvShow> {
    return this.http.get<TvShow>(`${this.url}/${showId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TvShowService.show(): error retrieving TV Show: ' + err));
      })
    );
  }

  create(show: TvShow): Observable<TvShow> {
    return this.http.post<TvShow>(this.url, show, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('TvShowService.create(): error creating TV Show: ' + err));
      })
    );
  }

  update(showId: number, updatedShow: TvShow): Observable<TvShow> {
    return this.http.put<TvShow>(`${this.url}/${showId}`, updatedShow, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('Error updating TV Show:', err);
        return throwError(() => new Error('TvShowService.update(): Failed to update TV Show'));
      })
    );
  }

  destroy(showId: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${showId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('Error deleting TV Show:', err);
        return throwError(() => new Error('TvShowService.destroy(): Failed to delete TV Show'));
      })
    );
  }
}
