package lab5_1;

public class Range {
        public final Integer low;
        public final Integer high;

        public Range(int low, int high) {
            if (low > high) throw new IllegalArgumentException("Range lo > hi: " + low + "," + high);
            this.low = low;
            this.high = high;
        }
        @Override public String toString() { return low + "," + high; }
}
