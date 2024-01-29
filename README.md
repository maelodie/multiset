# Multiset

The standard Java library collections offer numerous container interfaces and multiple implementations for each interface. In this project, we will construct a new type of collection: multisets. The next phase in this project will revisit this collection and involve developing tests.


A multiset is an unordered list of elements. Unlike a classic set, an element can appear multiple times in a multiset, and it is important to keep track of the number of occurrences. An interesting application of multisets that we will explore in this project is counting the frequency of word occurrences in a text to determine the most frequent words.


Several implementations of multisets are possible. A naive implementation would be to use an implementation of the List interface (such as ArrayList), but these do not have good complexity for our application (word counting). Instead, we will use an implementation of the Map interface, which associates each element with its number of occurrences. Note that we cannot use Set, as it does not allow an element to appear multiple times.


Our multiset is polymorphic. Similar to Java collections, we will use generics. Therefore, we will write MultiSet<T>.
