package ru.topacademy.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.topacademy.data.DataGenerator;

import java.time.Duration;

import static come.codeborne.selenide.Condition.*;
import static come.codeborne.selenide.Selenide.$;
import static come.codeborne.selenide.Selenide.open;

public class ChangeDateTest {

    @Test
    public void testSendForm() {
        $("[data-test-id=city] input").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planingDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification] .notification__title").shouldHave(exactText("Успешно!"), Duration.ofSeconds(20));
        $("[data-test-id=success-notification] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " +), Duration.ofSeconds(20)); // проверить
        $(".calendar-input__custom-control input").doubleClick().sendKeys(changeDate);
        $(".button").click();
        $("[data-test-id=replan-notification] .notification__title").shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id=replan-notification] button").click();
        $("[data-test-id=success-notification] .notification__title").shouldHave(exactText("Успешно!"), Duration.ofSeconds(40));
        $("[data-test-id=success-notification] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " +), Duration.ofSeconds(40)); // проверить
    }
}
