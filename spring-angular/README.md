# spring-angular

Before starting development run `npm install` once.

To start development mode, issue these commands:

1. Run `mvn spring-boot:run`
1. Run `npm start`
1. Open [http://localhost:4200](http://localhost:4200) in a browser

To build an executable standalone jar with static assets compiled for productive use:

1. Run `npm run build`
1. Run `mvn package`

To execute Angular component tests:

1. Run `npm test`
1. Open [http://localhost:9876](http://localhost:9876) to view results of Karma test runner

To run end-to-end tests:

1. Edit `protractor.conf.js` and change `browserName` to whatever browser to use for testing
1. Run `npm run e2e`

*Note: It is possible to specify multiple browsers. Some supported ones are:* `chrome`, `firefox`
