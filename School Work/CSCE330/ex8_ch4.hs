luhnDouble :: Int -> Int
luhnDouble x | x * 2 > 9 = (x * 2) -9
			 | otherwise = x * 2