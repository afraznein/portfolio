% Anthony Frazier
% CSCE330
% Homework 5
%
% October 10, 2017

exactly_3([_,_,_]).

at_least_3([_,_,_|_]).

at_most_3([]).
at_most_3([_|[]]).
at_most_3([_,_|[]]).
at_most_3([_,_,_|[]]).

intersect([A|_],B) :- member(A,B).

% Really can't figure out a way to do this without recursively calling to parse over the remainder of A

intersect([_|A],B) :- intersect(A,B).



all_intersect(A,B) :- member(D,A), intersect(D,B).
