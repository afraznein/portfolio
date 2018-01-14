chain :: (Integral a) => a -> [a]
chain 1 = [1]
chain a = if (a `mod` 2 == 0) 
          then a:chain ( a `div` 2 )
          else a:chain ((a * 3) +1)

chain' :: (Integral a) => a -> [a]

chain' 1 = [1]
chain' a  | (a `mod` 2 == 0) = a:chain' ( a `div` 2 )
          | otherwise = a:chain' ((a * 3) +1)

fst' :: (Int,a) -> Int
fst' (x,_) = x

max' :: Ord a => a -> a -> a
max' a b = if (a > b) 
           then a 
           else b

replicate' :: (Num i, Ord i) => i -> a -> [a]
replicate' 0 y = []
replicate' x y = y : replicate' (x-1) y

add' :: Int -> Int -> Int
add' x y = x + y
addOne = add' 1

divTwo :: (Integral a) => a -> a 
divTwo a = a `div` 2 
divTwo' = divTwo

factors' :: (Integral a) => a -> [a]
factors' a  = [b | b  <-[1..a], a `mod` b == 0]

isPrime :: (Integral a) => a -> Bool
isPrime a = (factors' a) == [1,a]

product' :: [Int] -> Int
product' [] = 1
product' (n:ns) = n * product' ns

product'' :: Num a => [a] -> a
product'' = foldr (*) 1

factorial' :: (Num a, Enum a) => a -> a
factorial' a = foldl (*) 1 [1..a]

data Day = Monday | Tuesday | Wednesday | Thursday |Friday | Saturday | Sunday
 deriving (Eq, Ord, Show, Read, Bounded, Enum)

