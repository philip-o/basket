package com.ogun.shop

object Basket {

  def main(args: Array[String]): Unit = {
    val items  = System.console().readLine("Enter your shopping list in a comma-separated format > ").split(",").sorted
    val apples = createItem("Apple",items.count(_.trim.equalsIgnoreCase("Apple")))
    val oranges = createItem("Orange", items.count(_.trim.equalsIgnoreCase("Orange")))
    val invalid = createItem("Invalid",items.size - (apples.count + oranges.count))
    if(invalid.count > 0)
      println(s"${invalid.count} items found in basket")

    println(s"Your bill is £${processItems(List(apples,oranges))}")
  }

  def createItem(name : String, amount : Int) = {
    name match {
      case "Apple" => Item("Apple",BigDecimal("0.6"),amount)
      case "Orange" => Item("Orange",BigDecimal("0.25"),amount)
      case _ => Item("Invalid",BigDecimal(0),amount)
    }
  }

  def processItems(items : List[Item]) : BigDecimal= {
    items.foldRight(BigDecimal("0"))((i, j) => j.+(i.price.*(i.count)))
  }
}

case class Item (name: String, price: BigDecimal, count : Int)
