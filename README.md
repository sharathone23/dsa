# Java Implementations of Fundamental Data Structures and Algorithms

> Diagrams are [Mermaid](https://mermaid.js.org/). They render on GitHub, in IntelliJ (enable Mermaid in *Settings → Languages & Frameworks → Markdown*), and in VS Code with the *Markdown Preview Mermaid Support* extension.

## Linear

### [LinkedList](linear/LinkedList.java)
Nodes point forward only. The `tail` reference makes `insertLast` O(1); `deleteLast` is still O(n) because we must walk to the node before `tail`.

```mermaid
flowchart LR
    H([head]) --> A[1] --> B[2] --> C[3] --> D[4] --> E[5] --> N((null))
    T([tail]) --> E
```

`reverse()` flips each `next` pointer using `prev / current / temp`:

```mermaid
flowchart RL
    H([head]) --> E[5] --> D[4] --> C[3] --> B[2] --> A[1] --> N((null))
    T([tail]) --> A
```

### [DoublyLinkedList](linear/DoublyLinkedList.java)
Each `DNode` has `prev` and `next` (plus an optional `key` used by the LRU cache). Both `deleteFirst` and `deleteLast` are O(1).

```mermaid
flowchart LR
    NL((null)) <-- prev --- A[1]
    A <--> B[2] <--> C[3] <--> D[4]
    D -- next --> NR((null))
    H([head]) --> A
    T([tail]) --> D
```

### [Stack](linear/Stack.java)
LIFO, built on `LinkedList`: `push` = `insertFirst`, `pop` = `deleteFirst`. The head is the top.

```mermaid
flowchart TB
    P[/"push(4)"/] -.-> TOP
    TOP["4  ← head / top"] --> S3[3] --> S2[2] --> S1[1]
    TOP -.-> POP[\"pop() returns 4"\]
```

### [Queue](linear/Queue.java)
FIFO, built on `LinkedList`: `enqueue` = `insertLast` (tail), `dequeue` = `deleteFirst` (head).

```mermaid
flowchart LR
    DQ[\"dequeue() → 1"\] -.- A
    A["1 ← head"] --> B[2] --> C[3] --> D["4 ← tail"]
    D -.- EQ[/"enqueue(5)"/]
```

### [Dequeue](linear/Dequeue.java)
Double-ended queue on a `DoublyLinkedList`, so removing from either end is O(1).

```mermaid
flowchart LR
    IF[/"insertFirst / removeFirst"/] <-.-> A
    A["1 ← head"] <--> B[2] <--> C["3 ← tail"]
    C <-.-> IL[/"insertLast / removeLast"/]
```

### [LRUCache](linear/LRUCache.java)
`HashMap<key, DNode>` gives O(1) lookup; the `DoublyLinkedList` keeps recency order. Head = most recently used, tail = next to evict.

```mermaid
flowchart LR
    subgraph MAP["HashMap&lt;Integer, DNode&gt;"]
        K3[key 3]
        K1[key 1]
    end
    subgraph DLL["DoublyLinkedList (capacity 2)"]
        N3["3 : 30<br/>head (MRU)"] <--> N1["1 : 10<br/>tail (LRU)"]
    end
    K3 --> N3
    K1 --> N1
```

`get(1)` unlinks node 1 and moves it to the front; `set(4, 40)` at capacity evicts the tail:

```mermaid
flowchart LR
    S0["[3, 1]"] -->|"get(1)"| S1["[1, 3]"] -->|"set(4,40)<br/>evict tail 3"| S2["[4, 1]"]
```

## Non Linear

### [Tree](nonlinear/Tree.java)
N-ary tree: every `TNode` holds a `List<TNode> children`. `search` is a DFS over all children.

```mermaid
flowchart TD
    R[1] --> A[2]
    R --> B[3]
    R --> C[4]
    A --> A1[5]
    A --> A2[6]
    C --> C1[7]
```

### [BinaryTree](nonlinear/BinaryTree.java)
At most two children (`left`, `right`), with an optional `parent` reference used for successor/predecessor.

```mermaid
flowchart TD
    N1[1] --> N2[2]
    N1 --> N3[3]
    N2 --> N4[4]
    N2 --> N5[5]
    N3 --> N6[6]
    N3 --> N7[7]
```

| Traversal | Order | Output for the tree above |
|---|---|---|
| In-order | Left → Root → Right | `4 2 5 1 6 3 7` |
| Pre-order | Root → Left → Right | `1 2 4 5 3 6 7` |
| Post-order | Left → Right → Root | `4 5 2 6 7 3 1` |
| Level order (BFS) | level by level | `1 2 3 4 5 6 7` |
| Spiral | alternate direction per level | `1 3 2 4 5 6 7` |
| Left view / Right view | first / last node per level | `1 2 4` / `1 3 7` |

LCA: `findLCA` returns the node where `n1` and `n2` split into different subtrees. `getLCA(4, 5) = 2`, `getLCA(4, 6) = 1`. Height = 3, diameter (in nodes) = 5.

### [BinarySearchTree](nonlinear/BinarySearchTree.java)
Invariant: `left < node < right`. Search, insert, and delete are O(h), where h is the tree's height. Highlighted: the path for `search(15)`.

```mermaid
flowchart TD
    N20[20] --> N10[10]
    N20 --> N30[30]
    N10 --> N5[5]
    N10 --> N15[15]
    N30 --> N25[25]
    N30 --> N35[35]
    classDef path fill:#ffd166,stroke:#b8860b,color:#000
    class N20,N10,N15 path
```

`delete` has three cases:

```mermaid
flowchart LR
    D{"node to delete"} -->|no children| L["just remove it"]
    D -->|one child| O["replace node with its child"]
    D -->|two children| T["copy in-order successor's value<br/>(min of right subtree)<br/>then delete the successor"]
```

In-order successor, with parent pointers: if a right subtree exists, take its leftmost node; otherwise walk up until you arrive from a left child. Successor of 15 = **20**, successor of 35 = **null**.

### [Trie](nonlinear/Trie.java)
Each `TrieNode` maps `Character → TrieNode`; `isWord` marks where an inserted word ends. Shown after inserting `cat`, `car`, `card`, `dog`:

```mermaid
flowchart TD
    ROOT(( )) --> C[c] --> A[a]
    A --> T["t ✔"]
    A --> R["r ✔"] --> D["d ✔"]
    ROOT --> D2[d] --> O[o] --> G["g ✔"]
    classDef word fill:#06d6a0,stroke:#047857,color:#000
    class T,R,D,G word
```

`search("ca")` → false (not `isWord`), `startsWith("ca")` → true.

### [BinaryHeap](nonlinear/BinaryHeap.java)
A complete binary tree stored in an array. For index `i`: parent `(i-1)/2`, left `2i+1`, right `2i+2`. Min-heap after inserting `10, 4, 9, 1, 7, 5, 3`:

```mermaid
flowchart TD
    I0["1<br/>[0]"] --> I1["4<br/>[1]"]
    I0 --> I2["3<br/>[2]"]
    I1 --> I3["10<br/>[3]"]
    I1 --> I4["7<br/>[4]"]
    I2 --> I5["9<br/>[5]"]
    I2 --> I6["5<br/>[6]"]
```

```mermaid
flowchart LR
    A0["[0] 1"] --- A1["[1] 4"] --- A2["[2] 3"] --- A3["[3] 10"] --- A4["[4] 7"] --- A5["[5] 9"] --- A6["[6] 5"]
```

- `insert` → put at the end, **heapifyUp** (swap with parent while smaller).
- `extractMinOrMax` → swap root with last, shrink, **heapifyDown** (swap with the smaller child).

### [PriorityQueue](nonlinear/PriorityQueue.java)
A thin wrapper over `BinaryHeap`: highest-priority element always at the root.

```mermaid
flowchart LR
    E[/"enqueue(x)"/] --> H["BinaryHeap.insert<br/>O(log n)"]
    H --> R(("root = min/max"))
    R --> P["peek() O(1)"]
    R --> DQ["dequeue() → extractMinOrMax<br/>O(log n)"]
```

### [Graph](nonlinear/Graph.java)
Adjacency list: `nodes` is a `List<GraphNode>`, each with `neighbours` and a `weightsMap`. Shared DFS/BFS traversal and BFS cycle detection (a visited neighbour that isn't your parent means a cycle).

```mermaid
flowchart LR
    subgraph nodes["List&lt;GraphNode&gt; nodes"]
        G1[1]
        G2[2]
        G3[3]
    end
    G1 -. neighbours .-> L1["[2, 3]"]
    G2 -. neighbours .-> L2["[1]"]
    G3 -. neighbours .-> L3["[1]"]
```

### [UndirectedGraph](nonlinear/UndirectedGraph.java)
`addEdge(v1, v2)` links both ways. `findShortestPath` is a BFS, so the distance = number of edges. From source **1**:

```mermaid
flowchart LR
    N1(("1<br/>d=0")) --- N2(("2<br/>d=1"))
    N1 --- N3(("3<br/>d=1"))
    N2 --- N4(("4<br/>d=2"))
    N3 --- N4
    N4 --- N5(("5<br/>d=3"))
```

BFS order: `1 2 3 4 5`.

### [DirectedGraph](nonlinear/DirectedGraph.java)
**Cycle detection**: DFS with a `recursionStack`; reaching a node already on the stack = back edge = cycle.

**Topological sort** (Kahn's algorithm): repeatedly take vertices with in-degree 0. For the DAG in `main`:

```mermaid
flowchart LR
    V5((5)) --> V2((2))
    V5 --> V0((0))
    V4((4)) --> V0
    V4 --> V1((1))
    V2 --> V3((3))
    V3 --> V1
```

Order produced: `5 4 2 0 3 1`.

**Shortest paths** on the weighted graph from `main` (source `0`). Dijkstra (non-negative weights, min-heap) and Bellman-Ford (relax every edge V−1 times, handles negative weights) agree:

```mermaid
flowchart LR
    W0(("0<br/>d=0")) -- 2 --> W1(("1<br/>d=2"))
    W0 -- 4 --> W2(("2<br/>d=3"))
    W1 -- 1 --> W2
    W1 -- 7 --> W3(("3<br/>d=8"))
    W2 -- 3 --> W4(("4<br/>d=6"))
    W4 -- 2 --> W3
    W4 -- 5 --> W5(("5<br/>d=9"))
    W3 -- 1 --> W5
```

Shortest path to 5: `0 → 1 → 2 → 4 → 3 → 5` = 2+1+3+2+1 = **9**.

## Sorting
Every example below sorts `[5, 1, 4, 2]` and follows the steps of that file's implementation.

### [BubbleSort](sorting/BubbleSort.java)
Swap adjacent out-of-order pairs; each pass bubbles the largest remaining value to the end. O(n²) time, O(1) space.

```mermaid
flowchart LR
    S0["5 1 4 2"] -->|"pass 1"| S1["1 4 2 <b>5</b>"] -->|"pass 2"| S2["1 2 <b>4 5</b>"] -->|"pass 3"| S3["<b>1 2 4 5</b>"]
```

### [SelectionSort](sorting/SelectionSort.java)
This version finds the **max** of the unsorted range and swaps it to the right end. O(n²) time, O(1) space.

```mermaid
flowchart LR
    S0["5 1 4 2"] -->|"max 5 → end"| S1["2 1 4 <b>5</b>"] -->|"max 4 in place"| S2["2 1 <b>4 5</b>"] -->|"max 2 → idx 1"| S3["<b>1 2 4 5</b>"]
```

### [InsertionSort](sorting/InsertionSort.java)
Take `key = input[i]` and shift larger elements right until key fits. O(n²) worst, O(n) on nearly sorted input.

```mermaid
flowchart LR
    S0["5 | 1 4 2"] -->|"key 1"| S1["1 5 | 4 2"] -->|"key 4"| S2["1 4 5 | 2"] -->|"key 2"| S3["1 2 4 5"]
```

### [MergeSort](sorting/MergeSort.java)
Divide in half until single elements, then merge sorted halves. O(n log n) time, O(n) space.

```mermaid
flowchart TD
    A["5 1 4 2"] --> B["5 1"]
    A --> C["4 2"]
    B --> B1["5"]
    B --> B2["1"]
    C --> C1["4"]
    C --> C2["2"]
    B1 --> M1["1 5"]
    B2 --> M1
    C1 --> M2["2 4"]
    C2 --> M2
    M1 --> R["1 2 4 5"]
    M2 --> R
```

### [QuickSort](sorting/QuickSort.java)
Lomuto partition: pivot = last element; everything `<= pivot` moves left of index `i`, then the pivot is swapped into `i`. Average O(n log n), worst O(n²).

```mermaid
flowchart TD
    A["5 1 4 <b>2</b><br/>pivot 2"] -->|"partition → p=1"| B["1 | <b>2</b> | 4 5"]
    B --> L["[1]<br/>done"]
    B --> R["4 <b>5</b><br/>pivot 5"]
    R -->|"partition → p=3"| R2["4 | <b>5</b>"]
    L --> F["1 2 4 5"]
    R2 --> F
```

### [HeapSort](sorting/HeapSort.java)
Insert everything into a min `BinaryHeap`, then extract the min n times. O(n log n) time, O(n) extra space (this version is not in-place).

```mermaid
flowchart LR
    I["insert 5,1,4,2"] --> H["heap array<br/>1 2 4 5"]
    H -->|"extract 1"| H1["2 5 4"]
    H1 -->|"extract 2"| H2["4 5"]
    H2 -->|"extract 4"| H3["5"]
    H3 -->|"extract 5"| O["output<br/>1 2 4 5"]
```
