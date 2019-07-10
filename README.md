# A "Kingdom Death: Monster" Companion App

This project was born out of a desire to quickly look up the various rules of KD:M without having to refer back to the
rulebook (which might be across the table or already in use) or dig through the decks (which are probably sitting in the
box to save scarce table space).

The main technical goal of this project was to play around with all the parts of Android development that didn't come up
as often in my last position as a mobile developer (where the projects were primarily background services). Namely:

* Kotlin
* Dagger2
* Databases / ORM
* UI patterns

## Architecture

Probably the most controversial decision was to separate out the View from the Activities. Activities are already a
main()-like entry point, as well as an all-purpose handle to the Android system. Using them for much beyond creating
the object graph feels to me like too much responsibility for one thing.

I also strongly prefer putting all the application-wide components into the Application itself (as opposed to static
singletons). Modules can't be tempted to touch dependencies they weren't injected with if they can't actually reach
them. Ask me about all the ways that can go wrong! We can swap war stories.

All of the more interesting UI pages are currently implemented in a Model-View-Presenter style. The choice between MVP,
MVC, and MVVM was quite arbitrary, and I fully intend to have them all by the time I'm done adding pages.

## Credit where credit is due

The entries for the Glossary page are taken from the
[Kingdom Death Monster Wikia](https://kingdom-death-monster.fandom.com/wiki/Kingdom_Death_Monster_Wikia).

The entries for the Fighting Arts and Disorders pages are taken from the
[Vibrant Lantern's Kingdom Death: Monster Card Database](http://vibrantlantern.com/carddb/).

