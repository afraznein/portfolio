{-
Anthony Frazier
November 29, 2017

Exercises 4,6,7,8 in Ch.6 [H], due on Thursday, November 30.
 Turn in a .hs file with comments and (commented) examples of use.
-}

{-Exercise 4-}

euclid :: Int -> Int -> Int
euclid a b  | a == b    = a
            | a > b     = euclid (a - b) b
            | otherwise = euclid a (b - a)
 {-
*Main> :reload
[1 of 1] Compiling Main             ( CSCE330_Homework11.hs, interpreted )
Ok, 1 module loaded.
*Main> euclid 6 27
3
*Main>
-}

{-Exercise 6-}
{-a-}

and' :: [Bool] -> Bool
and' [] = True
and' (x:[]) = x
and' (x:xs) | x == False = False
            | otherwise  = and' (xs)
{-
*Main> :reload
[1 of 1] Compiling Main             ( CSCE330_Homework11.hs, interpreted )
Ok, 1 module loaded.
*Main> and'[True, True, False]
False
*Main> and' [False, True, True]
False
*Main> and'[]
True
*Main> and'[True]
True
*Main>
-}

{-b-}

concat' :: [[a]] -> [a]
concat' [] = []
concat' (x:xs) = x ++ concat xs

{-
*Main> :reload
[1 of 1] Compiling Main             ( CSCE330_Homework11.hs, interpreted )
Ok, 1 module loaded.
*Main> concat' [[1,2,3],[4,5,6],[7,8,9]]
[1,2,3,4,5,6,7,8,9]
-}

{-c-}

replicate' :: Int -> a -> [a]
replicate' 0 _ = []
replicate' n a = a : replicate'(n-1) a

{-
*Main> :reload
[1 of 1] Compiling Main             ( CSCE330_Homework11.hs, interpreted )
Ok, 1 module loaded.
*Main> replicate' 10 [True, False]
[[True,False],[True,False],[True,False],[True,False],[True,False],[True,False],[True,False],[True,False],[True,False],[True,False]]
-}

{-d-}

selectNthElem :: [a] -> Int -> a
selectNthElem (x:xs) 0 = x
selectNthElem (x:xs) n = selectNthElem (xs) (n-1)

{-
*Main> :reload
[1 of 1] Compiling Main             ( CSCE330_Homework11.hs, interpreted )
Ok, 1 module loaded.
*Main> selectNthElem [0,1,2,3,4,5] 3
3
-}

{-e-}

elem' :: Eq a => a -> [a] -> Bool
elem' a [] = False
elem' a (x:xs) | a == x = True
               | otherwise  = elem' a xs
{-
Prelude> :reload
[1 of 1] Compiling Main             ( CSCE330_Homework11.hs, interpreted )
Ok, 1 module loaded.
*Main> elem' 1 [0,2,3,4,5,6,7,8,9, 1]
True
*Main> elem' 1 [0,2,3,4,5,6,7,8,9]
False
*Main>
-}

{-Exercise 7-}

merge :: Ord a => [a] -> [a] -> [a]
merge [] [] = []
merge a [] = a
merge [] a = a
merge (x:xs) (y:ys) | x < y     = x : merge xs (y:ys)
                    | otherwise = y : merge (x:xs) ys 

{-
*Main> :reload
Ok, 1 module loaded.
*Main> merge [1,2,5,6,7] [3,4,8,9]
[1,2,3,4,5,6,7,8,9]
*Main>
-}

{-Exercise 8-}

halve :: [a] -> ([a],[a])
halve xs = ((take n xs), (drop n xs))
           where n  = length xs `div` 2

msort :: Ord a => [a] -> [a]
msort []  = []
msort [x] = [x]
msort xs  = merge (msort ys) (msort zs)
            where (ys,zs) = halve xs

{-
*Main> :reload
[1 of 1] Compiling Main             ( CSCE330_Homework11.hs, interpreted )
Ok, 1 module loaded.
*Main> msort [1,2,3,4,5,9,8,7,6]
[1,2,3,4,5,6,7,8,9]
*Main>
-}