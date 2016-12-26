---
layout: post
title: Sentiment Analysis using WEKA
---

## Introduction

One of the most important aspects of a successfull product is the users want to use it for it fulfills their needs. To achieve that point, the executive people from companies need to evaluate their products performance when officially released to public. One way to do that is by knowing the users reaction towards the product's quality. By knowing the users reaction, they can improve the quality of production for they can learn about users expectation and sure it may help them to pay more attention to user-driven development (UDD), a processes in which the needs of users of a product become the top priority in the product development processes. 

Talking about the way to evaluate a product's quality, the executive people can find the public opinion in social media, such as Twitter, Facebook, Instagram, etc. They can find their product and analyze the users opinion towards it. They can do it manually, yet off course it will take a long time to get the evaluation process done. So, they need something that can do this job automatically so that they can be more productive rather than only just determining the positive or negative opinion from every tweet.

One of the common application used to do this job is Sentiment Analyzer. This app can determine how positive or negative a statement text is. It implements Natural Language Processing as the core technology to analyze and understand the content of a sentence text. In this brief article, I will explain about this application used for classifying tweet's sentiment extracted from Twitter. So, let's start!
  
-----

## Programming Languages

For this Sentiment Analysis application, we will use **Java (JSP, Servlet)** as the primary programming language. We'll also use several frameworks and libraries, such as **WEKA**, **AngularJS**, **Bootstrap**, **jQuery**, and **twitter4j (Java library for the Twitter API)**.

-----

## Supporting Model

This application uses a classification method called <b>SlidingWindow</b> which determines the model to be used when the <b>"disagreement"</b> condition is met. The <b>"disagreement"</b> is a condition where the core classifier can not classify the tweet into the class of positive or negative.<br />

If we choose to use <b>SlidingWindow</b>, there are two possible conditions when we receive the result of classification, namely:<br />   

<ul>
	<li><b>If the core classifier returns the <b>positive</b> or <b>negative</b> as the predicted class,</b>
		<ul>
			<li>put the corresponding instance in the data train. We should also check whether the total number of instance is more than 1000. If so, remove the first instance</li>
		</ul>
	</li>
	<li><b>If the core classifier returns <b>nan</b> as the predicted class,</b>
		<ul>
			<li>put the corresponding instance in the data test and it will be classified in the end of the process</li>
			<li>For this condition, we should consider these possibilities too:
				<ul>
					<li><b>If the number of instances in the data train is more than 0,</b>
						<ul>
							<li>decides upon a "disagreed" document by applying the learned model based on the last 1000 "agreed" documents (stored in "train" instances)</li>
						</ul>
					</li>
					<li><b>Else,</b>
						<ul>
							<li>unknown class for the train data is empty and can not do the training process. In this condition, we can just return random class as the final result.</li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>
	</li>
</ul>

If we choose <b>not</b> to use <b>SlidingWindow</b>, then there are also two possible conditions, namely:<br />

<ul>
	<li><b>If the core classifier returns the <b>positive</b> or <b>negative</b> as the predicted class,</b>
		<ul>
			<li>set the list of predicted class come from three possibilities</li>
		</ul>
	</li>
	<li><b>If the core classifier returns <b>nan</b> as the predicted class,</b>
		<ul>
			<li>decides upon a "disagreed" (out = nan) document by applying the learned model based on the previously built model</li>
		</ul>
	</li>
</ul>

-----

## General Concept

In this part, I will explain the core principal of this Sentiment Analysis and some code snippets so that you can understand the backend-logic better.

### Extracts Twitter Data

