# Coding Guide

--------------------------
## Android Studio Settings
Let's prepare our Android Studio first.

### Just editing
This is about the editing style, which makes the code clean and readable.

#### Copyrights
Follow instructions:
`File->Settings...->Editor->Copyright->Copyright Profiles`

You can also use `Ctrl +　Alt + S` or press setting button on the toolbar, your copyright might look like this:

```Java
Copyright (c) $today.year. Team Udo from Southeast University
All rights reserved.
```

After that, click back to `Copyright` and set a `scope` for your copyright, `All` is preferred.

#### File Header
Follow instructions:
`File->Settings...->Editor->File and Code Templates->File Header`
Here's the file header template:

```Java
/**
 * Author: your_name on ${DATE} ${HOUR}:${MINUTE}
 * E-mail: your_email_address
 */
```
Now Android Studio will generate all these codes for you, all you need to do is `New` a file.

#### Comments
* Locate the cursor right above the code you want to comment,
and type `/**`, then press `Enter`.

* Comment your incomplete code with
```Java
//TODO：describe briefly about the work in the future.<br>
```
After that, you will be able to navigate to your code in Android Studio, using to-do tab on the bottom.

#### Name it!
How to name the packages, classes, interfaces, methods, fields or variables?

Suppose this is already a habit rather than a rule for you, if not
you can still use `shift + F6` to rename them.

Here's some example:
* layout

Lowercase, Underscore "_" between words, use the format like "modular_function.xml":

name | correct ?
-----| :------:
layout_knowledge_gained_main.xml| &radic;
list_book.xml | X
* component id

Lowercase, Underscore "_" between words, for example:

component | name |correct ?
----------|------|:-----:
TextView | tv_book_name_show| &radic;
TextView | textbookname | X
EditText | et_book_name_edit | &radic;
EditText | textbookname | X

* resource

Lowercase, Underscore "_" between words, use the format like
"modular_function" (for public resources, use "function" directly):

name | correct ?
-----| :--------:
menu_icon_navigate.png | &radic;
line.png or separator.png | X

Trust Android Studio for the default settings, so far so good!

See [google 官方Java代码规范](http://www.hawstein.com/posts/google-java-style.html
) for more.

### Work with github

* Suppose you all have a GitHub account
* Install git
* If you are working on windows, a few more steps to follow:
>* cd to your git_install_path\bin in the command line
>* type: git config --global user.email  "github_register_email_address"
>* type: git config --gobal user.name "your_name"

* Navigate to `Settings->Version Control->git`, set your
git_install_path and `Test` it.
* Navigate to `GitHub`, set your `Login` and `Password`, and
don't forget to `Test`.
* If you want to create a repository, follow this:
>* Open your initial project
>* Navigate to
`Menu->VCS->Import to Version Control->Share Project on Github`
>* Enter your repository name
>* I'll leave you for the rest...

* If you want to pull from Github, choose
`Check out project from Version Control`
in the `Welcome to Android Studio` window.
* If you want to push to the Github,
there is a VCS&uarr; button on the toolbar.

### Tips for using Android Studio
* Close all the tabs, see what's on the screen.
>* Search Everywhere `Double Shift`
>* Go to File `Ctrl + Shift N`
>* Recent Files `Ctrl + E`
>* Navigation Bar `Alt + Home`
>* Drop files here from Explorer

* `Ctrl +ｏ`　for override
* `Shift + F6` for rename
* `Ctrl +　Shift + Alt + T` for refactor
* `Ctrl + B` for go to the definition

-----------------------------
## Clean Android Architecture
See article [Architecting Android...](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/) for details.

The objective is the separation of concerns by keeping the business rules not knowing anything at all about the outside world, thus, they can can be tested without any dependency
to any external element.

It is worth mentioning that each layer uses its own data model so this independence can be reached( you will see in code that a data mapper is needed in order to accomplish data transformation,
a price to be paid if you do not want to cross the use of your models over the entire application).

