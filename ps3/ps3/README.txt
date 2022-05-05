IMPORTANT NOTICE: 
This implementation is mostly inspired by https://github.com/dawiditer/MIT6005-ps3. Most of this project's code follows its skeleton.
The major difference is the choice of using ANTLR listener or ANTLR visitor when parsing the input.
This implementation chooses to use ANTLR listener as same as the MIT6005 lecture's example.

Following links are also helpful
1. 
https://github.com/ouyi/antlr4calc
This project helps to clarify the difference between ANTLR listener and ANTLR visitor and also
provides a great example of ANTLR grammar

2.
https://stackoverflow.com/questions/48094546/making-calculator-with-antlr
This question gives a general guidance of what should be noticed during the implementation, such as algebraic precedence
and left-recursion

3.
https://github.com/GeorgDangl/antlr-calculator
This project gives a complete example of ANTLR calculator which provides the important guidance of grammar
