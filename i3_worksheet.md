What technical debt has been cleaned up
========================================
A technical debt that got clean up was the GroceryList, it was not using the persistence layer before.

Show links to a commit where you paid off technical debt. Write 2-5 sentences
that explain what debt was paid, and what its classification is.
https://code.cs.umanitoba.ca/winter-2022-a02/group-8/recipe-app/-/commit/3504065f0020ba8c92b821f615c9cf95b1240b3b


What technical debt did you leave?
==================================
In the recipe, we instead of a list of instruction, we save the instructions as one long string. We replaced every newline with '$' to push it into the database.

What one item would you like to fix, and can't? Anything you write will not
be marked negatively. Classify this debt.
This was a prudent-inadvertent, we are not sure why we chose this way.

Discuss a Feature or User Story that was cut/re-prioritized
============================================
Video tutorial was completely cut from the feature list for our last iteration. This is because due to time constraints.

When did you change the priority of a Feature or User Story? Why was it
re-prioritized? Provide a link to the Feature or User Story. This can be from any
iteration.
We changed it on the last iteration when there is a clear time constraints in our planning.

Acceptance test/end-to-end
The user should be able to interact with the grocery list.
The user should be able to add a new ingredient to the grocery list.
The user should be able to modify an ingredient in the grocery list.
The user should be able to remove an ingredient from the grocery list.

The user should be able to interact with the recipe database.
The user should be able to look up the recipes in categories.
The user should be able to add their own recipe.
The user should be able to modify the recipes.
The user should be able to delete a recipe from the database.
==========================

Write a discussion about an end-to-end test that you wrote. What did you test,
how did you set up the test so it was not flaky? Provide a link to that test.

Acceptance test, untestable
===============

What challenges did you face when creating acceptance tests? What was difficult
or impossible to test?

Velocity/teamwork
=================
7/14 issues complete for this iteration.

Did your estimates get better or worse through the course? Show some
evidence of the estimates/actuals from tasks.
