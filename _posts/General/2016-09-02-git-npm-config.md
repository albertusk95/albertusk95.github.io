---
layout: post
title: git & npm proxy config
---

### Purpose:

Set and reset proxy for git and npm connection

### General explanation:

When you want to push your commit to GitHub using git or install some modules using npm, perhaps you encounter a problem where you can not execute the command properly. One common thing that causes the problem is you have not configured the proxy settings yet. So, let's talk about how to configure the proxy so that those commands can be executed normally.

### Setting proxy

**npm**<br />
> npm config set proxy http://proxy:port<br />
> npm config set https-proxy http://proxy:port<br />

**git**<br />
> git config --global http.proxy http://proxy:port<br />
> git config --global https.proxy http://proxy:port<br />

_**In case it needs authentication**_<br />
> git config --global http.proxy http[s]://username:password@proxy:port<br />

### Reseting proxy

**npm**<br />
> npm config rm proxy<br />
> npm config rm https-proxy<br />

**git**<br />
> git config --global --unset http.proxy<br />
> git config --global --unset https.proxy<br />

### Getting proxy settings

**npm**<br />
> npm config get list<br />
> npm config get proxy<br />
> npm config get https-proxy<br />

**git**<br />
> git config --global --list<br />
> git config --global --get http.proxy 