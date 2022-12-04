package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class WebFormTest {

    SelenideElement form = $(".form");


    @Test
    void formTestIsRight() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Яак Йоала");
        form.$("[data-test-id=phone] input").setValue("+79998887755");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=order-success").shouldHave(
                exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void formTestIsRight1() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Ёлкина Алёна");
        form.$("[data-test-id=phone] input").setValue("+79998887755");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=order-success").shouldHave(
                exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void formTestDoubleFirstname() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Николь Мария Петрова");
        form.$("[data-test-id=phone] input").setValue("+79993332211");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=order-success").shouldHave(
                exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void formTestLargeName() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Ибн Аль-Банна Аль-Марракеши");
        form.$("[data-test-id=phone] input").setValue("+79993332211");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=order-success").shouldHave(
                exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }


    @Test
    void formTestNoName() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+73332221144");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(
                exactText("Поле обязательно для заполнения"));
    }

    @Test
    void formTestEmptyForm() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(
                exactText("Поле обязательно для заполнения"));
    }

    @Test
    void formTestNoPhone() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Антон Чехов");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(
                exactText("Поле обязательно для заполнения"));
    }

    @Test
    void formTestLatinName() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Chekhov Anton");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(
                exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void formTestShortPhone() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Антон Чехов");
        form.$("[data-test-id=phone] input").setValue("+7999888554");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(
                exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void formTestLongPhone() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Федор Шаляпин");
        form.$("[data-test-id=phone] input").setValue("+799988855412");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(
                exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void formTestNoPlusPhone() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Федор Шаляпин");
        form.$("[data-test-id=phone] input").setValue("89998885541");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(
                exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void formTestLiterInPhone() {
        open("http://localhost:9999");
        form.$("[data-test-id=name] input").setValue("Федор Шаляпин");
        form.$("[data-test-id=phone] input").setValue("+phonenumber");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(
                exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
