import { browser, by, element } from 'protractor';

export class SpringAngularPage {
  navigateTo() {
    return browser.get('/');
  }

  getTitle() {
    return browser.getTitle();
  }
}
