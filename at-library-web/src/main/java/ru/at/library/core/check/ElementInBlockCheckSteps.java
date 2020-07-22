package ru.at.library.core.check;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.ru.А;
import cucumber.api.java.ru.И;
import ru.at.library.core.BrowserSteps;
import ru.at.library.core.cucumber.api.CoreScenario;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;
import static ru.at.library.core.steps.OtherSteps.getPropertyOrStringVariableOrValue;

public class ElementInBlockCheckSteps {
    private CoreScenario coreScenario = CoreScenario.getInstance();

    /**
     * Проверка появления элемента(не списка) на странице в течение Configuration.timeout.
     *
     * @param elementName название
     */
    @А("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" отображается на странице")
    public void elementAppeared(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(appear);
    }

    /**
     * Проверка появления элемента(не списка) на странице в течение
     * заданного количества секунд
     *
     * @param elementName название
     * @param seconds     количество секунд
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" отображается на странице в течение (\\d+) (?:секунд|секунды)")
    public void elementAppearedSecond(String elementName, String blockName, int seconds) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.waitUntil(appear, seconds * 1000);
    }

    /**
     * Проверка того, что элемент исчезнет со страницы (станет невидимым) в течение Configuration.timeout.
     * В случае, если свойство "waitingCustomElementsTimeout" в application.properties не задано,
     * таймаут равен 10 секундам
     *
     * @param elementName название
     */
    @И("^ожидается исчезновение (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\"")
    public void elemDisappears(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(disappears);
    }

    /**
     * Проверка того, что элемент исчезнет со страницы (станет невидимым) в течение seconds
     *
     * @param elementName название
     * @param seconds     время в секундах
     */
    @И("^ожидается исчезновение (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\" в течение (\\d+) (?:секунд|секунды)")
    public void elemDisappears(String elementName, String blockName, int seconds) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.waitUntil(disappears, seconds * 1000);
    }

    /**
     * Проверка, что элемент на странице кликабелен
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" (?:доступна|доступно|доступен) для нажатия")
    public void clickableField(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(enabled);
    }

    /**
     * Проверка, что элемент на странице кликабелен
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" (?:доступна|доступно|доступен) для нажатия в течение (\\d+) (?:секунд|секунды)$")
    public void clickableField(String elementName, String blockName, int second) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.waitUntil(enabled, second * 1000);
    }

    /**
     * Проверка, что кнопка/ссылка недоступна для нажатия
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" (?:недоступна|недоступно|недоступен) для (?:нажатия|редактирования)$")
    public void buttonIsNotActive(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(disabled);
    }

    /**
     * Проверка, что кнопка/ссылка недоступна для нажатия
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" (?:недоступна|недоступно|недоступен) для (?:нажатия|редактирования) в течение (\\d+) (?:секунд|секунды)$")
    public void buttonIsNotActive(String elementName, String blockName, int second) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.waitUntil(disabled, second * 1000);
    }

    /**
     * Проверка, что поле для ввода пусто
     */
    @И("^поле \"([^\"]*)\" в блоке \"([^\"]*)\" пусто$")
    public void fieldInputIsEmpty(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(empty);
    }

