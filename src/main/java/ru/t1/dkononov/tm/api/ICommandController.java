package ru.t1.dkononov.tm.api;

public interface ICommandController {

    void showSystemInfo();

    void showHelp();

    void showVersion();

    void showExit();

    void showAbout();

    void showErrorCommand();

    void showErrorArgument();

}
