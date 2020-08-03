//
// Created by S!dy on 12/10/19.
//



#include <algorithm>
#include <stack>
#include <iostream>
#include <map>
#include <chrono>
#include <thread>
#include <mutex>


#include "autocorrect.hpp"

using namespace std;
mutex g_lock;
int Min = 10000;
int counter = 0;
bool findAllPrefixes(trie_node *pNode, const string &str) {
    if (str.size() == 0) {
        return false;
    }
    if (str.size() == 1) {
        for (int i = 0; i < 128; i++) {
            if (pNode->children[i]) {
                if ((pNode->children[i]->payload == str[0]) && pNode->children[i]->is_terminal) {
                    return true;
                }
            }
        }
        return false;
    } else {
        for (int i = 0; i < 128; i++) {
            if (pNode->children[i]) {
                if (pNode->children[i]->payload == str[0]) {
                    return findAllPrefixes(pNode->children[i], str.substr(1, str.size() - 1));
                }
            }
        }
        return false;
    }
}
template <typename TimePoint>
std::chrono::milliseconds to_ms(TimePoint tp) {
    return std::chrono::duration_cast<std::chrono::milliseconds>(tp);
}

vector<string> gelAllWords(vector<string> vector, trie_node *pNode, string basicString) {
    if (pNode->is_terminal) {
        vector.push_back(basicString);
    }
    for (int i = 0; i < 128; i++) {
        if (pNode->children[i] != nullptr) {
            trie_node *current = pNode->children[i];
            basicString.push_back(current->payload);
            vector = gelAllWords(vector, current, basicString);
            basicString.pop_back();
        }
    }

    return vector;
}

trie::trie(const vector<string> &strings) {
    m_root = new trie_node;
    int len = static_cast<int>(strings.size());
    for (int i = 0; i < len; i++) {
        insert(strings[i]);
    }
}

trie::trie() {
    m_root = new trie_node;
    m_size = 0;
}

trie::~trie() {
    if (m_root) {
        stack<trie_node *> nodes;
        nodes.push(m_root);

        while (!nodes.empty()) {
            auto current = nodes.top();
            nodes.pop();
            for (int i = 0; i < 128; i++) {
                if (current->children[i]) {
                    nodes.push(current->children[i]);
                }
            }
            delete current;
        }

    }
    m_root = nullptr;
    m_size = 0;
}

bool trie::erase(const string &str) {
    if (contains(str)) {
        int len = str.length();
        trie_node *current = m_root;
        if (len < 1) {
            len = 1;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 128; j++) {
                if (current->children[j]) {
                    if (current->children[j]->payload == str[i]) {
                        current = current->children[j];
                        break;
                    }
                }
            }
        }
        if (current->is_terminal) {
            bool isEmpty = false;
            while (true) {
                for (int i = 0; i < 128; i++) {
                    if (current->children[i] != nullptr) {
                        isEmpty = false;
                        break;
                    } else {
                        isEmpty = true;
                    }
                }
                if (isEmpty) {
                    trie_node *child = current;
                    for (int i = 0; i < 128; i++) {
                        if (current->parent->children[i] == current) {
                            current = current->parent;
                            current->children[i] = nullptr;
                            break;
                        }
                    }
                    delete child;
                    if (current == m_root) {
                        break;
                    }
                } else {
                    current->is_terminal = false;
                    break;
                }
            }
        } else {
            current->is_terminal = false;
        }
        m_size--;
        return true;
    }

    return false;
}

bool trie::insert(const string &str) {
    if (contains(str)) {
        return false;
    } else {
        trie_node *current = m_root;
        int len = str.length();
        if (len < 1) {
            len = 1;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 128; j++) {
                if (current->children[j] != nullptr) {
                    if (current->children[j]->payload == str[i]) {
                        current = current->children[j];
                        break;
                    }
                } else {
                    trie_node *child = new trie_node;
                    if (len == 1) {
                        child->is_terminal = true;
                    } else {
                        child->is_terminal = false;
                    }
                    child->payload = str[i];
                    child->parent = current;
                    current->children[j] = child;
                    current = current->children[j];
                    break;
                }
            }
        }
        current->is_terminal = true;
        m_size++;
        return true;
    }

}

bool trie::contains(const string &str) const {
    if (m_size == 0) {
        return false;
    } else {
        trie_node *current = m_root;
        int len = str.length();
        if (len < 1) {
            len = 1;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 128; j++) {
                if (current->children[j]) {
                    if (len == 1) {
                        if (current->children[j]->payload == str[i] && current->children[j]->is_terminal) {
                            current = current->children[j];
                            break;
                        }
                    } else {
                        if (current->children[j]->payload == str[i]) {
                            current = current->children[j];
                            break;
                        }
                    }
                } else {
                    return false;//stange return check the alg
                }
            }
        }
        return current->is_terminal;
    }
}

