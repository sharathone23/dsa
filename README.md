# Java Implementations of Fundamental Data Structures and Algorithms

## Linear

### [LinkedList](linear/LinkedList.java)
```mermaid
flowchart LR
    H([head]) --> A[1] --> B[2] --> C[3] --> D[4] --> E[5] --> N((null))
    T([tail]) --> E
```

```mermaid
flowchart RL
    H([head]) --> E[5] --> D[4] --> C[3] --> B[2] --> A[1] --> N((null))
    T([tail]) --> A
```

### [DoublyLinkedList](linear/DoublyLinkedList.java)
```mermaid
flowchart LR
    NL((null)) --- A[1] <--> B[2] <--> C[3] <--> D[4] --- NR((null))
    H([head]) --> A
    T([tail]) --> D
```

### [Stack](linear/Stack.java)
```mermaid
flowchart TB
    P[/"push(4)"/] -.-> TOP
    TOP["4  ← head / top"] --> S3[3] --> S2[2] --> S1[1]
    TOP -.-> POP[\"pop() returns 4"\]
```

### [Queue](linear/Queue.java)
```mermaid
flowchart LR
    DQ[\"dequeue() → 1"\] -.- A
    A["1 ← head"] --> B[2] --> C[3] --> D["4 ← tail"]
    D -.- EQ[/"enqueue(5)"/]
```

### [Dequeue](linear/Dequeue.java)
```mermaid
flowchart LR
    IF[/"insertFirst / removeFirst"/] <-.-> A
    A["1 ← head"] <--> B[2] <--> C["3 ← tail"]
    C <-.-> IL[/"insertLast / removeLast"/]
```

### [LRUCache](linear/LRUCache.java)
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

```mermaid
flowchart LR
    S0["[3, 1]"] -->|"get(1)"| S1["[1, 3]"] -->|"set(4,40)<br/>evict tail 3"| S2["[4, 1]"]
```

## Non Linear

### [Tree](nonlinear/Tree.java)
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
```mermaid
flowchart TD
    N1[1] --> N2[2]
    N1 --> N3[3]
    N2 --> N4[4]
    N2 --> N5[5]
    N3 --> N6[6]
    N3 --> N7[7]
```

### [BinarySearchTree](nonlinear/BinarySearchTree.java)
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

```mermaid
flowchart LR
    D{"node to delete"} -->|no children| L["just remove it"]
    D -->|one child| O["replace node with its child"]
    D -->|two children| T["copy in-order successor's value<br/>(min of right subtree)<br/>then delete the successor"]
```

### [Trie](nonlinear/Trie.java)
```mermaid
flowchart TD
    ROOT(( )) --> C[c] --> A[a]
    A --> T["t ✔"]
    A --> R["r ✔"] --> D["d ✔"]
    ROOT --> D2[d] --> O[o] --> G["g ✔"]
    classDef word fill:#06d6a0,stroke:#047857,color:#000
    class T,R,D,G word
```

### [BinaryHeap](nonlinear/BinaryHeap.java)
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

### [PriorityQueue](nonlinear/PriorityQueue.java)
```mermaid
flowchart LR
    E[/"enqueue(x)"/] --> H["BinaryHeap.insert<br/>O(log n)"]
    H --> R(("root = min/max"))
    R --> P["peek() O(1)"]
    R --> DQ["dequeue() → extractMinOrMax<br/>O(log n)"]
```

### [Graph](nonlinear/Graph.java)
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
```mermaid
flowchart LR
    N1(("1<br/>d=0")) --- N2(("2<br/>d=1"))
    N1 --- N3(("3<br/>d=1"))
    N2 --- N4(("4<br/>d=2"))
    N3 --- N4
    N4 --- N5(("5<br/>d=3"))
```

### [DirectedGraph](nonlinear/DirectedGraph.java)
```mermaid
flowchart LR
    V5((5)) --> V2((2))
    V5 --> V0((0))
    V4((4)) --> V0
    V4 --> V1((1))
    V2 --> V3((3))
    V3 --> V1
```

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

## Sorting

### [BubbleSort](sorting/BubbleSort.java)
```mermaid
flowchart LR
    S0["5 1 4 2"] -->|"pass 1"| S1["1 4 2 <b>5</b>"] -->|"pass 2"| S2["1 2 <b>4 5</b>"] -->|"pass 3"| S3["<b>1 2 4 5</b>"]
```

### [SelectionSort](sorting/SelectionSort.java)
```mermaid
flowchart LR
    S0["5 1 4 2"] -->|"max 5 → end"| S1["2 1 4 <b>5</b>"] -->|"max 4 in place"| S2["2 1 <b>4 5</b>"] -->|"max 2 → idx 1"| S3["<b>1 2 4 5</b>"]
```

### [InsertionSort](sorting/InsertionSort.java)
```mermaid
flowchart LR
    S0["5 | 1 4 2"] -->|"key 1"| S1["1 5 | 4 2"] -->|"key 4"| S2["1 4 5 | 2"] -->|"key 2"| S3["1 2 4 5"]
```

### [MergeSort](sorting/MergeSort.java)
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
```mermaid
flowchart LR
    I["insert 5,1,4,2"] --> H["heap array<br/>1 2 4 5"]
    H -->|"extract 1"| H1["2 5 4"]
    H1 -->|"extract 2"| H2["4 5"]
    H2 -->|"extract 4"| H3["5"]
    H3 -->|"extract 5"| O["output<br/>1 2 4 5"]
```

