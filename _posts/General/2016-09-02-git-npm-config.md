---
layout: post
title: git & npm proxy config
---

### Purpose:

Set and reset proxy for git and npm connection

### General explanation:

When you want to push your commit to GitHub using git or install some modules using npm, perhaps you encounter a problem where you can not execute the command properly. One common thing that causes the problem is you have not configured the proxy settings yet. So, let's talk about how to configure the proxy so that those commands can be executed normally.

### Setting proxy

**npm**
> * npm config set proxy http://proxy:port
> * npm config set https-proxy http://proxy:port

**git**
> * git config --global http.proxy http://proxy:port
> * git config --global https.proxy http://proxy:port

### Reseting proxy

**npm**
> * npm config rm proxy
> * npm config rm https-proxy

**git**
> * git config --global --unset http.proxy
> * git config --global --unset https.proxy

### Getting proxy settings

**npm**
> * npm config get list
> * npm config get proxy
> * npm config get https-proxy

**git**
> * git config --global --list

 