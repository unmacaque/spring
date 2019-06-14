import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Message } from './message';

import { Observable } from 'rxjs/Observable';

const EventSource: any = window['EventSource'];

@Injectable()
export class MessageService {

  constructor(private http: HttpClient, private zone: NgZone) { }

  getMessages(): Observable<Message> {
    return Observable.create(observer => {
      let eventSource = new EventSource('/api/messages');
      eventSource.onmessage = event => {
        console.log(event);
        let message : Message = JSON.parse(event.data);
        this.zone.run(() => observer.next(message));
      };
      eventSource.onerror = (error) => observer.error(error);
      return () => eventSource.close();
    });
  }

  sendMessage(message : Message) : Observable<Message> {
    return this.http.post<Message>('/api/messages', message);
  }

}
