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

import com.codeborne.selenide.Selenide;
import cucumber.api.Scenario;
import lombok.extern.log4j.Log4j2;
import ru.bcs.at.library.core.cucumber.ScopedVariables;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Главный класс, отвечающий за сопровождение тестовых шагов
 */
@Log4j2
public final class CoreScenario {

    private static CoreScenario instance = new CoreScenario();

    /**
     * @author Anton Pavlov
     * Среда прогона тестов, хранит в себе: Cucumber.Scenario,
     * переменные, объявленные пользователем в сценарии и страницы, тестирование которых будет производиться
     */
    private static CoreEnvironment environment;

    private CoreScenario() {
    }

    public static CoreScenario getInstance() {
        return instance;
    }

    public CoreEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(CoreEnvironment coreEnvironment) {
        environment = coreEnvironment;
    }

    public static void sleep(int seconds) {
        Selenide.sleep(TimeUnit.MILLISECONDS.convert(seconds, TimeUnit.SECONDS));
    }

    /**
     * @author Anton Pavlov
     * Получение страницы, тестирование которой производится в данный момент
     */
    public CorePage getCurrentPage() {
        return environment.getPages().getCurrentPage();
    }

    /**
     * @author Anton Pavlov
     * Задание страницы, тестирование которой производится в данный момент
     */
    public void setCurrentPage(CorePage page) {
        if (page == null) {
            throw new IllegalArgumentException("Происходит переход на несуществующую страницу. " +
                    "Проверь аннотации @Name у используемых страниц");
        }
        environment.getPages().setCurrentPage(page);
    }

    /**
     * @author Anton Pavlov
     * Позволяет получить доступ к полям и методам конкретной страницы, которая передается в метод в качестве аргумента.
     * Пример использования: {@code withPage(CorePage.class, page -> { some actions with CorePage methods});}
     * Проверка отображения всех элементов страницы выполняется всегда
     *
     * @param clazz класс страницы, доступ к полям и методам которой необходимо получить
     */
    public static <T extends CorePage> void withPage(Class<T> clazz, Consumer<T> consumer) {
        withPage(clazz, true, consumer);
    }

    /**
     * @author Anton Pavlov
     * Позволяет получить доступ к полям и методам конкретной страницы.
     * Пример использования: {@code withPage(CorePage.class, page -> { some actions with CorePage methods});}
     * Проверка отображения всех элементов страницы опциональна
     *
     * @param clazz                   класс страницы, доступ к полям и методам которой необходимо получить
     * @param checkIfElementsAppeared флаг, отвечающий за проверку отображения всех элементов страницы, не помеченных аннотацией @Optional
     */
    public static <T extends CorePage> void withPage(Class<T> clazz, boolean checkIfElementsAppeared, Consumer<T> consumer) {
        Pages.withPage(clazz, checkIfElementsAppeared, consumer);
    }

    /**
     * @author Anton Pavlov
     * Возвращает текущий сценарий (Cucumber.api)
     */
    public Scenario getScenario() {
        return this.getEnvironment().getScenario();
    }

    /**
     * @author Anton Pavlov
     * Получение списка страниц
     */
    public Pages getPages() {
        return this.getEnvironment().getPages();
    }

    public CorePage getPage(String name) {
        return this.getEnvironment().getPage(name);
    }

    /**
     * @author Anton Pavlov
     * Выводит дополнительный информационный текст в отчет (уровень логирования INFO)
     */
    public void write(Object object) {
        this.getEnvironment().write(object);
    }

    /**
     * @author Anton Pavlov
     * Получение переменной по имени, заданного пользователем, из пула переменных "variables" в CoreEnvironment
     *
     * @param name - имя переменной, для которй необходимо получить ранее сохраненное значение
     */
    public Object getVar(String name) {
        Object obj = this.getEnvironment().getVar(name);
        if (obj == null) {
            throw new IllegalArgumentException("Переменная " + name + " не найдена");
        }
        return obj;
    }

    /**
     * @author Anton Pavlov
     * Получение переменной без проверки на NULL
     */
    public Object tryGetVar(String name) {
        return this.getEnvironment().getVar(name);
    }

    /**
     * @author Anton Pavlov
     * Получение страницы по классу с возможностью выполнить проверку отображения элементов страницы
     *
     * @param clazz                   - класс страницы, которую необходимо получить
     * @param checkIfElementsAppeared - флаг, определяющий проверку отображения элементов на странице
     */
    public <T extends CorePage> T getPage(Class<T> clazz, boolean checkIfElementsAppeared) {
        return Pages.getPage(clazz, checkIfElementsAppeared);
    }

    /**
     * @author Anton Pavlov
     * Получение страницы по классу (проверка отображения элементов страницы не выполняется)
     *
     * @param clazz - класс страницы, которую необходимо получить
     */
    public <T extends CorePage> T getPage(Class<T> clazz) {
        return Pages.getPage(clazz, true);
    }

    /**
     * @author Anton Pavlov
     * Получение страницы по классу и имени (оба параметра должны совпадать)
     *
     * @param clazz - класс страницы, которую необходимо получить
     * @param name  - название страницы, заданное в аннотации @Name
     */
    public <T extends CorePage> T getPage(Class<T> clazz, String name) {
        return this.getEnvironment().getPage(clazz, name);
    }

    /**
     * @author Anton Pavlov
     * Заменяет в строке все ключи переменных из пула переменных "variables" в классе CoreEnvironment на их значения
     *
     * @param stringToReplaceIn строка, в которой необходимо выполнить замену (не модифицируется)
     */
    public String replaceVariables(String stringToReplaceIn) {
        return this.getEnvironment().replaceVariables(stringToReplaceIn);
    }

    /**
     * @author Anton Pavlov
     * Добавление переменной в пул "variables" в классе CoreEnvironment
     *
     * @param name   имя переменной заданное пользователем, для которого сохраняется значение. Является ключом в пуле variables в классе CoreEnvironment
     * @param object значение, которое нужно сохранить в переменную
     */
    public void setVar(String name, Object object) {
        this.getEnvironment().setVar(name, object);
    }

    /**
     * @author Anton Pavlov
     * Получение всех переменных из пула "variables" в классе CoreEnvironment
     */
    public ScopedVariables getVars() {
        return this.getEnvironment().getVars();
    }
}
