# Train POS
./train_POS.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_POS &
# Train surr
./train_surr.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_surr &
#train coll
./train_coll.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_coll &
#train AWE
./train_AWE.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_AWE &
#train CWE
./train_CWE.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_CWE &
#train POS+surr+coll+AWE+CWE
./train_POS+surr+coll+AWE+CWE task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_c_POS_surr_coll_AWE_CWE &
#train POS+surr+coll+AWE
./train_POS+surr+coll+AWE task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_c_POS_surr_coll_AWE &
#train POS+surr+coll+CWE
./train_POS+surr+coll+CWE task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_c_POS_surr_coll_CWE &
