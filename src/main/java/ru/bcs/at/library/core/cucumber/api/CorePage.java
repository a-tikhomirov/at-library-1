/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.bcs.at.library.core.cucumber.api;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import ru.bcs.at.library.core.cucumber.annotations.Name;
import ru.bcs.at.library.core.cucumber.utils.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static ru.bcs.at.library.core.core.helpers.PropertyLoader.loadProperty;

/**
 * Класс для реализации паттерна PageObject
 */
@Log4j2
public abstract class CorePage extends ElementsContainer {
    /**
     * @author Anton Pavlov
     * Стандартный таймаут ожидания элементов в миллисекундах
     */
    private static final String WAITING_APPEAR_TIMEOUT_IN_MILLISECONDS = "20000";

    /**
     * @author Anton Pavlov
     * Получение блока со страницы по имени (аннотированного "Name")
     */
    public CorePage getBlock(String blockName) {
        return (CorePage) Optional.ofNullable(namedElements.get(blockName))
                .orElseThrow(() -> new IllegalArgumentException("Блок " + blockName + " не описан на странице " + this.getClass().getName()));
    }

    /**
     * @author Anton Pavlov
     * Получение списка блоков со страницы по имени (аннотированного "Name")
     */
    @SuppressWarnings("unchecked")
    public List<CorePage> getBlocksList(String listName) {
        Object value = namedElements.get(listName);
        if (!(value instanceof List)) {
            throw new IllegalArgumentException("Список " + listName + " не описан на странице " + this.getClass().getName());
        }
        Stream<Object> s = ((List) value).stream();
        return s.map(CorePage::castToCorePage).collect(toList());
    }

    /**
     * @author Anton Pavlov
     * Получение списка из элементов блока со страницы по имени (аннотированного "Name")
     */
    public List<SelenideElement> getBlockElements(String blockName) {
        return getBlock(blockName).namedElements.entrySet().stream()
                .map(x -> ((SelenideElement) x.getValue())).collect(toList());
    }

    /**
     * @author Anton Pavlov
     * Получение элемента блока со страницы по имени (аннотированного "Name")
     */
    public SelenideElement getBlockElement(String blockName, String elementName) {
        return ((SelenideElement) getBlock(blockName).namedElements.get(elementName));
    }

    /**
     * @author Anton Pavlov
     * Получение элемента со страницы по имени (аннотированного "Name")
     */
    public SelenideElement getElement(String elementName) {
        return (SelenideElement) Optional.ofNullable(namedElements.get(elementName))
                .orElseThrow(() -> new IllegalArgumentException("Элемент " + elementName + " не описан на странице " + this.getClass().getName()));
    }

    /**
     * @author Anton Pavlov
     * Получение элемента-списка со страницы по имени
     */
    @SuppressWarnings("unchecked")
    public List<SelenideElement> getElementsList(String listName) {
        Object value = namedElements.get(listName);
        if (!(value instanceof List)) {
            throw new IllegalArgumentException("Список " + listName + " не описан на странице " + this.getClass().getName());
        }
        Stream<Object> s = ((List) value).stream();
        return s.map(CorePage::castToSelenideElement).collect(toList());
    }

    /**
     * @author Anton Pavlov
     * Получение текстов всех элементов, содержащихся в элементе-списке,
     * состоящего как из редактируемых полей, так и статичных элементов по имени
     * Используется метод innerText(), который получает как видимый, так и скрытый текст из элемента,
     * обрезая перенос строк и пробелы в конце и начале строчки.
     */
    public List<String> getAnyElementsListInnerTexts(String listName) {
        List<SelenideElement> elementsList = getElementsList(listName);
        return elementsList.stream()
                .map(element -> element.getTagName().equals("input")
                        ? element.getValue().trim()
                        : element.innerText().trim()
                )
                .collect(toList());
    }

    /**
     * @author Anton Pavlov
     * Получение текста элемента, как редактируемого поля, так и статичного элемента по имени
     */
    public String getAnyElementText(String elementName) {
        return getAnyElementText(getElement(elementName));
    }

    /**
     * @author Anton Pavlov
     * Получение текста элемента, как редактируемого поля, так и статичного элемента по значению элемента
     */
    public String getAnyElementText(SelenideElement element) {
        if (element.getTagName().equals("input")) {
            return element.getValue();
        } else {
            return element.getText();
        }
    }

