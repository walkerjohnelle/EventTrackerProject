import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Rating } from '../models/rating';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RatingService {
  private url = environment.baseUrl + 'api/ratings';

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

  index(): Observable<Rating[]> {
    return this.http.get<Rating[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('RatingService.index(): error retrieving ratings: ' + err)
        );
      })
    );
  }

  show(ratingId: number): Observable<Rating> {
    return this.http
      .get<Rating>(this.url + '/' + ratingId, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error('RatingService.show(): error retrieving todo: ' + err)
          );
        })
      );
  }

  create(rating: Rating) {
    return this.http.post<Rating>(this.url, rating, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RatingService.create(): error creating todo: ' + err)
        );
      })
    );
  }

  update(ratingId: number, updatedRating: Rating): Observable<Rating> {
    return this.http
      .put<Rating>(
        `${this.url}/${ratingId}`,
        updatedRating,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.error('RatingService.update(): error updating rating:', err);
          return throwError(
            () =>
              new Error('RatingService.update(): error updating rating: ' + err)
          );
        })
      );
  }

  destroy(ratingId: number): Observable<void> {
    return this.http
      .delete<void>(`${this.url}/${ratingId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error('RatingService.destroy(): error deleting rating:', err);
          return throwError(
            () =>
              new Error(
                'RatingService.destroy(): error deleting rating: ' + err
              )
          );
        })
      );
  }
}
