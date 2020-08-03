#pragma once

#include "expr.hpp"
#include <iosfwd>
using namespace std;

namespace exprs {
    class number: public expr_base{
        friend class expr;

    private:
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        void write(ostream &out, WriteFormat fmt) const override;
        bool equals(const expr_base &variable) const  override;
        double num;
    public:
        number(double);
    };
    class variable:public expr_base{
        friend class expr;
    public:
        string name;

        variable(string);

    private:
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;
    };
    class expr_plus:public expr_base{
        friend class expr;
    private:
        expr first,second;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;

    public:
        expr_plus(const expr &,const expr &);
    };
    class expr_minus: public expr_base{
        friend class expr;
    private:
        expr first;
        expr second;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;

    public:
        expr_minus(const expr &,const expr &);
    };
    class expr_multiply: public expr_base{
        friend class expr;
    private:
        expr first;
        expr second;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;

    public:
        expr_multiply(const expr &, const expr &);
    };
    class expr_divide: public expr_base{
        friend class expr;
    private:
        expr first;
        expr second;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;

    public:
        expr_divide(const expr &, const expr &);
    };
    class expr_pow: public expr_base{
        friend class expr;
    private:
        expr first;
        expr second;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;

    public:
        expr_pow(const expr&, const expr&);
    };
    class expr_sin:public expr_base{
        friend class expr;
    private:
        expr first;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;

    public:
        expr_sin(const expr&);
    };
    class expr_cos:public expr_base{
        friend class expr;
    private:
        expr first;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out,WriteFormat fmt) const override;

    public:
        expr_cos(const expr&);
    };
    class expr_log:public expr_base {
    private:
        expr first;
        double evaluate(const variable_map_t &variables) const override;
        expr derive(string const &temp) const override;
        expr simplify() const override;
        bool equals(const expr_base &variable) const override;
        void write(ostream &out, WriteFormat fmt) const override;

    public:
        expr_log(const expr &);
    };
}
