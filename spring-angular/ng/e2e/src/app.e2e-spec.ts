import { SpringAngularPage } from './app.po';

describe('spring-angular App', () => {
  let page: SpringAngularPage;

  beforeEach(() => {
    page = new SpringAngularPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getTitle()).toEqual('Spring Boot &amp; Angular');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
