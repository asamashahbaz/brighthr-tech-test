# Bright Android Technical Test 2023

This project forms the basis for the technical task for Android developer applicants.
Please carefully read these instructions before beginning the task.

* The task should no more than 3 hours to complete
* You are welcome to consult the internet, documentation or code you have written to help you
* We encourage you to use tools like the debugger and Layout Inspector to diagnose issues.

*We will assess you on:*

* Understanding of best practices in Android development
* Clear code organisation
* The size and message of git commits

*We are less concerned with:*

* UI styling / animation etc.

## The Starter Project

This project uses the JSON placeholder API to display a list of posts. 
The list includes the title of each post and an
excerpt from each post's body.

The code of the starting application is *deliberately poor* and contains
*bugs* and several examples of *bad practice* according to Modern Android Development.
This is to give you the opportunity to demonstrate how you would tackle improving legacy
code.

## Your task:

1. Survey the code in this project and leave comments under everything you notice 
that needs to be fixed or improved.
2. Pick 2 or 3 of these issues and implement fixes / improvements.
    - Feel free to refactor and change the architecture of the code
    - You can add 3rd party libraries, but shouldn't need to (be prepared to explain your choices)
3. We would like to see an example of a *Unit* test
4. Suggest other improvements you might make if you had a few days to work on the code.

## The API

The app uses the following endpoints from the [JSON
placeholder](https://jsonplaceholder.typicode.com) API:

    GET https://jsonplaceholder.typicode.com/posts/

    GET https://jsonplaceholder.typicode.com/posts/{POST_ID}/

    GET https://jsonplaceholder.typicode.com/posts/{POST_ID}/comments/

## Submitting your work

-   You should submit the above as either a compressed git repository (zip file)
    or a link to your repository on github or similar hosting service.
    Please commit regularly as you carry out the task and
    keep the commit history in tact.

## Interview pairing tasks

In your interview we will ask you more about your process in completing the above tasks.

There will also be a short exercise where you will be paired with one of our Android team member
and given a task to develop this project further. You will have full access to internet
resources to help you.

## Future Work
The response from the repository could be wrapped inside a `Result` sealed class that contains class types for `Loading`, `Success` and `Error` states.  
By further extending the `Error` class type, we can have different types of classes that indicate a specific kind of error.
This would allow us to use string resources based on the error class type directly in the views and will mostly help us keep the ViewModel clean of Android platform related code.

We could also employ an event wrapper to set-up communication from composables to the `ViewModel`.  
This would allow us to pass a single `ViewModel` function reference to the composables and have them call it with the required event type and parameters.  
This would make the `ViewModel` more testable and would also allow us to use the same `ViewModel` for different views.

We could also have use-cases in the `domain` layer that take care of the business logic and the communication with the repository.  
These use-cases would then be injected into the `ViewModel` and called from there.