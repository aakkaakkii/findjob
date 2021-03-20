import darkPalette from "./darkPalette";
import lightPalette from "./lightPalette";

const theme = (mode) => {
    let themePallet = mode === 'dark' ? darkPalette : lightPalette;
    let palletMode = (mode && mode !== 'null') ? mode : 'light'
    console.log('-=----------------')
    console.log('-=----------------')
    console.log(palletMode, typeof palletMode)

    return {
        primaryColor: themePallet.primaryColor,
        primaryColorLight: themePallet.primaryColorLight,
        plainTextColor: themePallet.plainTextColor,
        plainTextColorLight: themePallet.plainTextColorLight,
        title1FontSize: "36px",
        title2FontSize: "28px",

        palette: {
            type: palletMode,
            primary: themePallet.palette.primary,
            secondary: themePallet.palette.secondary,
            // primary: themePalette(color, mode).palette.primary,
            // secondary: themePalette(color, mode).palette.secondary,
          /*  action: {
                hover: mode === "dark" ? "rgba(80,80,80, 0.9)" : "rgba(80,80,80, 0.05)",
                hoverOpacity: 0.05,
            },*/
        },


        /*    palette: {
                primary: {
                    // Purple and green play nicely together.
                    main: purple[500],
                },
                secondary: {
                    // This is green.A700 as hex.
                    main: '#11cb5f',
                },
            },*/
    }
}

export default theme;