# 综述
每一个交互界面由一个Activity构成，在Activity中将各个功能较为独立的视图定义为一个视图容器Container，Container仅仅包括Ui操作。在Container中用Presenter来做主要逻辑操作。下面将主要描述一下各个主要部分的书写注意点。

## Activity
每个交互界面都是以至少一个Activity为基础的，每个Activity都必须继承BaseActivity，在BaseActivity中内置了Navigator和ActivityModule，Navigator用来跳转，而ActivityModule主要提供Activity特有的依赖（暂时只有BaseActivity自身的引用），Activity已经设置好了ButterKnife，其子类可以直接使用。
> **注意：**
> 在继承BaseActivity时，应该始终覆盖它的getLayout方法。
> 此外，在其子类中应该尽量提供getCallingIntent方法，用于向Navigator提供
> Intent。如果有参数则可以直接写在该方法的参数列表中，如果参数较多可以采用
> Builer模式。

如果你的Activity需要依赖注入，则在private的initialInjector方法中定义依赖注入相关的操作，并在onCreate回调中调用。

## Container
Container是一组关系密切的View集合，此时我们使用的是MVP模式，Container需要实现对应的View（此处是MvpView）接口。在一个Container中，它不仅仅包含所有必须的ui控件，还需要有一个Presenter用来处理逻辑，有时候还需要一个Callback来与Activity交互。

在Container的书写中，暂时还需要自己在构造函数中手动添加布局，在onFinishInflate方法中进行Butterknife绑定，在onDetachFromWindow中解除ButterKnife绑定。
> **注意：**
> 当Container中需要依赖注入时，需要提供一个inject方法，在其中可以添加依赖注入，然后对具备依赖的成员变量进行初始化。并且记住在Activity中手动调用inject。一般建议在Activity中定义private的initialContainer方法，然后在onCreate回调中调用。

接下来讲一下Presenter，它应该在被注入后尽快调用attachView方法，并在onDetachFromWindow方法中detachView。之后将所有的控制事件转发给Presenter执行。

还有一个比较重要的是Callback，在类开头可以定义一个Callback同Activity交互，如果把Container看做一个Button控件，那么这个Callback大致就相当于OnClickListener。当Activity要与Container进行通信时，它只要获取Container的引用，直接调用它的方法即可。

Container中最基本的就是对应的MvpView接口的实现了，此处并没有多少难度。唯一需要注意的是，有些控件会需要在Activity中显示，此时只需要在Container中注入BaseActivity的实例即可。
> **注意：**
> 此处的Activity实例是BaseActivity，它主要的作用应该仅仅是为Container提供一些运行必须在Activity中运行的控件，而与Activity进行交互时应该尽量采用回调的方式。

> **一些展望：**
> 将来准备将Container写成一个基类，尽量减少重复代码，节省大家时间哈。

## MvpView
所有的View接口都必须继承这个接口，在这里写一些对应的View所支持的显示操作，这里并没有多少可以说的，主要看需求。

## MvpPresenter
所有的Presenter都必须实现MvpPresenter接口。它主要负责与View对应的逻辑操作，并反馈给View。显然一个Presenter对应一个MvpView，因此将MvpView作为泛型参数传给Presenter。此处的逻辑操作如果有依赖，则可以在构造函数中统一注入。
> **注意：**
> 每一个Usecase都需要在detachView的时候进行unsubscribe操作，因为想对
> Usecase保持一定的可配置性，因此这个步骤需要手动添加，否则容易造成内存
> 泄漏。

接下来是比较难的Dagger2的部分，它主要负责的其实仅仅是装配过程。依赖注入并不仅仅和Presentation层有关，它是一个全局的装配器。

## Module
对于特定的注入集合可以单独定义其Module，基本的Module已经有ApplicationMudule和ActivityModule。全局的依赖应该写在ApplicationMudule中，所有的Activity的依赖应该写在ActivityModule中。
此外，如果有新的内容不能通过Inject来解决时，就需要新建一个Module。

## Component
这只是一个接口。每个需要装配的组件都可以定义一个Component作为它的装配说明书，Component可以从Module中直接生成数据，或者从其他Component中借数据。

## Model
在这个层只是简单的一些数据，这些数据是和显示ui直接相关的，ui中需要什么数据，它就应该仅仅包括这些数据即可。一般来说可以提供一个较为直观的toString方法。

## mapper
这个package主要负责将domain层的数据转换成ui显示所需的Model，其中的transform方法可以实现相应的转换功能，最好提供其Collecction形式的transform方法。也可以通过重载的方式提供多种数据映射至Model。

## 写在最后
虽然Google比较推崇Fragment，但是实际上Fragment有很多问题，很多人已经开始慢慢尝试弃用Fragment，但是Fragment一些比较好的理念还是可以借鉴和发扬光大，具体可以参考[Square:从今天开始抛弃Fragment吧](https://github.com/hehonghuidev/android-tech-frontier/blob/master/issue-13/Square%EF%BC%9A%E4%BB%8E%E4%BB%8A%E5%A4%A9%E5%BC%80%E5%A7%8B%E6%8A%9B%E5%BC%83Fragment%E5%90%A7%EF%BC%81.md)。
