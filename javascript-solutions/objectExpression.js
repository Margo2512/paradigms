"use strict";

function Const(a) {
    this.evaluate = () => a;
    this.toString = () => a.toString();
    this.prefix = () => a.toString();
}

function Variable(a) {
    this.a = a;
    this.index = { x: 0, y: 1, z: 2 }[a];
    this.evaluate = (x, y, z) => [x, y, z][this.index];
    this.toString = () => this.a.toString();
    this.prefix = () => this.a.toString();
}

const OPERATIONS = {};

const Operations = function (action, operand, oper) {
    const Operation = function (...args) {
        this.operands = args;
        this.operSign = operand;
    };

    OPERATIONS[operand] = Operation;
    Operation.oper = oper;

    Operation.prototype.evaluate = function (...args) {
        return action(...this.operands.map((arg) => arg.evaluate(...args)));
    };

    Operation.prototype.toString = function () {
        return this.operands.map((oper) => oper.toString()).join(" ") + " " + this.operSign;
    };

    Operation.prototype.prefix = function() {
        const operands = this.operands.map(operand => operand.prefix());
        return "(" + this.operSign + " " + operands.join(" ") + ")";
    };

    return Operation;
};

const Add = Operations((a, b) => a + b, "+", 2);
const Subtract = Operations((a, b) => a - b, "-", 2);
const Multiply = Operations((a, b) => a * b, "*", 2);
const Divide = Operations((a, b) => a / b, "/", 2);
const Negate = Operations((a) => -a, "negate", 1);
const Exp = Operations(Math.exp, "exp", 1);
const Ln = Operations(Math.log, "ln", 1);
const ArcTan = Operations(Math.atan, "atan", 1);
const ArcTan2 = Operations(Math.atan2, "atan2", 2);
const Sum = Operations((...args) => args.reduce((a, b) => a + b, 0), "sum", -1);
const Avg = Operations((...args) => args.reduce((a, b) => a + b) / args.length, "avg", -1);


const VARIABLE = {
    x: new Variable("x"),
    y: new Variable("y"),
    z: new Variable("z"),
};

const parse = expression => {
    const data = expression.trim().split(/\s+/);
    const stack = [];
    for (const i of data) {
        (i in VARIABLE) ? stack.push(new Variable(i))
            : (i in OPERATIONS) ? stack.push(new OPERATIONS[i](...stack.splice(-OPERATIONS[i].oper)))
            : stack.push(new Const(parseFloat(i)));
    }
    return stack[0];
};

const parsePrefix = expression => {
    if (expression.trim() === "") {
        throw new Error("The mas is empty");
    }
    const data = expression.replace(/([()])/g, " $& ").trim().split(/\s+/);
    const stack = [];
    const prefix = () => {
        const symbol = data.shift();
        if (symbol === "(") {
            const oper = data.shift();
            if (oper in OPERATIONS) {
                const args = [];
                while (data[0] !== ")") {
                    args.push(prefix());
                }
                data.shift();
                if (OPERATIONS[oper].oper !== -1 && args.length !== OPERATIONS[oper].oper) {
                    throw new Error("The problem with the number of arguments");
                }
                return stack.push(new OPERATIONS[oper](...args)) && stack[stack.length - 1];
            } else {
                throw new Error("Having problems with oper");
            }
        } else if (symbol in VARIABLE) {
            return stack.push(VARIABLE[symbol]) && stack[stack.length - 1];
        } else {
            if (isNaN(symbol)) {
                throw new Error("Symbol failed to check for null");
            }
            return stack.push(new Const(parseFloat(symbol))) && stack[stack.length - 1];
        }
    };
    prefix();
    if (data.length > 0) {
        throw new Error("Not all data has been processed");
    }
    return stack.pop();

};

