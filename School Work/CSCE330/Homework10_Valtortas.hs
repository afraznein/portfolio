{-
Anthony Frazier
November 20, 2017

Chapter 4 Exercise 8
-}

luhnDouble :: Int -> Int
luhnDouble x | (x * 2) > 9 = (x * 2) - 9
             | otherwise = x * 2

luhn  :: Int -> Int -> Int -> Int -> Bool
luhn x y z a = ((luhnDouble x) + y + (luhnDouble z) + a) `mod` 10 == 0

{-GHCi, version 8.2.1: http://www.haskell.org/ghc/  :? for help
[1 of 1] Compiling Main             ( Homework10_Valtortas.hs, interpreted )
Ok, 1 module loaded.
Main> luhnDouble 3
6
Main> luhnDouble 6
3
Main> :reload
[1 of 1] Compiling Main             (  Homework10_Valtortas.hs, interpreted )
Ok, 1 module loaded.
Main> luhn 1 7 8 4
True
Main> luhn 4 7 8 3
False

Chapter 5 Exercise 5 
-}

pyths :: Int -> [(Int, Int, Int)]
pyths n = [(a,b,c) | a <-[1..n], b <- [1..n], c <- [1..n], a^2 + b^2 == c^2]

{-Prelude> :l Homework10_Valtortas.hs
[1 of 1] Compiling Main             ( Homework10_Valtortas.hs, interpreted )
Ok, 1 module loaded.
*Main> pyths 10
[(3,4,5),(4,3,5),(6,8,10),(8,6,10)]
-}


--Chapter 5 Exercise 6 

factors :: Int -> [Int]
factors n = [x | x <- [1..n], n `mod` x == 0]

perfects :: Int -> [Int]
perfects n = [x | x <- [1..n], sum (init (factors x)) == x]
-- We need to remove the factor of x = n, because if not our result will = 1

{-
Prelude> :reload
[1 of 1] Compiling Main             ( Homework10_Valtortas.hs, interpreted )
Ok, 1 module loaded.
Main> perfects 500
[6,28,496]
Main>
-}


--Chapter 5 Exercise 9
-- We probably want to use zip function because it will produce pairs of two lists
scalarproduct :: [Int] -> [Int] -> Int
scalarproduct xs ys = sum [(x * y) | (x,y) <- zip xs ys]
{-
Main> :reload
[1 of 1] Compiling Main             ( Homework10_Valtortas.hs, interpreted )

Homework10_Valtortas.hs:66:38: error:
    Parse error in pattern: zip
    Possibly caused by a missing 'do'?
   |
66 | scalarproduct xs ys = [sum (x * y) | zip (x,y) <- xs ys]
   |                                      ^^^^^^^^^
Failed, 0 modules loaded.
Prelude> :reload
[1 of 1] Compiling Main             ( Homework10_Valtortas.hs, interpreted )

Homework10_Valtortas.hs:66:41: error:
    Parse error in pattern: zip
    Possibly caused by a missing 'do'?
   |
66 | scalarproduct xs ys = [sum (x * y) | do zip (x,y) <- xs ys]
   |                                         ^^^^^^^^^
Failed, 0 modules loaded.
Prelude> :reload
[1 of 1] Compiling Main             ( Homework10_Valtortas.hs, interpreted )

Homework10_Valtortas.hs:66:23: error:
    * Couldn't match expected type `Int' with actual type `[Integer]'
    * In the expression: [sum (x * y) | (x, y) <- zip xs ys]
      In an equation for `scalarproduct':
          scalarproduct xs ys = [sum (x * y) | (x, y) <- zip xs ys]
   |
66 | scalarproduct xs ys = [sum (x * y) | (x,y) <- zip xs ys]
   |                       ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Homework10_Valtortas.hs:66:29: error:
    * Couldn't match expected type `t0 Integer' with actual type `Int'
    * In the first argument of `sum', namely `(x * y)'
      In the expression: sum (x * y)
      In the expression: [sum (x * y) | (x, y) <- zip xs ys]
   |
66 | scalarproduct xs ys = [sum (x * y) | (x,y) <- zip xs ys]
   |                             ^^^^^
Failed, 0 modules loaded.
Prelude> :reload
[1 of 1] Compiling Main             ( Homework10_Valtortas.hs, interpreted )
Ok, 1 module loaded.
Main> scalarproduct [1,2,3] [4,5,6]
32
Main>
-}