size_t trie::size() const {
    return m_size;
}

bool trie::empty() const {
    return m_size == 0;
}

vector<string> trie::search_by_prefix(const string &prefix) const {
    vector<string> words = {};
    string word = "";
    bool isFound = false;
    int i = 0;
    trie_node *current = m_root;

    while (prefix[i] != '\0') {
        for (int j = 0; j < 128; j++) {
            if (current->children[j]) {
                if (current->children[j]->payload == prefix[i]) {
                    current = current->children[j];
                    word.push_back(prefix[i]);
                    isFound = true;
                    break;
                }
            }
        }
        if (isFound == true) {
            isFound = false;
            i++;
        } else {
            return words;
        }
    }
    return gelAllWords(words, current, word);
}

vector<string> trie::get_prefixes(const string &str) const {
    vector<string> prefixes;
    for (int i = str.size(); i > 0; i--) {
        if (findAllPrefixes(m_root, str.substr(0, i))) {
            prefixes.push_back(str.substr(0, i));
        }
    }

    return prefixes;

}

trie::const_iterator trie::begin() const {
    trie_node *currnode = m_root;

    while (true) {
        if (currnode->children[0]) {
            currnode = currnode->children[0];
        }
        if (currnode->is_terminal == true) {
            break;
        }
    }
    return currnode;

}

trie::const_iterator trie::end() const {
    return nullptr;
}

trie createTrieFromDictionary() {

    freopen("dict.txt", "rb", stdin);
    string line;
    vector<string> strings;
    while (getline(std::cin, line)) {
        strings.push_back(line);
    }
    trie trie(strings);
    return trie;
}

trie::const_iterator::const_iterator(const trie_node *node) {
    current_node = node;
}

