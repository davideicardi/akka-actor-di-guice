package it.agilelab.akka.guice

import akka.actor.{Actor, ActorContext, ActorRef, Props}

import scala.reflect.ClassTag

class GuiceActorFactory[T <: Actor]()(implicit actorClass: ClassTag[T]) extends ActorFactory[T] {
  override def create(parent: ActorContext, name: String): ActorRef =
    parent.actorOf(Props(classOf[GuiceIndirectActorProducer], actorClass.runtimeClass), name)

  override def get(parent: ActorContext, name: String): Option[ActorRef] =
    parent.child(name)
}
