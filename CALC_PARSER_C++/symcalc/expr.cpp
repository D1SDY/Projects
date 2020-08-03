#include "expr.hpp"
#include "expr_impl.hpp"
#include "tokenizer.hpp"
#include <stdexcept>
#include <stack>
#include <sstream>

using namespace std;


const expr expr::ZERO = expr::number(0.0);
const expr expr::ONE = expr::number(1.0);

struct node {
    node(Token token) : token(token) {}

    Token token;
    node *left = nullptr;
    node *right = nullptr;
    node *parent = nullptr;
    bool func() const;
};

// TODO: overloaded operators +, -, *, /, functions pow, log, sin, cos,
//       expr::number, expr::variable, operator==, operator<<,
//       create_expression_tree
stack<Token> parse(std::string input) {
    istringstream stream(input);
    Tokenizer tokenizer = Tokenizer(stream);
    stack<Token> queue;
    stack<Token> stackOfOperators;
    while (true) {
        Token nextToken = tokenizer.next();
        if (nextToken == Token(TokenId::End)) {
            break;
        }
        if (nextToken.id == TokenId::Number) {
            queue.push(nextToken);
        }
        if (nextToken.id == TokenId::Identifier) {
            stackOfOperators.push(nextToken);
        }
        if (nextToken.id == TokenId::Plus || nextToken.id == TokenId::Minus || nextToken.id == TokenId::Multiply ||
            nextToken.id == TokenId::Divide || nextToken.id == TokenId::Power) {
            while (true) {
                if (stackOfOperators.empty() != true && stackOfOperators.top().id != TokenId::LParen && (
                        stackOfOperators.top().id == TokenId::Identifier || (
                                (stackOfOperators.top().op_precedence() == nextToken.op_precedence() &&
                                 stackOfOperators.top().associativity() == Associativity::Left &&
                                 stackOfOperators.top().is_binary_op()) || stackOfOperators.top().is_binary_op() &&
                                                                           stackOfOperators.top().op_precedence() >
                                                                           nextToken.op_precedence()))) {
                    queue.push(stackOfOperators.top());
                    stackOfOperators.pop();
                } else {
                    break;
                }
            }
            stackOfOperators.push(nextToken);
        }
        if (nextToken.id == TokenId::LParen) {
            stackOfOperators.push(nextToken);
        }
        if (nextToken.id == TokenId::RParen) {
            while (true) {
                if (stackOfOperators.empty() == false && stackOfOperators.top().id != TokenId::LParen) {
                    queue.push(stackOfOperators.top());
                    stackOfOperators.pop();
                } else {
                    break;
                }
            }
            if (stackOfOperators.empty()) {
                throw parse_error("invalid syntax");
            }
            stackOfOperators.pop();
        }
    }
    while (true) {
        if (stackOfOperators.empty()) {
            break;
        }
        Token top = stackOfOperators.top();
        if (top.id == TokenId::LParen || top.id == TokenId::RParen) {
            throw parse_error("invalid syntax");
        }
        queue.push(stackOfOperators.top());
        stackOfOperators.pop();
    }
    if (queue.empty()) {
        throw parse_error("invalid syntax");
    }
    return queue;
}

bool node::func() const {
    if (token.is_binary_op()) {
        return false;
    }
    if (token.id == TokenId::Number) {
        return false;
    }
    return (token.identifier == "log" || token.identifier == "sin" || token.identifier == "cos");
}

void deleteTrie(node *temp) {
    if (temp) {
        stack<node *> nodes;
        nodes.push(temp);

        while (!nodes.empty()) {
            auto current = nodes.top();
            nodes.pop();
            if (current->left) {
                nodes.push(current->left);
            }
            if (current->right) {
                nodes.push(current->right);
            }
            delete current;
        }
    }
}

