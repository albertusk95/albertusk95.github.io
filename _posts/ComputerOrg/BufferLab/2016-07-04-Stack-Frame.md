---
layout: post
title: Stack Frame
---

To discuss about this stack frame, we'll see from Assembly language point of view.

Basically, a program has functions which support the execution of that program. That functions run for being called by the previous function. This calling event which is usually called as **calling convention** has a concept related to stack. It means that when every function is called, then there'll be space formed which has these default elements, namely the function's **return address** and **argument values**. In addition, the space created can be also called as *stack frame**. This is an example of a **stack frame**.

<img src="https://github.com/albertusk95/albertusk95.github.io/blob/master/public/img/stackframe00.png?raw=true" alt="Stack Frame" title="https://i1.wp.com/i.stack.imgur.com/Fxn41.png" />

The picture above is a stack's illustration. 


