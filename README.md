# Android_Sprint3_Challenge
# Pokemon API

This Sprint explored material related to networking and asynchronous tasks on Android. During this Sprint, you studied threading, http requests, JSON and more. In your challenge this week, you demonstrate proficiency in these topics by creating an application that uses a free Pokemon API to allow users to search for their favorite monsters.

## Instructions

**Read these instructions carefully. Understand exactly what is expected _before_ starting this Sprint Challenge.**

Follow best practices to ensure your solution is consistent and well designed. You are scored on these aspects as well as meeting the project MVP (minimum viable product) requirements listed below.

Fork this repository and clone your fork to your computer. Create your Android Studio project in this cloned fork repository folder, then commit and create a pull request. Commit appropriately as you work. When finished, push your final project to GitHub and comment on the pull requestto indicate that your project is complete.

You have *3 hours*, and you should work *independently* — looking things up (search, notes) is all fair game. And questions about *process* / *logistics* (i.e. if you have a hard time opening/saving to GitHub) are fair game too.

Good luck!

### Screen Recording

This screen recording previews the look and feel of a completed project that meets the MVP requirements for this project.

<img src="https://github.com/LambdaSchool/Android_Sprint3_Challenge/blob/master/pokemonDemo.gif" width="300">

## Requirements

This project uses the **pokeapi.co API** ([API Documentation](https://pokeapi.co/docs/v2.html/#pokemon)). Use this API to search for pokemons and retrieve their information.

The requirements for this project are as follows:

1. Establish a `Pokemon` class with members for the Pokemon's name, spriteUrl, ID, abilities, and types. You may use app.quicktype.io to help.
2. Display a list of matching Pokemon and show the sprite (image) associated with the record.
3. Allow the user to save the Pokemon. Display a list of searched Pokemon in a scroll view. (**Stretch:** Implement a RecyclerView for the list with the sprites.)
4. Allow the user to view a Pokemon's information in a detail activity.
5. Allow users to remove saved items from their Pokemon list. (If you implement persistence as a stretch goal, keep the saved list in sync.)
> This can be done with a long press action

6. *Stretch*: Allow the user to search for a Pokemon using partial name matching. You will have to download a full list of the Pokemon names from the API and then perform your searches locally.
7. *Stretch*: Add persistance to the favorites list.

> Remember: a Bitmap cannot be serialized so If you wish to pass the pokemon object between activities, either implement parcelable or store the bitmap in the object as a string url and retreive it as necessary
