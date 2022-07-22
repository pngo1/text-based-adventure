package o1.adventure



class Action(input: String) {

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim


  def execute(actor: Player) = this.verb match {
    case "go"    => Some(actor.go(this.modifiers))
    case "rest"  => Some(actor.rest())
    case "quit"  => Some(actor.quit())
    case "collect" => Some(actor.collect(this.modifiers))
    case "drop" => Some(actor.drop(this.modifiers))
    case "answer" => Some(actor.answer(this.modifiers))
    case "unbox" => Some(actor.unbox(this.modifiers))
    case "hint" => Some(actor.hint)
    case "help" => Some(actor.help)
    case other   => None
  }


  override def toString = this.verb + " (modifiers: " + this.modifiers + ")"


}

