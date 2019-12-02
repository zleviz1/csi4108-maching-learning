# Document-retrieval---ranking
This is an information Retrieval(IR) system based for a collection of documents. The collection is a subset of the data used in the TREC 2011 Microblog retrieval task. Each line contains the id of the message and the actural message.

The results of the system on a set of 49 queris
An example topic could be in the following format:
\<top>
\<num> Number:MB001\</num>
\<tittle> BBC World Service staff cuts \</tittle>
\<querytime>  Tue Feb 08 12:30:27 +0000 2011 \</querytime>
\<querytweettime> 34952194402811904 \</querytweettime>
\<top>

# preprocessing: 
Implement preprocessing functions for tokenization and stopword removal.
The index terms will be all the words left after filtering out punctuation tokens, numbers, stopwords, etc.
Use the Porter stemmer to stem the index words. 

# Indexing:
Build an inverted index, with an entry for each word in the vocabulary.
An example of possible index is presented below.
![wechat screenshot_20180604172922](https://user-images.githubusercontent.com/15969187/40942688-fee61348-681c-11e8-8500-a99dcf86a0b7.png)


For weighting, i use the tf-idf weighting scheme (w_ij = tf_ij x idf_i). For each
query,  system will produce a ranked list of documents, starting with the most similar
to the query and ending with the least similar. For the query terms, i use a
modified tf-idf weighting scheme w_iq = (0.5 + 0.5 tf_iq)∙idf_i

# results:
each document score has been calculate as following:
<img width="640" alt="thumbnail_screen shot 2018-02-25 at 2 08 56 pm" src="https://user-images.githubusercontent.com/15969187/40942841-553a0d6c-681d-11e8-9266-9b511977e400.png">
