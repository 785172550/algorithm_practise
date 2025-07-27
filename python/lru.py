from collections import deque, defaultdict


"""'
lru cache method
pub:
    init(size)
    get(key)
    set(key, value)

private:
move_to_head()
evict()

"""


# based on linked list
class DLinkedNode:
    def __init__(self, key=0, value=0):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None


class DLinkedList:

    def __init__(self):
        self.head = DLinkedNode()
        self.tail = DLinkedNode()

        self.head.next = self.tail
        self.tail.prev = self.head

    def add(self, node: DLinkedNode):
        self.tail.next = node
        node.prev = self.tail
        node.next = None
        self.tail = node

    def addall(self, *nodes):
        for n in nodes:
            self.add(n)

    def move_to_end(self, node: DLinkedNode, last=True):

        # remove node
        temp = node.next
        node.prev.next = node.next
        temp.prev = node.prev

        if last:
            # add to tail
            self.add(node)

        else:
            # add to head
            node.prev.next = self.head
            self.head.prev = node
            self.head = node
            self.head.prev = None

    def __str__(self):

        cur = self.head.next
        res = []
        while cur is not None:
            res.append(cur.key)
            cur = cur.next

        return f"{res}"


d = DLinkedList()
n3 = DLinkedNode(key=3)
d.addall(DLinkedNode(key=1), n3, DLinkedNode(key=2), DLinkedNode(key=4))
print(d)

d.move_to_end(n3)
print(d)

# in python 3.2 OrderedDict has built in DLinkedNode

from collections import OrderedDict


class HW_LRU:
    def __init__(self, cap=0):
        self.cap = cap
        self.container = OrderedDict()

    def set(self, key, value):
        if key in self.container:
            self.container.move_to_end(key, last=False)
            self.container[key] = value
            return

        # new key
        if len(self.container) >= self.cap:
            oldest = self.container.popitem(last=True)
            del self.container[oldest]

        self.container[key] = value

    def get(self, key, default=None):

        if key in self.container:
            self.container.move_to_end(key, last=False)
            return self.container[key]
        else:
            return default
