prime(2) :- !.
prime(N) :- N > 2, \+ composite(N).
composite(N) :- N > 1, divisor(N, 2).
divisor(N, Divisor) :-
    Divisor * Divisor =< N,
    (N mod Divisor =:= 0 ; Divisorz is Divisor + 1, divisor(N, Divisorz)).

divisors_list(N, Divisors) :- divisors_count(N, 2, Divisors).

divisible(N, Divisors) :- N mod Divisors =:= 0.
divisors_count(N, Divisor, Divisors) :-
    (Divisor * Divisor > N ->
        Divisors = [N]
    ; divisible(N, Divisor) ->
        (
            Numberz is N // Divisor,
            Divisors = [Divisor | X],
            divisors_count(Numberz, Divisor, X)
        )
    ;
        Divisorz is Divisor + 1,
        divisors_count(N, Divisorz, Divisors)
    ).

prime_divisors(1, []) :- !.
prime_divisors(N, Divisors) :- number(N), N > 1, divisors_list(N, Divisors).

cube_divisors(N, D) :-
    N3 is N * N * N,
    findall(X, (divisors_list(N3, Divisors), member(X, Divisors), X \= 1), D).
