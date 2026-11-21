import assert from 'node:assert/strict';
import { describe, test } from 'node:test';

import { getCurrentTime, getTime, getTimeFromMillis, roundToNearest5 } from './clock.ts';

// Ported from the Java ConversationalClockTest
describe('getTime', () => {
  test('on the hour: 00:00 is "midnight"', () => {
    assert.equal(getTime(0, 0), 'midnight');
  });

  test('just after: 00:01 is "just after midnight"', () => {
    assert.equal(getTime(0, 1), 'just after midnight');
  });

  test('just before: 00:04 is "just before five past midnight"', () => {
    assert.equal(getTime(0, 4), 'just before five past midnight');
  });

  test('five past: 08:05 is "five past eight am"', () => {
    assert.equal(getTime(8, 5), 'five past eight am');
  });

  test('half past: 23:30 is "half past eleven pm"', () => {
    assert.equal(getTime(23, 30), 'half past eleven pm');
  });

  test('twenty to: 23:40 is "twenty to midnight"', () => {
    assert.equal(getTime(23, 40), 'twenty to midnight');
  });

  test('twenty five to: 23:35 is "twenty five to midnight"', () => {
    assert.equal(getTime(23, 35), 'twenty five to midnight');
  });

  for (const [hour, minute] of [
    [-1, 0],
    [0, -1],
    [24, 0],
    [0, 60],
  ] as const) {
    test(`rejects ${hour}:${minute}`, () => {
      assert.throws(() => getTime(hour, minute), {
        name: 'RangeError',
        message: `${hour}:${minute} is not valid. hourOfDay must be 0-23. minute must be 0-59.`,
      });
    });
  }

  test('rejects fractional values', () => {
    assert.throws(() => getTime(1.5, 0), RangeError);
  });
});

describe('bug fixes', () => {
  test('17:00 is "five pm"', () => {
    assert.equal(getTime(17, 0), 'five pm');
  });

  test('16:40 is "twenty to five pm"', () => {
    assert.equal(getTime(16, 40), 'twenty to five pm');
  });

  test('01:00 is "one am"', () => {
    assert.equal(getTime(1, 0), 'one am');
  });

  test('00:33 is "just before twenty five to one am"', () => {
    assert.equal(getTime(0, 33), 'just before twenty five to one am');
  });

  test('23:34 is "just before twenty five to midnight"', () => {
    assert.equal(getTime(23, 34), 'just before twenty five to midnight');
  });

  test('00:32 still names the current hour', () => {
    assert.equal(getTime(0, 32), 'just after half past midnight');
  });
});

describe('every minute of the day', () => {
  const HOUR_NAMES = [
    'midnight',
    'one am',
    'two am',
    'three am',
    'four am',
    'five am',
    'six am',
    'seven am',
    'eight am',
    'nine am',
    'ten am',
    'eleven am',
    'midday',
    'one pm',
    'two pm',
    'three pm',
    'four pm',
    'five pm',
    'six pm',
    'seven pm',
    'eight pm',
    'nine pm',
    'ten pm',
    'eleven pm',
  ];

  test('names each hour correctly on the hour', () => {
    HOUR_NAMES.forEach((name, hour) => {
      assert.equal(getTime(hour, 0), name);
    });
  });

  test('ends with the current hour up to xx:32 and the next hour from xx:33', () => {
    for (let hour = 0; hour < 24; hour++) {
      for (let minute = 0; minute < 60; minute++) {
        const expectedHour = minute < 33 ? hour : (hour + 1) % 24;
        const text = getTime(hour, minute);
        assert.ok(text.endsWith(` ${HOUR_NAMES[expectedHour]}`) || text === HOUR_NAMES[expectedHour], `${hour}:${minute} gave "${text}"`);
        assert.doesNotMatch(text, /[{}]|undefined/, `${hour}:${minute} gave "${text}"`);
      }
    }
  });
});

describe('getTimeFromMillis', () => {
  test('works out the time of day in UTC', () => {
    // 2014-07-18T07:29:35.844Z
    assert.equal(getTimeFromMillis(1405668575844), 'just before half past seven am');
  });
});

describe('roundToNearest5', () => {
  test('rounds 9 up to 10', () => {
    assert.equal(roundToNearest5(9), 10);
  });
});

describe('locales', () => {
  test('uses the Italian translations for it-IT', () => {
    assert.equal(getTime(8, 5, 'it-IT'), '(IT)five past eight am');
  });

  test('uses the French translations for fr-FR', () => {
    assert.equal(getTime(8, 5, 'fr-FR'), '(FR)five past eight am');
  });

  test('accepts tags in any case', () => {
    assert.equal(getTime(8, 5, 'it-it'), '(IT)five past eight am');
  });

  test('falls back to English for unsupported or invalid tags', () => {
    assert.equal(getTime(8, 5, 'de-DE'), 'five past eight am');
    assert.equal(getTime(8, 5, 'not a locale'), 'five past eight am');
  });
});

describe('getCurrentTime', () => {
  test('describes the given moment in local time', () => {
    assert.equal(getCurrentTime('en', new Date(2026, 0, 1, 8, 5)), 'five past eight am');
  });
});
