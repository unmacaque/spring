import { Injectable } from '@angular/core';
import { Message } from './message';

@Injectable()
export class MessageService {

  getMessages() : Promise<Message[]> {
    return Promise.resolve([]);
  }

}