    /**
     * @author Anton Pavlov
     * Получение текстов всех элементов, содержащихся в элементе-списке,
     * состоящего как из редактируемых полей, так и статичных элементов по имени
     */
    public List<String> getAnyElementsListTexts(String listName) {
        List<SelenideElement> elementsList = getElementsList(listName);
        return elementsList.stream()
                .map(element -> element.getTagName().equals("input")
                        ? element.getValue()
                        : element.getText()
                )
                .collect(toList());
    }

    /**
     * @author Anton Pavlov
     * Получение всех элементов страницы, не помеченных аннотацией "Optional"
     */
    public List<SelenideElement> getPrimaryElements() {
        if (primaryElements == null) {
            primaryElements = readWithWrappedElements();
        }
        return new ArrayList<>(primaryElements);
    }

    /**
     * @author Anton Pavlov
     * Обертка над CorePage.isAppeared
     * Ex: CorePage.appeared().doSomething();
     */
    public final CorePage appeared() {
        isAppeared();
        return this;
    }

    /**
     * @author Anton Pavlov
     * Обертка над CorePage.isDisappeared
     * Ex: CorePage.disappeared().doSomething();
     */
    public final CorePage disappeared() {
        isDisappeared();
        return this;
    }

    /**
     * @author Anton Pavlov
     * Проверка появления всех элементов страницы, не помеченных аннотацией "Optional"
     */
    protected void isAppeared() {
        String timeout = loadProperty("waitingAppearTimeout", WAITING_APPEAR_TIMEOUT_IN_MILLISECONDS);
        getPrimaryElements().parallelStream().forEach(elem ->
                elem.waitUntil(Condition.appear, Integer.valueOf(timeout)));
        eachForm(CorePage::isAppeared);
    }

