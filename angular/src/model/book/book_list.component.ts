import {Component, OnInit} from "@angular/core";
import {Book} from "./model/book";
import {BookApiService} from "./service/book_api.service";

@Component({
  templateUrl: './book_list.component.html'
})
export class BookListComponent implements OnInit{
  books: Book[];

  constructor(private api: BookApiService) {
  }

  ngOnInit() {
    this.api.index()
      .subscribe((response) => {
        this.books = response;
      });
  }
}
