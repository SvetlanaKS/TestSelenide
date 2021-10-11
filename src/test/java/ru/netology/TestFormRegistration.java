package ru.netology;

//import com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class TestFormRegistration {

    @BeforeEach
    static void setUpAll() {
        Configuration.browser = "chrome";
    }

    @Test
    void shouldOrderFormRegistration() {
        open("http://localhost:9999");
        $("[data-test-id=city]").setValue("Екатеринбург");

        LocalDate today = LocalDate.now();
        LocalDate data = today.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String formatDate = data.format(formatter);
        $("[data-test-id=date]").setValue(formatDate);

        $("[data-test-id=name]").setValue("Петров Василий");

        $("[data-test-id=phone]").setValue("+7999000000");

        $(".checkbox__control").click();

        $(Selectors.byText("Забронировать")).click();

        $(Selectors.withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

    }
}
