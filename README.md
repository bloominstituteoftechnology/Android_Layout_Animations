# Android_Sprint2_Challenge

## Introduction

For this challenge, you will create an app which will display a list of grocery items available for purchase, you will allow a user to select which items they would like to order. You will then allow them to send their list, which will be a list of all the item names. The list will be shared as plain text through whichever app the user would like to use. When the user shares the list, display a notification that their order has been placed.

<img src="https://github.com/LambdaSchool/Android_Sprint2_Challenge/blob/master/Sprint2Challenge_ShoppingList.gif?raw=true" width="250">

## Instructions
This sprint challenge is designed to ensure that you are competent with the concepts taught during this first sprint. Please read this entire README to make sure you understand what is expected of you. Failure to read and follow the instructions and requirements will be reflected in your results.

In your solution, it is especially important that you follow best practices such as good, consistent code style. You will be scored on these aspects as well as the project requirements below.

### Requirements
* list items in shopping list with RecyclerView
* click anywhere on the item flips the switch and adds itself to the shopping list
  > You'll need to check the documentation (here)[https://developer.android.com/reference/android/widget/Switch#setChecked(boolean)] for more information on how to toggle a switch programatically (hint: setChecked and isChecked)
* the user must have the ability to share their list of selected items as a plain text object using the external app of their choice
* when the list is shared, display a notification telling them that is has been shared
* app must implement a custom theme and launcher icon, however you do not need to have multuple themes nor do you need to allow the user to change themes, the customization of your theme may be just your own color scheme
* be sure to use the principles of design that we've discussed, that is more important than making it look like mine

### Tips
* the version of the list in this graphic implements sort functionality on the list. This is not required, however, you may add it as a challenge
* build a class which will step through the values in the provided Constants file and return a list of ShoppingItems
* the ShoppingItem class will be a data class consisting of the name, the resource id for the image, and the id for the item (this works well as the index of the value in the constants file, the i value as you step through it) 
