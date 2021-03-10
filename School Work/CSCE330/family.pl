% Anthony Frazier
% CSCE330 Homework3
% September 26, 2017


% This is the family relations exercise at the end of Ch.4 [L].
child(alberto,guido). child(alberto,antonietta).
child(giulia,enrico). child(giulia,annamaria).
child(dante,marco).   child(clara,marco).
child(dante,laura).   child(clara,laura).
child(marco,alberto).   child(marco,giulia).
child(laura,lawrence).  child(laura,julie).
child(emily,lawrence).  child(emily, julie).
child(claire,lawrence). child(claire, julie).
child(sam,emily). child(ben,emily).
child(sam,dave). child(ben,dave).
child(eve,claire). child(annabelle,claire).
child(eve,ed). child(annabelle,ed).
child(giulio,guido). child(donata,giulio).
child(sara,donata). child(marco2,donata).
child(giulio,antonietta).

male(guido). male(enrico).
male(marco). male(dante). male(alberto). male(lawrence).
male(sam). male(ben). male(dave). male(ed).
male(giulio). male(marco2).
female(antonietta). female(annamaria).
female(clara).  female(laura).  female(giulia). female(julie).
female(emily). female(claire). female(eve). female(annabelle).
female(donata). female(sara).

%Clauses from Figure 3.1, as required by Exercise of Ch.4[L]
parent(Y,X) :- child(X,Y).
father(Y,X) :- child(X,Y), male(Y).
opp_sex(X,Y) :- male(X), female(Y).
opp_sex(Y,X) :- male(X), female(Y).
grand_father(X,Z) :- father(X,Y), parent(Y,Z).

%New clauses for Exercise of Ch.4[L] follow
mother(Y,X) :- child(X,Y), female(Y).  %The first one is given to you
grand_parent(X,Z) :- parent(X,Y), parent(Y,Z).
great_grand_mother(X,Z) :- grand_parent(X,Y), parent(Y,Z), female(Y).

sibling(X,Y) :- parent(Z,X), parent(Z,Y), X\=Y.
brother(X,Y) :- sibling(X,Y), male(X).
sister(X,Y) :- sibling(X,Y), female(X).

half_sibling(X,Y) :- sibling(X,Y), (father(F,X), father(F,Y); mother(M,X), mother(M,Y)), X\=Y.
full_sibling(X,Y) :- sibling(X,Y), father(F,X), father(F,Y), mother(M,X), mother(M,Y), X\=Y.

first_cousin(X,Y) :- parent(P1,X), parent(P2,Y), sibling(P1,P2), X\=Y.
second_cousin(X,Y) :- parent(P1,X), parent(P2,Y), first_cousin(P1,P2), X\=Y.

half_first_cousin(X,Y) :- parent(P1, X), parent(P2,Y), half_sibling(P1,P2), X\=Y.
double_first_cousin(X,Y) :- parent(P1,X), parent(P2,Y), sibling(P1,P2), X\=Y.

first_cousin_twice_removed(X,Y) :- first_cousin(Z, X), grand_parent(Z, Y), X\=Y.

descendant(X,Y) :- child(X,Y).
descendant(X,Y) :- child(X,Z), descendant(Z,Y).
ancestor(X,Y) :- parent(X,Z), ancestor(Z,Y).

cousin(X,Y) :- parent(P1,X), parent(P2,Y), (sibling(P1,P2); cousin(P1,P2)).

closest_common_ancestor(X,Y,Z) :- ancestor(X,Y), ancestor(X,Z), child(C,X), \+ ancestor(C,Y), \+ ancestor(C,Z).

write_child(X,Y) :- write(X), write(' is a child of '), write(Y), nl.

write_descendant_chain(X,Y) :- child(X,Y), write_child(X,Y).
write_descendant_chain(X,Y) :- child(X,Z), write_child(X,Z), Y\=Z, write_descendant_chain(Z,Y).
