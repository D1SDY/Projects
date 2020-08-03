#include "expr.hpp"
#include "cmdline.hpp"

#include <iostream>

using namespace std;

void process_expr(std::ostream &os, expr initial_expr, vector<Commands::Command> const &commands) {
    expr e = std::move(initial_expr);
    for (const auto &cmd : commands) {
        using namespace Commands;
        cmd.match(
                [&](Commands::Derive const &derive) {
                    throw std::logic_error("not implemented yet");
                },
                [&](Commands::Simplify const &) {
                    throw std::logic_error("not implemented yet");
                },
                [&](Commands::Evaluate const &evaluate) {
                    throw std::logic_error("not implemented yet");
                },
                [&](Commands::Print const &p) {
                    os << e << endl;
                }
        );
    }
}

void handle_expr_line(std::ostream &os, std::string const &line, vector<Commands::Command> const &commands) {
    expr temp = create_expression_tree(line);
    process_expr(os, temp, commands);
}
