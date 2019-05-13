package com.example.zad1


//15
enum class Operation {
    SIMPLIFY {
        override fun toString(): String {
            return "simplify"
        }
    },
    FACTOR {
        override fun toString(): String {
            return "factor"
        }
    },
    DERIVE {
        override fun toString(): String {
            return "derive"
        }
    },
    INTEGRATE {
        override fun toString(): String {
            return "integrate"
        }
    },
    ZEROES {
        override fun toString(): String {
            return "zeroes"
        }
    },
    TANGENT {
        override fun toString(): String {
            return "tangent"
        }
    },
    AREA {
        override fun toString(): String {
            return "area"
        }
    },
    COS {
        override fun toString(): String {
            return "cos"
        }
    },
    SIN {
        override fun toString(): String {
            return "sin"
        }
    },
    TAN {
        override fun toString(): String {
            return "tan"
        }
    },
    ARCCOS {
        override fun toString(): String {
            return "arccos"
        }
    },
    ARCSIN {
        override fun toString(): String {
            return "arcsin"
        }
    },
    ARCTAN {
        override fun toString(): String {
            return "arctan"
        }
    },
    ABS {
        override fun toString(): String {
            return "abs"
        }
    },
    LOG {
        override fun toString(): String {
            return "log"
        }
    }
}