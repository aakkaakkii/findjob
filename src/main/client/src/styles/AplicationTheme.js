import darkPalette from "./darkPalette";
import lightPalette from "./lightPalette";
import {fade} from "@material-ui/core/styles/colorManipulator";

const theme = (mode) => {
    let themePallet = mode === 'dark' ? darkPalette : lightPalette;
    let palletMode = (mode && mode !== 'null') ? mode : 'light'

    return {
        primaryColor: themePallet.primaryColor,
        primaryColorLight: themePallet.primaryColorLight,
        plainTextColor: themePallet.plainTextColor,
        plainTextColorLight: themePallet.plainTextColorLight,
        backgroundColor: themePallet.backgroundColor,
        title1FontSize: "36px",
        title2FontSize: "28px",

        icon: {
            color: themePallet.palette.primary.dark,
            cursor: "pointer",
            padding: 2,
            "&:hover": {
                backgroundColor: fade(themePallet.palette.primary.light, 0.3),
                borderRadius: "50%"
            }
        },

        palette: {
            type: palletMode,
            primary: themePallet.palette.primary,
            secondary: themePallet.palette.secondary,
        },
    }
}

export default theme;