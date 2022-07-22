package o1.adventure

import scala.collection.mutable.{Buffer, Map}


class Area(var name: String, var description: String, var trivia: Buffer[(String, String)]) {

  private val neighbors = Map[String, Area]()
  private val item = Map[String, Item] ()



  def neighbor(direction: String) = this.neighbors.get(direction)

  def addItem(item: Item) = {
    this.item += item.name -> item
  }

  def contains (itemName: String) = this.item.keys.toVector.contains(itemName)

  def removeItem(itemName: String): Option[Item] = {
      if (this.contains(itemName)) this.item.remove(itemName) else None
  }


  def setNeighbor(direction: String, neighbor: Area) = {
    this.neighbors += direction -> neighbor
  }

  def setNeighbors(exits: Vector[(String, Area)]) = {
    this.neighbors ++= exits
  }



  def fullDescription = {
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    val challenge =  this.trivia.map(_._1).mkString(" ")
    val direction =  "\n\nDirections available: " + this.neighbors.keys.mkString(" ")
    if (this.name == "Middle")
       this.description + direction
    else if (this.name == "Home") {
       this.description + exitList
   } else {
       challenge + exitList
    }
  }


}
