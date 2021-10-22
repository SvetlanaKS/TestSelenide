package ru.netology;

//import com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class TestFormRegistration {

    @BeforeEach
    void setUp() {
            Configuration.browser = "chrome";
        open("http://localhost:9999");
    }

    @Test
    public void shouldOrderFormRegistration() {


        $("[data-test-id=city] .input__control").setValue("Екатеринбург");

        LocalDate today = LocalDate.now();
        LocalDate data = today.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formatDate = data.format(formatter);
        // $("[data-test-id=date]").setValue(formatDate);
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(formatDate);

        $("[data-test-id=name] .input__control").setValue("Петров Василий");

        $("[data-test-id=phone] .input__control").setValue("+79990000000");

        //$(".checkbox__control").click();
       $("div form fieldset label").click();

        $(Selectors.byText("Забронировать")).click();

        $(Selectors.withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        $("[data-test-id='notification'] .notification__content").shouldHave(text("Встреча успешно забронирована на " + formatDate));
    }
}
