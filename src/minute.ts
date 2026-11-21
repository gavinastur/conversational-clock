import type { MessageKey } from './locales/en.ts';

/**
 * Minute past the hour, in steps of five, mapped to its message key.
 * Ported as-is from the Java Minute enum.
 */
const MINUTES: ReadonlyMap<number, MessageKey> = new Map([
  [0, 'minute.zero'],
  [5, 'minute.five.past'],
  [10, 'minute.ten.past'],
  [15, 'minute.quarter.past'],
  [20, 'minute.twenty.past'],
  [25, 'minute.twentyfive.past'],
  [30, 'minute.half.past'],
  [35, 'minute.twentyfive.to'],
  [40, 'minute.twenty.to'],
  [45, 'minute.quarter.to'],
  [50, 'minute.ten.to'],
  [55, 'minute.five.to'],
  [60, 'minute.sixty'],
]);

export const JUST_AFTER: MessageKey = 'minute.just.after';
export const JUST_BEFORE: MessageKey = 'minute.just.before';
const UNKNOWN_MINUTE: MessageKey = 'minute.unknown';

/** Returns the message key for a minute, or "minute.unknown" if it isn't a multiple of five. */
export function getMinuteKey(mm: number): MessageKey {
  return MINUTES.get(mm) ?? UNKNOWN_MINUTE;
}
