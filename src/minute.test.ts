import assert from 'node:assert/strict';
import { describe, test } from 'node:test';

import { getMinuteKey } from './minute.ts';

describe('getMinuteKey', () => {
  test('maps multiples of five to message keys', () => {
    assert.equal(getMinuteKey(0), 'minute.zero');
    assert.equal(getMinuteKey(15), 'minute.quarter.past');
    assert.equal(getMinuteKey(35), 'minute.twentyfive.to');
    assert.equal(getMinuteKey(60), 'minute.sixty');
  });

  for (const minute of [-5, 7, 65]) {
    test(`rejects ${minute}`, () => {
      assert.throws(() => getMinuteKey(minute), {
        name: 'RangeError',
        message: `No message for minute ${minute}. minute must be a multiple of five from 0 to 60.`,
      });
    });
  }
});
