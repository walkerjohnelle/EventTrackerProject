import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url = environment.baseUrl + 'api/users';

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

  isLoggedIn(): boolean {
    const token = localStorage.getItem('authToken');
    return !!token;
  }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('UserService.index(): error retrieving users: ' + err));
      })
    );
  }

  show(userId: number): Observable<User> {
    return this.http.get<User>(`${this.url}/${userId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('UserService.show(): error retrieving user: ' + err));
      })
    );
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(this.url, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('UserService.create(): error creating user: ' + err));
      })
    );
  }

  update(userId: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.url}/${userId}`, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('UserService.update(): error updating user: ' + err));
      })
    );
  }

  delete(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${userId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('UserService.delete(): error deleting user: ' + err));
      })
    );
  }
}
