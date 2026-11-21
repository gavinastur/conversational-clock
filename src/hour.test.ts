import assert from 'node:assert/strict';
import { describe, test } from 'node:test';

import { getHourKey } from './hour.ts';

describe('getHourKey', () => {
  test('maps hours to message keys', () => {
    assert.equal(getHourKey(0), 'hour.midnight');
    assert.equal(getHourKey(12), 'hour.midday');
    assert.equal(getHourKey(17), 'hour.five.pm');
    assert.equal(getHourKey(23), 'hour.eleven.pm');
  });

  for (const hour of [-1, 24, 1.5]) {
    test(`rejects ${hour}`, () => {
      assert.throws(() => getHourKey(hour), {
        name: 'RangeError',
        message: `No message for hour ${hour}. hour must be 0-23.`,
      });
    });
  }
});
