import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from './message';

@Injectable()
export class MessageService {

  constructor(private http: HttpClient, private zone: NgZone) { }

  getMessages(): Observable<Message> {
    return new Observable(observer => {
      let eventSource: EventSource = new EventSource('/api/messages');
      eventSource.onmessage = (event: { data: string; }) => {
        console.debug(event);
        let message: Message = JSON.parse(event.data);
        this.zone.run(() => observer.next(message));
      };
      eventSource.onerror = (error: Event) => observer.error(error);
      return () => eventSource.close();
    });
  }

  sendMessage(message: Message): Observable<Message> {
    return this.http.post<Message>('/api/messages', message);
  }

}
