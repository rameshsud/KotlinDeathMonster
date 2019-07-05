# A "Kingdom Death: Monster" Companion App

This project was born out of a desire to quickly look up the various rules of KD:M without having to refer back to the
rulebook (which might be across the table or already in use) or dig through the decks (which are probably sitting in the
box to save table space).

I had 2 technical goals for this project:

* To serve as a reference for my style of coding and how I like to architect things
* To practice writing in Kotlin instead of Java

## As a reference

The more interesting pages are set up using a Model-View-Presenter pattern. The most significant design decision there
was to separate the View from the Activity. I treat Android Activities and Services more like main(); They're an entry
point into your page's code. You use them to create your object graph. You wouldn't put your UI code in main(). 

## As Kotlin practice

It's been pretty clear for some time that the future of Android is Kotlin, but we never quite got around to using it at
the last place I worked. If some of the constructs are inconsistent, or not quite idiomatic Kotlin, this is why.

## Credit where credit is due

The entries for the Glossary page are taken from the
[Kingdom Death Monster Wikia](https://kingdom-death-monster.fandom.com/wiki/Kingdom_Death_Monster_Wikia).

The entries for the Fighting Arts and Disorders pages are taken from
[Kevin Rau's Kingdom Death Utilities](http://www.kevinrau.com:82/kr/kingdomdeath/KD.asp).

