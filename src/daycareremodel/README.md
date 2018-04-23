# Daycare Remodeling Problem

## Credit for Problem description: Mark Floryan

## Problem Description
You administer an infant daycare that has n rooms filled with babies.
Each room has a capacity C<sub>i</sub>, denoting how many children can fit 
into each room. In addition, each room is filled to capacity every single day during work hours.
You've recently decided to remodel the entire building one room at a time, but there are some 
constraints. Every remodeled room takes 24 hours, and you need to move the children from the remodeled room temporarily
for that day. Additionally, once a room is successfully remodeled, the capacity of that room might go up
or down (or possibly stay the same). We'll call the new capacity of the i'th room C<sub>i</sub><sup>'</sup>. 

The problem you must solve then is this: you need to purchase a temporary trailer to hold extra
children during the remodeling days, but the trailer company charges based on the child capacity of
the trailer. Can you figure ou the minimum capacity extra trailer necessary in order to full remodel the daycare successfully?

Let's look at an example: Suppose the daycare has four rooms A, B, C, and D with capacities 6, 1, 3, and 3. The
new capacities after remodeling each room will become 6, 7, 5, and 5 respectively. If you buy a trailer with a
capacity of 1 child, you can move the child from room B there for one day and that room's new capacity becomes 7. You can keep the one 
child in the trailer, move 6 children into room B while you remodel room A. You can then continue to use room B as extra space while
remodeling rooms C and D. Notice that for this problem 1) children do NOT need to end up in the same room they started in 
after the remodeling is finished and 2) you may not longer have enough space to hold all of the original children and the extra trailer
may need to become permanent (we'll just call this case one of terrible planning).

## Input
The input file contains several test cases, each in the following format: The input begins with a line containing
one integer n (1 &le; n &le; 10<sup>6</sup>), which is the number of rooms in your daycare. Following this are n lines,
each describing a room as two integers C<sub>i</sub> and C<sub>i</sub><sup>'</sup>, the capacity of each rooms currently
and the capacity of that room after remodeling. A sample input file titled input.txt is located in this directory.

## Output
For each test case, display the total extra capacity (in number of children) trailer you must purchase 
(print this value on a line by itself).

