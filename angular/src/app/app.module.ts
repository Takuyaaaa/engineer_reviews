import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {BookListComponent} from '../model/book/book_list.component';
import {HttpClientModule} from '@angular/common/http';
import {BookApiService} from '../model/book/service/book_api.service';
import {RouterModule, Routes} from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {BookInfoComponent} from '../model/book/book_info.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'book'},
  {path: 'book',
    children: [
      {path: '', component: BookListComponent},
      {path: ':id', component: BookInfoComponent,
        children: [
          {path: '**', component: BookInfoComponent},
        ]
      }
    ],
  },
];

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    BookInfoComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbModule.forRoot(),
  ],
  providers: [
    BookApiService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
