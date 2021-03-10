color(red).
color(yellow).
color(orange).

colorCountries(France, Switzerland, Italy, Belgium, Holland, Germany, Austria):- color(France), color(Belgium), color(Holland), color(Germany), color(Switzerland), color(Austria), color(Italy),
\+ France = Belgium, \+ France = Germany, \+ France = Switzerland, \+ France = Italy,
\+ Belgium = Holland, \+ Belgium = Germany,
\+ Holland = Germany,
\+ Germany = Switzerland, \+ Germany = Austria,
\+ Switzerland = Austria, \+ Switzerland = Italy,
\+ Austria = Italy,
\+ Italy = Switzerland.