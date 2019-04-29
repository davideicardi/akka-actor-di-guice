package it.agilelab.akka.guice

import akka.actor.{Actor, ActorContext, ActorRef}

// This factory can be used only when no parameters should be passed to the child actors.
//  If you need parameters you should create a custom factory.
trait ActorFactory[T <: Actor] {
  def create(parent: ActorContext, name: String): ActorRef
  def get(parent: ActorContext, name: String): Option[ActorRef]
}
