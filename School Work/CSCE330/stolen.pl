person(doreen). person(donna).
person(danny). person(david).
 
across(danny,david). across(david,danny).
across(doreen,donna). across(donna,doreen).
 
beside(X,Y) :- \+ across(X,Y).
 
uniq_people(A,B,C,D) :-
        person(A), person(B),
        \+ A=B,
        person(C),
        \+ A=C, \+ B=C,
        person(D),
        \+ A=D, \+ B=D, \+ C=D.
 
solution(Pizza) :-
        uniq_people(Pizza,Lasagna,Chicken,Steak),
        uniq_people(Coke,Milk,Water,Coffee),
 
        \+ doreen=Steak,
        beside(doreen,Steak),
        Chicken=Coke,
        across(Lasagna,Milk),
        \+ david=Coffee,
        donna=Water,
        \+ danny=Steak.