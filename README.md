**Ghost Game**

Ghost is a written word game in which players take turns to add letters to a english word (from left to right). The first player that completes a valid word longer than 3 characters or introduce a letter that make the current fragment not extendable as a valid word lost. 
 
Write a web app that play intelligently agains an human user using the english dictionary provided. Let the human play always first. 

The exercise will be evaluated considering the strategy used by the computer to win the game, clarity and legibility of code, architecture of the solution, usage of industry standard frameworks and test coverage. 

The aim of the exercise is to present the best skills of the candidate, so, a complex solution is preferred to a minimalistic approach. 


https://en.wikipedia.org/wiki/Ghost_(game)

=================================================================================

My work environment has been: IntelliJ IDE with SonarLint to QualityCode, JDK 9.0, Tomcat 9.0.4
Frameworks used are: Spring MVC, Core, Context, Web and Test 3.2
Spring Internalization with i18n.
Perf4j, log4j, junit, jQuery, jstl, Maven 3.

After deploying the war file in an application server you can access the game using this url as an entry point:

http://127.0.0.1:8080/ghost

=================================================================================

Design patterns:
- Visitor pattern: to separate the alogorithm from the Tree object structure on which it operates. Best way 
to follow the open/closed principle. 
- Static holder singleton pattern: which provide me static factory, lazy initialization and Thread safe.
- MVC: Provided by the Spring MVC framework.

Handle error with @ExceptionHandler annotation of Spring, with the HTTPErrorHandlerController and with the custom IllegalPlayException

**GAME SOLUTION:**

- The first step is to load the data from goshtGameDict.txt into an appropriate data structure. Only words of four or more letters should be loaded. Words that begin with shorter words should be either removed or not loaded at all.

- The obvious implementation is to build a tree of words, which provide a structural relationships in the data, and an efficient searching. The tree structure has a root node which is no letters, and each child node is the result of adding the next letter in a word to a tree.

- The algorithm of game resolution is based on the construction of a tree

- The leaf nodes are complete words (I can throw away words that have an initial subset that is also a complete word).

- When I've built the complete tree and have all the leaf nodes, the leaf nodes with an odd-number of letters are goals for the computer and the nodes with an even number of letters are goals for human player

- I go up a level; if all the nodes beneath a given node are goals for player x, that node also becomes a goal for player x; or if any of the nodes beneath a given node are goals for player x, and the node will be hit on player x's turn, that node becomes a goal for player x.
  
  If a single character node is a goal for player computer, player computer can always win the game.

- So, firstly, this tree is post-order traversed to find which nodes are goals for every player and then it is pre-order traversed again to prune it. All non-logical nodes from the point of view of the computer player are eliminated. These nodes would never be played

- And all non-logical nodes which are winners for the logical computer, I will do a pruning except with the shortest way, to give a feeling of aggressive play

- If the machine detects that it is likely to lose, I am using Alpha-beta pruning to select the best movement, according to the "play intelligently" as the exercise says. I am examining a tree upto depth of 3.

- I am using the following letter selection consideration for the heuristics (only when machine thinks it can be lose):
  - Since the user plays first, the computer will win when the number of letters in the word are odd for the user to finish last.
  - The chances of extending the game and choosing more words increases when words are longer.
  - The more winning nodes there are, the more likely the human will be to fail

- Relating to the application web, all the users are going to share the same tree. They just have different pointers to different nodes of the tree.

 




