#!/usr/bin/env node
import { getCurrentTime, getTime } from './clock.ts';

/**
 * With no arguments, prints the current time in English. With a language tag argument
 * (e.g. "it-IT", "fr-FR"), prints every minute of the current hour in that locale.
 */
const [locale] = process.argv.slice(2);

if (locale === undefined) {
  console.log(getCurrentTime());
} else {
  const hour = new Date().getHours();
  for (let minute = 0; minute < 60; minute++) {
    console.log(`${hour}:${String(minute).padStart(2, '0')} ${getTime(hour, minute, locale)}`);
  }
}
