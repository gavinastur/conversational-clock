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
function resolveBundle(locale: string): Messages {
  let canonical: string | undefined;
  try {
    canonical = Intl.getCanonicalLocales(locale)[0];
  } catch {
    canonical = undefined;
  }

  if (canonical !== undefined) {
    const language = canonical.split('-')[0] ?? canonical;
    for (const candidate of [canonical, language]) {
      const bundle = bundles[candidate];
      if (bundle !== undefined) {
        return bundle;
      }
    }
  }
  return en;
}

/**
 * Looks up a message by key. Throws if the key doesn't exist, as Java's
 * ResourceBundle.getString does.
 */
export function getMessage(key: string, locale: string): string {
  const bundle = resolveBundle(locale);
  if (!Object.hasOwn(bundle, key)) {
    throw new Error(`Can't find message for key "${key}"`);
  }
  return bundle[key as MessageKey];
}

/** Replaces the {0} placeholder, as Java's MessageFormat.format does for these templates. */
export function format(template: string, argument: string): string {
  return template.replaceAll('{0}', argument);
}
