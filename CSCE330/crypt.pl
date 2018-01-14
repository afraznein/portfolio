% Anthony Frazier
% CSCE330 Homework 4
% October 3, 2017

%CROSS + ROADS = DANGER. C !=0 and R!=0
% 
%   C R O S S
%   R O A D S
%
% D A N G E R
%
digit(0).
digit(1).
digit(2).
digit(3).
digit(4).
digit(5).
digit(6).
digit(7).
digit(8).
digit(9).

uniqueAnswers(C,R,O,S,A,D,N,G,E):-  digit(C), digit(R), digit(O), digit(S), digit(A), digit(D), digit(N), digit(G), digit(E),
\+ C = R, \+ C = O, \+ C = S, \+ C = A, \+ C = D, \+ C = N, \+ C = G, \+ C = E,
\+ R = O, \+ R = S, \+ R = A, \+ R = D, \+ R = N, \+ R = G, \+ R = E,
\+ O = S, \+ O = A, \+ O = D, \+ O = N, \+ O = G, \+ O = E,
\+ S = A, \+ S = D, \+ S = N, \+ S = G, \+ S = E,
\+ A = D, \+ A = N, \+ A = G, \+ A = E,
\+ D = N, \+ D = G, \+ D = E,
\+ N = G, \+ N = E,
\+ G = E.

solution(C,R,O,S,A,D,N,G,E):- digit(S), 

R is (S+S) mod 10,
C1 is (S+S) // 10,

digit(S), digit(D),

E is (S+D+C1) mod 10,
C2 is (S+D+C1) // 10,

digit(O), digit(A),

G is (O+A+C2) mod 10,
C3 is (O+A+C2) // 10,

digit(R), digit(O),

N is (R+O+C3) mod 10,
C4 is (R+O+C3) // 10,

digit(C), C>0, digit(R), R>0, 

A is (C+R+C4) mod 10,
D is (C+R+C4) // 10,

uniqueAnswers(C,R,O,S,A,D,N,G,E).


% ?- solution(C,R,O,S,A,D,N,G,E).
% C = 9,
% R = 6,
% O = 2,
% S = 3,
% A = 5,
% D = 1,
% N = 8,
% G = 7,
% E = 4 