    /**
     * Проверка, что радиокнопка выбрана
     */
    @И("^(?:кнопка|ссылка|поле|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" (?:выбрана|выбрано|выбран)$")
    public void radioButtonIsSelected(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(selected);
    }

    /**
     * Проверка, что радиокнопка не выбрана
     */
    @И("^(?:кнопка|ссылка|поле|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" не (?:выбрана|выбрано|выбран)$")
    public void radioButtonIsNotSelected(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(not(selected));
    }

    /**
     * Проверка, что радиокнопка выбрана
     */
    @И("^чекбокс \"([^\"]*)\" в блоке \"([^\"]*)\" выбран$")
    public void checkBoxIsChecked(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(checked);
    }

    /**
     * Проверка, что радиокнопка не выбрана
     */
    @И("^чекбокс \"([^\"]*)\" в блоке \"([^\"]*)\" не выбран$")
    public void checkBoxIsNotChecked(String elementName, String blockName) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        element.shouldHave(not(checked));
    }


    /**
     * Проверка, что значение в поле содержит текст, указанный в шаге
     * (в приоритете: из property, из переменной сценария, значение аргумента).
     * Не чувствителен к регистру
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" содержит текст \"([^\"]*)\"$")
    @А("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" содержит текст")
    public void testFieldContainsInnerText(String elementName, String blockName, String expectedValue) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        expectedValue = getPropertyOrStringVariableOrValue(expectedValue);
        element.shouldHave(
                or("Текст элемента содержит",
                        text(expectedValue),
                        value(expectedValue)));
    }

    /**
     * Проверка, что значение в поле содержит текст, указанный в шаге
     * (в приоритете: из property, из переменной сценария, значение аргумента).
     * Не чувствителен к регистру
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" не содержит текст \"([^\"]*)\"$")
    @А("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" не содержит текст")
    public void testFieldNotContainsInnerText(String elementName, String blockName, String expectedValue) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        expectedValue = getPropertyOrStringVariableOrValue(expectedValue);
        element.shouldHave(
                and("Текст элемента не содержит",
                        not(text(expectedValue)),
                        not(value(expectedValue))));
    }


    /**
     * Проверка, что текста в поле равен значению, указанному в шаге
     * (в приоритете: из property, из переменной сценария, значение аргумента)
     */
    @И("^текст (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\" равен \"([^\"]*)\"$")
    @А("^текст (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\" равен$")
    public void compareValInFieldAndFromStep(String elementName, String blockName, String expectedValue) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        expectedValue = getPropertyOrStringVariableOrValue(expectedValue);
        element.shouldHave(
                or("Текст элемента равен",
                        exactText(expectedValue),
                        exactValue(expectedValue)));
    }

    /**
     * Производится проверка количества символов в поле со значением, указанным в шаге
     */
    @И("^текст (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\" содержит (\\d+) символов$")
    public void checkFieldSymbolsCount(String elementName, String blockName, int num) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);

        int lengthText = element.getText().length();
        int lengthValue = element.getValue().length();

        int sleepTime = 100;
        for (int time = 0; time < Configuration.timeout; time += sleepTime) {
            if (lengthText == num || lengthValue == num) {
                return;
            }
            sleep(sleepTime);
        }

        BrowserSteps.takeScreenshot();
        assertEquals(String.format("Неверное количество символов. Ожидаемый результат: %s, текущий результат: %s", num, lengthText), num, lengthText);
    }


    /**
     * Проверка, что у элемента есть атрибут с ожидаемым значением (в приоритете: из property, из переменной сценария, значение аргумента)
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" содержит атрибут \"([^\"]*)\" со значением \"([^\"]*)\"$")
    public void checkElemContainsAtrWithValue(String elementName, String blockName, String attribute, String expectedAttributeValue) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        expectedAttributeValue = getPropertyOrStringVariableOrValue(expectedAttributeValue);
        element.shouldHave(attribute(attribute, expectedAttributeValue));
    }


    /**
     * Проверка, что у элемента есть css с ожидаемым значением (в приоритете: из property, из переменной сценария, значение аргумента)
     */
    @И("^(?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) \"([^\"]*)\" в блоке \"([^\"]*)\" содержит css \"([^\"]*)\" со значением \"([^\"]*)\"$")
    public void checkCssInElement(String elementName, String blockName, String cssName, String cssValue) {
        SelenideElement element = coreScenario.getCurrentPage().getBlockElement(blockName,elementName);
        cssName = getPropertyOrStringVariableOrValue(cssName);
        cssValue = getPropertyOrStringVariableOrValue(cssValue);
        element.shouldHave(cssValue(cssName, cssValue));
    }

    /**
     * Сохранение значения элемента в переменную
     */
    @И("^значение (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) \"([^\"]*)\" в блоке \"([^\"]*)\" сохранено в переменную \"([^\"]*)\"$")
    public void storeElementValueInVariable(String elementName, String variableName) {
        String text = coreScenario.getCurrentPage().getElement(elementName).getText();
        coreScenario.setVar(variableName, text);
        coreScenario.write("Значение [" + text + "] сохранено в переменную [" + variableName + "]");
    }
}
