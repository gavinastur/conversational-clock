/*
 * The Conversational Clock responds with a polite response when asked for the time.
 *   00:00 is "midnight"
 *   00:01 is "just after midnight"
 *   00:05 is "five past midnight"
 *   23:45 is "quarter to midnight"
 */

import { getHourKey } from './hour.ts';
import type { MessageKey } from './locales/en.ts';
import { DEFAULT_LOCALE, format, getMessage } from './messages.ts';
import { getMinuteKey, JUST_AFTER, JUST_BEFORE } from './minute.ts';

const MS_PER_MINUTE = 60_000;
const MS_PER_HOUR = 60 * MS_PER_MINUTE;

/**
 * Gets the current local time as polite text, e.g. "five past midnight".
 *
 * @param locale BCP 47 language tag, e.g. "it-IT". Defaults to English.
 * @param now the moment to describe. Defaults to now.
 */
export const getCurrentTime = (locale: string = DEFAULT_LOCALE, now: Date = new Date()): string => {
  return getTime(now.getHours(), now.getMinutes(), locale);
};

/**
 * Gets the time of day for a timestamp as polite text, e.g. "five past midnight".
 * The time of day is worked out in UTC.
 *
 * @param milliseconds milliseconds since the Unix epoch
 * @param locale BCP 47 language tag, e.g. "it-IT". Defaults to English.
 */
export const getTimeFromMillis = (milliseconds: number, locale: string = DEFAULT_LOCALE): string => {
  const minute = Math.trunc(milliseconds / MS_PER_MINUTE) % 60;
  const hour = Math.trunc(milliseconds / MS_PER_HOUR) % 24;
  return getTime(hour, minute, locale);
};

/**
 * Gets a time of day as polite text, e.g. "five past midnight".
 *
 * @param hourOfDay hour, 0-23
 * @param minute minute, 0-59
 * @param locale BCP 47 language tag, e.g. "it-IT". Defaults to English.
 * @throws RangeError if the hour or minute is out of range or not a whole number
 */
export const getTime = (hourOfDay: number, minute: number, locale: string = DEFAULT_LOCALE): string => {
  if (!Number.isInteger(hourOfDay) || !Number.isInteger(minute) || hourOfDay > 23 || hourOfDay < 0 || minute > 59 || minute < 0) {
    throw new RangeError(`${hourOfDay}:${minute} is not valid. hourOfDay must be 0-23. minute must be 0-59.`);
  }

  const message = (key: MessageKey): string => getMessage(key, locale);
  const roundedMinute = roundToNearest5(minute);
  const nearestFive = message(getMinuteKey(roundedMinute));

  let template: string;
  switch (minute % 10) {
    case 1:
    case 2:
    case 6:
    case 7:
      // Just passed a multiple of five
      template = format(message(JUST_AFTER), nearestFive);
      break;
    case 3:
    case 4:
    case 8:
    case 9:
      // Just before a multiple of five
      template = format(message(JUST_BEFORE), nearestFive);
      break;
    default:
      // Exactly on a multiple of five, e.g. "five past ..."
      template = message(getMinuteKey(minute));
      break;
  }

  // From (just before) twenty five to, describe the time relative to the next hour
  const hour = roundedMinute < 35 ? hourOfDay : (hourOfDay + 1) % 24;

  return format(template, message(getHourKey(hour)));
};

/**
 * Rounds a minute to the nearest multiple of five, rounding halves up.
 *
 * @param minute number to be rounded
 */
export const roundToNearest5 = (minute: number): number => {
  return Math.floor((minute + 2) / 5) * 5;
};
