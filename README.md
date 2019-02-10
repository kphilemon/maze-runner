# Maze_Runner
[![Made with JAVA](https://img.shields.io/badge/Made_with-JAVA-1abc9c.svg)](https://en.wikipedia.org/wiki/Java_(programming_language))

Maze_Runner is a simple yet challenging console-based game written in pure java. 
It is _fully documented_ and designed as clean as possible for its readability and further development. 

This is the final assignment made for my first year programming course (Fundamentals of Programming) at the [University of Malaya](https://www.um.edu.my/).
Basic concepts of OOP such as class, inheritance, polymorphism, abstraction and encapsulation is implemented in this project. 
A simple maze generation algorithm is used to generate the dynamic game map as well.

## Getting Started
This project does not use any build tools because it's a relatively small project and does not have any dependencies.<br/>
So, we will compile and run it manually.

**1. Clone the repository**
```
$ git clone https://github.com/kphilemon/Maze_Runner.git
```
**2. Open command prompt and go to the src directory**
```
cd your_path_to_project\Maze_Runner\src
```
**3. Compile the source files**
```
javac main\*.java
```
**4. Run the program**
```
java main.Launcher
```

## Game Play
 ![gameplay.gif](https://github.com/kphilemon/Maze_Runner/blob/master/gameplay.gif)

## Game Description
Johnny, `J`, the maze runner, is trapped in a deadly maze.

To escape from the maze, he must find all the keys, `K`, that are scattered in the maze.
After collecting all the keys, the exit, `E`, will appear at the corner of the maze.

Johnny's visibility is very limited due to the heavy fog, `#`, he couldn't see beyond two blocks.
However, there's a holy torch, `%`, hidden somewhere in the maze. It could help to boost Johnny's visibility.

And of course, Johnny is just a normal guy, he couldn't see or walk through the maze walls.
Every valid movement will cost Johnny a drop of blood. So make your move wisely! 

### How to play?
1. Start the game by entering the size of the game map. (Difficulty increases with the map size) 

2. In the game, Johnny can move in four directions. Use the arrow keys (up, down, left, right) to control Johnny's movement.

3. Escape the maze to win the game!

**Note:** If the arrow keys do not work, click on the small window at the top left corner and try again.

## Author
Connect with me on [LinkedIn](https://www.linkedin.com/in/philemon-khor/) or follow me on [Github](https://github.com/kphilemon).


## License
This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/kphilemon/Maze_Runner/blob/master/LICENSE.md) file for details
