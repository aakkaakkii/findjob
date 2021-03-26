import i18n from "i18next";
import {initReactI18next} from "react-i18next";
import locale_GE from "./locale_GE";
import locale_EN from "./locale_EN";

export const EN = "en";
export const GE = "ge";

const resources = {
    ge: locale_GE,
    en: locale_EN,
};

i18n.use(initReactI18next).init({
    resources,
    lng: getLanguageFromCookie(),
    keySeparator: false,
    interpolation: {
        escapeValue: false,
    },
});

i18n.on('languageChanged', (lang) => {
    document.cookie = `locale=${lang}`
});

function getLanguageFromCookie() {
    let localeArray = document.cookie
        .split(";")
        .filter((d) => d.split("=")[0].trim() === "locale");

    return localeArray && localeArray.length !== 0
        ? localeArray[0].split("=")[1]
        : "ge";
}

export default i18n;
