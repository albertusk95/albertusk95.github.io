---
layout: post
title: Build a Simple Recommendation System using Python
---

### Introduction

Have you ever visited such sites providing services for movies, dating, food, music, books, shopping, or even jokes? I'm pretty sure that most of you have done so, yet have you ever noticed that in certain condition you suddenly find out several options of product that attract your attention? Have you ever thought that when you choose to click those options, it is one of the company's strategies to make you buy their products with larger amount (they also hope that you will spend your time longer on their site and come back again for you think that the provided products are interesting)?

So, if you are interested in finding the answers for those enquiries, the primary question is how do the provider / company provide those options that matched your preferences? The answer is they use a feature called the **recommender system**. This feature will try to find your preferences by analyzing your purchases history and by doing this, you can say that you are acquainted by the system. Because of every person has different preferences, the system will try to build a user model that stores your evaluation for each type of product. Your evaluation can be represented as ratings from one to five, as yes/no voting, etc.

Therefore, one of solution to build the user model with high performance is to collect more information from the other users. The core principal is the system will search for a large group of users and find a smaller set which have similar tastes as yours. Afterwards, the system will fetch the products they buy or like and combine them as a list of pre-user preferences. Next, the list will be processed again by calculating the probability of preference in which these values will be utilized to build a new list based on ranking (highest probability will be stored in the first place and the lowest will in the last place). This new list will be returned to the current user as the recommended items. This kind of technique is called as **collaborative filtering**.

As I mention before that the system need to collect another information from a smaller set of users, so it needs a method to find the similarities among users. There are several methods that can be implemented and I'll explain them in the next sub-section of this article.

-----

### Collecting Preferences

In this article, we will use movies as the type of product. The user can rate a movie from one to five where one is the lowest sentiment value and five is the highest sentiment value.

So, let's start by creating a small dictionary that stores the rating values for the movies chosen by every user. The simplest way is to represent it in JSON (JavaScript Object Notation) format (it is recommended to store the dictionary in a database when you have a large dataset). Here is the code.

// code critics

### Finding Similar Users

After creating the dictionary used for storing user's preferences, the next step is to find the users which have similar tastes. These users can be presumed as a small group in which every user's choices may also be accepted by the other user with high probability.

To find the similarity score, we can use several techniques, such as Euclidean Distance, Pearson Correlation Score, Manhattan Distance, Jaccard Coefficient, etc. Yet in this article, we will only use two techniques, namely Euclidean Distance and Pearson Correlation Score. You can find more information about other techniques for comparing items at [http://en.wikipedia.org/wiki/Metric_%28mathematics%29#Examples](http://en.wikipedia.org/wiki/Metric_%28mathematics%29#Examples).



