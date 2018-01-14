% Anthony Frazier CSCE330
%
% Exercise Three
% September 14, 2017
%
% acted_in(person,movie).
% directed(person,movie).
% released(movie,year).

acted_in(pitt,babel).
acted_in(blanchett,babel).
directed(inarritu,babel).
released(babel,2006).

acted_in(copley,district9).
acted_in(james,district9).
directed(blomkamp,district9).
released(district9,2009).

acted_in(sandler,click).
acted_in(beckinsale,click).
directed(coraci,click).
released(click,2006).

acted_in(dicaprio,theAviator).
acted_in(blanchett,theAviator).
acted_in(beckinsale,theAviator).
directed(scorsese,theAviator).
released(theAviator,2004).

acted_in(dicaprio,inception).
acted_in(page,inception).
directed(nolan,inception).
released(inception,2010).

acted_in(pine, starTrek).
acted_in(quinto, starTrek).
directed(abrams, starTrek).
released(starTrek,2009).

acted_in(ridley,forceAwakens).
directed(abrams,forceAwakens).
released(forceAwakens,2015).

acted_in(downey, hulk).
directed(leterrier, hulk).
released(hulk, 2008).

acted_in(downey, ironMan).
directed(favreau, ironMan).
released(ironMan, 2008).

acted_in(downey, tropicThunder).
directed(stiller, tropicThunder).
released(tropicThunder, 2008).



% #1 acted_in(dicaprio, babel). false.
%
% #2 directed(X, district9). X=blomkamp.
%
% #3 ?- acted_in(X,click). X = sandler ; X = beckinsale. ?-acted_in(X,theAviator). X = dicaprio ;X = blanchett; X = beckinsale.
%
% #4 ?- released(X, 2010), acted_in(Y, X). X = inception, Y = dicaprio ; X = inception, Y = page.
%
% #5 ?- released(X, 2009), directed(Y, X). X = district9, Y = blomkamp ; #5 X = starTrek, Y = abrams.
%
% #6 ?- directed(X, Y), directed(X, Z). X = inarritu,Y = Z, Z = babel ;X = blomkamp,Y = Z, Z = district9 ;X = coraci,Y = Z, Z = click ;X = scorsese,Y = Z, Z = theAviator ;X = nolan,
%  #6Y = Z, Z = inception ;X = abrams,Y = Z, Z = starTrek ;X = abrams,Y = starTrek,Z = forceAwakens
%
% #7 ?- directed(X,Y).X = inarritu,Y = babel ;X = blomkamp,Y = district9 ;X = coraci,Y = click ;X = scorsese,Y = theAviator ;X = nolan,Y = inception ;X = abrams,Y = starTrek ;
%  #7 X = abrams, Y = forceAwakens.
%
% #8 ?- acted_in(X,Y), acted_in(X,Z), released(Y,2008), released(Z,2008).X = downey,Y = Z, Z = hulk ;X = downey,Y = hulk,Z = ironMan
%
% #9 ?- acted_in(X,W), acted_in(X,Y), acted_in(X,Z), released(W,2008),
% released(Y,2008),released(Z,2008). X = downey, W = Y, Y = Z, Z = hulk
% % ; X = downey, W = Y, Y = hulk, Z = ironMan ; X = downey, W = Y, Y = hulk, Z = tropicThunder ;
%
% #10  acted(X,Y), directed(Z,Y). failure.

