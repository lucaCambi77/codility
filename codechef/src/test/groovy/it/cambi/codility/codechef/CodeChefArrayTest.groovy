package it.cambi.codility.codechef


import spock.lang.Specification

class CodeChefArrayTest extends Specification {

    def "card Removal Test"() {
        given:
        def result = cardRemoval(input)

        expect:
        result == expectedResult

        where:
        input                        | expectedResult
        new int[]{1, 1, 2, 2, 3}     | 3
        new int[]{8, 8, 8, 8, 8}     | 0
        new int[]{5, 6, 7, 8, 9, 10} | 5
    }

    private int cardRemoval(int[] arr) {

        int[] ints = new int[11];

        for (Integer integer : arr) {
            ints[integer] = ints[integer] + 1;
        }

        Arrays.sort(ints);

        int sol = 0;
        for (int i = ints.length - 2; i >= 0; i--) {

            sol += ints[i];
        }

        return sol;
    }
}
