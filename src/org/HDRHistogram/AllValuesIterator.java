package org.HDRHistogram;

import java.util.Iterator;

/**
 * Used for iterating through histogram values using the finest granularity steps supported by the underlying
 * representation. The iteration steps through all possible unit value levels, regardless of whether or not
 * there were recorded values for that value level, and terminates when all recorded histogram values are exhausted.
 */

public class AllValuesIterator extends AbstractHistogramIterator implements Iterator<HistogramIterationValue> {
    int visitedSubBucketIndex;
    int visitedBucketIndex;

    /**
     * Reset iterator for re-use in a fresh iteration over the same histogram data set.
     */
    public void reset() {
        reset(histogram, rawCounts);
    }

    private void reset(final Histogram histogram, boolean rawCounts) {
        super.resetIterator(histogram, rawCounts);
        visitedSubBucketIndex = -1;
        visitedBucketIndex = -1;
    }

    AllValuesIterator(final Histogram histogram, boolean rawCounts) {
        reset(histogram, rawCounts);
    }

    void incrementIterationLevel() {
        visitedSubBucketIndex = currentSubBucketIndex;
        visitedBucketIndex = currentBucketIndex;
    }

    boolean reachedIterationLevel() {
        return (visitedSubBucketIndex != currentSubBucketIndex) || (visitedBucketIndex != currentBucketIndex);
    }
}