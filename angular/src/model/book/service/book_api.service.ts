import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

export class BookApiService {

  constructor(private http: HttpClient) {
  }

  public index(): Observable<any> {
    return this.http.get('http://localhost:8080/book');
  }
}
