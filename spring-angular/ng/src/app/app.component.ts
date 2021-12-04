import { Component, OnInit } from '@angular/core';
import { Message } from './message/message';
import { MessageService } from './message/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  messages: Message[] = [];

  constructor(private messageService: MessageService) {
  }

  ngOnInit() {
    this.messageService.getMessages()
      .subscribe(
        {
          next: (message: Message) => {
            this.messages.push(message);
          },
          error: (error: ErrorEvent) => console.error(error)
        }
      );
  }

}
