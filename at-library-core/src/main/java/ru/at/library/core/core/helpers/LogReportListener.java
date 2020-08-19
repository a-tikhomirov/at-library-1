package ru.at.library.core.core.helpers;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogReportListener {

    private static boolean turn = false;

    private LogReportListener() {
    }

    /**
     * Добавляет фильтры логирования.
     * <ul>
     * <li> лог (Log4j2)</li>
     * <li> reportPortal</li>
     * <li> отчет allure</li>
     * </ul>
     */
    public synchronized static void turnOn() {
        turnListenerSelenide();

    }

    private synchronized static void turnListenerSelenide() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
//                        .enableLogs(LogType.BROWSER, Level.ALL)
        );
        log.trace("Включен слушатель Selenide в Allure");
    }


}
