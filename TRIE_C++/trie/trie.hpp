#pragma once

#include <vector>
#include <string>
#include <iterator>
#include <iosfwd>
using namespace std;

// Předpokládáme pouze základní ASCII znaky
static const size_t num_chars = 128;

struct trie_node {
    trie_node* children[num_chars] = {}; //!< pole ukazatelů na přímé následovníky daného uzlu
    trie_node* parent = nullptr;         //!< ukazatel na přímého předka daného uzlu
    char payload = 0;                    //!< znak, který tento uzel reprezentuje
    bool is_terminal = false;            //!< označuje, jestli v tomto uzlu končí slovo
};

class trie
{
public:
    class const_iterator {
        const trie_node* current_node = nullptr;
        friend ostream& operator<<(ostream &os, trie::const_iterator const &i);
    public:
        using iterator_category = forward_iterator_tag;
        using value_type = string;
        using reference = string;
        using pointer = string;
        using difference_type = ptrdiff_t;

        const_iterator() = default;
        const_iterator(const trie_node* node);

        const_iterator& operator++();
        const_iterator operator++(int);

        reference operator*() const;
        bool operator==(const const_iterator& rhs) const;
        bool operator!=(const const_iterator& rhs) const;
    };

    /**
     * Vytvoří trii obsahující všechny řetězce z daného vektoru
     */
    trie(const vector<string>& strings);

    trie();
    trie(const trie& rhs);
    trie& operator=(const trie& rhs);
    trie(trie&& rhs);
    trie& operator=(trie&& rhs);
    ~trie();

    /**
     * Odstraní daný řetězec z trie.
     * Vrátí true, pokud byl řetězec odstraněn (byl předtím v trii), jinak false.
     */
    bool erase(const string& str);

    /**
     * Vloží řetězec do trie.
     * Vrátí true, pokud byl řetězec vložen (nebyl předtím v trii), jinak false.
     */
    bool insert(const string& str);

    /**
     * Vrátí true, pokud je řetězec v trii, jinak false.
     */
    bool contains(const string& str) const;

    /**
     * Vrátí počet unikátních řetězců v trii.
     */
    size_t size() const;

    /**
     * Vrací `true` pokud v listu nejsou žádné prvky.
     */
    bool empty() const;

    /**
     * Vrátí všechny prvky trie, které začínají daným prefixem.
     *
     * Prefixy jsou inkluzivní, tj. search_by_prefix("abc") vrátí mezi výsledky
     * i "abc", pokud je "abc" v trii.
     */
    vector<string> search_by_prefix(const string& prefix) const;

    /**
     * Vrátí všechny řetězce z trie, jež jsou prefixem daného řetězce.
     *
     * Prefixy jsou inkluzivní, tj. get_prefixes("abc") vrátí mezi výsledky
     * i "abc", pokud je "abc" v trii.
     */
    vector<string> get_prefixes(const string& str) const;

    const_iterator begin() const;
    const_iterator end() const;



    /**
     * Prohodí všechny prvky mezi touto trií a `rhs`.
     *
     * Složitost: O(1)
     */
    void swap(trie& rhs);

	// Relační operátory

    /**
     * Vrací `true` pokud je trie `rhs` roven této.
     *
     * Dvě trie si jsou rovny, pokud obsahují stejné prvky.
     */
	bool operator==(const trie& rhs) const;

     /**
      * Vrací `true` pokud je tato trie menší než `rhs`.
      *
      * Trie jsou porovnávány tak, že všechny řetězce v lexikografickém pořadí
      * se porovnají lexikograficky.
      * To znamená, že ["abc"] < ["abc", "b"], ["aac", "b"] < ["abc", "b"].
      */
	bool operator<(const trie& rhs) const;

    /**
     * Vrátí novou trii, která obsahuje průnik této a `rhs`, tj. řežezce
     * přítomné v obou.
     *
     * Složitost: Implementace by neměla zbytečně procházet prvky, o kterých už ví,
     *            že v druhé trii být nemohou (např. ["aa", "ab", "ac"...], ["x"]).
     */
    trie operator&(trie const& rhs) const;

    /**
     * Vrátí novou trii, která obsahuje sjednocení této a `rhs`, tj. řežezce
     * přítomné alespoň v jedné z nich.
     */
    trie operator|(trie const& rhs) const;

private:
    //! ukazatel na kořenový uzel stromu
    trie_node* m_root = nullptr;

    //! počet unikátních slov, které trie obsahuje
    size_t m_size = 0;

};

//! 2 trie jsou si nerovné právě tehdy, když si nejsou rovné (viz operator==)
bool operator!=(const trie& lhs, const trie& rhs);
//! Lexicografické uspořádání, viz operator<
bool operator<=(const trie& lhs, const trie& rhs);
//! Lexicografické uspořádání, viz operator<
bool operator>(const trie& lhs, const trie& rhs);
//! Lexicografické uspořádání, viz operator<
bool operator>=(const trie& lhs, const trie& rhs);

/**
 * ADL customization point pro swap.
 * Výsledek `swap(lhs, rhs);` by měl být ekvivalentní s výsledkem
 * `lhs.swap(rhs);`
 */
void swap(trie& lhs, trie& rhs);


/**
 * Operátor výpisu do proudu.
 *
 * Tuto funkci netestujeme, ale pokud ji vhodně implementujete, budete mít
 * ve výstupech z testů užitěčně vypsaný obsah trie.
 */
ostream& operator<<(ostream& out, trie const& trie);
