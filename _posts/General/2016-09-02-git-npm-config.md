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
>> * npm config set proxy http://proxy:port
>> * npm config set https-proxy http://proxy:port

**git**<br />
>> * git config --global http.proxy http://proxy:port
>> * git config --global https.proxy http://proxy:port

### Reseting proxy

**npm**<br />
>> * npm config rm proxy
>> * npm config rm https-proxy

**git**<br />
>> * git config --global --unset http.proxy
>> * git config --global --unset https.proxy

### Getting proxy settings

**npm**<br />
>> * npm config get list
>> * npm config get proxy
>> * npm config get https-proxy

**git**<br />
>> * git config --global --list 