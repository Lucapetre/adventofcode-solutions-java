package year2021.day18;

public class SnailfishNumber {

    private SnailfishNumber parent;
    private SnailfishNumber leftChild;
    private SnailfishNumber rightChild;

    private int magnitude;

    private SnailfishNumber (SnailfishNumber parent) {
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
    }

    private SnailfishNumber(SnailfishNumber leftChild, SnailfishNumber rightChild) {
        this.parent = null;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.leftChild.parent = this;
        this.rightChild.parent = this;
    }

    private SnailfishNumber(int number, SnailfishNumber parent) {
        this(parent);
        this.magnitude = number;
    }

    private SnailfishNumber(String line, int index, SnailfishNumber parent) {
        this(parent);
        this.parent = parent;
        index++;
        if(line.charAt(index) == '[') {
            leftChild = new SnailfishNumber(line,index,this);
        } else {
            leftChild = new SnailfishNumber(Integer.parseInt(line.substring(index,index+1)), this);
        }

        index += leftChild.length();
        index++;

        if(line.charAt(index) == '[') {
            rightChild = new SnailfishNumber(line,index,this);
        } else {
            rightChild = new SnailfishNumber(Integer.parseInt(line.substring(index,index+1)), this);
        }

    }

    public SnailfishNumber(String line) {
        this(line,0,null);
    }

    private boolean isLeaf() { // is final node with no children
        if(leftChild == null && rightChild == null) {
            return true;
        }
        if (leftChild == null || rightChild == null) {
            throw new IllegalStateException("A Child is null when it shouldn't be!");
        }
        return false;
    }

    private boolean isPair() { // has 2 leaves as children
        if(isLeaf()) {
            return false;
        }

        return leftChild.isLeaf() && rightChild.isLeaf();
    }

    private int length() { // length of the number string an in input
        if(isLeaf()) {
            return 1;
        }
        return 3 + leftChild.length() + rightChild.length();
    }

    private int depth() { // depth in the tree of numbers (nesting)
        int depth = 0;
        SnailfishNumber depthCounter = this;
        while(depthCounter.parent != null) {
            depth++;
            depthCounter = depthCounter.parent;
        }
        return depth;
    }

    public int getTotalMagnitude() {
        if(isLeaf()) {
            return magnitude;
        }
        return 3 * leftChild.getTotalMagnitude() + 2 * rightChild.getTotalMagnitude();
    }

    private SnailfishNumber checkExplosion() { // returns the pair which will explode or null if no number explodes
        if(this.isLeaf()) {
            return null;
        }
        if (this.isPair() && this.depth() == 4) {
            return this;
        }
        SnailfishNumber child = leftChild.checkExplosion();
        if(child != null)
            return child;
        return rightChild.checkExplosion();
    }

    private SnailfishNumber checkSplit() { // returns the leaf which will split
        if(this.isLeaf()) {
            if (this.magnitude >= 10) {
                return this;
            } else {
                return null;
            }
        }
        SnailfishNumber child = leftChild.checkSplit();
        if(child != null)
            return child;
        return rightChild.checkSplit();
    }

    private void explode() { // explode a pair
        int leftVal = leftChild.magnitude;
        int rightVal = rightChild.magnitude;
        leftChild = null;
        rightChild = null;
        this.magnitude = 0;

        SnailfishNumber parentNumber = this.parent;
        SnailfishNumber childNumber = this;

        // go up until the child isn't the left one of the parent
        while (parentNumber != null && parentNumber.leftChild == childNumber) {
            parentNumber = parentNumber.parent;
            childNumber = childNumber.parent;
        }
        // if parent still exists, go the other way (from right to left) and around (right to go to the end)
        if(parentNumber != null) {
            childNumber = parentNumber.leftChild;
            while(!childNumber.isLeaf()) {
                childNumber = childNumber.rightChild;
            }
            childNumber.magnitude += leftVal;
        }

        parentNumber = this.parent;
        childNumber = this;

        // same thing as before but in reverse
        while (parentNumber != null && parentNumber.rightChild == childNumber) {
            parentNumber = parentNumber.parent;
            childNumber = childNumber.parent;
        }
        if(parentNumber != null) {
            childNumber = parentNumber.rightChild;
            while(!childNumber.isLeaf()) {
                childNumber = childNumber.leftChild;
            }
            childNumber.magnitude += rightVal;
        }
    }

    private void split() { // split leaf in pair
        int leftVal = magnitude / 2;
        int rightVal = magnitude - leftVal;
        this.magnitude = 0;
        this.leftChild = new SnailfishNumber(leftVal, this);
        this.rightChild = new SnailfishNumber(rightVal, this);
    }

    private void reduceNumber() { // explode and split until it's valid
        while (true) {
            SnailfishNumber toExplode = checkExplosion();
            if(toExplode != null) {
                toExplode.explode();
                continue;
            }
            SnailfishNumber toSplit = checkSplit();
            if(toSplit != null) {
                toSplit.split();
                continue;
            }
            break;
        }
    }

    public SnailfishNumber addNumber(SnailfishNumber number) { // add 2 numbers together
        SnailfishNumber result = new SnailfishNumber(this,number);
        result.reduceNumber();
        return result;
    }
}
