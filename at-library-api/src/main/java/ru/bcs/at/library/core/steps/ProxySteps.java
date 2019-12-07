/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 * <p>Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.bcs.at.library.core.steps;

import io.cucumber.java.ru.И;
import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import ru.bcs.at.library.core.core.helpers.PropertyLoader;

/**
 * <h1>Шаги по включению/отключению прокси</h1>
 */
@Log4j2
public class ProxySteps {

    /**
     * <p>Используется прокси</p>
     *
     * @param proxyHost адрес proxy, например: s-nsk-proxy-01.global.bcs
     * @param proxyPort порт proxy, например: 8080
     */
    @Deprecated
    @И("^используется proxy: \"([^\"]+)\" port: \"([^\"]+)\"$")
    public void turnOnProxy(String proxyHost, String proxyPort) {
        proxyHost = PropertyLoader.loadValueFromFileOrPropertyOrVariableOrDefault(proxyHost);
        proxyPort = PropertyLoader.loadValueFromFileOrPropertyOrVariableOrDefault(proxyPort);

        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", proxyPort);
        System.setProperty("https.proxyHost", proxyHost);
        System.setProperty("https.proxyPort", proxyPort);

        RestAssured.proxy = ProxySpecification.host(proxyHost).withPort(Integer.parseInt(proxyPort));

    }

    /**
     * <p>Выключить использование прокси</p>
     */
    @Deprecated
    @И("^выключено использование proxy$")
    public void turnOffProxy() {
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");

        RestAssured.proxy = null;
    }

    /**
     * <p>Найти http-запрос через прокси</p>
     */
    @И("^через прокси отправлен запрос \"([^\"]+)\"$")
    public void findRequestOnProxy(String url) {
        url = PropertyLoader.loadValueFromFileOrPropertyOrVariableOrDefault(url);
        RestAssured.config =
                RestAssuredConfig.newConfig().jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));

        RequestSpecification request = RestAssured.given();
        Response response = request.request(Method.GET, url);

        response.then().assertThat().body(Matchers.not(Matchers.equalTo("")));
    }
}
