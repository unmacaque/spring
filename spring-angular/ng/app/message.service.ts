import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Message } from './message';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class MessageService {

  constructor(private http: Http) { }

  getMessages() : Promise<Message[]> {
    return this.http.get('/api/messages')
      .toPromise()
      .then(response => response.json()._embedded.messages as Message[])
      .catch(error => Promise.reject(error));
  }

}
