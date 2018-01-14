double x = x + x
quadruple x = double (double x)

factorial n = product [1..n]
average ns = sum ns `div` length ns

n = a `div` length xs
     where
         a = 10
         xs = [1,2,3,4,5]

lastOne :: [a] -> a
lastOne x = head(reverse x)

lastTwo :: [a] -> a
lastTwo x = x !! ( length x - 1)

initOne x = reverse(tail(reverse x) )

initTwo x = reverse(drop 1(reverse x))

bools :: [Bool]
bools = [True]

nums :: [[Int]]
nums = [[42]]

add :: Int -> Int -> Int -> Int
add a b c = a * b + c

copy :: a -> (a,a)
copy a = (a,a)

apply :: (a -> b) -> a -> b
apply a b = a b



second :: [a] -> a
second xs = head (tail xs)

swap :: (a,b) -> (b,a)
swap (x,y) = (y,x)

pair :: a -> b -> (a,b)
pair x y = (x,y)

double :: Num a => a -> a
double x = x * 2

palindrome :: Eq a => [a] -> Bool
palindrome xs = reverse xs == xs

twice :: (a -> a) -> a -> a
twice f x = f (f x)

