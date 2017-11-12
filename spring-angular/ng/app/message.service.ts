import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Message } from './message';

import { Observable } from 'rxjs/Observable';

@Injectable()
export class MessageService {

  constructor(private http: HttpClient) { }

  getMessages(): Observable<Object> {
    return this.http.get('/api/messages');
  }

}
