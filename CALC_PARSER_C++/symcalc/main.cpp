#include "cmdline.hpp" // parse_command
#include "app.hpp" // handle_expr_line
#include <iostream>
#include <vector>
using namespace std;

int main(int argc, char *argv[]){
    vector<Commands::Command> cmd;
    string str;
    int i=1;
    while (true){
        if(i<argc){
               mapbox::util::variant<Commands::Print, Commands::Derive, Commands::Evaluate, Commands::Simplify> command = parse_command(argv[i]);
               cmd.push_back(command);
               i++;
        }else{
            break;
        }
    }
    while (getline(std::cin, str)) {
            handle_expr_line(cout, str, cmd);
    }

}
