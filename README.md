# The Conversational Clock

The Conversational Clock is a TypeScript library that responds with a polite response when asked for the time.

    00:00 is "midnight"
    00:01 is "just after midnight"
    00:05 is "five past midnight"
    23:45 is "quarter to midnight"

Requires Node.js 26 or later.

## Usage

```ts
import { getCurrentTime, getTime, getTimeFromMillis } from 'conversational-clock';

getTime(8, 5); // "five past eight am"
getTime(8, 5, 'it-IT'); // Italian
getCurrentTime(); // the current local time
getTimeFromMillis(Date.now()); // time of day in UTC
```

Locales are BCP 47 language tags. English, French (`fr-FR`) and Italian (`it-IT`) are included; anything else falls back to English.

## Command line

```sh
npm start              # current time in English
npm start -- it-IT     # every minute of the current hour, in Italian
```

## Development

```sh
npm install
npm test               # run the tests
npm run test:coverage  # run the tests with a coverage report (fails below 100%)
npm run build          # type-check
npm run lint           # format and lint with Biome
```
