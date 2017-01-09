---
layout: post
title: Get Acquainted with Machine Translation
---

### Introduction

Have you ever used Google Translate, Bing Translator, or BabelFish? Why do you choose to use those kind of apps? You simply try to find the meaning of the foreign language in more efficient way, don't you? Thus, the motivation of these apps is simple. They ease your needs when you are in a foreign place in which almost nobody speaks in your language. You just type the foreign language and it gives you the translated language. You even don't need to open your conventional dictionary, find the suitable index, and browse line by line till find the word you are looking for. Still, the interesting question is how does this machine translation software work?

### General Operations

_Translation_ means taking a sentence in one language (source language) and yielding a new sentence in other language (target language) which has same meaning. _Machine_ means the translation process is done by software rather than humans. Generally, any machine translation (MT) software implements this workflow:

<ul>
	<li>Input Phase
		<ul>
			<li>Source Text</li>
			<li>Deformating</li>
			<li>Pre-editing</li>
		</ul>
	</li>
	<li>Analysis Phase
		<ul>
			<li>Morphological Analysis</li>
			<li>Syntax Analysis</li>
			<li>Semantic Analysis</li>
		</ul>
	</li>
	<li>Representation Phase
		<ul>
			<li>Internal Representation of Source Language</li>
			<li>Transfer to Internal Representation of Target Language</li>
		</ul>
	</li>
	<li>Generation Phase
		<ul>
			<li>Syntax Generation</li>
			<li>Semantic Generation</li>
		</ul>
	</li>
	<li>Output Phase
		<ul>
			<li>Reformating</li>
			<li>Post-editing</li>
			<li>Target Text</li>
		</ul>
	</li>
</ul>

Let's take a look at each of the phase.

### _Input Phase_

This is the step where the MT system receives the source language containing two portions, namely non-translation and translation materials. Non-translation materials are charts, figures, and any materials that do not need translation. Whilst the latter portion is the natural text.

Afterwards, the MT system will **deformat** the source text, where it removes any portions from the source text that do not need translation (non-translation materials). This sub-step returns a source text that contains only translation materials (natural text).

However, the current state of the source text may not be forming the effective sentence, which means that the source text is still too long, there are words that are repeated, the sentence is rambling, etc. In this case, we can handle this problem by segmenting the source text into a shorter text while still considering the semantic. This is done in the **pre-editing** sub-step and it returns the pre-processed source text to be sent to the text analyzer.

### _Analysis Phase_ 