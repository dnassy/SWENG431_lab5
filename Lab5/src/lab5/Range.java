package lab5;

public class Range {
        public final int lo;
        public final int hi;

        public Range(int lo, int hi) {
            if (lo > hi) throw new IllegalArgumentException("Range lo > hi: " + lo + "," + hi);
            this.lo = lo;
            this.hi = hi;
        }
        @Override public String toString() { return lo + "," + hi; }
}
