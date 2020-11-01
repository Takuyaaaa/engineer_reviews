import {Component, OnInit} from '@angular/core';
import {BookApiService} from "./service/book_api.service";
import {ActivatedRoute} from "@angular/router";
import {Book} from "./model/book";

@Component({
  templateUrl: './book_info.component.html'
})
export class BookInfoComponent implements OnInit{

  id: string;

  book: Book;

  constructor(private api: BookApiService,
              private route: ActivatedRoute,) {
  }

  ngOnInit() {

    this.id = this.route.snapshot.paramMap.get("id");

    this.api.show(this.id)
      .subscribe((book) => {
        this.book = book;
      })
  }

}
