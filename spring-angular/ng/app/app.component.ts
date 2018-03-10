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
        message => {
          this.messages.push(message);
        },
        (error: HttpErrorResponse) => console.error(error.message)
      );
  }

}
