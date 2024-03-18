package io.github.rubendalebout.refinery.interfaces;

import java.util.List;

public interface Language {
    // ISO related functions and variable
    String iso = "en";
    String getISO();

    // Messages
    String noPermission();

    // Menu title
    String menuTitle();
}
