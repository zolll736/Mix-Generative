package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.ui.standalone.ClassPathResource;

import com.mysql.jdbc.log.LogFactory;

public class WordEmbedding {

	private static Logger log;
	
	
	public static int computeEditDistance(String s1, String s2) {
		
		s1 = s1.toLowerCase();
	    s2 = s2.toLowerCase();

	    int[] costs = new int[s2.length() + 1];
	    for (int i = 0; i <= s1.length(); i++) {
	        int lastValue = i;
	        for (int j = 0; j <= s2.length(); j++) {
	            if (i == 0) {
	                costs[j] = j;
	            } else {
	                if (j > 0) {
	                    int newValue = costs[j - 1];
	                    if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
	                        newValue = Math.min(Math.min(newValue, lastValue),
	                                costs[j]) + 1;
	                    }
	                    costs[j - 1] = lastValue;
	                    lastValue = newValue;
	                }
	            }
	        }
	        if (i > 0) {
	            costs[s2.length()] = lastValue;
	        }
	    }
	    return costs[s2.length()];
	}

	public static double printDistance(String s1, String s2) {
		
		System.out.println("In String Match:"+s1+" Second String:"+s2);
	    double similarityOfStrings = 0.0;
	    int editDistance = 0;
	    if (s1.length() < s2.length()) { // s1 should always be bigger
	        String swap = s1;
	        s1 = s2;
	        s2 = swap;
	    }
	    int bigLen = s1.length();
	    editDistance = computeEditDistance(s1, s2);
	    if (bigLen == 0) {
	        similarityOfStrings = 100; /* both strings are zero length */
	    } else {
	        similarityOfStrings = (bigLen - editDistance) / (double) bigLen*100;
	    }
	  
	   
		return similarityOfStrings;
	    
	   
		
	}


	public static String st=null;

	public static String[] stopWordsofwordnet = {
		"details","a","information", "as", "able", "about",
			 "above", "according", "accordingly", "across", "actually",
			 "after", "afterwards", "again", "against", "aint", "all",
			 "allow", "allows", "almost", "alone", "along", "already",
			 "also", "although", "always", "am", "among", "amongst", "an",
			 "and", "another", "any", "anybody", "anyhow", "anyone", "anything",
			 "anyway", "anyways", "anywhere", "apart", "appear", "appreciate",
			 "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking",
			 "associated", "at", "available", "away", "awfully", "be", "became", "because",
			 "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being",
			 "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both",
			 "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes",
			 "certain", "certainly", "changes", "clearly", "co", "com", "come",
			 "comes", "concerning", "consequently", "consider", "considering", "contain",
			 "containing",    "contains","corresponding","could", "couldnt", "course", "currently",
			 "definitely", "described", "despite", "did", "didnt", "different", "do", "does",
			 "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu",
			 "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially",
			 "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere",
			 "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed",   
			 "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further",
			 "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone"
			     , "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have",
			     "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter",
			     "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither",
			     "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored",
			     "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
		
	public static String stopword(String s)
	{	
	   System.out.println("In Stopword Remove Method ");
	   System.out.println(s);
		s=s.trim().replaceAll("\\s+", " ");
		String[] words = s.split(" ");
	    String rStr = "";
	     
		//remove stop words here from the temp list
		  for (int i = 0; i < words.length; i++) {
			  if(Arrays.asList(stopWordsofwordnet).contains(words[i].toLowerCase())){
			  }else{
				  rStr += words[i]+" ";
				  
				  
			  }
			}  
		System.out.println("wordlist:"+rStr);
		
	
		 String models = String.join(",", rStr);

	       
	        System.out.println("string: " + models);

        
        
		return rStr;
		
			}
	
	
	
	
	

    public static void main(String[] args) throws Exception {
        /*
                Initial model training phase
         */
    	
    
        String filePath = new ClassPathResource("raw_sentences.txt").getFile().getAbsolutePath();

        log.info("Load & Vectorize Sentences....");
        // Strip white space before and after for each line
        SentenceIterator iter = new BasicLineIterator(filePath);
  
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

     
        VocabCache cache = new AbstractCache<SequenceElement>();
     

        log.info("Building model....");
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
               // .epochs(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .vocabCache(cache)
                .build();

        log.info("Fitting Word2Vec model....");
        vec.fit();


        Collection<String> lst = vec.wordsNearest("query", 10);
        log.info("Closest words to 'day' on 1st run: " + lst);

   
      
        Word2Vec word2Vec =new Word2Vec();

     
        SentenceIterator iterator = new BasicLineIterator(filePath);
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

        word2Vec.setTokenizerFactory(tokenizerFactory);
        word2Vec.setSentenceIter(iterator);


        log.info("Word2vec uptraining...");

        word2Vec.fit();

        lst = word2Vec.wordsNearestSum("query", 10);
        
      
        
        log.info("Closest words to 'query' : " + lst);

}
}