Here is an schema so you can see how it looks like:
![3layer schema](http://fernandocejas.com/wp-content/uploads/2014/09/clean_architecture_android.png)

### Presentation Layer
Is here, where the logic related with views and animations happens.

Here fragments and activities are only views, there is no logic inside them other than UI logic,
and this is where all the rendering stuff takes place.

Presenters in this layer are composed with interactors (use cases) that perform the job in a new thread
outside the android UI thread, and come back using a callback with the data that will be rendered in the view.

Then we can discuss how to code and where to put them according to packages.

Root name of the package in this layer may look like this: `app_package.presentation`

#### exception package
Typically, we have a class named ErrorMessageFactory.
It turns an Exception into a String message, which is easier to display

#### internal.di package
The place we use [dagger2](http://google.github.io/dagger/) to do Dependency Inject stuff.

Module is a wrapper for all @Provides-annotated methods.
Component is an interface for using @Inject to get an instance.

#### model package
Classes in mapper package simply map `<Some>Data` into `<Some>ModelData`.

`<Some>Data` is passed by the domain layer.
`<Some>ModelData` is ui-related data.

Define all the ui-related data here with a suf-fix like `ModelData`.

#### navigation package
Define a Navigator Class, then we can navigate through the application.

Add a pre-fix like `navigateTo` before every method.

Also remember to add `@Inject`-annotate to the construct method.

#### presenter package
Presenter is an interface for all presenters.
This is the logic layer for the ui, which mean we can only do some ui-related operations,
like check the user id or the password.

Business logic will be passed to domain layer.
We should avoid running to much code in the ui thread.

* A presenter should have a view reference to interact with the View.
* A presenter should also register all use cases it might need,
along with the corresponding <Some>ModelDataWrapper.

In the `destroy()` method, remember to `unsubscribe` all the use cases.

Also pay attention to the code listed below:
```Java
@Inject
  public UserListPresenter(@Named("userList") UseCase getUserListUserCase,
      UserModelDataMapper userModelDataMapper) {
    this.getUserListUseCase = getUserListUserCase;
    this.userModelDataMapper = userModelDataMapper;
  }
```
Use `@Inject` to annotate the constructor so that
[Dagger](http://google.github.io/dagger/)
can create instances of a class through it.

When a new instance is requested, Dagger will obtain the required parameters values and invoke this constructor.

The `@Named("userList")` is a qualifier for `UseCase`, because `UseCase` is the super class of `GetUserList`.
We might need some qualifier to distinguish between different `UseCases`.

We interact with the domain layer with interactors.
Hereby, we trigger `execute()` method of the `UseCase`, and then specify a `Subscriber`.

More to be discussed about the lifecycle and Presenter interface.

#### view package
We put all the activities in the activity package, all the fragments in the fragment package
and all the adapter in the adapter `package.Components` are those view components defined by our selves.

View interface is critical for presenters to work functionally.
It should provide all view related operations, like `showLoading()`, `showError()` etc.

A little bit more to mention about `Activity` is the `IckPick` tool.Add `@State`-annotate to the state you want to store.

### Domain Layer
We provide interactors for presentation layer and fetch data using repository interface from data layer.
All the business logic will be handled here and only in here.

Then we can discuss how to code and where to put codes according to packages.

#### exception package
Here we have a `ErrorBundle` interface and a default implementation named `DefaultErrorBundle`.
It bundles all the `Exceptions` from data layer.

#### executor package
That's for you to find out, haha...

#### interactor package
`UseCase` is an abstract class with an abstract method called `buildUseCaseObservable()`.
We pass request-related data to the `construct` method of the `UseCase`,
then `UseCase` build an Observable object to start a asynchronous task.
The `unsubscribe()` method will abandon the `Subscription`, then
`Observable` instance will stop emitting and `Subscriber` instance will stop receiving.

#### repository package
This is an interface for data layer. We only discuss data repository at the aspect of domain layer,
which means we won't know anything about how the data is fetched afterwards.
This is just an agreement to the data layer, nothing more.

##### Some Confusions
Let's compare package `repository` with package `interactors`. Domain layer implement `interactors` and define the interface for presentation layer as well.
Domain layer also define an interface for data layer, but data layer itself have to implement it.
It is easy to understand where we can implement the interfaces,
but why do domain layer define both interfaces for presentation layer and data layer?

Another problem is we call ui-related data `<Some>ModelData` and put them in the model package,
we also call pure data in the data layer `<Some>Entity` and put them in the entity package,
then what about the data in the domain layer?
In this example, they just put them in the domain package.

### Data Layer
We can fetch data from ram, files or cloud here and provide an interface for the domain layer
when it request some kind of data.

That's for you to find out...

---------------------
## Open Source tools
See [android open project](https://github.com/Trinea/android-open-project), dig anything you want.

Article and user guides like [dagger2](http://google.github.io/dagger/users-guide.html),
[Grokking RxJava, Part 1: The Basics](http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/),
[NotRxJava guide for lazy folks](http://yarikx.github.io/NotRxJava/),
[Retrofit 2.0 Official](http://square.github.io/retrofit/),
[Retrofit 2.0：有史以来最大的改进](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0915/3460.html) is available here.

More's coming...
