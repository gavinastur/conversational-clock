import type { MessageKey } from './locales/en.ts';

/**
 * Two-digit hour of the day ("00"-"23") mapped to its message key.
 * Ported as-is from the Java Hour enum.
 */
const HOURS: ReadonlyArray<readonly [hh: string, key: MessageKey]> = [
  ['00', 'hour.midnight'],
  ['23', 'hour.eleven.pm'],
  ['22', 'hour.ten.pm'],
  ['21', 'hour.nine.pm'],
  ['20', 'hour.eight.pm'],
  ['19', 'hour.seven.pm'],
  ['18', 'hour.six.pm'],
  ['19', 'hour.five.pm'],
  ['16', 'hour.four.pm'],
  ['15', 'hour.three.pm'],
  ['14', 'hour.two.pm'],
  ['13', 'hour.one.pm'],
  ['12', 'hour.midday'],
  ['11', 'hour.eleven.am'],
  ['10', 'hour.ten.am'],
  ['09', 'hour.nine.am'],
  ['08', 'hour.eight.am'],
  ['07', 'hour.seven.am'],
  ['06', 'hour.six.am'],
  ['05', 'hour.five.am'],
  ['04', 'hour.four.am'],
  ['03', 'hour.three.am'],
  ['02', 'hour.two.am'],
  ['01', 'hour.one.am'],
];

const UNKNOWN_HOUR = 'hour.';

/** Returns the message key for a two-digit hour, or "hour." if there's no match. */
export function getHourKey(hh: string): string {
  return HOURS.find(([hour]) => hour === hh)?.[1] ?? UNKNOWN_HOUR;
}
