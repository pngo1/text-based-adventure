package o1.adventure
import scala.collection.mutable.Buffer


class SwiftAdventure {
  val title = "Swift Adventure"

  private val northTrivia = Buffer (("In the album's title song, \"Red\", what colour is losing him?", "blue"))
  private val eastTrivia = Buffer(("Which song was played in the ending scene of Netflix series \"You\"?", "exile"))
  private val southTrivia = Buffer(("Which song in Evermore is named after an attraction?", "coney island"))
  private val westTrivia = Buffer(("How many songs in Lover feature the word \"blue\"?", "5"))

  private val holyGround      = new Area("Middle", "You are at the middle of Swift Land Theme Park.", Buffer(("","")))
  private val redLand = new Area("Red", "\nWelcome to the land of heartbreak, Sad Girl and Boy Autumn", northTrivia)
  private val folkloreGarden      = new Area("Folklore", "\nJust let your imagination run wild", eastTrivia)
  private val evermoreForest = new Area("Evermore", "\nNow you are travelling further into the folklorian forest", southTrivia)
  private val loverFest    = new Area("Lover", "\nStep into daylight and see your shape of golden", westTrivia)
  private val home        = new Area("Home", "Home sweet home!",Buffer(("","")))
  private val destination = home

       holyGround.setNeighbors(Vector("north" -> redLand, "east" -> folkloreGarden, "south" -> evermoreForest, "west" -> loverFest   ))
  redLand.setNeighbors(Vector(                        "east" -> folkloreGarden, "south" -> holyGround,      "west" -> loverFest   ))
  evermoreForest.setNeighbors(Vector("north" -> holyGround,      "east" -> folkloreGarden, "south" -> evermoreForest, "west" -> loverFest   ))
     loverFest.setNeighbors(Vector("north" -> redLand, "east" -> holyGround, "south" -> evermoreForest, "west" -> redLand))
       folkloreGarden.setNeighbors(Vector("north" -> redLand, "east" -> home,   "south" -> evermoreForest, "west" -> redLand))
         home.setNeighbors(Vector(                                                                  "west" -> folkloreGarden     ))


  private val scarf = new Item("scarf", "But you keep my old scarf from that very first week 'cause it reminds you of innocence and it smells like me.")
  private val keychain = new Item("keychain", "I want to wear his initial on a chain round my neck.")
  private val paperRing = new Item("paper ring", "I like shiny things, but I'd marry you with paper rings.")
  private val flower = new Item("flower", "I want to watch wisteria grow right over my bare feet.")
  private val cardigan = new Item("cardigan", "And when I felt like I was an old cardigan under someone's bed, you put me on and said I was your favorite.")
  private val flannelShirt = new Item("flannel shirt", "Your Midas touch on the Chevy door. November flush and your flannel cure.")


  redLand.addItem(scarf)
  loverFest.addItem(keychain)
  loverFest.addItem(paperRing)
  folkloreGarden.addItem(flower)
  folkloreGarden.addItem(cardigan)
  evermoreForest.addItem(flannelShirt)

  val player = new Player(holyGround)

  var turnCount = 0

  val timeLimit = 12

  def isComplete = this.player.location == this.destination && this.player.repertoire.size > 2

  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  def welcomeMessage = "Welcome to Swift Land. \nThe sky is blue and wind is running through your hair. \nAn adventure is awaiting. \n\nCommand \"help\" to be instructed."

  def goodbyeMessage = {
    if (this.isComplete)
      "Well done! You are now a 100% certified Swiftie!!!"
    else if (this.turnCount == this.timeLimit)
      "Oh no! Time's up.\nGame over!"
    else  // game over due to player quitting
      "Quitter!"
  }


  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) {
      this.turnCount += 1
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }


}

