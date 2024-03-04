import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { TvShow } from '../models/tv-show';

@Injectable({
  providedIn: 'root',
})
export class TvShowService {
  private url = environment.baseUrl + 'api/shows';

  constructor(private http: HttpClient) {}

  index(): Observable<TvShow[]> {
    return this.http.get<TvShow[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'TvShowService.index(): error retrieving TV Shows: ' + err
            )
        );
      })
    );
  }

  show(showId: number): Observable<TvShow> {
    return this.http.get<TvShow>(this.url + '/' + showId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('TvShowService.show(): error retrieving TV Shows: ' + err)
        );
      })
    );
  }

  create(show: TvShow) {
    return this.http.post<TvShow>(this.url, show).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error('TvShowService.create(): error creating TV Show: ' + err)
        );
      })
    );
  }

  update(showId: number, updatedShow: TvShow): Observable<TvShow> {
    return this.http.put<TvShow>(this.url + '/' + showId, updatedShow).pipe(
      catchError((err: any) => {
        console.error('Error updating TV Show:', err);
        return throwError('Failed to update TV Show');
      }),
      tap(() => {
        console.log('TV Show updated successfully');
      })
    );
  }

  destroy(showId: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${showId}`).pipe(
      catchError((err: any) => {
        console.error('Error deleting TV Show:', err);
        return throwError('Failed to delete TV Show');
      }),
      tap(() => {
        console.log('TV Show deleted successfully');
      })
    );
  }
}
