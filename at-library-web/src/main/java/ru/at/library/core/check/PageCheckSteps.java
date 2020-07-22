package ru.at.library.core.check;

import cucumber.api.java.ru.И;
import ru.at.library.core.cucumber.api.CoreScenario;

import static com.codeborne.selenide.WebDriverRunner.isIE;

public class PageCheckSteps {

    private CoreScenario coreScenario = CoreScenario.getInstance();


    /**
     * Проверка того, что все элементы, которые описаны в классе страницы с аннотацией @Name,
     * но без аннотации @Optional, не появились на странице
     *
     * @param nameOfPage название страница|блок|форма|вкладка
     */
    @И("^(?:страница|блок|форма|вкладка) \"([^\"]*)\" не (?:загрузилась|загрузился)$")
    public void loadPageFailed(String nameOfPage) {
        coreScenario.setCurrentPage(coreScenario.getPage(nameOfPage));
        if (isIE()) {
            coreScenario.getCurrentPage().ieDisappeared();
        } else {
            coreScenario.getCurrentPage().disappeared();
        }
    }


    /**
     * Проверка того, что блок исчез/стал невидимым
     */
    @И("^(?:страница|блок|форма) \"([^\"]*)\" (?:скрыт|скрыта)")
    public void blockDisappeared(String nameOfPage) {
        if (isIE()) {
            coreScenario.getPage(nameOfPage).ieDisappeared();
        } else coreScenario.getPage(nameOfPage).disappeared();
    }

    /**
     * Проверка того, что блок отображается
     */
    @И("^(?:страница|блок|форма|вкладка) \"([^\"]*)\" отображается на странице")
    public void blockAppeared(String nameOfPage) {
        coreScenario.getPage(nameOfPage).isAppeared();
    }
}
