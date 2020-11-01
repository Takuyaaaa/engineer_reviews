import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {BookListComponent} from '../model/book/book_list.component';
import {HttpClientModule} from '@angular/common/http';
import {BookApiService} from '../model/book/service/book_api.service';
import {RouterModule, Routes} from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'book'},
  {path: 'book', component: BookListComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent
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
