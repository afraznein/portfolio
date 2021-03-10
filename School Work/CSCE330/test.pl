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

solution(Pizza) :-

unique(Lasagna,Pizza,Chicken,Steak),
unique(Coke,Milk,Coffee,Water),

beside(doreen,Steak),
\+ doreen = Steak,
Chicken = Coke,
across(Lasagna,Milk),
\+ david = Coffee,
donna = Water,
\+ danny = Steak.