trie::const_iterator &trie::const_iterator::operator++() {
    const_iterator temp = *this;
    const_iterator parent = temp.current_node->parent;
    const_iterator root = temp.current_node;
    while (root.current_node->parent != nullptr) {
        root.current_node = root.current_node->parent;
    }

    int position = 0;
    for (int j = 0; j < 128; j++) {
        if (temp.current_node->children[j]) {
            while (true) {
                if (temp.current_node->children[0]) {
                    temp.current_node = temp.current_node->children[0];
                    if (temp.current_node->is_terminal == true) {
                        return *this = temp;
                    }
                } else {
                    break;
                }
            }
        } else {
            for (int i = 0; i < 128; i++) {
                if (parent.current_node->children[i]) {
                    if (parent.current_node->children[i]->payload == temp.current_node->payload) {
                        position = i;
                        break;
                    }
                }
            }
            if (temp.current_node->parent != root.current_node) {
                while (!temp.current_node->parent->children[position + 1]) {
                    temp.current_node = temp.current_node->parent;
                    for (int i = 0; i < 128; i++) {
                        if (temp.current_node->parent->children[i]) {
                            if (temp.current_node->parent->children[i]->payload == temp.current_node->payload) {
                                position = i;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (temp.current_node->parent == root.current_node) {
                        break;
                    }
                }
            }
            char chr = temp.current_node->payload;
            for (int i = 0; i < 128; i++) {
                if (temp.current_node->parent->children[i]) {
                    if (temp.current_node->parent->children[i]->payload == chr) {
                        position = i;
                        break;
                    }
                }
            }
            if (temp.current_node->parent->children[position + 1]) {
                temp.current_node = temp.current_node->parent->children[position + 1];
                while (!temp.current_node->is_terminal) {
                    temp.current_node = temp.current_node->children[0];
                }
            } else {
                temp = nullptr;
            }


        }
        return *this = temp;

    }
}

trie::const_iterator trie::const_iterator::operator++(int) {
    operator++();
    return *this;
}

trie::const_iterator::reference trie::const_iterator::operator*() const {
    const_iterator temp = *this;
    string str;
    while (temp.current_node->parent) {
        if (temp.current_node->payload == '\0') {

        } else {
            str.push_back(temp.current_node->payload);
        }
        temp.current_node = temp.current_node->parent;
    }

    reverse(str.begin(), str.end());
    return str;
}

bool trie::const_iterator::operator==(const trie::const_iterator &rhs) const {
    const_iterator temp = *this;
    if (temp.current_node == rhs.current_node) {
        return true;
    }
    return false;
}

bool trie::const_iterator::operator!=(const trie::const_iterator &rhs) const {
    const_iterator temp = *this;
    if (temp.current_node == rhs.current_node) {
        return false;
    }
    return true;
}

template<typename T>
typename T::size_type LevenshteinDistance(const T &source,
                                          const T &target) {
    if (source.size() > target.size()) {
        return LevenshteinDistance(target, source);
    }

    using TSizeType = typename T::size_type;
    const TSizeType min_size = source.size(), max_size = target.size();
    std::vector<TSizeType> lev_dist(min_size + 1);

    for (TSizeType i = 0; i <= min_size; ++i) {
        lev_dist[i] = i;
    }

    for (TSizeType j = 1; j <= max_size; ++j) {
        TSizeType previous_diagonal = lev_dist[0], previous_diagonal_save;
        ++lev_dist[0];

        for (TSizeType i = 1; i <= min_size; ++i) {
            previous_diagonal_save = lev_dist[i];
            if (source[i - 1] == target[j - 1]) {
                lev_dist[i] = previous_diagonal;
            } else {
                lev_dist[i] = std::min(std::min(lev_dist[i - 1], lev_dist[i]), previous_diagonal) + 1;
            }
            previous_diagonal = previous_diagonal_save;
        }
    }

    return lev_dist[min_size];
}

void threadFunc(vector<int> levDist) {
    for (int i = 0; i < levDist.size(); i++) {
        if (levDist[i] < Min) {
            Min = levDist[i];
        }
    }
}


void multiThread() {
    string temp;
    cin >> temp;
    cout << "input was: " << temp << endl;
    auto start = std::chrono::high_resolution_clock::now();
    trie trie = createTrieFromDictionary();
    vector<int> levDist;
    if (trie.contains(temp) == false) {
        cout << "word was typed wrong" << endl;
        auto it = trie.begin();
        while (it != trie.end()) {
            levDist.push_back(LevenshteinDistance(temp, *it));
            it++;
        }
        map<string, int> final;
        it = trie.begin();
        thread thr1(threadFunc, levDist);
        for (int i = 0; i < levDist.size(); i++) {
            final.insert(pair<string, int>(*it, levDist[i]));
            it++;
        }
        cout << "possible variants:" << endl;
        thr1.join();
        for (auto it2 = final.begin(); it2 != final.end(); ++it2) {
            if (it2->second == Min) {
                cout << "                   " << it2->first << endl;
            }
        }


    } else {
        cout << "word was typed correctly" << endl;
    }
    auto end = std::chrono::high_resolution_clock::now();
    std::cout << "Needed " << to_ms(end - start).count() << " ms to finish.\n";
}

void oneThread() {
    string temp;
    cin>>temp;
    cout << "input was: " << temp << endl;
    auto start = std::chrono::high_resolution_clock::now();
    trie trie = createTrieFromDictionary();
    if (trie.contains(temp) == false) {
        cout << "word was typed wrong" << endl;
        auto it = trie.begin();
        vector<int> levDist;
        while (it != trie.end()) {
            levDist.push_back(LevenshteinDistance(temp, *it));
            it++;
        }
        map<string, int> final;
        it = trie.begin();
        for (int i = 0; i < levDist.size(); i++) {
            final.insert(pair<string, int>(*it, levDist[i]));
            it++;
        }
        int min = 10000;
        for (int i = 0; i < levDist.size(); i++) {
            if (levDist[i] < min) {
                min = levDist[i];
            }
        }
        cout << "possible variants:" << endl;
        for (auto it2 = final.begin(); it2 != final.end(); ++it2) {
            if (it2->second == min) {
                cout << "                   " << it2->first << endl;
            }
        }
    } else {
        cout << "word was typed corectly " << endl;
    }
    auto end = std::chrono::high_resolution_clock::now();
    std::cout << "Needed " << to_ms(end - start).count() << " ms to finish.\n";
}

void start() {

    string str;
    string option;
    bool isMultiThread = false;
    if(counter==0){
        cout << "Hello, this this is mine implementation of T9" << endl;
        cout << "to start program type --start to show avaible commands type --help" << endl;
    }
    while (true) {
        cin >> str;
        if (str == "--start") {
            if (counter == 0) {
                cout << "started" << endl;
                cout << "Choose options for program" << endl;
            }
            cout << "For for work with one thread type '--one', for work with many - '--multi' " << endl;
            while (true) {
                cin >> option;
                if (option == "--one") {
                    break;
                } else if (option == "--multi") {
                    isMultiThread = true;
                    break;
                } else if (option == "--q") {
                    cout << "stop" << endl;
                    return;
                } else {
                    cout << "Unexpected input" << endl;
                }
            }
            if (isMultiThread) {
                cout << "program will work multi thread" << endl;
                cout << "type your word" << endl;
                multiThread();
                break;
            } else {
                cout << "program will work with one thread" << endl;
                cout << "type your word" << endl;
                oneThread();
                break;
            }
            counter++;
        } else if (str == "--help") {
            cout << "Print --start to start" << endl;
            cout << "Print --help to show allowed commands" << endl;
            cout << "Print --q to quit" << endl;
        } else if (str == "--q") {
            cout << "stop" << endl;
            break;
        } else {
            cout << "Unexpected input" << endl;
        }
    }


}
