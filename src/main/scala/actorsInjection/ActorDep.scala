package actorsInjection

import akka.actor.{Actor, ActorRef}

trait ActorDep[T <: Actor] {
  val instance: ActorRef
}
