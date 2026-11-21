import type { MessageKey } from './locales/en.ts';

/** Message key for each multiple of five minutes past the hour, indexed by minute / 5 (0-60). */
const MINUTE_KEYS = [
  'minute.zero',
  'minute.five.past',
  'minute.ten.past',
  'minute.quarter.past',
  'minute.twenty.past',
  'minute.twentyfive.past',
  'minute.half.past',
  'minute.twentyfive.to',
  'minute.twenty.to',
  'minute.quarter.to',
  'minute.ten.to',
  'minute.five.to',
  'minute.sixty',
] as const satisfies readonly MessageKey[];

export const JUST_AFTER: MessageKey = 'minute.just.after';
export const JUST_BEFORE: MessageKey = 'minute.just.before';

/**
 * Returns the message key for a multiple of five minutes past the hour.
 *
 * @throws RangeError if the minute isn't a multiple of five from 0 to 60
 */
export function getMinuteKey(minute: number): MessageKey {
  const key = MINUTE_KEYS[minute / 5];
  if (key === undefined) {
    throw new RangeError(`No message for minute ${minute}. minute must be a multiple of five from 0 to 60.`);
  }
  return key;
}
