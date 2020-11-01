import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {BookInterface} from "../model/book";

@Injectable()
export class BookApiService {

  constructor(private http: HttpClient) {
  }

  public index(): Observable<BookInterface[]> {
    return this.http.get<BookInterface[]>('http://0.0.0.0:8080/book');
  }

  public show(id: string): Observable<BookInterface> {
    return this.http.get<BookInterface>('http://0.0.0.0:8080/book/'+id);
  }

}
