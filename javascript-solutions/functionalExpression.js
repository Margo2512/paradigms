"use strict"

const cnst = a => () => a
const INDEX = {x: 0, y: 1, z: 2}
const variable = variable => (...args) => args[INDEX[variable]];
const operations = func => (...operations) => (...args) =>
    func(...operations.map((operations) => operations(...args)))
const add = operations((a, b) => a+b)
const subtract = operations((a, b) => a - b)
const multiply = operations((a, b) => a * b)
const divide = operations((a, b) => a / b)
const negate = operations(a => -a)
const one = cnst(1)
const two = cnst(2)
const sinh = operations(Math.sinh)
const cosh = operations(Math.cosh)




