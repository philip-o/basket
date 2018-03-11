package com.ogun.shop

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class BasketSpec extends FeatureSpec with GivenWhenThen with Matchers {

  scenario("Empty basket of items") {
    Given("I have a basket of no items")
    val items = List.empty[Item]

    When("I add no items")

    Then("The till should calculate a total of 0")
    Basket.processItems(items) should be(0)
  }

  scenario("1 item in basket") {
    Given("I have a basket of no items")
    var items = List.empty[Item]

    When("I add an apple")
    items ::= Basket.createItem("Apple",1)

    Then("The till should calculate a total of 0.6")
    Basket.processItems(items) should be(0.6)
  }

  scenario("2 apples in basket") {
    Given("I have a basket of no items")
    var items = List.empty[Item]

    When("I add an apple")
    items ::= Basket.createItem("Apple",2)

    Then("The till should calculate a total of 1.2")
    Basket.processItems(items) should be(1.2)
  }

  scenario("1 apple, 1 orange in basket") {
    Given("I have a basket of no items")
    var items = List.empty[Item]

    When("I add an apple and orange")
    items ::= Basket.createItem("Apple",1)
    items ::= Basket.createItem("Orange",1)

    Then("The till should calculate a total of 0.85")
    Basket.processItems(items) should be(0.85)
  }

  scenario("1 pear in basket") {
    Given("I have a basket of no items")
    var items = List.empty[Item]

    When("I add an apple and orange")
    items ::= Basket.createItem("Pear",1)

    Then("The till should calculate a total of 0.0")
    Basket.processItems(items) should be(0)
  }

  scenario("3 for 2") {
    Given("I have a basket of no items")
    var items = List.empty[Item]

    When("I add an apple and orange")
    items ::= Basket.createItem("Orange",3)

    Then("The till should calculate a total of 0.75")
    Basket.processItems(items) should be(0.75)
  }
}
