thing(danny).
thing(david).
thing(donna).
thing(doreen).

across(danny,david).
across(donna,doreen).

across(x,y) :- across(y,x).
beside(x,y) :- \+ across(x,y).

unique(A,B,C,D) :- 
thing(A), thing(B), 
\+ A=B,
thing(C),
\+ A=C, \+ B=C,
thing(D),
\+ A=D, \+ B=D, \+ C=D.

solution(pizza) :- 

unique(lasagna,pizza,chicken,steak),
unique(coke,milk,coffee,water),

beside(doreen,steak),
\+ doreen = steak,
chicken = coke, 
across(lasagna,milk),
\+ david = coffee, 
donna = water, 
\+ danny = steak.