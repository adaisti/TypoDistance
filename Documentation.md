# Project

This project implements Ukkonen's cut-off algorithm for calculating all approximate string matches with k mismatches both in it's traditional way and with a modification that replaces the Levenhstein distance with a weighted typo optimized distance. The goal is to find approximately matching strings that are more probably have been searched by a user who was typing on a Finnish QWERTY keyboard. 

# Implementation

The project is implemented in Java. All relevant code is to be found in the project package and by changing the method calls in the main method various searchs patterns, texts and mismatches amounts can be tried out.

# Typo distance

The typo optimized distance takes in account 3 kinds of most common typing mistakes: pressing a key next to (close to) the intended key, pressing the key twice (when only once was meant) and pressing the key too lightly, so that the intended letter is missing. 

For the first kind of an error, the weight of a substitution was set 1 if the letters are on neighbour keys on the same row on the QWERTY keyboard and 2 if the keys are next to each other, but not on the same row, and otherwise 3. (It is more common to hit a key on the same row than the intended key.) All letters of the Finnish alphabet (a-ö) were taken in account and the relations are symmetrical between letters, but neighbour keys of letters that had numbers or special characters were only considered as possible mismatches, not given any relations when being the intended key. Scecials keys and cases of the letters were not taken in account (although it is common to for example press CAPS LOCK when intending for an a).

For the second kind of an error the weight of deletion was set 1 if the letter was the same as the matching previous letter, and otherwise 3. For the third option, the weight of an insertion was set 1. The weight of no operation is always 0 just like in the Levenhstein distance calculation.

The typo distance is (symmetria, metriikka? -> toimivuus matriisilla?)

aikavaativuus
