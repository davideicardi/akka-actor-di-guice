# akka-actor-di-guice

**NOTE: Work in progress ...** 

A minimal Akka Actor example using Guice for dependency injection.

Features:

- Inject actors to other components (see `App.scala` where I inject `PrinterActor`)
- Create child actors (see `PrinterActor.scala` where I create `PrintJobActor` using a factory)
- Inject components inside actors (see `PrintJobActor.scala` where I inject `PrinterService` component)
- No named components (I don't like it...)
- Unit test friendly

All the "magic" is inside the Guice `AppModule`, where actors are bind and factories are created.

## References

- http://letitcrash.com/post/55958814293/akka-dependency-injection
- https://medium.com/supercharges-mobile-product-guide/akka-with-google-guice-adb2b83f045
- https://doc.akka.io/docs/akka/current/actors.html#dependency-injection
