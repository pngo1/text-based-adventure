package o1.adventure

import scala.collection.mutable.Map

class Player(startingArea: Area) {

  private var currentLocation = startingArea
  private var quitCommandGiven = false
  private var items = Map[ String, Item] ()


  def hasQuit = this.quitCommandGiven

  def location = this.currentLocation

  def drop(itemName: String) = {
    if (this.has(itemName)) {
      this.location.addItem(this.items(itemName))
      this.items -= itemName
      "You drop the " + s"$itemName."
    } else "You don't have that!"
  }

  def answer(response: String) = {
   if (this.currentLocation.trivia.exists(_._2 == response)) {
      if (this.currentLocation.name == "Red") {
          this.currentLocation.trivia.remove(0)
          "Well Done. Here comes a scarf for you. Come and collect it!"
    } else if (this.currentLocation.name == "Folklore") {
           this.currentLocation.trivia.remove(0)
          "Wowsa. Flower or cardigan? Take one or you may have fewer turns to take."
    } else if (this.currentLocation.name == "Lover") {
          this.currentLocation.trivia.remove(0)
          "Good job! Choose a keychain or paper ring as your reward. \nYou can take both but it can mean that you have fewer turns to take."
    } else if (this.currentLocation.name == "Evermore") {
          this.currentLocation.trivia.remove(0)
          "Cool! You can collect a flannel shirt for yourself now."
   } else ""
     } else "Oops. Command \"hint\" to get some hints"}


  def hint = {
    if (this.currentLocation.name == "Red") {
          "This color is a symbol of sadness."
    } else if (this.currentLocation.name == "Folklore") {
          "This song tells the perspectives of two ex-lovers seeing each other following the breakup."
    } else if (this.currentLocation.name == "Lover") {
          "Obviously, one is \"Lover\""
    } else if (this.currentLocation.name == "Evermore") {
          "This is an entertainment area in the southwestern section of New York borough of Brooklyn."
   } else "Go in any direction to explore"
  }



  def unbox (itemName: String): String = {
   if (this.has(itemName))
        s"\n${this.items(itemName).description}"
   else "If you want to unbox something, you need to collect it first."}


  def has (itemName: String) = this.items.keys.toVector.contains(itemName)

  def collect (itemName: String) = {
    if (this.currentLocation.contains(itemName)) {
      this.items += itemName -> this.currentLocation.removeItem(itemName).get
     "You pick up the " + s"$itemName."}
    else
    "There is no " + s"$itemName" + " here to pick up."
  }


  def repertoire = this.items.keys.toVector


  def go(direction: String) = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    val zoneName = "\nYou are currently entering: " + this.currentLocation.name + " zone"
    if (destination.isDefined) "You go " + direction + "." + zoneName  else "You can't go " + direction + "."
  }

 def help = {
   val title = "GENERAL INSTRUCTION"
   val movementInstruction = "\n\nTo move or enter or exit a zone, you enter command \"go + directions available\".\nTo rest, command \"rest\". To quit, command \"quit\"."
   val triviaInstruction = "\nTo answer a trivia, command \"answer + the answer for the trivia\"."
   val hintInstruction = "\nTo get hints, command \"hint\". The more hints you ask for, the higher chance is that you may lose the game."
   val pickInstruction = "\nYou can only collect items after answering the trivia correctly. Command \"collect + the item you want\"."
   val unboxInstruction = "\nYou can discover the meaning of these items by entering the command \"unbox + the item you collect\"."
   val generalInstruction = "\nTo win, reach the \"Home\" zone with at least 2 items and take fewer than 12 turns. \n\nGood luck with your adventure."

   title + movementInstruction + triviaInstruction + hintInstruction + pickInstruction + unboxInstruction + generalInstruction

 }

  def rest() = {
    "You can rest for a while. Better get a move on, though."
  }


  def quit() = {
    this.quitCommandGiven = true
    ""
  }





}


