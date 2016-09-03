---
layout: post
title: npm not behind proxy errors and solutions
---

### Problem:

Perhaps you had encountered a problem where you can not use npm command to install some modules. It means that you got an error when you type **npm install** and the error is something like bad connection or misconfiguration for proxy if you were behind one. So, let's talk about how to solve this kind of problem.

### Solution:

A kind of error message that you may encounter when trying to install module from npm is written below:<br />

> npm ERR! Error: connect ECONNREFUSED someproxy:port<br />
> npm ERR!     at Object.exports._errnoException (util.js:907:11)<br />
> npm ERR!     at exports._exceptionWithHostPort (util.js:930:20)<br />
> npm ERR!     at TCPConnectWrap.afterConnect [as oncomplete] (net.js:1077:14)<br />
> npm ERR!  { [Error: connect ECONNREFUSED someproxy:port]<br />
> npm ERR!   code: 'ECONNREFUSED',<br />
> npm ERR!   errno: 'ECONNREFUSED',<br />
> npm ERR!   syscall: 'connect',<br />
> npm ERR!   address: 'someproxy',<br />
> npm ERR!   port: port }<br />
> npm ERR!<br />
> npm ERR! If you are behind a proxy, please make sure that the<br />
> npm ERR! 'proxy' config is set properly.  See: 'npm help config'<br />

If you got this error message, chances are you have a bad proxy configuration. Maybe you're behind a proxy when you use **npm install** command.<br />

Yet in this article, I am not talking about the possibility you have a bad proxy configuration as you're behind a proxy. I am talking about when you encounter this connection error while you are not behind any proxies. So, it is like a confusion as you got a proxy config error while your connection is direct and does not need proxy.<br />

So, make sure that you are not behind any proxies yet got this error. You can simply check your proxy config by this command:<br />

**user config**
> npm config get proxy<br />
> npm config get https-proxy<br />

**global config**
> npm config get proxy -g<br />
> npm config get https-proxy -g<br />

If you have a proxy listed for one or both of those proxy protocols (either user or global config), use this command:

**user config**
> npm config rm proxy<br />
> npm config rm https-proxy<br />

**global config**
> npm config rm proxy -g<br />
> npm config rm https-proxy -g<br />

Now, your proxy and https-proxy will be **null**. You can now install modules with npm. If you did it successfully, then this articles is not discussing your problem. VOILA!<br />

So, for those who did not do it successfully, just use this command:<br />

> set http-proxy=<br />
> set https-proxy=<br />

And try to install your modules. You should be able to do it.




