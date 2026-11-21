import type { MessageKey } from './locales/en.ts';

/** Message key for each hour of the day, indexed 0-23. */
const HOUR_KEYS = [
  'hour.midnight',
  'hour.one.am',
  'hour.two.am',
  'hour.three.am',
  'hour.four.am',
  'hour.five.am',
  'hour.six.am',
  'hour.seven.am',
  'hour.eight.am',
  'hour.nine.am',
  'hour.ten.am',
  'hour.eleven.am',
  'hour.midday',
  'hour.one.pm',
  'hour.two.pm',
  'hour.three.pm',
  'hour.four.pm',
  'hour.five.pm',
  'hour.six.pm',
  'hour.seven.pm',
  'hour.eight.pm',
  'hour.nine.pm',
  'hour.ten.pm',
  'hour.eleven.pm',
] as const satisfies readonly MessageKey[];

/**
 * Returns the message key for an hour of the day.
 *
 * @throws RangeError if the hour isn't a whole number from 0 to 23
 */
export const getHourKey = (hour: number): MessageKey => {
  const key = HOUR_KEYS[hour];
  if (key === undefined) {
    throw new RangeError(`No message for hour ${hour}. hour must be 0-23.`);
  }
  return key;
};
