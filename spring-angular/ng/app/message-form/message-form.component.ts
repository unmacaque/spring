import { Component, OnInit } from '@angular/core';

import { Message } from '../message/message';
import { MessageService } from '../message/message.service'

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  message = new Message();

  constructor(private messageService : MessageService) { }

  ngOnInit() {
  }

  onMessageSend() {
    this.messageService.sendMessage(this.message).subscribe(console.log);
  }

}
