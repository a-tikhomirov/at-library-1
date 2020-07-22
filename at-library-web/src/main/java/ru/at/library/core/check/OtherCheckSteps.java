package ru.at.library.core.check;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.ru.И;
import ru.at.library.core.cucumber.api.CoreScenario;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.*;
import static ru.at.library.core.steps.OtherSteps.deleteFiles;
import static ru.at.library.core.steps.OtherSteps.getDownloadsDir;

/**
 * WEB шаги
 * <p>
 * В coreScenario используется хранилище переменных. Для сохранения/изъятия переменных используются методы setVar/getVar
 * Каждая страница, с которой предполагается взаимодействие, должна быть описана в соответствующем классе,
 * наследующем CorePage. Для каждого элемента следует задать имя на русском, через аннотацию @Name, чтобы искать
 * можно было именно по русскому описанию, а не по селектору. Селекторы следует хранить только в классе страницы,
 * не в степах, в степах - взаимодействие по русскому названию элемента.
 */
public class OtherCheckSteps {
    private CoreScenario coreScenario = CoreScenario.getInstance();



    /**
     * Проверка, что на странице не отображаются редактируемые элементы, такие как:
     * -input
     * -textarea
     */
    @И("^открыта read-only форма$")
    public void openReadOnlyForm() {
        int inputsCount = getDisplayedElementsByCss("input").size();
        assertTrue("Форма не read-only. Количество input-полей: " + inputsCount, inputsCount == 0);
        int textareasCount = getDisplayedElementsByCss("textarea").size();
        assertTrue("Форма не read-only. Количество элементов textarea: " + textareasCount, textareasCount == 0);
    }

    private List<SelenideElement> getDisplayedElementsByCss(String cssSelector) {
        return $$(cssSelector).stream()
                .filter(SelenideElement::isDisplayed)
                .collect(Collectors.toList());
    }


    /**
     * Выполняется поиск нужного файла в папке /Downloads
     * Поиск осуществляется по содержанию ожидаемого текста в названии файла. Можно передавать регулярное выражение.
     * После выполнения проверки файл удаляется
     */
    @И("^файл \"([^\"]*)\" загрузился в папку /Downloads$")
    public void testFileDownloaded(String fileName) {
        File downloads = getDownloadsDir();
        File[] expectedFiles = downloads.listFiles((files, file) -> file.contains(fileName));
        assertNotNull("Ошибка поиска файла", expectedFiles);
        assertFalse("Файл не загрузился", expectedFiles.length == 0);
        assertTrue(String.format("В папке присутствуют более одного файла с одинаковым названием, содержащим текст [%s]", fileName),
                expectedFiles.length == 1);
        deleteFiles(expectedFiles);
    }

//---------------------------------------------------Проверки в Блоке
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------
//---------------------------------------------------

//    /**
//     * Проверка, что значение в поле содержит текст, указанный в шаге
//     * (в приоритете: из property, из переменной сценария, значение аргумента).
//     * Не чувствителен к регистру
//     */
//    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" содержит текст \"([^\"]*)\"$")
//    @А("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" содержит текст")
//    public void testFieldContainsInnerText(String elementName, String blockName, String expectedValue) {
//        CorePage currentPage = coreScenario.getCurrentPage();
//
//        SelenideElement element = coreScenario.getPage(blockName).getElement(elementName);
//        expectedValue = getPropertyOrStringVariableOrValue(expectedValue);
//        element.shouldHave(
//                or("Текст элемента содержит",
//                        text(expectedValue),
//                        value(expectedValue)));
//
//        coreScenario.setCurrentPage(currentPage);
//    }
//
//
//    /**
//     * Проверка, что текста в поле равен значению, указанному в шаге
//     * (в приоритете: из property, из переменной сценария, значение аргумента)
//     */
//    @И("^текст (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\" равен \"([^\"]*)\"$")
//    @А("^текст (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\" равен$")
//    public void /**/compareValInFieldAndFromStep(String elementName, String blockName, String expectedValue) {
//        CorePage currentPage = coreScenario.getCurrentPage();
//
//        SelenideElement element = coreScenario.getPage(blockName).getElement(elementName);
//        expectedValue = getPropertyOrStringVariableOrValue(expectedValue);
//        element.shouldHave(
//                or("Текст элемента равен",
//                        exactText(expectedValue),
//                        exactValue(expectedValue)));
//
//        coreScenario.setCurrentPage(currentPage);
//    }

//    /**
//     * Проверка того, что элемент в блоке отображается на странице
//     *
//     * @param elementName имя элемента
//     * @param blockName   имя блока
//     */
//    @А("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" отображается на странице")
//    public void visible(String elementName, String blockName) {
//        CorePage currentPage = coreScenario.getCurrentPage();
//
//        coreScenario.getPage(blockName).getElement(elementName)
//                .shouldHave(visible);
//
//        coreScenario.setCurrentPage(currentPage);
//    }

//
//    /**
//     * Проверка того, что элемент в блоке не отображается на странице
//     *
//     * @param elementName имя элемента
//     * @param blockName   имя блока
//     */
//    @А("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" не отображается на странице")
//    public void notVisible(String elementName, String blockName) {
//        CorePage currentPage = coreScenario.getCurrentPage();
//
//        coreScenario.getPage(blockName).getElement(elementName)
//                .shouldHave(not(visible));
//
//        coreScenario.setCurrentPage(currentPage);
//    }
//
//
//    /**
//     * Получение текста элемента в блоке и сохранение его в переменную
//     *
//     * @param elementName  имя элемента
//     * @param blockName    имя блока
//     * @param variableName имя переменной
//     */
//    @И("^текст (?:поля|элемента|текста) \"([^\"]*)\" в блоке \"([^\"]*)\" сохранен в переменную \"([^\"]*)\"$")
//    public void saveTextElementInBlock(String elementName, String blockName, String variableName) {
//        CorePage currentPage = coreScenario.getCurrentPage();
//
//        String elementText = coreScenario.getPage(blockName).getElement(elementName).getText();
//        coreScenario.setVar(variableName, elementText);
//        coreScenario.write("Значение [" + elementText + "] сохранено в переменную [" + variableName + "]");
//
//        coreScenario.setCurrentPage(currentPage);
//    }


}
