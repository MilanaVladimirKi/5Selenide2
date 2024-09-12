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

    @Test
    public void testValCity() {
        $("[data-test-id=city] input").setValue("Париж");
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planingDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"), Duration.ofSeconds(20));
    }

    @Test
    public void testNotCity() {
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planingDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(20));
    }

    @Test
    public void testValDate() {
        $("[data-test-id=city] input").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=date].input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"), Duration.ofSeconds(20));
    }

    @Test
    public void testNotDate() {
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=date].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(20));
    }

    @Test
    public void testValName() {
        $("[data-test-id=city] input").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planningDate);
        $("[data-test-id=name] input").setValue("К@1435 12$%#@");
        $("[data-test-id=phone] input").setValue(phone);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы."), Duration.ofSeconds(20)); // проверить
    }

    @Test
    public void testNotName() {
        $("[data-test-id=city] input").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planningDate);
        $("[data-test-id=phone] input").setValue(phone);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(20));
    }

    @Test
    public void testValPhone() {
        $("[data-test-id=city] input").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planningDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(+7987654);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(""), Duration.ofSeconds(20)); // проверить
    }

    @Test
    public void testNotPhone() {
        $("[data-test-id=city] input").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planningDate);
        $("[data-test-id=name] input").setValue(name);
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(20));
    }

    @Test
    public void TestCheckBox() {
        $("[data-test-id=city] input").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(planningDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $(".button").click();
        $("[data-test-id=argeement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих данных"), Duration.ofSeconds(20));
    }

}
