//
// Created by S!dy on 12/10/19.
//

#include <cstddef>
#include <vector>
#include <string>
#include <iterator>
#include <iosfwd>

#ifndef STRIAANT_AUTOCORRECT_HPP
#define STRIAANT_AUTOCORRECT_HPP
using namespace std;

#endif //STRIAANT_AUTOCORRECT_HPP

static const size_t num_chars=128;
struct trie_node{
    trie_node* children[num_chars]={};
    trie_node* parent= nullptr;
    char payload=0;
    bool is_terminal=false;
};

class trie
{
public:
    class const_iterator {
        const trie_node* current_node = nullptr;
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


    trie(const vector<string>& strings);

    trie();
    ~trie();

    bool erase(const string& str);

    bool insert(const string& str);

    bool contains(const string& str) const;

    size_t size() const;

    bool empty() const;

    vector<string> search_by_prefix(const string& prefix) const;

    vector<string> get_prefixes(const string& str) const;

    const_iterator begin() const;
    const_iterator end() const;


private:
    //! ukazatel na kořenový uzel stromu
    trie_node* m_root = nullptr;

    //! počet unikátních slov, které trie obsahuje
    size_t m_size = 0;

};
trie createTrieFromDictionary();
void start();




