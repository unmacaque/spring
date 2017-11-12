import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Message } from './message';
import { MessageService } from './message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  messages: Message[] = [];

  constructor(private messageService: MessageService) {
  }

  ngOnInit() {
    this.messageService.getMessages()
    .subscribe(
        data => {
          const messages: Message[] = data['_embedded'].messages as Message[];
          this.messages = messages.sort((a, b) => (a.createdDate > b.createdDate ? -1 : 1));
        },
        (error: HttpErrorResponse) => console.error(error.message)
      );
  }

}
