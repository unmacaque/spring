import { SpringAngularPage } from './app.po';

describe('spring-angular App', () => {
  let page: SpringAngularPage;

  beforeEach(() => {
    page = new SpringAngularPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
