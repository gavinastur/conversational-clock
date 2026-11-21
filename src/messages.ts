import { en, type MessageKey, type Messages } from './locales/en.ts';
import { frFR } from './locales/fr-FR.ts';
import { itIT } from './locales/it-IT.ts';

export const DEFAULT_LOCALE = 'en';

const bundles: Readonly<Record<string, Messages>> = {
  en,
  'fr-FR': frFR,
  'it-IT': itIT,
};

/**
 * Picks the closest bundle for a locale, like Java's ResourceBundle:
 * the full tag ("it-IT"), then the language ("it"), then the default.
 * Invalid or unsupported tags fall back to the default.
 */
const resolveBundle = (locale: string): Messages => {
  let parsed: Intl.Locale;
  try {
    parsed = new Intl.Locale(locale);
  } catch {
    return en;
  }
  return bundles[parsed.baseName] ?? bundles[parsed.language] ?? en;
};

/** Looks up a message by key in the closest bundle for a locale. */
export const getMessage = (key: MessageKey, locale: string): string => {
  return resolveBundle(locale)[key];
};

/** Replaces the {0} placeholder, as Java's MessageFormat.format does for these templates. */
export const format = (template: string, argument: string): string => {
  return template.replaceAll('{0}', argument);
};
