% Anthony Frazier
% CSCE330 Exercise Two (Chapter Two)
% September 14, 2017
%
% #1 - Give an example of an atomic sentence not in the KB but entailed
% by it: St. George is left of Yonge. (St. George is left of bay, and
% bay is left of yonge).
%
% #2 - Explain why 'spadina is right of bathhurst' is not entailed in
% the KB. : There is no 'right' relation in the KB at all.

left(bay, yonge).
% for question 5 left(yonge, bay).
left(st-george, bay).
left(spadina, st-george).
left(bathurst, spadina).
left(christie, bathurst).

west(X,Y) :- left(X,Y).
west(X,Z) :- left(X,Y), west(Y,Z).
% for question 4 west(X,Z) :- west(X,Y), left(Y,Z).
east(Y,X) :- west(X,Y).

% #3 - Track the back-chaining procedure of the following queries:
%
% a) west(spadina, st-george). : True, west (X,Y) :- left(X,Y)
% X = spadina Y = st-george.
%
% b) east(yonge, bay). : True, east(Y,X) :- west(X,Y)
% X = bay, Y = yonge.
%
% c) west(christie, spadina). : True, christie->bathurst->spadina,
% west(X,Y) :- left(X,Y).
%
% d) west(yonge, yonge). : False, can't be west of itself
%
% e) east(st-george, bathurst). : True, bathurst->spadina->st-george ,
% east(Y,X) :- west(X,Y).
%
% f) west(bay, sherbourne). : False, sherbourne is not entailed in the
% KB
%
%
% #4 - Suppose the second conditional changes to 'If X is west of Y, and
% Y is left of Z, then X is west of Z.' Would this change what is
% entailed?
%
% No, it will not change what is entailed. It will still return
% false for west(yonge, yonge).
%
% #5 - Suppose the incorrect statement yonge is left of bay is added to
% the KB. For what values of X would the resulting query : spadina is
% west of X return true?
%
% This returns true for st-george, bay, yonge, and then gets stuck in an
% infinite loop between bay and yonge.
%
% - What happens now with the back chaining procedure on the query (3f)
% bay is west of sherbourne?
%
% This still returns false, sherbourne is still not entailed in the KB.












