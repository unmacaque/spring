import { TestBed, async } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { Message } from './message/message';
import { MessageComponent } from './message/message.component';
import { MessageFormComponent } from './message-form/message-form.component';
import { MessageService } from './message/message.service';

import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';

class MockMessageService {
  getMessages() : Observable<Message> {
    return Observable.of({
      title: 'Hello Karma',
      author: 'Karma',
      content: 'Angular is awesome',
      createdDate: new Date(),
      lastModifiedDate: new Date()
    });
  };
}

describe('AppComponent', () => {

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        MessageComponent,
        MessageFormComponent
      ],
      imports: [
        FormsModule,
        MatCardModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        NoopAnimationsModule
      ],
      providers: [
        { provide: MessageService, useClass: MockMessageService }
      ]
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it(`should have zero messages by default`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.messages).toEqual([]);
  }));

  it('should have a div element', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('div')).toBeTruthy();
  }));
});
