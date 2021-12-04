import { TestBed, waitForAsync } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Observable, of } from 'rxjs';
import { AppComponent } from './app.component';
import { MessageFormComponent } from './message-form/message-form.component';
import { Message } from './message/message';
import { MessageComponent } from './message/message.component';
import { MessageService } from './message/message.service';

class MockMessageService {
  getMessages(): Observable<Message> {
    return of({
      title: 'Hello Karma',
      author: 'Karma',
      content: 'Angular is awesome',
      createdDate: new Date(),
      lastModifiedDate: new Date()
    });
  };
}

describe('AppComponent', () => {

  beforeEach(waitForAsync(() => {
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

  it('should create the app', waitForAsync(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it(`should have zero messages by default`, waitForAsync(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.messages).toEqual([]);
  }));

  it('should have a div element', waitForAsync(() => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('div')).toBeTruthy();
  }));
});
