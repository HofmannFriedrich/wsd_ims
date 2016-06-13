# Train POS
./train_POS.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_POS > models/model_POS.log 2>&1 &
# Train surr
./train_surr.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_surr > models/model_surr.log 2>&1 &
#train coll
./train_coll.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_coll > models/model_coll.log 2>&1 &
#train AWE
./train_AWE.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_AWE > models/model_AWE.log 2>&1 &
#train CWE
./train_CWE.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_CWE > models/model_CWE.log 2>&1 &
#train POS+surr+coll
./train_c_POS_surr_coll.bash task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_POS_surr_coll > models/model_POS_surr_coll.log 2>&1 &
#train POS+surr+coll+AWE+CWE
./train_POS+surr+coll+AWE+CWE task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_c_POS_surr_coll_AWE_CWE > models/model_POS_surr_coll_AWE_CWE.log 2>&1 &
#train POS+surr+coll+AWE
./train_POS+surr+coll+AWE task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_c_POS_surr_coll_AWE > models/model_POS_surr_coll_AWE.log 2>&1 &
#train POS+surr+coll+CWE
./train_POS+surr+coll+CWE task17/train/lexical-sample/english-lexical-sample.train.xml task17/train/lexical-sample/english-lexical-sample.train.key models/model_c_POS_surr_coll_CWE > models/model_POS_surr_coll_CWE.log 2>&1 &
