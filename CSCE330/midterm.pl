%before(X,Y,L) :- append(_,[X|A],Z), append(Z,[Y|A],L).
%con(X,Y,Z) :- member(A,Z), before(X,Y,A).
%supplier(X,Z) :- supplies(X,B), supplier(B,Z).

rel(a,c).
rel(a,b).
rel(b,f).
rel(f,g).
clos(X,Y) :- rel(X,Y).
clos(X,Y) :- rel(X,Z), clos(Z,Y).


%delete(X,Y,Z) :- member(A,Y), member(A,Z), \+ member(X,Z). 
overlap(X,Y,Z,W) :- (X >= Z, Y =< W) ; (X =< Z, Y >= W).


delete(X,Y,Z) :- member(A,Y), member(A,Z), \+ member(X,Z).

composer(monteverdi, 1567, 1643).
composer(bach, 1685, 1750).
composer(vivaldi, 1678, 1750).
composer(mozart, 1756, 1791).
composer(haydn, 1732, 1809).
composer(beethoven, 1770, 1827).
composer(schubert, 1797, 1828).
composer(brahms, 1833, 1897).
composer(verdi, 1813, 1901).
composer(debussy, 1862, 1918).

overlap(X,Y,Z,W) :- (X >= Z, Y =< W) ; (X =< Z, Y >= W).
contemporary(X,Y) :- composer(X,A,B), composer(Y,C,D), overlap(A,B,C,D).