expr BuildExprTrie(node *root) {
    if (root == nullptr) return shared_ptr<expr_base>();
    expr left = BuildExprTrie(root->left);
    expr right = BuildExprTrie(root->right);
    Token token = root->token;
    expr expression;
    if (token.is_binary_op()) {
        if (token.id == TokenId::Plus) {
            expression = make_shared<exprs::expr_plus>(left, right);
        } else if (token.id == TokenId::Minus) {
            expression = make_shared<exprs::expr_minus>(left, right);
        } else if (token.id == TokenId::Multiply) {
            expression = make_shared<exprs::expr_multiply>(left, right);
        } else if (token.id == TokenId::Divide) {
            expression = make_shared<exprs::expr_divide>(left, right);
        } else if (token.id == TokenId::Power) {
            expression = make_shared<exprs::expr_pow>(left, right);
        } else {
            throw logic_error("undefined operator");
        }
    } else if (token.id == TokenId::Number) {
        expression = make_shared<exprs::number>(token.number);
    } else if (token.id == TokenId::Identifier && token.identifier == "sin") {
        expression = make_shared<exprs::expr_sin>(left);
    } else if (token.id == TokenId::Identifier && token.identifier == "cos") {
        expression = make_shared<exprs::expr_cos>(left);
    } else if (token.id == TokenId::Identifier && token.identifier == "log") {
        expression = make_shared<exprs::expr_log>(left);
    } else {
        expression = make_shared<exprs::variable>(token.identifier);
    }
    return expression;
}

expr create_expression_tree(const string &expression) {
    stack<Token> ArgumentsQueue = parse(expression);
    node *m_root = new node(ArgumentsQueue.top());
    ArgumentsQueue.pop();
    node *current = m_root;

    while (true) {
        if (ArgumentsQueue.empty() == true) {
            break;
        }
        while (true) {
            if (current != nullptr ) {
                if(current->left != nullptr){
                    current = current->parent;
                }else{
                    break;
                }
            } else {
                break;
            }
        }
        if (current == nullptr) {
            deleteTrie(m_root);
            throw parse_error("invalid syntax");
        }
        if(m_root->left== nullptr){
            if(m_root->token.is_binary_op()==false){
                if(m_root->func()==false){
                    deleteTrie(m_root);
                    throw parse_error("invalid syntax");
                }
            }
        }
        node *temp = new node(ArgumentsQueue.top());
        if (current != nullptr && current->right == nullptr && current->func() == false) {
            current->right = temp;
            temp->parent = current;
        } else if (current != nullptr && current->left == nullptr) {
            current->left = temp;
            temp->parent = current;
        }
        if (temp->token.is_binary_op() || temp->func()) {
            current = temp;
        }

        ArgumentsQueue.pop();
    }

    if (m_root->token.is_binary_op()) {
        if (m_root->left == nullptr || m_root->right == nullptr) {
            deleteTrie(m_root);
            throw parse_error("invalid syntax");
        }
    }
    if (m_root->func()) {
        if (m_root->left == nullptr) {
            deleteTrie(m_root);
            throw parse_error("invalid syntax");
        }
    }
    expr result = BuildExprTrie(m_root);
    deleteTrie(m_root);

    return result;
}
expr expr::number(double n) {
    return make_shared<exprs::number>(exprs::number(n));
}

expr expr::variable(string name) {
    return make_shared<exprs::variable>(exprs::variable(name));
}

expr operator+(expr a, expr b) {
    return make_shared<exprs::expr_plus>(exprs::expr_plus(a, b));
}

expr operator-(expr a, expr b) {
    return make_shared<exprs::expr_minus>(exprs::expr_minus(a, b));
}

expr operator*(expr a, expr b) {
    return make_shared<exprs::expr_multiply>(exprs::expr_multiply(a, b));
}

expr pow(expr m, expr e) {
    return make_shared<exprs::expr_pow>(exprs::expr_pow(m, e));
}

expr operator/(expr a, expr b) {
    return make_shared<exprs::expr_divide>(exprs::expr_divide(a, b));
}

expr sin(expr e) {
    return make_shared<exprs::expr_sin>(exprs::expr_sin(e));
}

expr cos(expr e) {
    return make_shared<exprs::expr_cos>(exprs::expr_cos(e));
}

expr log(expr e) {
    return make_shared<exprs::expr_log>(exprs::expr_log(e));
}

bool operator==(const expr &a, const expr &b) {
    if (a->equals(b->shared_from_this().operator*()) == true) {
        return true;
    } else {
        return false;
    }
}

ostream &operator<<(std::ostream &os, const expr &e) {
    e->write(os, expr::WriteFormat::Prefix);
    return os;
}

ostream &operator<<(std::ostream &os, const fmt_expr &fmt) {
    fmt.e->write(os, fmt.fmt);
    return os;
}

