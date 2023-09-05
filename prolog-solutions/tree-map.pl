map_build(ListMap, TreeMap) :-
    map_build(ListMap, nil, TreeMap).
map_build([], TreeMap, TreeMap).
map_build([P1 | P2], ATree, TreeMap) :-
    P1 = (Key, Value),
    map_put(ATree, NewTree, Key, Value),
    map_build(P2, NewTree, TreeMap).

map_put(nil, f(nil, nil, Key, Value), Key, Value).
map_put(f(Left, Right, Key, _), f(Left, Right, Key, Value), Key, Value) :- !.
map_put(f(Left, Right, Key0, Value0), f(Left0, Right, Key0, Value0), Key, Value) :- Key @< Key0,
    map_put(Left, Left0, Key, Value).
map_put(f(Left, Right, Key0, Value0), f(Left, Right0, Key0, Value0), Key, Value) :-
    map_put(Right, Right0, Key, Value).

map_get(f(Left, Right, Key, Value), Skey, Rvalue) :-
    (
        Skey = Key,
        Rvalue = Value
    );
    (
        Skey @< Key,
        map_get(Left, Skey, Rvalue)
    );
    (
        map_get(Right, Skey, Rvalue)
    ).

map_values(nil, []).
map_values(f(Left, Right, Key, Value), Values) :- map_values(Left, Lvalues), map_values(Right, Rvalues),
    append(Lvalues, [Value | Rvalues], Values).