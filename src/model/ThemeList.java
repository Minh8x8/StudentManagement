package model;

import java.util.Map;

public class ThemeList {
    public String[][] themeList;
    public ThemeList() {
        String[][] themeList = new String[][] {
                {"--------Core Themes--------", ""},
                {"FlatLaf Light", "com.formdev.flatlaf.FlatLightLaf"},
                {"FlatLaf Dark", "com.formdev.flatlaf.FlatDarkLaf"},
                {"FlatLaf IntelliJ", "com.formdev.flatlaf.FlatIntelliJLaf"},
                {"FlatLaf Dracula", "com.formdev.flatlaf.FlatDarculaLaf"},
                {"--------IntelliJ Themes--------", ""},
                {"Arc", "com.formdev.flatlaf.intellijthemes.FlatArcIJTheme"},
                {"Arc - Orange", "com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme"},
                {"Arc Dark", "com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme"},
                {"Arc Dark - Orange", "com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme"},
                {"Carbon", "com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme"},
                {"Cobalt 2", "com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme"},
                {"Cyan light", "com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme"},
                {"Dark Flat", "com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme"},
                {"Dark purple", "com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme"},
                {"Dracula", "com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme"},
                {"Gradianto Dark Fuchsia", "com.formdev.flatlaf.intellijthemes.FlatGradiantoDarkFuchsiaIJTheme"},
                {"Gradianto Deep Ocean", "com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme"},
                {"Gradianto Midnight Blue", "com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme"},
                {"Gradianto Nature Green", "com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme"},
                {"Gray", "com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme"},
                {"Gruvbox Dark Hard", "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme"},
                {"Gruvbox Dark Medium", "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme"},
                {"Gruvbox Dark Soft", "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkSoftIJTheme"},
                {"Hiberbee Dark", "com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme"},
                {"High contrast", "com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme"},
                {"Light Flat", "com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme"},
                {"Material Design Dark", "com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme"},
                {"Monocai", "com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme"},
                {"Monokai Pro", "com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme"},
                {"Nord", "com.formdev.flatlaf.intellijthemes.FlatNordIJTheme"},
                {"One Dark", "com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme"},
                {"Solarized Dark", "com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme"},
                {"Solarized Light", "com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme"},
                {"Spacegray", "com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme"},
                {"Vuesion", "com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme"},
                {"Xcode-Dark", "com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme"},



        };
        this.themeList = themeList;
    }
    public String[] getThemeListName() {
        String[] themeListName = new String[themeList.length];
        for (int i = 0; i < themeListName.length; i++) {
            themeListName[i] = themeList[i][0];
        }
        return themeListName;
}
}