    private void eachForm(Consumer<CorePage> func) {
        Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(ru.bcs.at.library.core.cucumber.annotations.Optional.class) == null)
                .forEach(f -> {
                    if (CorePage.class.isAssignableFrom(f.getType())) {
                        CorePage corePage = CoreScenario.getInstance().getPage((Class<? extends CorePage>) f.getType());
                        func.accept(corePage);
                    }
                });
    }

    /**
     * @author Anton Pavlov
     * Проверка, что все элементы страницы, не помеченные аннотацией "Optional", исчезли
     */
    protected void isDisappeared() {
        String timeout = loadProperty("waitingAppearTimeout", WAITING_APPEAR_TIMEOUT_IN_MILLISECONDS);
        getPrimaryElements().parallelStream().forEach(elem ->
                elem.waitWhile(Condition.exist, Integer.valueOf(timeout)));
    }

    /**
     * @author Anton Pavlov
     * Обертка над CorePage.isAppearedInIe
     * Ex: CorePage.ieAppeared().doSomething();
     * Используется при работе с IE
     */
    public final CorePage ieAppeared() {
        isAppearedInIe();
        return this;
    }

    /**
     * @author Anton Pavlov
     * Обертка над CorePage.isDisappearedInIe
     * Ex: CorePage.ieDisappeared().doSomething();
     * Используется при работе с IE
     */
    public final CorePage ieDisappeared() {
        isDisappearedInIe();
        return this;
    }

    /**
     * @author Anton Pavlov
     * Проверка появления всех элементов страницы, не помеченных аннотацией "Optional".
     * Вместо parallelStream используется stream из-за медленной работы IE
     */
    protected void isAppearedInIe() {
        String timeout = loadProperty("waitingAppearTimeout", WAITING_APPEAR_TIMEOUT_IN_MILLISECONDS);
        getPrimaryElements().stream().forEach(elem ->
                elem.waitUntil(Condition.appear, Integer.valueOf(timeout)));
        eachForm(CorePage::isAppearedInIe);
    }

    /**
     * @author Anton Pavlov
     * Проверка, что все элементы страницы, не помеченные аннотацией "Optional", исчезли
     * Вместо parallelStream используется stream из-за медленной работы IE
     */
    protected void isDisappearedInIe() {
        String timeout = loadProperty("waitingAppearTimeout", WAITING_APPEAR_TIMEOUT_IN_MILLISECONDS);
        getPrimaryElements().stream().forEach(elem ->
                elem.waitWhile(Condition.exist, Integer.valueOf(timeout)));
    }


    /**
     * @param condition Selenide.Condition
     * @param timeout   максимальное время ожидания для перехода элементов в заданное состояние
     * @param elements  произвольное количество selenide-элементов
     * @author Anton Pavlov
     * Обертка над Selenide.waitUntil для произвольного количества элементов
     */
    public void waitElementsUntil(Condition condition, int timeout, SelenideElement... elements) {
        Spectators.waitElementsUntil(condition, timeout, elements);
    }

    /**
     * @param elements список selenide-элементов
     * @author Anton Pavlov
     * Обертка над Selenide.waitUntil для работы со списком элементов
     */
    public void waitElementsUntil(Condition condition, int timeout, List<SelenideElement> elements) {
        Spectators.waitElementsUntil(condition, timeout, elements);
    }

    /**
     * @param elementNames произвольное количество строковых переменных с именами элементов
     * @author Anton Pavlov
     * Проверка, что все переданные элементы в течении заданного периода времени
     * перешли в состояние Selenide.Condition
     */
    public void waitElementsUntil(Condition condition, int timeout, String... elementNames) {
        List<SelenideElement> elements = Arrays.stream(elementNames)
                .map(name -> namedElements.get(name))
                .flatMap(v -> v instanceof List ? ((List<?>) v).stream() : Stream.of(v))
                .map(CorePage::castToSelenideElement)
                .filter(Objects::nonNull)
                .collect(toList());
        Spectators.waitElementsUntil(condition, timeout, elements);
    }

    /**
     * @author Anton Pavlov
     * Поиск элемента по имени внутри списка элементов
     */
    public static SelenideElement getButtonFromListByName(List<SelenideElement> listButtons, String nameOfButton) {
        List<String> names = new ArrayList<>();
        for (SelenideElement button : listButtons) {
            names.add(button.getText());
        }
        return listButtons.get(names.indexOf(nameOfButton));
    }

    /**
     * @author Anton Pavlov
     * Приведение объекта к типу SelenideElement
     */
    private static SelenideElement castToSelenideElement(Object object) {
        if (object instanceof SelenideElement) {
            return (SelenideElement) object;
        }
        return null;
    }

    private static CorePage castToCorePage(Object object) {
        if (object instanceof CorePage) {
            return (CorePage) object;
        }
        return null;
    }

    /**
     * @author Anton Pavlov
     * Список всех элементов страницы
     */
    private Map<String, Object> namedElements;
    /**
     * @author Anton Pavlov
     * Список элементов страницы, не помеченных аннотацией "Optional"
     */
    private List<SelenideElement> primaryElements;

    @Override
    public void setSelf(SelenideElement self) {
        super.setSelf(self);
        initialize();
    }

    public CorePage initialize() {
        namedElements = readNamedElements();
        primaryElements = readWithWrappedElements();
        return this;
    }

    /**
     * @author Anton Pavlov
     * Поиск и инициализации элементов страницы
     */
    private Map<String, Object> readNamedElements() {
        checkNamedAnnotations();
        return Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .peek((Field f) -> {
                    if (!SelenideElement.class.isAssignableFrom(f.getType())
                            && !CorePage.class.isAssignableFrom(f.getType())) {
                        if (List.class.isAssignableFrom(f.getType())) {
                            ParameterizedType listType = (ParameterizedType) f.getGenericType();
                            Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
                            if (SelenideElement.class.isAssignableFrom(listClass) || CorePage.class.isAssignableFrom(listClass)) {
                                return;
                            }
                        }
                        throw new IllegalStateException(
                                format("Поле с аннотацией @Name должно иметь тип SelenideElement или List<SelenideElement>.\n" +
                                        "Если поле описывает блок, оно должно принадлежать классу, унаследованному от CorePage.\n" +
                                        "Найдено поле с типом %s", f.getType()));
                    }
                })
                .collect(toMap(f -> f.getDeclaredAnnotation(Name.class).value(), this::extractFieldValueViaReflection));
    }

    /**
     * @author Anton Pavlov
     * Поиск по аннотации "Name"
     */
    private void checkNamedAnnotations() {
        List<String> list = Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .map(f -> f.getDeclaredAnnotation(Name.class).value())
                .collect(toList());
        if (list.size() != new HashSet<>(list).size()) {
            throw new IllegalStateException("Найдено несколько аннотаций @Name с одинаковым значением в классе " + this.getClass().getName());
        }
    }

    /**
     * @author Anton Pavlov
     * Поиск и инициализации элементов страницы без аннотации Optional
     */
    private List<SelenideElement> readWithWrappedElements() {
        return Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(ru.bcs.at.library.core.cucumber.annotations.Optional.class) == null)
                .map(this::extractFieldValueViaReflection)
                .flatMap(v -> v instanceof List ? ((List<?>) v).stream() : Stream.of(v))
                .map(CorePage::castToSelenideElement)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    private Object extractFieldValueViaReflection(Field field) {
        return Reflection.extractFieldValue(field, this);
    }
}