<ul>
	<li><b>Sets the query</b></li>
	<ul>
		<li>
			We begin by fetching user's query and assign the value to a global variable.
		</li>
	</ul>
	<li><b>Extracts tweet</b></li>
	<ul>
		<li>
			Afterwards, we fetch the corresponding tweets which contain the keyword inputted by the user. This step uses Twitter API so it does need some authentication tokens (you can get these tokens from your Twitter's developer account). 
		</li>
	</ul>
	<li><b>Sets the tweet text</b></li>
	<ul>
		<li>
			To achieve the optimum result of classification, we only need the text feature from the original tweet. It means that we do not need another supporting features, such as published time, username and name of person posting the tweet, location when he/she was posting the tweet, etc. So, we just store the tweet text into a list. Simple!		
		</li>
	</ul>
	<li><b>Sets the amount of tweet</b></li>
	<ul>
		<li>
			The last step for this stage is we just need to store the number of extracted tweets.
		</li>
	</ul>
</ul>

### Sentiment Analysis

<ul>
	<li><b>Initializes Sentiment Processor</b>
		<ul>
			<li><b>Sentiment Analyser</b>
				<ul>
					<li><b>Trainer</b>
						<ul>
							<li><b>Initializes BidiMap objects for text, feature, and complex representation</b>
								<ul>
									<li>
										We use <b>DualHashBidiMap</b> that stores the pair of <i>String</i> and <i>Integer</i>. The content of this BidiMap is all of the attributes from the dataset of every tweet's representation, namely <b>text</b>, <b>feature</b>, and <b>complex</b>. I will explain these three representations in the next sub-point.
									</li>
								</ul>
							</li>
							<li><b>Trains lexicon model</b>
								<ul>
									<li>It is a supervised learning using LibSVM classifier</li>
									<li>It trains the model for the lexicon-based representation</li>
									<li>It saves the model in order to use it on the provided test sets</li> 
									<li>The rest of the model representation forms will be created on-the-fly because of the minimum term frequency threshold that takes both train and test sets into consideration</li>
								</ul>
							</li>
							<li><b>Trains text model</b>
								<ul>
									<li>The concept is this model will determine the sentiment value based on the whole opinion or division of opinions</li>
									<li>It builds and saves the text-based model built on the training set</li>
									<li>The training set has already been preprocessed, where every special symbol is converted into a string representing the symbol (example: converts @user into 'usermentionsymbol', www.example.com into 'urllinksymbol', etc)</li>
									<li>The general steps are:
										<ul>
											<li>Retrieves a new instances with a filter inside</li>
											<li>Saves the instances to a file named according to the type of representation. In this case, we use text representation, so the name will be 'T.arff'</li>
											<li>Writes the attributes from the filtered instances (can be retreived from 'T.arff') to a file in 'attributes' folder (text.tsv). The attributes are the tokenized words and the representation is based on the used tokenizer</li>
											<li>Creates classifier (NaiveBayesMultinomial), build model, and save model</li>
										</ul>
									</li>
								</ul>
							</li>
							<li><b>Trains feature model</b>
								<ul>
									<li>The concept is this model will determine the sentiment value based on the aspects of product users like or dislike</li>
									<li>It builds and saves the feature-based model built on the training set</li>
									<li>The training set has already been preprocessed, where every special symbol is converted into a string representing the symbol (example: converts @user into 'usermentionsymbol', www.example.com into 'urllinksymbol', etc)</li>
									<li>The general steps are:
										<ul>
											<li>Retrieves a new instances with a filter inside</li>
											<li>Saves the instances to a file named according to the type of representation. In this case, we use feature representation, so the name will be 'F.arff'</li>
											<li>Writes the attributes from the filtered instances (can be retreived from 'F.arff') to a file in 'attributes' folder (feature.tsv). The attributes are the tokenized words and the representation is based on the used tokenizer</li>
											<li>Creates classifier (NaiveBayesMultinomial), build model, and save model</li>
										</ul>
									</li>
								</ul>
							</li>
							<li><b>Trains complex model</b>
								<ul>
									<li>The concept is this model will determine the sentiment value based on the combination of text and POS (Part of Speech). Each word is assigned to the corresponding POS</li>
									<li>It builds and saves the complex-based model built on the training set</li>
									<li>The training set has already been preprocessed, where every special symbol is converted into a string representing the symbol (example: converts @user into 'usermentionsymbol', www.example.com into 'urllinksymbol', etc)</li>
									<li>The general steps are:
										<ul>
											<li>Retrieves a new instances with a filter inside</li>
											<li>Saves the instances to a file named according to the type of representation. In this case, we use complex representation, so the name will be 'C.arff'</li>
											<li>Writes the attributes from the filtered instances (can be retreived from 'C.arff') to a file in 'attributes' folder (complex.tsv). The attributes are the tokenized words and the representation is based on the used tokenizer</li>
											<li>Creates classifier (NaiveBayesMultinomial), build model, and save model</li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li><b>Polarity Classifier</b>
						<ul>
							<li><b>Initializes BidiMap objects for text, feature, and complex representation</b>
								<ul>
									<li>We create objects of <b>DualHashBidiMap</b> and initialize the value with the previous <b>DualHashBidiMap</b> explained in the <b>Trainer</b> sub-point</li>
								</ul>
							</li>
							<li><b>Initializes filter (StringToWordVector) and tokenizer (NGramTokenizer)</b>
								<ul>
									<li>We choose <b>StringToWordVector</b> as the filterer and <b>NGramTokenizer</b> as the tokenizer. The minimum and maximum size for the tokenizer is 2</li>
								</ul>	
							</li>
							<li><b>Initializes classifiers (MNB and LibSVM)</b>
								<ul>
									<li>Read the model which is built previously</li>
									<li>We use <b>NaiveBayesMultinomial</b> for the text, feature, and complex representation</li>
									<li>We use <b>LibSVM</b> for the lexicon representation</li>
									<li>We also build an instance for every representation. It is built from the data train (<b>T.arff</b>, <b>F.arff</b>, and <b>C.arff</b>)</li> 
								</ul>
							</li>
						</ul>
					</li>
					<li><b>Tweet Preprocessor</b>
						<ul>
							<li><b>Initializes preprocessor for lexicon, text, feature, and complex representation</b>
								<ul>
									<li>We create the object of attributes that will be used to preprocess all the representations before doing the classification. By preprocessing the words, we normalize them to a new sentence without any ambiguities. Here are the preprocessor for every representation:
										<ul>
											<li><b>Lexicon Preprocessor</b>
												<ul>
													<li>Sets the general score for every lexicon (use SentiWordNet as the resource)</li>
													<li><b>Gets the abbreviations and store them in a hash-table</b>
														<ul>
															<li>Fetches the list of abbreviations and return its contents in a hash-table</li>
															<li>Here are the examples: <i>afk=away from keyboard</i>, <i>aka=also known as</i>, <i>asap=as soon as possible</i></li>
															<li>From the examples, the fetched results will be stored in a hash-table: <i><afk, away from keyboard></i>, <i><aka, also known as></i>, <i><asap, as soon as possible></i></li>
														</ul>
													</li>
													<li><b>Gets the happy and sad emotions and store them in a linked list</b>
														<ul>
															<li>Fetches the list of the happy emoticons from a dictionary and store them in a linked list</li>
															<li>Here are the examples: <i>:-)</i>, <i>:)</i>, <i>;)</i></li>
															<li>From the examples, the fetched results will be stored in a linked list: <i>[:-), :), ;)]</i></li>
														</ul>
													</li>
													<li><b>Gets the list of positive and negative words</b></li>
												</ul>
											</li>
											<li><b>Text Preprocessor</b>
												<ul>
													<li><b>Gets the abbreviations and store them in a hash-table</b>
														<ul>
															<li>Fetches the list of abbreviations and return its contents in a hash-table</li>
															<li>Here are the examples: <i>afk=away from keyboard</i>, <i>aka=also known as</i>, <i>asap=as soon as possible</i></li>
															<li>From the examples, the fetched results will be stored in a hash-table: <i><afk, away from keyboard></i>, <i><aka, also known as></i>, <i><asap, as soon as possible></i></li>
														</ul>
													</li>
													<li><b>Gets the happy and sad emotions and store them in a linked list</b>
														<ul>
															<li>Fetches the list of the happy emoticons from a dictionary and store them in a linked list</li>
															<li>Here are the examples: <i>:-)</i>, <i>:)</i>, <i>;)</i></li>
															<li>From the examples, the fetched results will be stored in a linked list: <i>[:-), :), ;)]</i></li>
														</ul>
													</li>
												</ul>
											</li>
											<li><b>Feature Preprocessor</b>
												<ul>
													<li>Gets the abbreviations and store them in a hash-table</li>
													<li>Gets the happy and sad emoticons then store them in a linked list</li>
													<li>Creates the combination of dot symbol and store them in a linked list</li>
													<li>Creates the combination of exclamation symbol and store them in a linked list</li>	
												</ul>
											</li>
											<li><b>Complex Preprocessor</b>
												<ul>
													<li>This preprocessor only returns the Part of Speech (POS) of tweets</li>
												</ul>
											</li>
										</ul>
									</li>
								</ul>
							</li>
							<li><b>Initializes Part of Speech (POS) tagger</b>
								<ul>
									<li>We create an object that will be used to analyze the POS using this tagger: <b>wsj-0-18-left3words-distsim.tagger</b></li>
								</ul>
							</li>
						</ul>
					</li>
					<li><b>Initializes filterer (StringToWordVector) and tokenizer (NGramTokenizer)</b>
						<ul>
							<li>We use <b>StringToWordVector</b> as the filterer and <b>NGramTokenizer</b> as the tokenizer</li>
						</ul>
					</li>
					<li><b>Initializes data train and data test in case of we need to use sliding window</b>
						<ul>
							
						</ul>
					</li>
				</ul>
			</li>
			<li>Tweet initialization
				<ul>
					<li>Initializes the list of tweet sentiment</li>
					<li>Initializes the list of preprocessed tweet</li>
					<li>Initializes the list of class distribution</li>
					<li>Initializes the list of predicted class</li>
				</ul>
			</li>
			<li>Get Polarity
				<ul>
					<li>Preprocesses the lexicon, text, feature, and complex</li>
					<li>Initializes the instances (data test) of lexicon, text, feature, and complex</li>
				</ul>
			</li>
			<li>Execution of Algorithm
				<ul>
					<li>Gets the instances of every data test representation and applies a filter to it</li>
					<li>Reformates the instances of lexicon, text, feature, and complex
						<ul>
							<li>Removes the attributes from the data test that are not used in the data train</li>
						</ul>
					</li>
					<li>Apply the classifier
						<ul>
							<li>Classifier for text, feature, and complex representation (HC)
								<ul>
									<li>Gets the probability for each class (positive/negative)</li>
									<li>Uses double[] preds = mnb_classifiers[i].distributionForInstance(test.get(0))</li>
									<li>Text-based representation
										<ul>
											<li>Distribution for positive (hc[0]): preds[0]*31.07</li>
											<li>Distribution for negative (hc[1]): preds[1]*31.07</li>
										</ul>
									</li>
									<li>Feature-based representation
										<ul>
											<li>Distribution for positive (hc[2]): preds[0]*11.95</li>
											<li>Distribution for negative (hc[3]): preds[1]*11.95</li>
										</ul>
									</li>
									<li>Complex-based representation
										<ul>
											<li>Distribution for positive (hc[4]): preds[0]*30.95</li>
											<li>Distribution for negative (hc[5]): preds[1]*30.95</li>
										</ul>
									</li>
								</ul>
							</li>
							<li>Classifier for lexicon representation (LC)
								<ul>
									<li>Gets the probability for each class (positive/negative)</li>
									<li>Presumes that the value is stored in a variable called lc_value</li>
								</ul>
							</li>
							<li>Counts the probabilities
								<ul>
									<li>HC classifier 
										<ul>
											<li>Positive score (ps): (hc[0] + hc[2] + hc[4]) / 73.97</li>
											<li>Negative score (ns): (hc[1] + hc[3] + hc[5]) / 73.97</li>
											<li>Final score (hc_value): (1 + ps - ns) / 2</li>
										</ul>
									</li>
									<li>Comparison between HC and LC 
										<ul>
											<li>If hc_value < 0.5 AND lc_value > 0.5: output is negative</li>
											<li>Else if hc_value > 0.5 AND lc_value < 0.5: output is positive</li>
											<li>Else: output is nan</li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>Check the Sliding Window
						<ul>
							<li>Case 0: uses sliding window
								<ul>
									<li>If HC and LC agree (positive/negative), then put this document in the data train</li>		
									<li>Else, add the document into the data test. It will be classified in the end of the process
										<ul>
											<li>If the number of data train's instances is more than 0 
												<ul>
													<li>Calls clarifyOnSlidingWindow
														<ul>
															<li>Adds an instance into the data train at the end of file</li>
															<li>Sets a filter for the data train and store the filtered data train in a new instances</li>
															<li>Prepares the data train and the data test</li>
															<li>Builds classifier from the data train (1000 training data)</li>
															<li>Predicts the class based on the model created before</li>
														</ul>
													</li>
												</ul>
											</li>
											<li>Else, the final class is unknown for the data train is empty and can not do the training process</li>
										</ul>
									</li>
								</ul>
							</li>
							<li>Case 1: does not use sliding window
								<ul>
									<li>If HC and LC agree (positive/negative), then sets the list of predicted class come from three possibilities</li>
									<li>Else,
										<ul>
											<li>Calls clarifyOnModel which decides upon a "disagreed" (output class = nan) document by applying the learned model based on the previously built model
												<ul>
													<li>Gets the text-based representation of the document</li>
													<li>Re-orders attributes so that they are compatible with the data train</li>
													<li>Finds the polarity of the document based on the previously built model, namely Liebherr, goethe, or Cisco</li>
												</ul>
											</li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						Done
					</li>
				</ul>
			</li>
		</ul>
	</li>
</ul>


-----

### Don't start a business, solve a problem

As a founder, we should focus to the problems and the solutions for them. The main key point here is we can choose one existing problem that annoys the community. From that problem, try to find a solution in a creative way. Just try to create something that can ease people works.

----- 

### Innovating with creativity

One of the important thing if we want to bring our startup to the top rank is innovation. There are many existing solutions in various methods for one problem. We do need to offer something that has not been provided yet in the existing solutions. We must have a strong characteristic so that people can recognize our solutions.

-----

### How to think like a founder

As stated before, you need strong consistency and persistence if you want to become a founder. It does not enough if we only have great skills yet we can not create a good plan for our startup. A founder must have a good long term plan management skill. The first step is to find a problem and solve it. If it has already been developed successfully, try to implement the long term plan so that they can offer more innovations.<br />

So, the primary point is as a founder you need to always find a problem and solve it. Being a problem solver is the key to become a successfull startup founder. 
