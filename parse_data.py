#!/usr/bin/env python

import re
import sys
import json
import sqlite3

import db_info
import locations


trending_data_hash = {}

for line in sys.stdin:
    if line.startswith('{'):
        try:
            tweet = json.loads(line.strip())
        except:
            continue
        if(not tweet["geo"] == None):
            tweet_coord = tweet["geo"]["coordinates"]
            for loc in locations.TAGGED_LOCATIONS:
                if(tweet_coord[0] < loc[1][0] and tweet_coord[0] > loc[0][0] and tweet_coord[1] > loc[0][1] and tweet_coord[1] < loc[1][1]):
                    for word in tweet["text"].split(" "):
                        if word.startswith("#"):
                            w = re.sub('\W+', '', word.lower())
                            if trending_data_hash.has_key(w):
                                trending_data_hash[w] += 1
                            else:
                                trending_data_hash[w] = 1


conn = sqlite3.connect(db_info.DB_NAME)
c = conn.cursor()

sorted_trends = sorted(trending_data_hash.items(),key=lambda x: x[1], reverse=True)


for key,value in sorted_trends[:10]: #top 10
    insert_str = "INSERT INTO %s VALUES (\"%s\",\"%s\",%d)" % (db_info.TREND_TABLE, "Manhattan", key, value)
    c.execute(insert_str)
    
    
conn.commit()

conn.close()




#data = nltk.pos_tag(nltk.word_tokenize(json.loads(line.strip())["text"]))
#for item in data:
#    if item[1].startswith("NN") or item[1].startswith("VB"):
#        try:
#            print item[0]
#        except:
#            continue
#print json.dumps(json.loads(line.strip()),sort_keys=True, indent = 4)
                    
                    
        
        
        
        
