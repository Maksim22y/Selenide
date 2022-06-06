/*1)Зайти на сайт:"G-Store"
* 2)Ввійти в аакаунт
* 3)Перевірити чи зайшоі в аккаунт
* 4)В інпуті ввести:"iphone"
* 5)Перевірити чи кількість товару на сторінці не менше 16
* 6)*/
package ru.gstore;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestingSite {
    @Test
    public void main() {
        Configuration.timeout = 15000;
        setUp();
        login();
        inputSearch();
        buyAndCheck();
    }

    void setUp() {

        open("https://gstore.ua/ru/");
    }
    void login() {
        $x("//a[@class='userbar__button __active']").click();
        $x("//*[@id=\"login_form_id\"]/dl/dd[1]/iput").setValue("killativspidey@gmail.com");
        $x("//*[@id=\"login_form_id\"]/dl/dd[2]/input").setValue("gimno2000").pressEnter();
        $x("//div[@class=\"userbar userbar--user-outline-icon j-user-tabs\"]").shouldBe(disappear);
        //$x("//span[@class=\"userbar__button-text\"]").shouldBe(Condition.text("VT"));
    }
     void inputSearch() {
         $x("//input[@class='search__input']").setValue("iphone");

         Condition clickable = and("can be clicked", visible, enabled);
         $$x("//button[@class='search__button']").findBy(clickable).click();

         $$x("//div[@class=\"catalogCard-view\"]").shouldHave(sizeGreaterThanOrEqual(16));
     }

     void buyAndCheck() {
        String priceProduct = $(By.cssSelector("div[data-id=\"319\"] .catalogCard-price")).getText();
        String nameEqual = $(By.cssSelector("div[data-id=\"319\"] .catalogCard-title ")).getText();
        $x("//a[@id=\"j-buy-button-widget-319\"]").click();
        $(By.cssSelector("div[class=\"cart-set-i\"]:nth-child(1) .cart-title")).shouldHave(text(nameEqual));
        $x("//div[@class=\"cart-cost j-sum-p\"]").shouldHave(text(priceProduct));
    }


}