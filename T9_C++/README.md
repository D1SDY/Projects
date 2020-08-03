<h1>About</h1>
This is me implementation of T9(autocorrect).Programm will detect if given dictionary contains input word,
if not it will write out the most familiar word(according LevenshteinDistance algoritm)
<h1>Implementation</h1>
The dictionary has been readen, line by line. Every line represent one word,
after word was readen it has been added to vector, and on the end I build the prefix trie acourding to vector of words. Then the user is been requested to input his word, and if trie conteins that word, program will wrtite that word was typed correctly, and if not will write out list of closest words.
<h1>Guide line</h1>
<ul>
    <li>User can start the program with command "--start"</li>
    <li>User can see the list of commands with "--help"</li>
    <li>User can swith with options of running program(One core and Multi core) respectivly "--one" and "--multi"</li>
    <li>User can quit the program with "--q" command</li>
</ul>
<h1>Input and outputs</h1>
<ul>
    <li>For inputed word "boods" program will write out "bonds, books, boots, foods, goods, woods"</li>
    <li> Time results: <br>
        Single core - 133ms<br>
        Multi core - 120ms
    </li>s
</ul>