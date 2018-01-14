lastOne :: [a] -> a
lastOne x = head(reverse x)

lastTwo :: [a] -> a
lastTwo x = x !! ( length x - 1)