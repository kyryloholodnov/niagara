package com.github.holodnov.algorithms.levenshtein;

/**
 * @author Kyrylo Holodnov
 */
public interface OperationsWeights {

    /**
     * Retrieves weight of deletion operation for input character.
     *
     * @param c input character
     * @return weight of deletion
     */
    default double getDeletionWeight(char c) {
        return 1.0;
    }

    /**
     * Retrieves weight of insertion operation for input character.
     *
     * @param c input character
     * @return weight of insertion
     */
    default double getInsertionWeight(char c) {
        return 1.0;
    }

    /**
     * Retrieves weight of substitution operation for input characters.
     *
     * @param oldChar character that will be substituted
     * @param newChar character that will be set
     * @return weight of substitution
     */
    default double getSubstitutionWeight(char oldChar, char newChar) {
        return 1.0;
    }
}
