import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';

@Injectable()
export class BookApiService {

  constructor(private http: HttpClient) {
  }

  public index(): Observable<any> {
    return this.http.get('http://0.0.0.0:8080/book');
  }
}
