## Binary Search Tree with Lazy Deletion
#CS1C Foothill College November 1, 2015

The project is to make a binary search tree implement lazy deletion with the
use of a boolean attribute named "deleted" that is in the node class associated
with the tree. 

The first task was to get it to work on integers and then apply it to strings
read into the program from a text file. The file was to drive it to simulate
an inventory in the supermarket buy stocking and removing items.

The challenge was to get the insert, remove, find, findMin, findMax etc. functions
to accurately work with both states of the tree 1) with nodes present but
"soft deleted" by adjusting the boolean attribute to true. and 2) the state of 
the tree where the nodes were actually deleted.

1) The project drove home the use of recursive methods that were necessary to 
traverse the tree. Lots of exposure to using those methods here.

2) The use of generic classes and methods. Kinda unhappy with the number of 
warnings I'm still getting, put the utility of this strategy and the 
implementation is becoming evident.

3) The use of Functor class to give more degrees of freedom passing a method
around through the use of an interface with a class that has one method and
no instance variables in it. This is sort of a work around the fact that Java
does not allow functions to be passed in as an argument -- a first class variable
I believe it is called -- like javascript and other language do.

4) The utility of the Functor pattern inspired me to work on creating a more
controlled an documented testing process. In this project I created a testing
class with output methods to show the various states of the tree. The debugging
methods can be toggled on or off through the use of a boolean that is passed
in as an argument with the testing class object is instantiated. 

5) The second part of the project was to take a tree of SongEntry nodes and
an Itunes store simulation to populate a tree of custom nodes that carry strings.
My strategy on this was to add an attribute to the primary node (LazySTNode)
and use the base methods in the LazySearchTree class modifying them by adding
methods that utilized them in the class that created the tree of SongEntry 
nodes. I got it to populate the tree of SongEntry nodes and was able to find 
a node based on song title and pull out data as needed from the associated
SongEntry node. I ran out of time to polish this up. 