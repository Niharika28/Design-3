// Time Complexity : O(N+L) - n is integer l- is lists
// Space Complexity : O(n) - stack space
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// USing Stack and Native Iterator

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack();
        st.push(nestedList.iterator());
    }

    // O(1)
    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    //(O(1)
    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
            }else {
                nextEl = st.peek().next();
                if(nextEl.isInteger()) {
                    return true;
                }else{
                    st.push(nextEl.getList().iterator());
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */





// USing dfs approach
public class NestedIterator implements Iterator<Integer> {

    List<Integer> li;
    int i;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.li = new ArrayList<>();
        dfs(nestedList);
    }

    private void dfs(List<NestedInteger> nestedList) {
        for(NestedInteger nl : nestedList) {
            if(nl.isInteger()) {
                li.add(nl.getInteger());
            }else{
                dfs(nl.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return li.get(i++);
    }

    @Override
    public boolean hasNext() {
        return i < li.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */