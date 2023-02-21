public class wordSort implements Comparable<wordSort>{
    Integer id;
    String word;
    Integer frequency;

    public wordSort(Integer id, String word, Integer frequency)
    {
        this.id = id;
        this.word = word;
        this.frequency = frequency;
    }

        @Override
        public String toString()
        {
            return "words(word:" + word + " num: " + frequency + ")";
        }

        @Override
        public int compareTo(wordSort otherWords)
        {
            Integer i = 0;
            if(this.frequency > otherWords.frequency) i = 1;
            if(this.frequency < otherWords.frequency) i = -1;
            return i;
        }
}
