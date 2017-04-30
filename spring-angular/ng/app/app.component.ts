import { Component, OnInit } from '@angular/core';
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
    this.messageService.getMessages().then(messages => this.messages = messages);
  }

}
