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

    val ZERO = BigDecimal("0")

    def processApples(apples : Item) : BigDecimal = {
      apples.count match {
        case z if z <= 0 => ZERO
        case x if x > 1 => apples.price.+(processApples(apples.copy(count = apples.count-2)))
        case 1 => apples.price
      }
    }

    def processOranges(oranges : Item) : BigDecimal = {
      oranges.count match {
        case z if z <= 0 => ZERO
        case y if y > 2 => oranges.price.*(2).+(processOranges(oranges.copy(count = oranges.count - 3)))
        case x => oranges.price.*(x).+(processOranges(oranges.copy(count = oranges.count - x)))
      }
    }

    items match {
      case Nil => ZERO
      case head :: tail => head.name match {
        case "Apple" => processApples(head).+(processItems(tail))
        case "Orange" => processOranges(head).+(processItems(tail))
        case _ => processItems(tail)
      }
    }
  }
}

case class Item (name: String, price: BigDecimal, count : Int)
