package it.agilelab.akka.guice

import akka.actor.{Actor, ActorRef}

trait ActorDep[T <: Actor] {
  val instance: ActorRef
}
